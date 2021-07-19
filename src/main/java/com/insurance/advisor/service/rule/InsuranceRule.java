package com.insurance.advisor.service.rule;

import com.insurance.advisor.service.exception.IneligibilityException;

public interface InsuranceRule {
    int execute() throws IneligibilityException;
}
