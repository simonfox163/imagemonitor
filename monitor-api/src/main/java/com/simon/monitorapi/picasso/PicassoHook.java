package com.simon.monitorapi.picasso;

import android.net.Uri;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by simon on 2021/4/15.
 */

public class PicassoHook {
    public static List<Transformation> process(Uri uri, List<Transformation> transformations, int resourceId, int targetWidth, int targetHeight){
        if(null == transformations){
            transformations = new ArrayList<>();
        }
        transformations.add(new PicassoTransformation(uri,resourceId,targetWidth,targetHeight));
        return transformations;
    }
}

