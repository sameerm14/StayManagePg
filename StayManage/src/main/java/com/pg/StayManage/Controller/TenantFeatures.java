package com.pg.StayManage.Controller;

import com.pg.StayManage.Dto.TRoomDto;
import com.pg.StayManage.Model.Food;
import com.pg.StayManage.Model.Rent;
import com.pg.StayManage.Model.Room;
import com.pg.StayManage.Dto.FoodDto;
import com.pg.StayManage.Repository.FoodRepository;
import com.pg.StayManage.Service.TenantsFeatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.pg.StayManage.Service.FoodService;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "${allowed.origin}")
@RestController
@RequestMapping("/tenant/features")
public class TenantFeatures {

    @Autowired
    private TenantsFeatureService tenantsFeatureService;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private FoodService foodService;

    @GetMapping("/{roomnumber}")
    public ResponseEntity<?> getTenantsRoom(@PathVariable String roomnumber) {
        Optional<Room> getroom = tenantsFeatureService.getTenantsByRoomId(roomnumber);
        if (getroom.isPresent()) {
            return new ResponseEntity<>(getroom, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something Went Wrong", HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/rentStatus")
    public ResponseEntity<?> getTenantsByRoom(@RequestParam String mobile) {
        Optional<Rent> tenantrent = tenantsFeatureService.getRentStatus(mobile);
        if (tenantrent.isPresent()) {
            return new ResponseEntity<>(tenantrent, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Something went wrong", HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/gettodayfood")
    public ResponseEntity<?> getTodayFood() {
        String dayOfWeek = DateTimeFormatter.ofPattern("EEEE").format(LocalDate.now());
        return new ResponseEntity<>(foodRepository.findByDayOfWeek(dayOfWeek), HttpStatus.OK);
    }

    @GetMapping("/allmeals")
    public ResponseEntity<List<FoodDto>> getallMeals() {
        List<FoodDto> meals = foodService.AllMeals();
        return new ResponseEntity<>(meals, HttpStatus.OK);
        // public ResponseEntity<List<Food>> getallMeals() {
        // List<Food> allmeals = foodRepository.findAll();
        // return new ResponseEntity<>(allmeals, HttpStatus.OK);
    }

    @PostMapping("/addImages")
    public ResponseEntity<Void> addImage(@RequestParam("image") MultipartFile image,
            @RequestParam("temail") String email) {
        try {
            tenantsFeatureService.saveTenantImage(email, image);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getMyroom")
    public ResponseEntity<TRoomDto> getTRoom(@RequestParam String email) {

        TRoomDto roomdata = tenantsFeatureService.getTRoomData(email);
        if (roomdata != null) {
            return new ResponseEntity<>(roomdata, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
