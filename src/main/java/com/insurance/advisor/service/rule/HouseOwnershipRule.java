package com.insurance.advisor.service.rule;

import com.insurance.advisor.model.House;
import com.insurance.advisor.model.InsurableData;
import com.insurance.advisor.model.OwnershipStatus;
import com.insurance.advisor.service.exception.IneligibilityException;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class HouseOwnershipRule implements InsuranceRule{

    private InsurableData data;

    @Override
    public int execute() throws IneligibilityException {
        for(House house:data.getHouses()){
            if(house.getOwnership_status() == OwnershipStatus.mortgaged){
                return 1;
            }
        }
        return 0;
    }
}
