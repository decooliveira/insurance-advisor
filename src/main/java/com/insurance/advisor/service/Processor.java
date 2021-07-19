package com.insurance.advisor.service;

import com.insurance.advisor.service.exception.IneligibilityException;
import com.insurance.advisor.service.rule.InsuranceRule;

import java.util.List;

public interface Processor {
    int process(List<InsuranceRule> rules) throws IneligibilityException;
}
