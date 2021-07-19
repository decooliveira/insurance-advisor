package com.insurance.advisor.model;

import lombok.Getter;

@Getter
public enum Score {

    REGULAR("regular"),
    ECONOMIC("economic"),
    RESPONSIBLE("responsible"),
    INELIGIBLE("ineligible");

    private String name;

    Score(String name) {
        this.name = name;
    }

    public static Score get(int points) {
        if (points <= 0) {
            return Score.ECONOMIC;
        } else if (points == 1 || points == 2) {
            return Score.REGULAR;
        } else if (points >= 3) {
            return Score.RESPONSIBLE;
        } else {
            return Score.INELIGIBLE;
        }
    }
}
