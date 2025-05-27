package com.pg.StayManage.Service;

import com.pg.StayManage.Dto.RoomDto;
import com.pg.StayManage.Model.Room;
import com.pg.StayManage.Model.RoomImage;
import com.pg.StayManage.Repository.RoomImageRepository;
import com.pg.StayManage.Repository.RoomRepo;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomService {

    @Autowired
    private RoomRepo roomRepository;

    @Autowired
    private RoomImageRepository roomImageRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public List<RoomDto> getAllRooms() {
        return roomRepository.findAll().stream().map(room -> {
            RoomDto roomDTO = new RoomDto();
            roomDTO.setRoomNumber(room.getRoomNumber());
            roomDTO.setNooftenants(room.getNooftenants());
            roomDTO.setFloor(room.getFloor());

            List<String> imageUrls = roomImageRepository.findByRoomNumber(room.getRoomNumber())
                    .stream()
                    .map(image -> {
                        String imageUrl = image.getImageUrl().replaceFirst("^/uploads/", "");
                        return "http://192.168.0.103:8080/uploads/" + imageUrl;
                    })
                    .collect(Collectors.toList());

            roomDTO.setImages(imageUrls);
            return roomDTO;
        }).collect(Collectors.toList());
    }

    public boolean addRoom(Room room) {
        String roomnumber = room.getRoomNumber();
        Optional<Room> roomispresent = roomRepository.findByRoomNumber(roomnumber);
        if (roomispresent.isPresent()) {
            return false;
        }
        if (room.getNooftenants() == null || room.getNooftenants().trim().isEmpty()) {
            room.setNooftenants("0");
        }

        if (room.getOccupiedBeds() == null || room.getOccupiedBeds().trim().isEmpty()) {
            room.setOccupiedBeds("0");
        }

        if (room.getAvailableBeds() == null || room.getAvailableBeds().trim().isEmpty()) {
            System.out.println(room.getTotalbeds());
            room.setAvailableBeds(room.getTotalbeds());
        }
        roomRepository.save(room);
        return true;
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public List<Room> getOnlyRooms() {
        return roomRepository.findAll();
    }

    @Transactional
    public void deleteRoom(String roomNumber) {
        List<RoomImage> images = roomImageRepository.findByRoomNumber(roomNumber);
        if (!images.isEmpty()) {
            roomImageRepository.deleteByRoomNumber(roomNumber);
        }
        roomRepository.deleteByRoomNumber(roomNumber);
    }

    public void addRoomImages(String roomId, List<MultipartFile> imageFiles) throws IOException {
        Optional<Room> optionalRoom = roomRepository.findByRoomNumber(roomId);
        if (optionalRoom.isPresent()) {
            Room room = optionalRoom.get();
            for (MultipartFile file : imageFiles) {
                String filename = file.getOriginalFilename();
                Path uploadDir = Paths.get(
                        "C:\\Users\\Dluci\\OneDrive\\Desktop\\StayManage\\StayManage version1\\StayManage\\uploads");
                if (!Files.exists(uploadDir)) {
                    Files.createDirectories(uploadDir);
                }
                Path filePath = uploadDir.resolve(filename);
                file.transferTo(filePath.toFile());
                RoomImage image = new RoomImage();
                image.setImageUrl("/uploads/" + filename);
                image.setRoomNumber(roomId);
                image.setRoom(room);
                roomImageRepository.save(image);
            }
        } else {
            throw new RuntimeException("Room not found with id: " + roomId);
        }
    }

}
