package com.insurance.advisor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalInformation {

    @PositiveOrZero(message = "For age, please provide a value greater or equal to zero")
    private int age;

    @PositiveOrZero(message="For dependents, please provide a value greater or equal to zero")
    private int dependents;
    private House house;

    @PositiveOrZero(message="For income, please provide a value greater or equal to zero")
    private int income;

    private MaritalStatus marital_status;

    @Size(min = 3,max=3, message="Please provide three values for the risk questions")
    private List<Integer> risk_questions;

    @Valid
    private Vehicle vehicle;

}
