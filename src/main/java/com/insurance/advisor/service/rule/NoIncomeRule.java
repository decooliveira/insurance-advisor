package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NoIncomeRule implements InsuranceRule{

    private InsurableData data;

    @Override
    public int execute() throws IneligibilityException {
        if(data.getUser().getIncome() == 0){
            throw new IneligibilityException();
        }
        return 0;
    }
}
