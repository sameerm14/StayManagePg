package com.pg.StayManage.Repository;

import com.pg.StayManage.Model.Food;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FoodRepository extends JpaRepository<Food, Long> {
    List<Food> findByDayOfWeek(String dayOfWeek);
}
