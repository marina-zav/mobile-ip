package com.asav.android;

import android.content.Context;
import android.util.Log;

import com.asav.android.db.ClassifierResult;

import org.tensorflow.lite.support.common.TensorOperator;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Model from https://github.com/kartikbatra056/CelebFaces-Attributes-Prediction
public class CelebTfLiteClassifier extends TfLiteClassifier {

    /**
     * Tag for the {@link Log}.
     */
    private static final String TAG = "CelebTfLite";

    private static final String MODEL_FILE = "celeb_model_3.tflite";

    public CelebTfLiteClassifier(final Context context) throws IOException {
        super(context, MODEL_FILE);

    }

    protected void addPixelValue(int val) {
        imgData.putFloat((((val >> 16) & 0xFF)  - 129.171f) / 67.42f);
        imgData.putFloat((((val >> 8) & 0xFF) - 108.579f) / 62.118f);
        imgData.putFloat(((val & 0xFF)  - 97.7f) / 61.12f);
    }

    @Override
    protected TensorOperator getPreprocessNormalizeOp() {
        return null;
    }

    private static float sigmoid(float x) {
        float ex = Double.valueOf(Math.exp(-x)).floatValue();
        return 1 / (1 + ex);
    }

    protected ClassifierResult getResults(float[][][] outputs) {
        float bald = sigmoid(outputs[0][0][4]);
        float blackHair = sigmoid(outputs[0][0][8]);
        float blondHair = sigmoid(outputs[0][0][9]);
        float brownHair = sigmoid(outputs[0][0][11]);
        float eyeglasses = sigmoid(outputs[0][0][15]);
        float grayHair = sigmoid(outputs[0][0][17]);
        float mustache = sigmoid(outputs[0][0][22]);
        float noBeard = sigmoid(outputs[0][0][24]);
        float smiling = sigmoid(outputs[0][0][31]);
        float straightHair = sigmoid(outputs[0][0][32]);
        float wavyHair = sigmoid(outputs[0][0][33]);
        float wearingEarrings = sigmoid(outputs[0][0][34]);
        float wearingHat = sigmoid(outputs[0][0][35]);

        String out_result = String.format("bald: %s, blackHair: %s, blondHair: %s, brownHair: %s, " +
                        "eyeglasses: %s, grayHair: %s, mustache: %s, noBeard: %s, smiling: %s, " +
                        "straightHair: %s, wavyHair: %s, wearingEarrings: %s, wearingHat: %s",
                bald, blackHair, blondHair, brownHair, eyeglasses, grayHair, mustache, noBeard, smiling, straightHair, wavyHair, wearingEarrings, wearingHat);
        Log.i(TAG, out_result);


        CelebFaceData res = new CelebFaceData(bald,
                blackHair,
                blondHair,
                brownHair,
                eyeglasses,
                grayHair,
                mustache,
                noBeard,
                smiling,
                straightHair,
                wavyHair,
                wearingEarrings,
                wearingHat);
        return res;
    }

}
