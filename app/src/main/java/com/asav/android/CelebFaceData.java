package com.asav.android;

import com.asav.android.db.ClassifierResult;

import java.io.Serializable;

public class CelebFaceData implements ClassifierResult, Serializable {
    public float blackHair = 0; // 8
    public float blondHair = 0; // 9
    public float brownHair = 0; // 11
    public float grayHair = 0; // 17
    public float bald = 0; // 4
    public float wavyHair = 0;   //33
    public float straightHair = 0; // 32
    public float eyeglasses = 0; // 15
    public float mustache = 0;  // 22
    public float noBeard = 0;  // 24
    public float smiling = 0;   // 31
    public float wearingEarrings = 0; //34
    public float wearingHat = 0; //35
    public float[] features = null;

    public CelebFaceData() {
    }

    public CelebFaceData(float bald,
                         float blackHair,
                         float blondHair,
                         float brownHair,
                         float eyeglasses,
                         float grayHair,
                         float mustache,
                         float noBeard,
                         float smiling,
                         float straightHair,
                         float wavyHair,
                         float wearingEarrings,
                         float wearingHat) {
        this.bald = bald;
        this.blackHair = blackHair;
        this.blondHair = blondHair;
        this.brownHair = brownHair;
        this.eyeglasses = eyeglasses;
        this.grayHair = grayHair;
        this.mustache = mustache;
        this.noBeard = noBeard;
        this.smiling = smiling;
        this.straightHair = straightHair;
        this.wavyHair = wavyHair;
        this.wearingEarrings = wearingEarrings;
        this.wearingHat = wearingHat;
//        this.features = new float[features.length];
//        System.arraycopy(features, 0, this.features, 0, features.length);
    }

    public String whichHair() {
        String result = "";
        if (isTrue(this.straightHair, null)) {
            result += " straight";
        } else if (isTrue(this.wavyHair, null)) {
            result += " wavy";
        } else if (isTrue(this.bald, null)) {
            result += " bald";
        }
        if (isTrue(this.blackHair, null)) {
            result += " black";
        } else if (isTrue(this.blondHair, null)) {
            result += " blond";
        } else if (isTrue(this.grayHair, null)) {
            result += " gray";
        } else if (isTrue(this.brownHair, null)) {
            result += " brown";
        }
        return result;
    }

    public boolean hasEyeglasses() {
        return isTrue(this.eyeglasses, null);
    }

    public boolean hasMustache() {
        return isTrue(this.mustache, null);
    }

    public boolean hasBeard() {
        return !isTrue(this.noBeard, null);
    }

    public boolean isSmiling() {
        return isTrue(this.smiling, null);
    }

    public boolean isWearingEarrings() {
        return isTrue(this.wearingEarrings, null);
    }

    public boolean isWearingHat() {
        return isTrue(this.wearingHat, null);
    }

    public boolean isTrue(double value, Double threshold) {
        threshold = threshold != null ? threshold : 0.5;
        return value >= threshold;
    }

    public String toString() {
        return String.format("hair=%s glasses=%s mustache=%s beard=%s smile=%s earrings=%s hat=%s",
                whichHair(), hasEyeglasses(), hasMustache(), hasBeard(), isSmiling(), isWearingEarrings(), isWearingHat());
    }

}
