package com.pg.StayManage.Service;

import com.pg.StayManage.Model.DueRents;
import com.pg.StayManage.Repository.DueRentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    private DueRentRepo dueRentRepo;

    public void send(String mobile, String name) {
        DueRents due = new DueRents();
        due.setMobile(mobile);
        due.setName(name);
        dueRentRepo.save(due);
    }
}
