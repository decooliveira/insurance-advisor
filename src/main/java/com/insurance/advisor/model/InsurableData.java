package com.insurance.advisor.model;

import java.util.List;

public interface InsurableData {
    List<Vehicle> getVehicles();
    User getUser();
    List<House> getHouses();
    int getBaseScore();
    List<Integer> getRiskAnswers();
}
