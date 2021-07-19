package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class BaseScoreRule implements InsuranceRule{

    private InsurableData data;

    @Override
    public int execute() throws IneligibilityException {
        return data.getBaseScore();
    }
}
