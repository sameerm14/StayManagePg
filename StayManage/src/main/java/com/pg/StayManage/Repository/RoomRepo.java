package com.pg.StayManage.Repository;

import com.pg.StayManage.Model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface RoomRepo extends JpaRepository<Room, Long> {
    Optional<Room> findByRoomNumber(String roomid);

    void deleteByRoomNumber(String roomNumber);

    @Query("SELECT SUM(r.totalbeds) FROM Room r")
    Long findTotalBeds();

    @Query("SELECT SUM(r.AvailableBeds) FROM Room r")
    Long findAvailableBeds();

    @Query("SELECT SUM(r.OccupiedBeds) FROM Room r")
    Long findOccupiedBeds();

    @Query("SELECT COUNT(r) FROM Room r WHERE r.AvailableRooms = true")
    Long findAvailableRooms();

    Long countByRoomOccupied(boolean roomOccupied);
}
