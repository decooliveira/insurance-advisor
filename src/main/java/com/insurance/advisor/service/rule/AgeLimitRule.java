package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AgeLimitRule implements InsuranceRule{

    private InsurableData data;
    private int ageLimit;

    @Override
    public int execute() throws IneligibilityException {
        if(data.getUser().getAge() > ageLimit){
            throw new IneligibilityException();
        }
        return 0;
    }
}
