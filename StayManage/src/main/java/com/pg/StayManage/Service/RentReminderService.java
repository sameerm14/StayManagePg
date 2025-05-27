package com.pg.StayManage.Service;

import com.pg.StayManage.Model.Rent;
import com.pg.StayManage.Model.Tenant;
import com.pg.StayManage.Repository.RentRepository;
import com.pg.StayManage.Repository.TenantRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class RentReminderService {

    private static final int GRACE_PERIOD_DAYS = 5;

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private TenantRepo tenantRepo;

    // Runs daily at 9 AM
    @Scheduled(cron = "0 0 9 * * ?")
    public void sendDailyRentReminders() {
        List<Rent> rents = rentRepository.findAll();
        LocalDate today = LocalDate.now();

        for (Rent rent : rents) {
            if (!rent.isPaid() && rent.getDueDate() != null) {
                long daysBetween = ChronoUnit.DAYS.between(rent.getDueDate(), today);
                Optional<Tenant> tenant = tenantRepo.findByPhone(rent.getPhone());
                if(tenant.isPresent()){
                    Tenant tent = tenant.get();
                    if (daysBetween == 0) {
                        // Rent due today
                        notificationService.send(rent.getPhone(), tent.getName());
                    } else if (daysBetween > GRACE_PERIOD_DAYS) {
                        // Rent is overdue
                        notificationService.send(rent.getPhone(), tent.getName());
                    }
                }

            }
        }
    }
}
