package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IncomeRule implements InsuranceRule{

    private InsurableData data;
    private int incomeValue;

    @Override
    public int execute() throws IneligibilityException {
        int userIncome = data.getUser().getIncome();
        if(userIncome > incomeValue){
            return -1;
        }
        return 0;
    }
}
