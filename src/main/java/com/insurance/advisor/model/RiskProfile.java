package com.insurance.advisor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RiskProfile {

    private String auto;
    private String disability;
    private String home;
    private String life;
}
