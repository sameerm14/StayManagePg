package com.pg.StayManage.Dto;

import java.util.List;

public class TRoomDto {

    private String flooar;
    private List<String> images;

    public String getFlooar() {
        return flooar;
    }

    public void setFlooar(String flooar) {
        this.flooar = flooar;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

}
