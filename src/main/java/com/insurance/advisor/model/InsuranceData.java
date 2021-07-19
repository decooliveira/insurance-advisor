package com.insurance.advisor.model;

import lombok.Builder;
import lombok.Data;

import static java.util.Objects.nonNull;

@Data
@Builder
public class InsuranceData implements InsurableData {

    private Vehicle vehicle;
    private House house;
    private User user;
    private int baseScore;

    public static InsuranceData of(PersonalInformation personalInformation) {

        User user = buildUser(personalInformation);
        House house = buildHouse(personalInformation);
        Vehicle vehicle = buildVehicle(personalInformation);
        int baseScore = buildBaseScore(personalInformation);
        return InsuranceData.builder()
                .vehicle(vehicle)
                .house(house)
                .user(user)
                .baseScore(baseScore)
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

    private static Vehicle buildVehicle(PersonalInformation personalInformation) {
        return Vehicle.builder()
                .year(personalInformation.getVehicle().getYear())
                .build();
    }

    private static House buildHouse(PersonalInformation personalInformation) {
        if (nonNull(personalInformation.getHouse())
                && nonNull(personalInformation.getHouse().getOwnership_status())) {
            return House.builder()
                    .ownership_status(personalInformation.getHouse().getOwnership_status())
                    .build();
        }
        return null;
    }

    private static int buildBaseScore(PersonalInformation personalInformation) {
        return personalInformation.getRisk_questions().stream().mapToInt(Integer::intValue).sum();
    }
}
