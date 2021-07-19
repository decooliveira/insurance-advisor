package com.insurance.advisor.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    private int age;
    private int dependents;
    private int income;
    private MaritalStatus maritalStatus;
}
