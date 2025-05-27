package com.pg.StayManage.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pg.StayManage.Model.Contactus;
import com.pg.StayManage.Repository.contactusRepo;

@Service
public class contctusService {

    @Autowired
    private contactusRepo ContactusRepo;

    public boolean savecontactus(Contactus contactus) {
        ContactusRepo.save(contactus);
        return true;

    }

}
