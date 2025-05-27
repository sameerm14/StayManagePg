package com.pg.StayManage.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
@Table(name = "tenant")
public class Tenant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "email", nullable = true)
    private String email;

    @Column(name = "pass", nullable = true)
    private String pass;

    @Column(name = "tno", nullable = false)
    private String tno;

    @Column(name = "role", nullable = true)
    private String role = "ROLE_TENANT";

    @Column(name = "profileurl", nullable = true)
    private String profileUrl;

    @Column(name = "rentStatus", nullable = false)
    private boolean rentStatus;

    @Column(name = "roomNumber", nullable = true)
    private String roomNumber;

    @Column(name = "roomSharing", nullable = true)
    private String roomSharing;

    @Column(name = "trent", nullable = true)
    private Long trent;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "room_id")
    @JsonBackReference

    private Room room;

    public String getRoomSharing() {
        return roomSharing;
    }

    public void setRoomSharing(String roomSharing) {
        this.roomSharing = roomSharing;
    }

    public Long getTrent() {
        return trent;
    }

    public void setTrent(Long trent) {
        this.trent = trent;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
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

    public boolean getRentStatus() {
        return rentStatus;
    }

    public void setRentStatus(boolean rentStatus) {
        this.rentStatus = rentStatus;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public boolean isRentStatus() {
        return rentStatus;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getTno() {
        return tno;
    }

    public void setTno(String tno) {
        this.tno = tno;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }
}