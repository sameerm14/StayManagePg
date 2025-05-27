package com.pg.StayManage.Model;

import jakarta.persistence.*;
import java.time.LocalTime;

@Entity
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "mealType", nullable = false)
    private String mealType;

    @Column(name = "foodName", nullable = false)
    private String foodName;

    @Column(name = "foodDescription")
    private String foodDescription;

    @Column(name = "mealStartTime", nullable = false)
    private LocalTime mealStartTime;

    @Column(name = "mealEndTime", nullable = false)
    private LocalTime mealEndTime;

    @Column(name = "dayOfWeek", nullable = false)
    private String dayOfWeek;

    @Column(name = "imageUrl")
    private String imageUrl;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
