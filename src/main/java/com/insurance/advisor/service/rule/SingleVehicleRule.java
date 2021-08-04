package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.model.Vehicle;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

import java.util.List;
import static java.util.Objects.nonNull;

@AllArgsConstructor
public class SingleVehicleRule implements InsuranceRule{
    private InsurableData data;

    @Override
    public int execute() throws IneligibilityException {
        List<Vehicle> vehicles = data.getVehicles();
        if(nonNull(vehicles) && vehicles.size() == 1) {
            return 1;
        }
        return 0;
    }
}
