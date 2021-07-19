package com.insurance.advisor.controller;

import com.insurance.advisor.model.PersonalInformation;
import com.insurance.advisor.model.RiskProfile;
import com.insurance.advisor.service.RiskCalculator;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
@AllArgsConstructor
@Validated
public class AdvisorController {

    private RiskCalculator riskCalculator;


    @PostMapping("calculate")
    @ResponseBody
    public ResponseEntity<RiskProfile> calculate(@Valid @RequestBody PersonalInformation personalInformation) {

        RiskProfile riskProfile = riskCalculator.run(personalInformation);
        return new ResponseEntity<>(riskProfile, HttpStatus.OK);
    }
}
