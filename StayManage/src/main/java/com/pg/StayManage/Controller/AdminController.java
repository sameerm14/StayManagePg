package com.pg.StayManage.Controller;

import com.itextpdf.text.DocumentException;
import com.pg.StayManage.Dto.AllRentDto;
import com.pg.StayManage.Dto.PgData;
import com.pg.StayManage.Dto.ViewTenantsDto;
import com.pg.StayManage.Model.Admin;
import com.pg.StayManage.Model.Message;
import com.pg.StayManage.Model.loginresponse;
import com.pg.StayManage.Service.AdminService;
import com.pg.StayManage.Service.PdfGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@CrossOrigin(origins = "${allowed.origin}")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping("/register")
    public ResponseEntity<?> registerAdmin(@RequestBody Admin admin) {
        boolean saved = adminService.saveAdmin(admin);
        if (saved) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/api/health")
    public String health() {
        return "OK";
    }
    
    // Admin Login endpoint
    @PostMapping("/login")
    public ResponseEntity<loginresponse> login(@RequestBody Admin admin) {
        String Jwttoken = adminService.verifyAdmin(admin);
        if (Jwttoken != null) {
            String role = "ADMIN";
            loginresponse response = new loginresponse(Jwttoken, role);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @Autowired
    private PdfGenerationService pdfGenerationService;

    @GetMapping(value = "/generateRentReport", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<byte[]> generateRentReport(@RequestParam int year, @RequestParam int month) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            pdfGenerationService.generateRentReport(baos, year, month); // Change your service method to write to
                                                                        // OutputStream

            byte[] pdfBytes = baos.toByteArray();
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_PDF);
            headers.setContentDisposition(ContentDisposition.builder("attachment")
                    .filename("rentReport_" + year + "_" + month + ".pdf")
                    .build());

            return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getPgData")
    public ResponseEntity<PgData> getAllData() {
        PgData alldata = adminService.getAllRoomsCount();
        return new ResponseEntity<>(alldata, HttpStatus.OK);
    }

    @GetMapping("/getRentsAll")
    public ResponseEntity<List<AllRentDto>> getRents() {
        List<AllRentDto> rentDto = adminService.getAllRents();
        if (rentDto != null) {
            return new ResponseEntity<>(rentDto, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/GetAllTenants")
    public ResponseEntity<List<ViewTenantsDto>> getTenants() {
        List<ViewTenantsDto> vtd = adminService.getViewTenanats();
        if (vtd != null) {
            return new ResponseEntity<>(vtd, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @DeleteMapping("/deleteTenant")

    public ResponseEntity<Void> deleteTenants(@RequestParam String tno) {
        boolean val = adminService.deleteTenant(tno);
        if (val) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/message")
    public ResponseEntity<Void> sentmessage(@RequestBody Message message) {
        adminService.sendMessage(message);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @GetMapping("/getMessages")
    public ResponseEntity<List<Message>> sentmessage() {
        List<Message> message = adminService.getMessages();
        if (message != null) {
            return new ResponseEntity<>(message, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.OK);
        }

    }
}
