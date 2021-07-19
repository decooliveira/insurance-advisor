package com.insurance.advisor.service;

import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.model.InsuranceType;
import com.insurance.advisor.model.RiskProfile;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RiskProfileBuilder {

    private InsuranceFactory factory;

    public RiskProfile build(InsurableData data) {

        return RiskProfile.builder().auto(factory.build(InsuranceType.AUTO, data).getScore())
                .disability(factory.build(InsuranceType.DISABILITY, data).getScore())
                .home(factory.build(InsuranceType.HOME, data).getScore())
                .life(factory.build(InsuranceType.LIFE, data).getScore())
                .build();
    }

}
