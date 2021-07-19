package com.insurance.advisor.service.strategy;

import com.insurance.advisor.model.*;
import com.insurance.advisor.model.insurance.Insurance;
import com.insurance.advisor.service.RulesProcessor;
import com.insurance.advisor.service.rule.RulesFactory;
import org.junit.jupiter.api.*;

import java.util.List;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DisabilityInsuranceCalculatorTest {

    public House house;
    public Vehicle vehicle;
    public List<Integer> riskQuestions;
    public PersonalInformation personalInformation;
    public InsuranceData insuranceData;
    public Insurance insurance;
    public DisabilityCalculator disabilityCalculator;

    @BeforeAll
    public void init() {

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
        disabilityCalculator = new DisabilityCalculator(new RulesFactory(),new RulesProcessor());
        insurance = disabilityCalculator.calculate(insuranceData);
    }

    @Test
    @DisplayName("Calculate Disability Insurance")
    public void When_NoIncome_Then_Return_Not_Eligible() {
        Assertions.assertEquals(Score.INELIGIBLE.getName(),insurance.getScore());
    }
}