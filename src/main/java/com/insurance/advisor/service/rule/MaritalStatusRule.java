package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.model.InsuranceType;
import com.insurance.advisor.model.MaritalStatus;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class MaritalStatusRule implements InsuranceRule {

    private InsurableData data;
    private InsuranceType type;

    @Override
    public int execute() throws IneligibilityException {
        boolean married = data.getUser().getMaritalStatus() == MaritalStatus.married;
        if (married && type == InsuranceType.LIFE) {
            return 1;
        } else if (married && type == InsuranceType.DISABILITY) {
            return -1;
        }
        return 0;
    }
}
