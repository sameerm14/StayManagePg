package com.pg.StayManage.Service;

import com.pg.StayManage.Dto.RoomDto;
import com.pg.StayManage.Model.Room;
import com.pg.StayManage.Model.RoomImage;
import com.pg.StayManage.Repository.RoomImageRepository;
import com.pg.StayManage.Repository.RoomRepo;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.util.Map;


@Service
public class RoomService {

    @Autowired
    private RoomRepo roomRepository;

    @Autowired
    private RoomImageRepository roomImageRepository;

    @Autowired
    private Cloudinary cloudinary;


  public List<RoomDto> getAllRooms() {
    return roomRepository.findAll().stream().map(room -> {
        RoomDto roomDTO = new RoomDto();
        roomDTO.setRoomNumber(room.getRoomNumber());
        roomDTO.setNooftenants(room.getNooftenants());
        roomDTO.setFloor(room.getFloor());

        List<String> imageUrls = roomImageRepository
                .findByRoomNumber(room.getRoomNumber())
                .stream()
                .map(image -> image.getImageUrl()) 
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
    
   @Transactional
    public void addRoomImages(String roomId, List<MultipartFile> imageFiles) throws IOException {

    Room room = roomRepository.findByRoomNumber(roomId)
            .orElseThrow(() -> new RuntimeException("Room not found with id: " + roomId));

    for (MultipartFile file : imageFiles) {

        Map uploadResult = cloudinary.uploader().upload(
                file.getBytes(),
                ObjectUtils.asMap(
                        "folder", "StayManage/rooms"
                )
        );

        String imageUrl = uploadResult.get("secure_url").toString();

        RoomImage image = new RoomImage();
        image.setImageUrl(imageUrl);
        image.setRoomNumber(roomId);
        image.setRoom(room);

        roomImageRepository.save(image);
    }
}



}
