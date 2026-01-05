package com.pg.StayManage.Controller;

import com.pg.StayManage.Dto.MyRoomDto;
import com.pg.StayManage.Dto.RentDto;
import com.pg.StayManage.Dto.TenantDto;
import com.pg.StayManage.Model.Tenant;
import com.pg.StayManage.Model.loginresponse;
import com.pg.StayManage.Service.TenantService;
import com.pg.StayManage.Service.TenantsFeatureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "${allowed.origin}")
@RestController
@RequestMapping("/api/tenants")
public class TenantController {

    @Autowired
    private TenantService tenantService;

    @Autowired
    private TenantsFeatureService tenantsFeatureService;

    // @PostMapping("/addtenant")
    // public ResponseEntity<Void> createTenant(@RequestBody Tenant tenant) {
    //     boolean ans = tenantService.saveTenant(tenant);
    //     if (ans) {
    //         return new ResponseEntity<>(HttpStatus.CREATED);
    //     } else {
    //         return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    //     }

    // }

    @PostMapping("/Paymentdone")
    public ResponseEntity<Void> rentPay(@RequestBody RentDto rentDto) {
        boolean value = tenantsFeatureService.createMonthlyRent(rentDto);
        if (value) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<Iterable<Tenant>> getTenantsByRoom(@PathVariable Long roomId) {
        Iterable<Tenant> alltenants = tenantService.getTenantsByRoomId(roomId);
        return new ResponseEntity<>(alltenants, HttpStatus.OK);
    }

    @PutMapping("/{tenantId}/rentstatus")
    public ResponseEntity<String> updateRentStatus(@PathVariable Long tenantId, @RequestParam boolean paid) {
        boolean tenant = tenantService.updateRentStatus(tenantId, paid);
        if (tenant) {
            return new ResponseEntity<>("Status changed Successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tenantregister")
    public ResponseEntity<Void> registerTenant(@RequestBody TenantDto tenantDto) {
        boolean register = tenantService.regTenant(tenantDto);
        if (register) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/tenantlogin")
    public ResponseEntity<loginresponse> logintenant(@RequestBody TenantDto tenantDto) {
        String jwttoken = tenantService.loginten(tenantDto);
        if (jwttoken != null) {
            String role = "TENANT";
            loginresponse response = new loginresponse(jwttoken, role);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/myroom")
    public ResponseEntity<MyRoomDto> myRoomDetails(@RequestParam String email) {
        try {
            MyRoomDto rooms = tenantsFeatureService.getMyRoom(email);
            if (rooms != null) {
                return new ResponseEntity<>(rooms, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
