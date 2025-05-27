package com.pg.StayManage.Repository;

import com.pg.StayManage.Model.Tenant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface TenantRepo extends JpaRepository<Tenant, Long> {
    List<Tenant> findByRoomId(Long roomId);

    Optional<Tenant> findByPhone(String phone);

    Optional<Tenant> findByEmail(String email);

    Optional<Tenant> findByName(String name);

    Optional<Tenant> findByTno(String tno);

    int deleteByTno(String tno);

    @Query("SELECT SUM(t.trent) FROM Tenant t")
    Long findTotalRent();
}
