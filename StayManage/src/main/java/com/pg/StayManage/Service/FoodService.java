package com.pg.StayManage.Service;

import com.pg.StayManage.Dto.FoodDto;
import com.pg.StayManage.Model.Food;
import com.pg.StayManage.Repository.FoodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.beans.factory.annotation.Value;
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
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.util.Map;



@Service
public class FoodService {

    @Autowired
    private FoodRepository foodRepository;
    
    @Autowired
    private Cloudinary cloudinary;

    
    public List<Food> getMealsForToday() {
        String dayOfWeek = DateTimeFormatter.ofPattern("EEEE").format(LocalDate.now());
        return foodRepository.findByDayOfWeek(dayOfWeek);
    }

public void addFoodWithImage(Food food, MultipartFile imageFile) throws IOException {

    Map uploadResult = cloudinary.uploader().upload(
            imageFile.getBytes(),
            ObjectUtils.asMap(
                    "folder", "StayManage/food"
            )
    );

    String imageUrl = uploadResult.get("secure_url").toString();
    food.setImageUrl(imageUrl);

    foodRepository.save(food);
}

    public boolean deleteFood(Long id) {
        Food food = foodRepository.findById(id).orElse(null);
        if (food == null) return false;
        foodRepository.delete(food);
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

        String imageUrl = food.getImageUrl();

        if (imageUrl != null && !imageUrl.isEmpty()) {
            foodDTO.setImages(Collections.singletonList(imageUrl));  // use imageUrl directly
        } else {
            foodDTO.setImages(Collections.emptyList());
        }

        return foodDTO;
    }).collect(Collectors.toList());
}
}
