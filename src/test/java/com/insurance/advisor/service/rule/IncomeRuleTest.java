package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class IncomeRuleTest {

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
    @DisplayName("Rule: Income > 200k then deducts 1 point")
    public void When_Income_GreaterThan_200K_Then_Deducts_1() {

        try{
            IncomeRule rule = new IncomeRule(insuranceData, 200_000);
            Assertions.assertEquals(0,rule.execute());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}