package com.pg.StayManage.Dto;

import java.time.LocalDate;
import java.util.List;

public class MyRoomDto {

    private String name;
    private String phone;
    private String email;
    private LocalDate dueDate;
    private boolean rentStatus;
    private List<String> images;
    private String tProfile;
    private String roomShairing;
    private Long trent;

    public String getRoomShairing() {
        return roomShairing;
    }

    public void setRoomShairing(String roomShairing) {
        this.roomShairing = roomShairing;
    }

    public Long getTrent() {
        return trent;
    }

    public void setTrent(Long trent) {
        this.trent = trent;
    }

    public String getName() {
        return name;
    }

    public String gettProfile() {
        return tProfile;
    }

    public void settProfile(String tProfile) {
        this.tProfile = tProfile;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(boolean rentStatus) {
        this.rentStatus = rentStatus;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
