package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DependentsRule implements InsuranceRule{

    private InsurableData data;

    @Override
    public int execute() throws IneligibilityException {
        if(data.getUser().getDependents() > 0){
            return 1;
        }
        return 0;
    }
}
