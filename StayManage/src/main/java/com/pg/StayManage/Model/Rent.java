package com.pg.StayManage.Model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "rent")
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "tno", nullable = false)
    private String tno;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "month", nullable = true)
    private LocalDate month;

    @Column(name = "paid", nullable = false)
    private boolean paid;

    @Column(name = "pmethod", nullable = true)
    private String pmethod;

    @Column(name = "dueDate", nullable = true)
    private LocalDate dueDate;

    @Column(name = "transactionId", nullable = true)
    private String transactionId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String isPmethod() {
        return pmethod;
    }

    public void setPmethod(String pmethod) {
        this.pmethod = pmethod;
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public LocalDate getMonth() {
        return month;
    }

    public void setMonth(LocalDate month) {
        this.month = month;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getPmethod() {
        return pmethod;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
