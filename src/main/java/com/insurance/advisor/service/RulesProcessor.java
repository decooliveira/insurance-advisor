package com.insurance.advisor.service;

import com.insurance.advisor.service.exception.IneligibilityException;
import com.insurance.advisor.service.rule.InsuranceRule;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RulesProcessor implements Processor{

    @Override
    public int process(List<InsuranceRule> rules) throws IneligibilityException {

            int points = 0;
            for (InsuranceRule rule : rules) {
                points += rule.execute();
            }
            return points;
    }
}
