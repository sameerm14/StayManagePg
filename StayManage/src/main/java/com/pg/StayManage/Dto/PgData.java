package com.pg.StayManage.Dto;

public class PgData {

    private Long totalRooms;
    private Long totalBeds;
    private Long AvailableBeds;
    private Long AvailableRooms;
    private Long OccupiedBeds;
    private Long OccupiedRooms;
    private Long totalTenants;
    private Long trent;

    public Long getTotalRooms() {
        return totalRooms;
    }

    public void setTotalRooms(Long totalRooms) {
        this.totalRooms = totalRooms;
    }

    public Long getTotalBeds() {
        return totalBeds;
    }

    public void setTotalBeds(Long totalBeds) {
        this.totalBeds = totalBeds;
    }

    public Long getAvailableBeds() {
        return AvailableBeds;
    }

    public void setAvailableBeds(Long availableBeds) {
        AvailableBeds = availableBeds;
    }

    public Long getAvailableRooms() {
        return AvailableRooms;
    }

    public void setAvailableRooms(Long availableRooms) {
        AvailableRooms = availableRooms;
    }

    public Long getOccupiedBeds() {
        return OccupiedBeds;
    }

    public void setOccupiedBeds(Long occupiedBeds) {
        OccupiedBeds = occupiedBeds;
    }

    public Long getOccupiedRooms() {
        return OccupiedRooms;
    }

    public void setOccupiedRooms(Long occupiedRooms) {
        OccupiedRooms = occupiedRooms;
    }

    public Long getTotalTenants() {
        return totalTenants;
    }

    public void setTotalTenants(Long totalTenants) {
        this.totalTenants = totalTenants;
    }

    public Long getTrent() {
        return trent;
    }

    public void setTrent(Long trent) {
        this.trent = trent;
    }

}
