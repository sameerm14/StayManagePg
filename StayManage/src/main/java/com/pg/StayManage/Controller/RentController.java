package com.pg.StayManage.Controller;

import com.pg.StayManage.Model.Rent;
import com.pg.StayManage.Service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import org.springframework.web.bind.annotation.RequestBody;

@CrossOrigin(origins = "${allowed.origin}")
@RestController
@RequestMapping("/rent")
public class RentController {
    @Autowired
    private RentService rentService;

    public String postMethodName(@RequestBody String entity) {
        // TODO: process POST request

        return entity;
    }

    @GetMapping("/duetoday")
    public List<Rent> getRentDueToday() {
        return rentService.getRentDueToday();
    }

    @GetMapping("/overdue")
    public List<Rent> getOverdueRents() {
        return rentService.getOverdueRents();
    }

    @PutMapping("/markpaid/{tno}")
    public Rent markRentAsPaid(@PathVariable String tno) {
        return rentService.markRentAsPaid(tno);
    }

    @GetMapping("/history/{tno}")
    public List<Rent> getRentHistory(@PathVariable String tno) {
        return rentService.getRentHistoryForTenant(tno);
    }
}
