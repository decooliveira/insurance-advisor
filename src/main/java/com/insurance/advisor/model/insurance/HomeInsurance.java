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
public class HomeInsurance implements Insurance {

    private int points;
    private String score;

    public static HomeInsurance of(int points){
        return new HomeInsurance(points, Score.get(points).getName());
    }
}
