package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.Constants;
import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.model.InsuranceType;
import com.insurance.advisor.model.RuleType;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RulesFactory {

    public List<InsuranceRule> get(InsuranceType insuranceType, InsurableData data) {
        switch (insuranceType) {
            case AUTO:
                return List.of(
                        new BaseScoreRule(data),
                        new AgeRangeRule(data, Constants.AGE_LOWER_LIMIT, Constants.AGE_UPPER_LIMIT),
                        new IncomeRule(data, Constants.INCOME_VALUE),
                        new VehicleRule(data)
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
                        new MaritalStatusRule(data, InsuranceType.DISABILITY)

                );
            case HOME:
                return List.of(
                        new BaseScoreRule(data),
                        new NoHouseRule(data),
                        new AgeRangeRule(data, Constants.AGE_LOWER_LIMIT, Constants.AGE_UPPER_LIMIT),
                        new IncomeRule(data, Constants.INCOME_VALUE),
                        new HouseOwnershipRule(data)
                );
            case LIFE:
                return List.of(
                        new BaseScoreRule(data),
                        new AgeLimitRule(data, Constants.AGE_LIMIT),
                        new AgeRangeRule(data, Constants.AGE_LOWER_LIMIT, Constants.AGE_UPPER_LIMIT),
                        new IncomeRule(data, Constants.INCOME_VALUE),
                        new DependentsRule(data),
                        new MaritalStatusRule(data, InsuranceType.LIFE)
                );

            default:
                return new ArrayList<>();
        }
    }
}
