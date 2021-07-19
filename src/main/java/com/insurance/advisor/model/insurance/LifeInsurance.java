package com.insurance.advisor.model.insurance;

import com.insurance.advisor.model.Score;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LifeInsurance implements Insurance {

    private int points;
    private String score;

    public static LifeInsurance of(int points){
        return new LifeInsurance(points, Score.get(points).getName());
    }
}
