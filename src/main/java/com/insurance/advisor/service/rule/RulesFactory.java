package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.*;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RulesFactory {

    public List<InsuranceRule> get(InsuranceType insuranceType, InsurableData data, AdditionalParam param) {
        switch (insuranceType) {
            case AUTO:
                return List.of(
                        new BaseScoreRule(data),
                        new AgeRangeRule(data, Constants.AGE_LOWER_LIMIT, Constants.AGE_UPPER_LIMIT),
                        new IncomeRule(data, Constants.INCOME_VALUE),
                        new SingleVehicleRule(data),
                        new VehicleAgeRule((Vehicle) param),
                        new RiskAndIncomeRule(data)
                );
            case DISABILITY:
                return List.of(
                        new BaseScoreRule(data),
                        new NoIncomeRule(data),
                        new AgeLimitRule(data, Constants.AGE_LIMIT),
                        new AgeRangeRule(data, Constants.AGE_LOWER_LIMIT, Constants.AGE_UPPER_LIMIT),
                        new IncomeRule(data, Constants.INCOME_VALUE),
                        new HouseOwnershipRule(data),
                        new DependentsRule(data),
                        new DisabilityRiskAnswerRule(data),
                        new MaritalStatusRule(data, InsuranceType.DISABILITY),
                        new RiskAndIncomeRule(data)

                );
            case HOME:
                return List.of(
                        new BaseScoreRule(data),
                        new NoHouseRule(data),
                        new AgeRangeRule(data, Constants.AGE_LOWER_LIMIT, Constants.AGE_UPPER_LIMIT),
                        new IncomeRule(data, Constants.INCOME_VALUE),
                        new HouseOwnershipRule(data),
                        new SingleHouseRule(data),
                        new RiskAndIncomeRule(data)
                );
            case LIFE:
                return List.of(
                        new BaseScoreRule(data),
                        new AgeLimitRule(data, Constants.AGE_LIMIT),
                        new AgeRangeRule(data, Constants.AGE_LOWER_LIMIT, Constants.AGE_UPPER_LIMIT),
                        new IncomeRule(data, Constants.INCOME_VALUE),
                        new DependentsRule(data),
                        new MaritalStatusRule(data, InsuranceType.LIFE),
                        new RiskAndIncomeRule(data)
                );

            default:
                return new ArrayList<>();
        }
    }
}
