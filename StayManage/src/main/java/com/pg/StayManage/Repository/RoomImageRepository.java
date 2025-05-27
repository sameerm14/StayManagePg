package com.pg.StayManage.Repository;

import com.pg.StayManage.Model.RoomImage;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomImageRepository extends JpaRepository<RoomImage, Long> {

    List<RoomImage> findByRoomNumber(String roomNumber);

    void deleteByRoomNumber(String roomNumber);

}
