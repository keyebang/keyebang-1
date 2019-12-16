package com.shg.keyebang.aatools;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import com.shg.keyebang.MyApplication;

import androidx.annotation.NonNull;

public class DisplayUtil {

    private static float nonCompatDensity;
    private static float nonCompatScaleDensity;

    public static void setCustomDensity(@NonNull Activity activity, @NonNull Application application){
        final DisplayMetrics appDisplayMetrics = application.getResources().getDisplayMetrics();
        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();

        if(nonCompatDensity == 0){
            nonCompatDensity = appDisplayMetrics.density;
            nonCompatScaleDensity = appDisplayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(@NonNull Configuration configuration) {
                    if(configuration != null && configuration.fontScale > 0){
                        nonCompatScaleDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }
                @Override
                public void onLowMemory() {
                }
            });
        }

        final float targetDensity = appDisplayMetrics.widthPixels/360;
        final float targetScaledDensity = targetDensity * (nonCompatScaleDensity / nonCompatDensity);
        final int targetDensityDpi = (int)(160 * targetDensity);

        appDisplayMetrics.density =  targetDensity;
        appDisplayMetrics.scaledDensity = targetScaledDensity;
        appDisplayMetrics.densityDpi = targetDensityDpi;

        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDensityDpi;
    }

    public static int pxTodp(int px){
        final DisplayMetrics displayMetrics = MyApplication.getContext().getResources().getDisplayMetrics();
        int dp = px * (int)displayMetrics.density;
        return dp;
    }

    public static float pxTodp(float px){
        final DisplayMetrics displayMetrics = MyApplication.getContext().getResources().getDisplayMetrics();
        float dp = px * (float) displayMetrics.density;
        return dp;
    }

    public static int dpTopx(int dp){
        final DisplayMetrics displayMetrics = MyApplication.getContext().getResources().getDisplayMetrics();
        float px = (float) dp * displayMetrics.density;
        return (int)px;
    }
}
