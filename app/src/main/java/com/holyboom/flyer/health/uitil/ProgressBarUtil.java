package com.holyboom.flyer.health.uitil;

import android.content.Context;
import android.widget.ProgressBar;

/**
 * Created by flyer on 15/3/22.
 */
public class ProgressBarUtil {
    Context context;
    ProgressBar progressBar;
    public ProgressBarUtil(Context context){
        this.context = context;
        progressBar = new ProgressBar(context);
    }
}
