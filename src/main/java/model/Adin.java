package com.RealEstate.model;

public class Buyer {
    private String email;
    private String fullName;
    private String phoneNumber;
    private String preferredLocation;
    private String password;
    private double budget;

    // Default constructor
    public Buyer() {
    }

    // Constructor with all parameters
    public Buyer(String email, String fullName, String phoneNumber, String preferredLocation, String password, double budget) {
        this.email = email;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.preferredLocation = preferredLocation;
        this.password = password;
        this.budget = budget;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public void setPreferredLocation(String preferredLocation) {
        this.preferredLocation = preferredLocation;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }
} 