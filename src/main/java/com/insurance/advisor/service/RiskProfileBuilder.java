package com.insurance.advisor.service;

import com.insurance.advisor.model.*;
import com.insurance.advisor.model.insurance.AutoInsurance;
import com.insurance.advisor.model.insurance.Insurance;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RiskProfileBuilder {

    private InsuranceFactory factory;

//    public RiskProfile build(InsurableData data) {
//
//        List<String> autoInsurances = new ArrayList<>();
//        List<String> homeInsurances = new ArrayList<>();
//
//        for(Vehicle vehicle:data.getVehicles()){
//           String auto = factory.build(InsuranceType.AUTO, data).getScore();
//           autoInsurances.add(auto);
//        }
//
//        for(House house:data.getHouses()){
//            String home = factory.build(InsuranceType.HOME, data).getScore();
//            homeInsurances.add(home);
//        }
//        return RiskProfile.builder()
//                    .auto(autoInsurances)
//                    .disability(factory.build(InsuranceType.DISABILITY, data).getScore())
//                    .home(homeInsurances)
//                .life(factory.build(InsuranceType.LIFE, data).getScore())
//                .build();
//    }

}
