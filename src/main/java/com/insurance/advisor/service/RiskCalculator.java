package com.insurance.advisor.service;

import com.insurance.advisor.model.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RiskCalculator {

    private RiskProfileBuilder riskProfileBuilder;

    public RiskProfile run(PersonalInformation personalInformation) {
        InsurableData data = InsuranceData.of(personalInformation);
        return riskProfileBuilder.build(data);
    }

}


