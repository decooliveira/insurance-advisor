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
public class DisabilityInsurance implements Insurance {

    private int points;
    private String score;
    int id;

    public static DisabilityInsurance of(int points, int id) {
        return new DisabilityInsurance(points, Score.get(points).getName(), id);
    }
}
