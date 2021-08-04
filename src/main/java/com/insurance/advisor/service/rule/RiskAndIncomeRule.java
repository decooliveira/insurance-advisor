package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.Constants;
import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class RiskAndIncomeRule implements InsuranceRule{

    private InsurableData data;

    @Override
    public int execute() throws IneligibilityException {
        List<Integer> riskAnswers = data.getRiskAnswers();
        int income = data.getUser().getIncome();

        if(data.getBaseScore() == 0 && income < Constants.INCOME_LOWER_LIMIT){
            throw new IneligibilityException();
        }

        return 0;
    }
}
