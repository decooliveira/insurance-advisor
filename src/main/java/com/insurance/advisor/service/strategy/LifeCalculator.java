package com.insurance.advisor.service.strategy;

import com.insurance.advisor.model.*;
import com.insurance.advisor.model.insurance.AutoInsurance;
import com.insurance.advisor.model.insurance.Insurance;
import com.insurance.advisor.service.RulesProcessor;
import com.insurance.advisor.service.rule.*;
import lombok.AllArgsConstructor;
import net.bytebuddy.implementation.bytecode.Addition;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("lifeCalculator")
@AllArgsConstructor
public class LifeCalculator implements InsuranceCalculator {

    private RulesFactory factory;
    private RulesProcessor rulesProcessor;

    @Override
    public Insurance calculate(InsurableData data, AdditionalParam param) {

        try {
            List<InsuranceRule> rules = factory.get(InsuranceType.LIFE, data, param);
            int riskPoints = rulesProcessor.process(rules);
            return AutoInsurance.of(riskPoints, 0);
        } catch (Exception e) {
            return AutoInsurance.builder().score(Score.INELIGIBLE.getName()).build();
        }
    }
}
