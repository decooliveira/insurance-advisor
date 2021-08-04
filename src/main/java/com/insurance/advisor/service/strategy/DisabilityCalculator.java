package com.insurance.advisor.service.strategy;

import com.insurance.advisor.model.AdditionalParam;
import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.model.InsuranceType;
import com.insurance.advisor.model.Score;
import com.insurance.advisor.model.insurance.AutoInsurance;
import com.insurance.advisor.model.insurance.Insurance;
import com.insurance.advisor.service.RulesProcessor;
import com.insurance.advisor.service.rule.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("disabilityCalculator")
@AllArgsConstructor
public class DisabilityCalculator implements InsuranceCalculator {

    private RulesFactory factory;
    private RulesProcessor rulesProcessor;

    @Override
    public Insurance calculate(InsurableData data, AdditionalParam param) {

        try {
            List<InsuranceRule> rules = factory.get(InsuranceType.DISABILITY, data, param);
            int riskPoints = rulesProcessor.process(rules);
            return AutoInsurance.of(riskPoints,0);
        } catch (Exception e) {
            return AutoInsurance.builder().score(Score.INELIGIBLE.getName()).build();
        }
    }
}
