package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

import static java.util.Objects.isNull;

@AllArgsConstructor
public class NoHouseRule implements InsuranceRule{

    private InsurableData data;

    @Override
    public int execute() throws IneligibilityException {

        if(isNull(data.getHouse())){
            throw new IneligibilityException();
        }
        return 0;
    }
}
