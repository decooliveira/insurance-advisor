package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class AgeRangeRule implements InsuranceRule{

    private InsurableData data;
    private int ageLower;
    private int ageUpper;

    @Override
    public int execute() throws IneligibilityException {
        int userAge = data.getUser().getAge();
        if(userAge < ageLower){
            return -2;
        }else if(userAge >= ageLower && userAge <= ageUpper){
            return -1;
        }else{
            return 0;
        }
    }
}
