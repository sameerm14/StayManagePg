package com.pg.StayManage.Dto;

import java.util.List;

public class RoomDto {

    private String roomNumber;
    private String nooftenants;
    private List<String> images;
    private String floor;

    public String getRoomNumber() {
        return roomNumber;
    }

    public List<String> getImages() {
        return images;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getNooftenants() {
        return nooftenants;
    }

    public void setNooftenants(String nooftenants) {
        this.nooftenants = nooftenants;
    }

}
