package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class HouseOwnershipRuleTest {

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
    @DisplayName("When house is owned returns zero")
    public void When_Owned_Then_Returns_0() {
        try{
            House owned = House.builder().ownership_status(OwnershipStatus.owned).build();
            insuranceData.setHouse(owned);
            HouseOwnershipRule rules = new HouseOwnershipRule(insuranceData);
            Assertions.assertEquals(0, rules.execute());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Rule: House is Mortgaged then add 1 point")
    public void When_Mortgaged_Then_Add_1() {
        try{
            InsuranceData data = insuranceData;
            House mortgagedHouse = House.builder().ownership_status(OwnershipStatus.mortgaged).build();
            data.setHouse(mortgagedHouse);
            HouseOwnershipRule rule = new HouseOwnershipRule(data);
            Assertions.assertEquals(1, rule.execute());
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}