package com.pg.StayManage.Repository;

import com.pg.StayManage.Model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentRepository extends JpaRepository<Rent, Long> {
    Optional<Rent> findByTnoAndMonth(String tno, LocalDate month);

    List<Rent> findByDueDateAndPaidFalse(LocalDate dueDate);

    List<Rent> findByDueDateBeforeAndPaidFalse(LocalDate today);

    List<Rent> findByTnoOrderByMonthDesc(String tno);

    List<Rent> findByMonthBetween(LocalDate start, LocalDate end);

    Optional<Rent> findByPhone(String phone);

    Optional<Rent> findByPhoneAndMonth(String phone, LocalDate month);

    Optional<Rent> findByTno(String tno);

    int deleteByTno(String tno);
}
