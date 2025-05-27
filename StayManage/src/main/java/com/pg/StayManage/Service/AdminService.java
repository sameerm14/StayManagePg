package com.pg.StayManage.Service;

import com.pg.StayManage.Dto.AllRentDto;
import com.pg.StayManage.Dto.PgData;
import com.pg.StayManage.Dto.ViewTenantsDto;
import com.pg.StayManage.Model.Admin;
import com.pg.StayManage.Model.Message;
import com.pg.StayManage.Model.Room;
import com.pg.StayManage.Model.Tenant;
import com.pg.StayManage.Repository.AdminRepo;
import com.pg.StayManage.Repository.MessageRepo;
import com.pg.StayManage.Repository.RentRepository;
import com.pg.StayManage.Repository.RoomRepo;
import com.pg.StayManage.Repository.TenantRepo;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private RoomRepo roomRepo;

    @Autowired
    private TenantRepo tenantRepo;

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private MessageRepo messageRepo;

    public boolean saveAdmin(Admin admins) {
        Optional<Admin> admin = adminRepo.findByUsername(admins.getUsername());
        if (admin.isPresent()) {
            return false;
        } else {
            String pass = BCrypt.hashpw(admins.getPassword(), BCrypt.gensalt(12));
            admins.setPassword(pass);
            adminRepo.save(admins);
            return true;
        }
    }

    public String verifyAdmin(Admin username) {
        Optional<Admin> adminpresent = adminRepo.findByUsername(username.getUsername());
        if (adminpresent.isPresent()) {
            Admin admin = adminpresent.get();
            boolean verify = BCrypt.checkpw(username.getPassword(), admin.getPassword());
            if (verify) {
                String token = jwtService.generateToken(admin.getUsername(), admin.getRole());
                return token;
            }
        }
        return null;
    }

    public PgData getAllRoomsCount() {
        Long AllRooms = roomRepo.count();
        Long TotalBeds = roomRepo.findTotalBeds();
        Long AvailableRooms = roomRepo.findAvailableRooms();
        Long AvailableBeds = roomRepo.findAvailableBeds();
        Long OccupiedBeds = roomRepo.findOccupiedBeds();
        Long OccupiedRooms = roomRepo.countByRoomOccupied(true);
        Long totalTenants = tenantRepo.count();
        Long totalRent = tenantRepo.findTotalRent();

        PgData pg = new PgData();
        pg.setTotalRooms(AllRooms);
        pg.setTotalBeds(TotalBeds);
        pg.setAvailableBeds(AvailableBeds);
        pg.setAvailableRooms(AvailableRooms);
        pg.setOccupiedBeds(OccupiedBeds);
        pg.setOccupiedRooms(OccupiedRooms);
        pg.setTotalTenants(totalTenants);
        pg.setTrent(totalRent != null ? totalRent : 0L);
        return pg;
    }

    public List<AllRentDto> getAllRents() {
        List<Tenant> rentDto = tenantRepo.findAll();
        List<AllRentDto> rentDtos = rentDto.stream().map(rent -> {
            AllRentDto rents = new AllRentDto();
            rents.setTno(rent.getTno());
            rents.setName(rent.getName());
            rents.setPhone(rent.getPhone());
            rents.setRentStatus(rent.getRentStatus());

            return rents;
        }).collect(Collectors.toList());
        return rentDtos;
    }

    public List<ViewTenantsDto> getViewTenanats() {

        List<Tenant> tenants = tenantRepo.findAll();

        List<ViewTenantsDto> tenantsDto = tenants.stream().map(tent -> {
            ViewTenantsDto vtd = new ViewTenantsDto();
            vtd.setTno(tent.getTno());
            vtd.setName(tent.getName());
            vtd.setPhone(tent.getPhone());
            vtd.setEmail(tent.getEmail());
            vtd.setRoomNumber(tent.getRoomNumber());
            vtd.setRentStatus(tent.getRentStatus());
            return vtd;
        }).collect(Collectors.toList());
        return tenantsDto;
    }

    @Transactional
    public boolean deleteTenant(String tno) {
        Optional<Tenant> tenant1 = tenantRepo.findByTno(tno);
        if (tenant1.isPresent()) {
            Tenant tent = tenant1.get();
            Optional<Room> room = roomRepo.findByRoomNumber(tent.getRoomNumber());
            if (room.isPresent()) {
                Room room1 = room.get();
                int availableBeds = Integer.parseInt(room1.getAvailableBeds());
                int occupiedBeds = Integer.parseInt(room1.getOccupiedBeds());
                int nooftenants = Integer.parseInt(room1.getNooftenants());

                availableBeds++;
                occupiedBeds--;
                nooftenants--;

                room1.setAvailableBeds(String.valueOf(availableBeds));
                room1.setOccupiedBeds(String.valueOf(occupiedBeds));
                room1.setNooftenants(String.valueOf(nooftenants));
                roomRepo.save(room1);
            }
            tenantRepo.deleteByTno(tno);
            rentRepository.deleteByTno(tno);

        }

        return true;
    }

    public void sendMessage(Message message) {
        messageRepo.save(message);
    }

    public List<Message> getMessages() {
        return messageRepo.findAll();
    }
}