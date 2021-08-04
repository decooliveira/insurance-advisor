package com.insurance.advisor.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.nonNull;

@Data
@Builder
public class InsuranceData implements InsurableData {

    private List<Vehicle> vehicles;
    private List<House> houses;
    private User user;
    private int baseScore;
    private List<Integer> riskAnswers;

    public static InsuranceData of(PersonalInformation personalInformation) {

        User user = buildUser(personalInformation);
        List<House> houses = buildHouses(personalInformation);
        List<Vehicle> vehicles = buildVehicles(personalInformation);
        int baseScore = buildBaseScore(personalInformation);
        return InsuranceData.builder()
                .vehicles(vehicles)
                .houses(houses)
                .user(user)
                .baseScore(baseScore)
                .riskAnswers(personalInformation.getRisk_questions())
                .build();
    }

    private static User buildUser(PersonalInformation personalInformation) {
        return User.builder()
                .age(personalInformation.getAge())
                .dependents(personalInformation.getDependents())
                .income(personalInformation.getIncome())
                .maritalStatus(personalInformation.getMarital_status())
                .build();
    }

    private static List<Vehicle> buildVehicles(PersonalInformation personalInformation) {
        List<Vehicle> vehicles = new ArrayList<>();

        for(Vehicle vehicle:personalInformation.getVehicles()){
            vehicles.add(vehicle);
        }

        return vehicles;
    }

    private static List<House> buildHouses(PersonalInformation personalInformation) {
        List<House> houses = new ArrayList<>();
        if (nonNull(personalInformation.getHouses()) || !personalInformation.getHouses().isEmpty()){
            for(House house: personalInformation.getHouses()){
                houses.add(house);
            }
        }
        return houses;
    }

    private static int buildBaseScore(PersonalInformation personalInformation) {
        return personalInformation.getRisk_questions().stream().mapToInt(Integer::intValue).sum();
    }
}
