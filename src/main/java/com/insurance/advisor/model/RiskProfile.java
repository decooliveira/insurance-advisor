package com.insurance.advisor.model;

import com.insurance.advisor.model.insurance.Insurance;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RiskProfile {

    private List<Insurance> auto;
    private String disability;
    private List<Insurance> home;
    private String life;
}
