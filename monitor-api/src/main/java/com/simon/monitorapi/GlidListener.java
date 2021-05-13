package com.simon.monitorapi;

import android.util.Log;

import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import androidx.annotation.Nullable;

/**
 * Created by simon on 2021/3/10.
 */

public class GlidListener<R> implements RequestListener<R>{
    @Override
    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<R> target, boolean isFirstResource) {
        Log.d("Glid-","onLoadFailed");
        return false;
    }

    @Override
    public boolean onResourceReady(R resource, Object model, Target<R> target, DataSource dataSource, boolean isFirstResource)  {
        Log.d("Glid-","onResourceReady");
        return false;
    }
}
