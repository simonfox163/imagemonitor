package com.simon.monitorapi.picasso;

import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Log;

import com.squareup.picasso.Transformation;

/**
 * Created by simon on 2021/4/15.
 */

public class PicassoTransformation implements Transformation {

    private String mUri;
    private int targetWidth;
    private int targetHeight;

    public PicassoTransformation(Uri uri, int resourceId, int targetWidth, int targetHeight){
        if(uri == null){
            mUri = String.valueOf(resourceId);
        }else{
            mUri = uri.toString();
        }
        this.targetWidth=targetWidth;
        this.targetHeight=targetHeight;
    }

    @Override
    public Bitmap transform(Bitmap source) {
//        return LargeImageManager.getInstance().transform(mUri,source,"Picasso",targetWidth,targetHeight);
        Log.d("Hook_Picasso ","bitmap " + mUri.toString()  + "width: " + targetWidth + "height: " + targetHeight );
        return  source;
    }

    @Override
    public String key() {
        return "LargeImage&Picasso&PicassoLargeImageTransformation";
    }
}
