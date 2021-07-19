package com.insurance.advisor.service.strategy;

import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.model.insurance.Insurance;

public interface InsuranceCalculator {

    Insurance calculate(InsurableData data);
}
