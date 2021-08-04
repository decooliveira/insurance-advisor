package com.insurance.advisor.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.insurance.advisor.model.*;
import com.insurance.advisor.model.insurance.AutoInsurance;
import com.insurance.advisor.model.insurance.HomeInsurance;
import com.insurance.advisor.model.insurance.Insurance;
import com.insurance.advisor.service.RiskCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
//@WebMvcTest({AdvisorController.class, RiskCalculator.class})

class AdvisorControllerTest {


    @Autowired
    private RiskCalculator riskCalculator;


    private PersonalInformation personalInformation;


    @BeforeEach
    public void setup() {
        List<House> houses = new ArrayList<>();
        List<Vehicle> vehicles = new ArrayList<>();

        vehicles.add(new Vehicle(1, 2018));
        houses.add(new House(1, OwnershipStatus.owned));

        personalInformation = PersonalInformation.builder()
                .age(35)
                .dependents(2)
                .houses(houses)
                .vehicles(vehicles)
                .income(100000)
                .risk_questions(Arrays.asList(0, 1, 0))
                .marital_status(MaritalStatus.single)
                .build();
    }

    @Test
    void When_Invoke_Returns_Success() throws Exception {

        riskCalculator.calculate(personalInformation);
        Assertions.assertNotNull(riskCalculator);
    }

    @Test
    void When_SecondRiskAnswerIsTrue_ThenDisabilityIsResponsible() throws Exception {
        List<Integer> riskQuestions = Arrays.asList(0, 1, 0);
        personalInformation.setRisk_questions(riskQuestions);
        RiskProfile riskProfile = riskCalculator.calculate(personalInformation);

        List<Insurance> autoInsurances = new ArrayList<>();
        autoInsurances.add(new AutoInsurance(2, Score.REGULAR.getName(), 1));

        List<Insurance> homeInsurances = new ArrayList<>();
        homeInsurances.add(new HomeInsurance(1, Score.REGULAR.getName(), 1));


        Assertions.assertEquals(Score.RESPONSIBLE.getName(), riskProfile.getDisability());

    }


    @Test
    void When_AllRiskAnswersAreFalseAndIncomeLowerThanIncomeLowerLimit_ThenAllInsurancesIneligible() throws Exception {
        List<Integer> riskQuestions = Arrays.asList(0, 0, 0);
        personalInformation.setRisk_questions(riskQuestions);
        personalInformation.setIncome(24000);
        RiskProfile riskProfileResponse = riskCalculator.calculate(personalInformation);

        List<Insurance> autoInsurances = new ArrayList<>();
        autoInsurances.add(new AutoInsurance(2, Score.INELIGIBLE.getName(), 1));

        List<Insurance> homeInsurances = new ArrayList<>();
        homeInsurances.add(new HomeInsurance(1, Score.INELIGIBLE.getName(), 1));


        RiskProfile expectedRiskProfile = RiskProfile.builder()
                .auto(autoInsurances)
                .disability(Score.INELIGIBLE.getName())
                .home(homeInsurances)
                .life(Score.INELIGIBLE.getName())
                .build();

        Assertions.assertEquals(expectedRiskProfile.toString(),riskProfileResponse.toString());

    }
}