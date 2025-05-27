package com.pg.StayManage.Controller;

import com.pg.StayManage.Dto.FoodDto;
import com.pg.StayManage.Model.Food;
import com.pg.StayManage.Service.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalTime;
import java.util.List;

@CrossOrigin(origins = "${allowed.origin}")
@RestController
@RequestMapping("/api/food")
public class FoodController {

    @Autowired
    private FoodService foodService;

    @GetMapping("/meals/today")
    public ResponseEntity<List<Food>> getMealsForToday() {
        List<Food> meals = foodService.getMealsForToday();
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    @GetMapping("/allmeals")
    public ResponseEntity<List<FoodDto>> getallMeals() {
        List<FoodDto> meals = foodService.AllMeals();
        return new ResponseEntity<>(meals, HttpStatus.OK);
    }

    @PostMapping("/addfoodwithimage")
    public ResponseEntity<Void> addFoodWithImage(
            @RequestParam("mealType") String mealType,
            @RequestParam("foodName") String foodName,
            @RequestParam("foodDescription") String foodDescription,
            @RequestParam("mealStartTime") String mealStartTime,
            @RequestParam("mealEndTime") String mealEndTime,
            @RequestParam("dayOfWeek") String dayOfWeek,
            @RequestParam("imageFile") MultipartFile imageFile) {
        try {
            Food food = new Food();
            food.setMealType(mealType);
            food.setFoodName(foodName);
            food.setFoodDescription(foodDescription);
            food.setMealStartTime(LocalTime.parse(mealStartTime));
            food.setMealEndTime(LocalTime.parse(mealEndTime));
            food.setDayOfWeek(dayOfWeek);

            foodService.addFoodWithImage(food, imageFile);

            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/deleteFood")
    public ResponseEntity<String> deleteFoodById(@RequestParam Long id) {
        boolean deleted = foodService.deleteFood(id);
        if (deleted) {
            return new ResponseEntity<>("Food deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Food not found", HttpStatus.NOT_FOUND);
        }
    }

}
