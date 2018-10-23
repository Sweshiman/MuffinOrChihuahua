package com.ixdproject.madam.backend;

public class MufChiValues {

    private int fluffinessValue = 0;
    private int roundnessValue = 0;
    private int colorValue = 0;

    public MufChiValues(int fluffinessValue, int roundnessValue, int colorValue) {
        this.fluffinessValue = fluffinessValue;
        this.roundnessValue = roundnessValue;
        this.colorValue = colorValue;
    }

    public boolean isCorrect(MufChiValues correctValues) {
        int dogginess = this.fluffinessValue * correctValues.fluffinessValue
                + this.roundnessValue * correctValues.roundnessValue
                + this.colorValue * correctValues.colorValue;

        return dogginess >= .6;
    }

    public int getFluffinessValue() {
        return fluffinessValue;
    }

    public int getRoundnessValue() {
        return roundnessValue;
    }

    public int getColorValue() {
        return colorValue;
    }

    public void setFluffinessValue(int fluffynessValue) {
        this.fluffinessValue = fluffynessValue;
    }

    public void setRoundnessValue(int roundnessValue) {
        this.roundnessValue = roundnessValue;
    }

    public void setColorValue(int colorValue) {
        this.colorValue = colorValue;
    }
}
