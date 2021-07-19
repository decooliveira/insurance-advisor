package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.Constants;
import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.model.Vehicle;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

import static java.util.Objects.nonNull;
import static java.util.Objects.isNull;

@AllArgsConstructor
public class VehicleRule implements InsuranceRule {

    private InsurableData data;

    @Override
    public int execute() throws IneligibilityException {
        Vehicle vehicle = data.getVehicle();
        if(isNull(vehicle)){
            throw new IneligibilityException();
        }else if(nonNull(vehicle) && vehicle.getAge() <= Constants.VEHICLE_AGE) {
            return 1;
        }
        return 0;
    }
}
