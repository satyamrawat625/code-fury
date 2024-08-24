package com.ecommerce.model;

public class Admin extends User {

    private long adminID;
    private String adminRole;

    public Admin() {
        super();
        adminID = 0;
        adminRole = "";
    }

    public Admin( String name, String email, String password, long adminID, String adminRole) {
        super( name, email, password);
        this.adminID = adminID;
        this.adminRole = adminRole;
    }

    public String getAdminRole() {
        return adminRole;
    }

    public void setAdminRole(String adminRole) {
        this.adminRole = adminRole;
    }

    public double getAdminID() {
        return adminID;
    }

    public void setAdminID(Long adminID) {
        this.adminID = adminID;
    }

    @Override
    public String toString() {
        return "Admin{" + super.toString() +
                "adminID=" + adminID +
                ", adminRole='" + adminRole + '\'' +
                '}';
    }
}