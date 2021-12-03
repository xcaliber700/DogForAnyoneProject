package com.example.dogforanyone;

import java.io.Serializable;

public class Owner implements Serializable {
    private int id;
    private String fullName;
    private int ownerPicId;
    private String phoneNumber;
    private String location;
    private int dogId;

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public int getOwnerPicId() {
        return ownerPicId;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getLocation() {
        return location;
    }

    public int getDogId() {
        return dogId;
    }
    public Owner(){}

    public Owner(int id, String fullName, int ownerPicId,String phoneNumber, String location, int dogId) {
        this.id = id;
        this.fullName = fullName;
        this.ownerPicId = ownerPicId;
        this.phoneNumber = phoneNumber;
        this.location = location;
        this.dogId = dogId;
    }
}
