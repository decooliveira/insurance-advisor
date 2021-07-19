package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MaritalStatusRuleTest {

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
    @DisplayName("When married and life insurance then add 1 ")
    public void When_Married_And_LifeInsurance_Then_Add_1() {
        try {
            MaritalStatusRule rule = new MaritalStatusRule(insuranceData, InsuranceType.LIFE);
            Assertions.assertEquals(1, rule.execute());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Rule: When married deducts 1 point of disability insurance")
    public void When_Married_And_DisabilityInsurance_Then_Deducts_1() {
        try {
            MaritalStatusRule rule = new MaritalStatusRule(insuranceData, InsuranceType.DISABILITY);
            Assertions.assertEquals(-1, rule.execute());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}