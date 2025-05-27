package com.pg.StayManage.Service;

import com.pg.StayManage.Dto.TenantDto;

import com.pg.StayManage.Model.Room;
import com.pg.StayManage.Model.Tenant;
import com.pg.StayManage.Repository.RoomRepo;
import com.pg.StayManage.Repository.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TenantService {

    @Autowired
    private TenantRepo tenantRepo;

    @Autowired
    private RentService rentService;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private JwtService jwtService;

    public boolean saveTenant(Tenant tenant) {
        Optional<Room> findRoom = roomRepo.findByRoomNumber(tenant.getRoom().getRoomNumber());
        if (findRoom.isPresent()) {
            Room room1 = findRoom.get();
            int AvailableBeds;
            AvailableBeds = parseOrDefault(room1.getAvailableBeds(), 0);
            if (AvailableBeds > 0) {
                int currentTenants;
                int occupiedBeds;

                try {
                    currentTenants = parseOrDefault(room1.getNooftenants(), 0);
                    occupiedBeds = parseOrDefault(room1.getOccupiedBeds(), 0);
                    AvailableBeds = parseOrDefault(room1.getAvailableBeds(), 0);
                } catch (NumberFormatException e) {
                    throw new RuntimeException(
                            "Invalid number format in noOfTenants for room: " + room1.getRoomNumber());
                }
                currentTenants++;
                occupiedBeds++;
                AvailableBeds--;

                room1.setNooftenants(String.valueOf(currentTenants));
                room1.setAvailableBeds(String.valueOf(AvailableBeds));
                room1.setOccupiedBeds(String.valueOf(occupiedBeds));

                if (AvailableBeds == 0) {
                    room1.setRoomOccupied(true);
                }

                if (AvailableBeds > 0) {
                    room1.setAvailableRooms(true);
                } else {
                    room1.setAvailableRooms(false);
                }

                roomRepo.save(room1);
                tenant.setRoom(room1);
                tenant.setRoomNumber(room1.getRoomNumber());
                tenantRepo.save(tenant);
                return true;
            }

        }
        return false;

    }

    private int parseOrDefault(String value, int defaultValue) {
        if (value == null || value.trim().isEmpty()) {
            return defaultValue;
        }
        try {
            return Integer.parseInt(value.trim());
        } catch (NumberFormatException e) {
            return defaultValue; // Return default value if the format is invalid
        }
    }

    public Iterable<Tenant> getTenants() {
        Iterable<Tenant> tenants = tenantRepo.findAll();
        return tenants;
    }

    public Iterable<Tenant> getTenantsByRoomId(Long id) {
        Iterable<Tenant> tenant = tenantRepo.findByRoomId(id);
        return tenant;
    }

    public boolean updateRentStatus(Long id, boolean paid) {
        Optional<Tenant> tenant = tenantRepo.findById(id);

        if (tenant.isPresent()) {
            Tenant tent = tenant.get();
            tent.setRentStatus(paid);
            rentService.setRentpaid(id);
            tenantRepo.save(tent);
            return true;
        } else {
            return false;
        }
    }

    public boolean regTenant(TenantDto tenantDto) {
        Optional<Tenant> tenantpresent = tenantRepo.findByPhone(tenantDto.getPhone());

        if (tenantpresent.isPresent()) {
            Tenant regten = tenantpresent.get();
            String password = BCrypt.hashpw(tenantDto.getPass(), BCrypt.gensalt(12));
            regten.setPass(password);
            regten.setEmail(tenantDto.getEmail());
            tenantRepo.save(regten);
            return true;
        } else {
            return false;
        }
    }

    public String loginten(TenantDto tenantDto) {
        Optional<Tenant> tenantpresent = tenantRepo.findByEmail(tenantDto.getEmail());
        if (tenantpresent.isPresent()) {
            Tenant regten = tenantpresent.get();
            boolean password = BCrypt.checkpw(tenantDto.getPass(), regten.getPass());
            if (password) {
                String token = jwtService.generateToken(regten.getName(), regten.getRole());
                return token;
            }
        }
        return null;
    }
}
