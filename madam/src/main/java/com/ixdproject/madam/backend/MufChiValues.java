package com.ixdproject.madam.backend;

public class MufChiValues {

    private int fluffinessValue;
    private int roundnessValue;
    private int colorValue;

    public MufChiValues(int fluffinessValue, int roundnessValue, int colorValue) {
        this.fluffinessValue = fluffinessValue;
        this.roundnessValue = roundnessValue;
        this.colorValue = colorValue;
    }

    public boolean isCorrect(MufChiValues correctValues) {
        int dogginess = this.fluffinessValue * correctValues.fluffinessValue
                + this.roundnessValue * correctValues.roundnessValue
                + this.colorValue * correctValues.colorValue;
        return dogginess >= 6000/*.6*/;
    }

    public void setFluffinessValue(int fluffinessValue) {
        this.fluffinessValue = fluffinessValue;
    }

    public void setRoundnessValue(int roundnessValue) {
        this.roundnessValue = roundnessValue;
    }

    public void setColorValue(int colorValue) {
        this.colorValue = colorValue;
    }
}
