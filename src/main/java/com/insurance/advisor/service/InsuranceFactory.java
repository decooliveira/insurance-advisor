package com.insurance.advisor.service;

import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.model.InsuranceType;
import com.insurance.advisor.model.insurance.Insurance;
import com.insurance.advisor.service.strategy.CalculatorFactory;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class InsuranceFactory {

    private CalculatorFactory factory;

    public Insurance build(InsuranceType type, InsurableData data) {

        return factory.build(type).calculate(data);

    }
}
