package com.insurance.advisor.service;

import com.insurance.advisor.model.*;
import com.insurance.advisor.model.insurance.Insurance;
import com.insurance.advisor.model.insurance.LifeInsurance;
import com.insurance.advisor.service.strategy.AutoCalculator;
import com.insurance.advisor.service.strategy.DisabilityCalculator;
import com.insurance.advisor.service.strategy.HomeCalculator;
import com.insurance.advisor.service.strategy.LifeCalculator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RiskCalculator {

    private AutoCalculator autoCalculator;
    private HomeCalculator homeCalculator;
    private LifeCalculator lifeCalculator;
    private DisabilityCalculator disabilityCalculator;


    public RiskProfile calculate(PersonalInformation personalInformation) {

        InsurableData data = InsuranceData.of(personalInformation);
        RiskProfile riskProfile = new RiskProfile();


        List<Insurance> auto = new ArrayList<>();
        List<Insurance> home = new ArrayList<>();

        for (Vehicle vehicle : data.getVehicles()) {
            Insurance autoInsurance = autoCalculator.calculate(data, vehicle);
            auto.add(autoInsurance);
        }

        for (House house : data.getHouses()) {
            Insurance homeInsurance = homeCalculator.calculate(data, house);
            home.add(homeInsurance);
        }

        Insurance lifeInsurance = lifeCalculator.calculate(data, null);
        Insurance disabilityInsurance = disabilityCalculator.calculate(data, null);

        riskProfile.setAuto(auto);
        riskProfile.setHome(home);
        riskProfile.setDisability(disabilityInsurance.getScore());
        riskProfile.setLife(lifeInsurance.getScore());
        return riskProfile;
    }

}


