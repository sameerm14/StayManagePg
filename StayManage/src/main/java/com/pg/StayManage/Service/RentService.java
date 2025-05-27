package com.pg.StayManage.Service;

import com.pg.StayManage.Model.Rent;
import com.pg.StayManage.Repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Optional;

@Service
public class RentService {
    @Autowired
    private RentRepository rentRepository;

    public List<Rent> getRentDueToday() {
        LocalDate today = LocalDate.now();
        return rentRepository.findByDueDateAndPaidFalse(today);
    }

    public List<Rent> getOverdueRents() {
        LocalDate today = LocalDate.now();
        return rentRepository.findByDueDateBeforeAndPaidFalse(today);
    }

    public Rent markRentAsPaid(String tno) {
        LocalDate currentMonthStart = YearMonth.now().atDay(1);

        Rent rent = rentRepository.findByTnoAndMonth(tno, currentMonthStart)
                .orElseThrow(() -> new RuntimeException("Rent record not found for this tenant and month"));

        rent.setPaid(true);
        return rentRepository.save(rent);
    }

    public List<Rent> getRentHistoryForTenant(String tno) {
        return rentRepository.findByTnoOrderByMonthDesc(tno);
    }

    public void setRentpaid(Long id) {
        Optional<Rent> rent = rentRepository.findById(id);
        if (rent.isPresent()) {
            Rent rent1 = rent.get();
            rent1.setPaid(true);
        }
    }
}
