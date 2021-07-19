package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.*;
import com.insurance.advisor.service.exception.IneligibilityException;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class VehicleRuleTest {

    public House house;
    public Vehicle vehicle;
    public List<Integer> riskQuestions;
    public PersonalInformation personalInformation;
    public InsuranceData insuranceData;

    @BeforeAll
    public void  init() {

        house = House.builder()
                .ownership_status(OwnershipStatus.owned)
                .build();

        vehicle = Vehicle.builder()
                .year(2018)
                .build();

        riskQuestions = List.of(0, 1, 0);

        personalInformation = PersonalInformation.builder()
                .age(35)
                .dependents(2)
                .marital_status(MaritalStatus.married)
                .income(0)
                .house(house)
                .vehicle(vehicle)
                .risk_questions(riskQuestions)
                .build();
        insuranceData = InsuranceData.of(personalInformation);
    }

    @Test
    @DisplayName("Rule: No vehicle then ineligible for auto insurance")
    public void When_VehicleIsNull_Then_ThrowsInegibilityException() {
        try{
            InsuranceData data = insuranceData;
            data.setVehicle(null);
            VehicleRule rule = new VehicleRule(insuranceData);
            assertThrows(IneligibilityException.class, () -> rule.execute());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("When vehicle age is under five years then add 1")
    public void When_VehicleAgeIsUnder5_Then_Add_1() {
        try{
            VehicleRule rule = new VehicleRule(insuranceData);
            Assertions.assertEquals(1,rule.execute());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}