package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;
//todo: name refactor
@AllArgsConstructor
public class DisabilityRiskAnswerRule implements InsuranceRule{

    private InsurableData data;

    @Override
    public int execute() throws IneligibilityException {
        List<Integer> riskAnswers = data.getRiskAnswers();
        if(Objects.nonNull(riskAnswers) && !riskAnswers.isEmpty() && riskAnswers.size()>=2){
            Integer answer = riskAnswers.get(1);
            if(answer == 1){
                return 2;
            }
        }
        return 0;
    }
}
