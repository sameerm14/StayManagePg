package com.pg.StayManage.Dto;

import java.time.LocalTime;
import java.util.List;

public class FoodDto {

    private String mealType;
    private String foodName;
    private String foodDescription;
    private LocalTime mealStartTime;
    private LocalTime mealEndTime;
    private String dayOfWeek;
    private List<String> images;

    public String getMealType() {
        return mealType;
    }

    public void setMealType(String mealType) {
        this.mealType = mealType;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public String getFoodDescription() {
        return foodDescription;
    }

    public void setFoodDescription(String foodDescription) {
        this.foodDescription = foodDescription;
    }

    public LocalTime getMealStartTime() {
        return mealStartTime;
    }

    public void setMealStartTime(LocalTime mealStartTime) {
        this.mealStartTime = mealStartTime;
    }

    public LocalTime getMealEndTime() {
        return mealEndTime;
    }

    public void setMealEndTime(LocalTime mealEndTime) {
        this.mealEndTime = mealEndTime;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
