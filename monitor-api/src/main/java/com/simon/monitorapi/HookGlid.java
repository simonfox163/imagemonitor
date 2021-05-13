package com.simon.monitorapi;

import android.util.Log;

import com.bumptech.glide.request.RequestListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 2021/3/10.
 */

public class HookGlid {

    public static List<RequestListener> process(List<RequestListener> requestListener){
        Log.d("Glid-","HookGlid");
        if(requestListener == null ){
            requestListener = new ArrayList<>();
        }
        requestListener.add(new GlidListener());
        return requestListener;
    }
}
