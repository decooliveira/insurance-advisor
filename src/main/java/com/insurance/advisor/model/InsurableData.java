package com.insurance.advisor.model;

public interface InsurableData {
    Vehicle getVehicle();
    User getUser();
    House getHouse();
    int getBaseScore();
}
