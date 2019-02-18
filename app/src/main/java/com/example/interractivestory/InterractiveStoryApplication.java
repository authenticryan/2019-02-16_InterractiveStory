package com.example.interractivestory;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

// Author Ryan Dsouza 
// Contact - authenticryanis@gmail.com  

public class InterractiveStoryApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Prevent orientation to Landscape. When orientation changes, the app destroys and creates new methods
        registerActivityLifecycleCallbacks(new ActivityLifecycleAdapter() {
            @Override
            public void onActivityCreated(Activity a, Bundle savedInstanceState) {
                a.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
            }
        });
    }
}
