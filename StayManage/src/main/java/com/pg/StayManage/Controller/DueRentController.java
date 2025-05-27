package com.pg.StayManage.Controller;

import com.pg.StayManage.Model.DueRents;
import com.pg.StayManage.Repository.DueRentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "${allowed.origin}")
@RestController
@RequestMapping("/api/dues")
public class DueRentController {

    @Autowired
    private DueRentRepo dueRentRepo;

    @GetMapping
    public ResponseEntity<List<DueRents>> getDues() {
        List<DueRents> dues = dueRentRepo.findAll();
        return new ResponseEntity<>(dues, HttpStatus.OK);
    }
}
