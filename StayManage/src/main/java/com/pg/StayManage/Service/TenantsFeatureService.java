package com.pg.StayManage.Service;

import com.pg.StayManage.Dto.MyRoomDto;
import com.pg.StayManage.Dto.RentDto;
import com.pg.StayManage.Dto.TRoomDto;
import com.pg.StayManage.Model.Rent;
import com.pg.StayManage.Model.Room;
import com.pg.StayManage.Model.Tenant;
import com.pg.StayManage.Model.loginresponse;
import com.pg.StayManage.Repository.RentRepository;
import com.pg.StayManage.Repository.RoomImageRepository;
import com.pg.StayManage.Repository.RoomRepo;
import com.pg.StayManage.Repository.TenantRepo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;



@Service
public class TenantsFeatureService {

    @Autowired
    private RoomRepo roomRepo;
    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private TenantRepo tenantRepo;

    @Autowired
    private RoomImageRepository roomImageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;
    
    public Optional<Room> getTenantsByRoomId(String roomnumber) {
        return roomRepo.findByRoomNumber(roomnumber);

    }

    public Optional<Rent> getRentStatus(String phone) {
        return rentRepository.findByPhone(phone);
    }

    public boolean createMonthlyRent(RentDto rentDto) {
        LocalDate today = LocalDate.now();
        LocalDate rentMonth = today;
        if (rentRepository.findByTnoAndMonth(rentDto.getTno(), rentMonth).isPresent()) {
            return false;
        }

        LocalDate dueDate = rentMonth.plusMonths(1);
        Rent rent = new Rent();
        rent.setTno(rentDto.getTno());
        rent.setMonth(rentMonth);
        rent.setPhone(rentDto.getPhone());
        rent.setPaid(true);
        rent.setPmethod(rentDto.getPmethod());
        rent.setDueDate(dueDate);
        rent.setTransactionId(rentDto.getTransactionId());
        rentRepository.save(rent);

        Optional<Tenant> tent = tenantRepo.findByTno(rentDto.getTno());
        if (tent.isPresent()) {
            Tenant tent1 = tent.get();
            tent1.setRentStatus(true);
            tenantRepo.save(tent1);
        }

        return true;
    }

    public MyRoomDto getMyRoom(String email) {
        Optional<Tenant> tenant1 = tenantRepo.findByEmail(email);
        if (tenant1.isPresent()) {
            Tenant tent1 = tenant1.get();
            MyRoomDto myroom = new MyRoomDto();
            myroom.setName(tent1.getName());
            myroom.setEmail(tent1.getEmail());
            myroom.setPhone(tent1.getPhone());
            myroom.setRentStatus(tent1.getRentStatus());
            myroom.settProfile(tent1.getProfileUrl());
            myroom.setRoomShairing(tent1.getRoomSharing());
            myroom.setTrent(tent1.getTrent());

            if (tent1.getProfileUrl() != null && !tent1.getProfileUrl().isEmpty()) {
                String imageUrl = tent1.getProfileUrl().replaceFirst("^uploads/", "");
                String fullImageUrl = "http://localhost:8080/uploads/userimage/" + imageUrl;
                myroom.settProfile(fullImageUrl);
            }

            Optional<Rent> rent1 = rentRepository.findByPhone(tent1.getPhone());
            if (rent1.isPresent()) {
                Rent rent = rent1.get();
                myroom.setDueDate(rent.getDueDate());
            }

            return myroom;
        }
        return null;
    }
    
public String saveTenantImage(String email, MultipartFile image) throws IOException {

    Tenant tenant = tenantRepo.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Tenant not found with email: " + email));

    Path userDir = Paths.get(uploadDir, "userimage");
    if (!Files.exists(userDir)) {
        Files.createDirectories(userDir);
    }

    String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
    Path filePath = userDir.resolve(fileName);

    Files.copy(image.getInputStream(), filePath);

    // IMPORTANT: store relative URL
    tenant.setProfileUrl("/uploads/userimage/" + fileName);
    tenantRepo.save(tenant);

    return "Image uploaded successfully!";
}

    public TRoomDto getTRoomData(String email) {
        Optional<Tenant> tent = tenantRepo.findByEmail(email);
        if (tent.isPresent()) {
            Tenant ten1 = tent.get();
            Optional<Room> room1 = roomRepo.findByRoomNumber(ten1.getRoomNumber());
            if (room1.isPresent()) {
                Room room = room1.get();
                TRoomDto troom = new TRoomDto();
                troom.setFlooar(room.getFloor());
                List<String> imageUrls = roomImageRepository.findByRoomNumber(room.getRoomNumber())
                        .stream()
                        .map(image -> {
                            String imageUrl = image.getImageUrl().replaceFirst("^/uploads/", "");
                            return "http://localhost:8080/uploads/" + imageUrl;
                        })
                        .collect(Collectors.toList());

                troom.setImages(imageUrls);
                return troom;
            }
        }
        return null;
    }
}
