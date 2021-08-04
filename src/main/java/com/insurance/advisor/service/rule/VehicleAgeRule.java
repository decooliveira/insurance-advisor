package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.Constants;
import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.model.Vehicle;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

import java.util.List;

import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;

@AllArgsConstructor
public class VehicleAgeRule implements InsuranceRule {

    private Vehicle vehicle;

    @Override
    public int execute() throws IneligibilityException {
        if(isNull(vehicle)){
            throw new IneligibilityException();
       }else if(nonNull(vehicle) && vehicle.getAge() <= Constants.VEHICLE_AGE) {
            return 1;
      }
        return 0;
    }
}
