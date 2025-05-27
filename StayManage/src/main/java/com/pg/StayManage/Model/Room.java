package com.pg.StayManage.Model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @JsonProperty("roomNumber")
    @Column(name = "roomNumber", nullable = false)
    private String roomNumber;

    @JsonProperty("nooftenants")
    @Column(name = "nooftenants")
    private String nooftenants;

    @Column(name = "floor")
    private String floor;

    @JsonProperty("OccupiedBeds")
    @Column(name = "OccupiedBeds")
    private String OccupiedBeds;

    @JsonProperty("totalbeds")
    @Column(name = "totalbeds")
    private String totalbeds;

    @JsonProperty("AvailableBeds")
    @Column(name = " AvailableBeds")
    private String AvailableBeds;

    @Column(name = " AvailableRooms")
    private boolean AvailableRooms = true;

    @Column(name = "RoomOccupied")
    private boolean roomOccupied = false;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Tenant> tenants = new ArrayList<>();

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<RoomImage> images = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<Tenant> getTenants() {
        return tenants;
    }

    public void setTenants(List<Tenant> tenants) {
        this.tenants = tenants;
    }

    public List<RoomImage> getImages() {
        return images;
    }

    public void setImages(List<RoomImage> images) {
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

    public String getOccupiedBeds() {
        return OccupiedBeds;
    }

    public void setOccupiedBeds(String occupiedBeds) {
        OccupiedBeds = occupiedBeds;
    }

    public String getAvailableBeds() {
        return AvailableBeds;
    }

    public void setAvailableBeds(String availableBeds) {
        AvailableBeds = availableBeds;
    }

    public String getTotalbeds() {
        return totalbeds;
    }

    public void setTotalbeds(String totalbeds) {
        this.totalbeds = totalbeds;
    }

    public boolean isRoomOccupied() {
        return roomOccupied;
    }

    public void setRoomOccupied(boolean roomOccupied) {
        this.roomOccupied = roomOccupied;
    }

    public boolean isAvailableRooms() {
        return AvailableRooms;
    }

    public void setAvailableRooms(boolean availableRooms) {
        AvailableRooms = availableRooms;
    }

}