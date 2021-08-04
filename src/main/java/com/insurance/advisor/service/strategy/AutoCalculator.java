package com.insurance.advisor.service.strategy;

import com.insurance.advisor.model.*;
import com.insurance.advisor.model.insurance.AutoInsurance;
import com.insurance.advisor.model.insurance.Insurance;
import com.insurance.advisor.service.RulesProcessor;
import com.insurance.advisor.service.rule.*;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;


@Service("autoCalculator")
@AllArgsConstructor
public class AutoCalculator implements InsuranceCalculator {

    private RulesFactory factory;
    private RulesProcessor rulesProcessor;

    public Insurance calculate(InsurableData data, AdditionalParam param) {

        try {
            List<InsuranceRule> rules = factory.get(InsuranceType.AUTO, data,param);
            int riskPoints = rulesProcessor.process(rules);
            Vehicle vehicle = (Vehicle) param;
            return AutoInsurance.of(riskPoints,vehicle.getId());
        } catch (Exception e) {
            return AutoInsurance.builder().score(Score.INELIGIBLE.getName()).build();
        }
    }
}
