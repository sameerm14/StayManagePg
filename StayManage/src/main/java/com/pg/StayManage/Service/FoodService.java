package com.pg.StayManage.Service;

import com.pg.StayManage.Dto.FoodDto;
import com.pg.StayManage.Model.Food;
import com.pg.StayManage.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;

    public List<Food> getMealsForToday() {
        String dayOfWeek = DateTimeFormatter.ofPattern("EEEE").format(LocalDate.now());
        return foodRepository.findByDayOfWeek(dayOfWeek);
    }

    public void addFoodWithImage(Food food, MultipartFile imageFile) throws IOException {
        String uploadDir = "uploads/food-images/";
        File dir = new File(uploadDir);
        if (!dir.exists())
            dir.mkdirs();
        String fileName = System.currentTimeMillis() + "_" + imageFile.getOriginalFilename();
        Path filePath = Paths.get(uploadDir, fileName);
        Files.write(filePath, imageFile.getBytes());
        food.setImageUrl("/" + uploadDir + fileName);
        foodRepository.save(food);
    }

    public boolean deleteFood(Long id) {
        Food food = foodRepository.findById(id).orElse(null);
        if (food == null) {
            return false;
        }
        foodRepository.delete(food);
        String imageUrl = food.getImageUrl();
        if (imageUrl != null && !imageUrl.isEmpty()) {
            String fileName = imageUrl.split("/")[3];
            String imagePath = "uploads/food-images/" + fileName;
            File imageFile = new File(imagePath);
            if (imageFile.exists()) {
                boolean isDeleted = imageFile.delete();
                if (!isDeleted) {
                    System.out.println("Failed to delete image file: " + imagePath);
                }
            }
        }
        return true;
    }

    public List<FoodDto> AllMeals() {
        return foodRepository.findAll().stream().map(food -> {
            FoodDto foodDTO = new FoodDto();
            foodDTO.setFoodName(food.getFoodName());
            foodDTO.setMealType(food.getMealType());
            foodDTO.setFoodDescription(food.getFoodDescription());
            foodDTO.setMealStartTime(food.getMealStartTime());
            foodDTO.setMealEndTime(food.getMealEndTime());
            foodDTO.setDayOfWeek(food.getDayOfWeek());

            String rawImagePath = food.getImageUrl();

            if (rawImagePath != null && !rawImagePath.isEmpty()) {
                String fullUrl = "http://localhost:8080" + rawImagePath.replace("\\", "/");
                foodDTO.setImages(Collections.singletonList(fullUrl));
            } else {
                foodDTO.setImages(Collections.emptyList());
            }

            return foodDTO;
        }).collect(Collectors.toList());
    }
}
