package com.simon.monitorapi.fresco;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.util.Log;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import com.facebook.imagepipeline.request.Postprocessor;
import static com.facebook.imagepipeline.request.BasePostprocessor.FALLBACK_BITMAP_CONFIGURATION;

/**
 * Created by simon on 2021/4/15.
 */

public class FrescoProcessor implements Postprocessor {

    private Postprocessor mOriginProcessor;
    private Uri mUri;
    private ResizeOptions mResizeOptions;
    public FrescoProcessor(Postprocessor mOriginProcessor, Uri mUri, ResizeOptions resizeOptions) {
        this.mOriginProcessor = mOriginProcessor;
        this.mUri = mUri;
        this.mResizeOptions = resizeOptions;
    }

    @Override
    public CloseableReference<Bitmap> process(Bitmap sourceBitmap, PlatformBitmapFactory bitmapFactory) {


        int width = 0;
        int height = 0;
        if(null != mResizeOptions){
            width = mResizeOptions.width;
            height = mResizeOptions.height;
        }
//        sourceBitmap = LargeImageManager.getInstance().transform(mUri.toString(),sourceBitmap,"Fresco",width,height);
//        Log.d("Hook_Fresco_","CloseableReference width:" + width + " heigth " + height);
        Log.d("Hook_Fresco_","CloseableReference width:" + sourceBitmap.getWidth() + " heigth " + sourceBitmap.getHeight());

        if(null != mOriginProcessor){
            return mOriginProcessor.process(sourceBitmap,bitmapFactory);
        }
        final Bitmap.Config sourceBitmapConfig = sourceBitmap.getConfig();
        CloseableReference<Bitmap> destBitmapRef =
                bitmapFactory.createBitmapInternal(
                        sourceBitmap.getWidth(),
                        sourceBitmap.getHeight(),
                        sourceBitmapConfig != null ? sourceBitmapConfig : FALLBACK_BITMAP_CONFIGURATION);
        try {
            process(destBitmapRef.get(),sourceBitmap);
            return CloseableReference.cloneOrNull(destBitmapRef);
        }finally {
            CloseableReference.closeSafely(destBitmapRef);
        }
    }

    public void process(Bitmap destBitmap, Bitmap sourceBitmap) {
        internalCopyBitmap(destBitmap, sourceBitmap);
        process(destBitmap);
    }

    public void process(Bitmap bitmap) {
    }

    private static void internalCopyBitmap(Bitmap destBitmap,Bitmap sourceBitmap){
        if(destBitmap.getConfig() == sourceBitmap.getConfig()){
            Bitmaps.copyBitmap(destBitmap, sourceBitmap);
        }else{
            Canvas canvas = new Canvas(destBitmap);
            canvas.drawBitmap(sourceBitmap,0,0,null);
        }
    }

    @Override
    public String getName() {
        if(null != mOriginProcessor){
            return mOriginProcessor.getName();
        }
        return "LargeImage&Fresco&FrescoLargeImageProcessor";
    }

    @Override
    public CacheKey getPostprocessorCacheKey() {
        if(null != mOriginProcessor){
            return mOriginProcessor.getPostprocessorCacheKey();
        }
        return null;
    }
}
