package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DependentsRuleTest {

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
               // .house(house)
                //.vehicle(vehicle)
                .risk_questions(riskQuestions)
                .build();
        insuranceData = InsuranceData.of(personalInformation);
    }

    @Test
    @DisplayName("Rule: Dependents > 0 then adds 1 risk point")
    public void WhenDependents_Greater_Than_Zero_Then_Add_1() {
        DependentsRule dependentsRule = new DependentsRule(insuranceData);
        try {
            Assertions.assertEquals(1, dependentsRule.execute());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}