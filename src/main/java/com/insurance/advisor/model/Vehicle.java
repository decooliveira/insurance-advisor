package com.insurance.advisor.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Positive;
import java.util.Calendar;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicle implements Insurable, AdditionalParam{

    private int id;
    @Positive(message="For vehicle year, please provide a value greater than zero")
    private int year;

    public int getAge(){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - year;
    }
}
