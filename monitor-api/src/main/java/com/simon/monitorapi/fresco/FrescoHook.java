package com.simon.monitorapi.fresco;

import android.net.Uri;

import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.Postprocessor;

/**
 * Created by simon on 2021/4/15.
 */

public class FrescoHook {
    public static Postprocessor process(Uri uri, Postprocessor postprocessor, ResizeOptions resizeOptions){
        return new FrescoProcessor(postprocessor,uri,resizeOptions);
    }
}
