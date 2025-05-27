package com.pg.StayManage.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pg.StayManage.Model.Contactus;
import com.pg.StayManage.Service.contctusService;

@CrossOrigin(origins = "${allowed.origin}")
@RestController
@RequestMapping("/contactus")
public class contactusController {

    @Autowired
    private contctusService contctusService;

    @PostMapping("/contact")
    public ResponseEntity<Void> contact(@RequestBody Contactus contactus) {

        boolean stored = contctusService.savecontactus(contactus);
        if (stored) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
