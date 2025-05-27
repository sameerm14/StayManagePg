package com.pg.StayManage.Controller;

import com.pg.StayManage.Model.Notification;
import com.pg.StayManage.Repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin(origins = "${allowed.origin}")
@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    @Autowired
    private NotificationRepository notificationRepository;

    @PostMapping("/sendnotification")
    public ResponseEntity<String> sendNotification(@RequestParam String message) {
        LocalDate localDate = LocalDate.now();
        Notification noti = new Notification();
        noti.setMessage(message);
        noti.setLocalDate(localDate);
        notificationRepository.save(noti);
        return new ResponseEntity<>("Message sent Successfully", HttpStatus.OK);
    }

    @GetMapping("/getNotification")
    public ResponseEntity<List<Notification>> getNotification() {
        List<Notification> notification = notificationRepository.findAll();
        return new ResponseEntity<>(notification, HttpStatus.OK);
    }
}
