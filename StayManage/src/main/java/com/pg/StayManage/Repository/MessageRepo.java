package com.pg.StayManage.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pg.StayManage.Model.Message;

public interface MessageRepo extends JpaRepository<Message, Long> {

}
