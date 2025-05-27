package com.pg.StayManage.Model;

public class loginresponse {

    private String token;
    private String role;

    // Constructors
    public loginresponse(String token, String role) {
        this.token = token;
        this.role = role;

    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }

}
