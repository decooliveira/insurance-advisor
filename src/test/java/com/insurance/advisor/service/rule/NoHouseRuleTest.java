package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.*;
import com.insurance.advisor.service.exception.IneligibilityException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NoHouseRuleTest {

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
               // .house(null)
                //.vehicle(vehicle)
                .risk_questions(riskQuestions)
                .build();
        insuranceData = InsuranceData.of(personalInformation);
    }

    @Test
    @DisplayName("Rule: No house then ineligible for home insurance")
    public void When_NoHouse_Then_RaiseIneligibilityException() {
        try {
            NoHouseRule rule = new NoHouseRule(insuranceData);
            assertThrows(IneligibilityException.class,() -> rule.execute());
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}