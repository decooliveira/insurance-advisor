package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.Constants;
import com.insurance.advisor.model.House;
import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.model.Vehicle;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

import java.util.List;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
@AllArgsConstructor
public class SingleHouseRule implements InsuranceRule{

    private InsurableData data;

    @Override
    public int execute() throws IneligibilityException {
        List<House> houses = data.getHouses();
        if(nonNull(houses) && houses.size() ==1){
            return 1;
        }

        return 0;
    }
}
