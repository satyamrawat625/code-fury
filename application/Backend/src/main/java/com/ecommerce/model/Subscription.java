package com.ecommerce.model;

import java.util.Date;

public class Subscription {
    private int id;
    private Customer customer;
    private Product product;
    private String frequency; // "weekly", "bi-weekly", "monthly"
    private Date startDate;
    private Date endDate;
    private boolean isActive;

    // Constructors, Getters and Setters
    public Subscription() {}

    public Subscription(int id, Customer customer, Product product, String frequency, Date startDate, Date endDate, boolean isActive) {
        this.id = id;
        this.customer = customer;
        this.product = product;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    @Override
    public String toString() {
        return "Subscription{" +
                "id=" + id +
//                ", customer=" + customer +
//                ", product=" + product +
                ", frequency='" + frequency + '\'' +
//                ", startDate=" + startDate +
//                ", endDate=" + endDate +
                ", isActive=" + isActive +
                '}';
    }
}
