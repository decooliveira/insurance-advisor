package com.insurance.advisor.model.insurance;

import com.insurance.advisor.model.Score;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AutoInsurance implements Insurance{

    private int points;
    private String score;
    private int id;

    public static AutoInsurance of(int points, int id){
        return new AutoInsurance(points, Score.get(points).getName(),id);
    }
}
