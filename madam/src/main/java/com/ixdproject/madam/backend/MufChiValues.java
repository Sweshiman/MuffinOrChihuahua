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

    public boolean isDog(MufChiValues correctValues) {
        int dogginess = ((this.fluffinessValue / 55) - 8) * correctValues.fluffinessValue
                * ((this.roundnessValue / 55 ) - 8) * correctValues.roundnessValue
                * ((this.colorValue / 55) - 8) * correctValues.colorValue;
        System.out.println("FluffinessWeight: " + ((this.fluffinessValue / 55) - 8) +
                            "RoundnessWeight: " + ((this.roundnessValue / 55 ) - 8) +
                            "ColorWeight: " + ((this.colorValue / 55) - 8));
        System.out.println(dogginess);
        return dogginess >= 3000;
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

    public int getFluffinessValue() {
        return fluffinessValue;
    }

    public int getRoundnessValue() {
        return roundnessValue;
    }

    public int getColorValue() {
        return colorValue;
    }
}
