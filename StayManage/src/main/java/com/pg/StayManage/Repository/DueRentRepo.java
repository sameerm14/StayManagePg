package com.pg.StayManage.Repository;

import com.pg.StayManage.Model.DueRents;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DueRentRepo extends JpaRepository<DueRents, Long> {
}
