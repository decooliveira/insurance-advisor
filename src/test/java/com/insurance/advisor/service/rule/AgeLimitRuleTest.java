package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.*;
import com.insurance.advisor.service.exception.IneligibilityException;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AgeLimitRuleTest {

    public int AGE_LIMIT = 60;
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
                //.house(house)
                //.vehicle(vehicle)
                .risk_questions(riskQuestions)
                .build();
        insuranceData = InsuranceData.of(personalInformation);
    }

    @Test
    @DisplayName("Test should pass when age is under 60")
    public void WhenAgeUnder60_ThenReturnsZero() {
        AgeLimitRule ageLimitRule = new AgeLimitRule(insuranceData, AGE_LIMIT);
        try {
            Assertions.assertEquals(0, ageLimitRule.execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    @DisplayName("Rule: Age Over 60")
    public void WhenAgeOver60_ThenException() {
        PersonalInformation info = personalInformation;
        info.setAge(62);
        InsuranceData data = InsuranceData.of(info);
        AgeLimitRule ageLimitRule = new AgeLimitRule(data, AGE_LIMIT);
        try {
            assertThrows(IneligibilityException.class,() -> ageLimitRule.execute());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}