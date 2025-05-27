package com.pg.StayManage.Controller;

import com.pg.StayManage.Dto.RoomDto;
import com.pg.StayManage.Model.Room;
import com.pg.StayManage.Service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "${allowed.origin}")
@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @GetMapping
    public ResponseEntity<List<RoomDto>> getRooms() {
        List<RoomDto> rooms = roomService.getAllRooms();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/onlyrooms")
    public ResponseEntity<List<Room>> getOnlyRooms() {
        List<Room> rooms = roomService.getOnlyRooms();
        return ResponseEntity.ok(rooms);
    }

    @PostMapping("/addroom")
    public ResponseEntity<String> addRoom(@RequestBody Room room) {
        boolean addroom = roomService.addRoom(room);
        if (addroom) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

    }

    @PostMapping("/addRoomWithImages")
    public ResponseEntity<String> addRoomWithImages(@RequestParam String roomnumber,
            @RequestParam("images") List<MultipartFile> imageFiles) throws IOException {
        roomService.addRoomImages(roomnumber, imageFiles);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Room>> getRoomById(@PathVariable Long id) {
        Optional<Room> room = roomService.getRoomById(id);
        return new ResponseEntity<>(room, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRoom(@PathVariable String id) {
        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
