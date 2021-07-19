package com.insurance.advisor.service.strategy;

import com.insurance.advisor.model.InsuranceType;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CalculatorFactory {

    private AutoCalculator autoInsuranceCalculator;

    private DisabilityCalculator disabilityInsuranceCalculator;

    private HomeCalculator homeInsuranceCalculator;

    private LifeCalculator lifeInsuranceCalculator;

    public InsuranceCalculator build(InsuranceType insuranceType) {

        switch (insuranceType) {
            case AUTO:
                return this.autoInsuranceCalculator;
            case DISABILITY:
                return this.disabilityInsuranceCalculator;
            case HOME:
                return this.homeInsuranceCalculator;
            case LIFE:
                return this.lifeInsuranceCalculator;
            default:
                return null;
        }
    }
}
