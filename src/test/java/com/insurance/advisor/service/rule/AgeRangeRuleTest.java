package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.*;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AgeRangeRuleTest {

    public House house;
    public Vehicle vehicle;
    public List<Integer> riskQuestions;
    public PersonalInformation personalInformation;
    public InsuranceData insuranceData;
    public AgeRangeRule ageRangeRule;

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
               // .vehicle(vehicle)
                .risk_questions(riskQuestions)
                .build();
        insuranceData = InsuranceData.of(personalInformation);

        ageRangeRule = new AgeRangeRule(insuranceData,30,40);
    }

    @Test
    @DisplayName("When age is under 30 then it deducts 2")
    public void WhenAgeIsUnder30_ThenDeducts2() {
        PersonalInformation info = personalInformation;
        info.setAge(29);
        InsuranceData data = InsuranceData.of(info);
        AgeRangeRule ar = new AgeRangeRule(data,30,40);
        try {
            Assertions.assertEquals(-2, ar.execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Rule: Age between 30 and 40")
    public void When_AgeBetween30_And_40_ThenDeducts1() {
        try {
            Assertions.assertEquals(-1, ageRangeRule.execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}