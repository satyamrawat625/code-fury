package com.ecommerce.model;

public class Customer extends User {
    private String address;
    private String phoneNumber;

    public Customer() {
        super();
        address="";
        phoneNumber = "";
    }

    public Customer(String name, String email, String password, String address, String phoneNumber) {
        super( name, email, password);
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Customer{" + super.toString() +
                "address='" + address + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
