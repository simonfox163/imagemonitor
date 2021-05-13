package com.simon.imagemonitor;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;


import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private ImageView mGlideView;
    private ImageView mPicaView;
    private SimpleDraweeView mFresView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        setGlidSrc();
        setPicasso();
        setFres();
    }

    @SuppressLint("WrongViewCast")
    private void initView(){
        mGlideView = findViewById(R.id.iv_glide);
        mPicaView = findViewById(R.id.iv_picasso);
        mFresView = (SimpleDraweeView)findViewById(R.id.iv_fres);
    }

    private void setGlidSrc() {
//        Glide.with(this).load(R.drawable.ic_test_two).into(mGlideView);
        Glide.with(this).load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fattach.bbs.miui.com%2Fforum%2F201312%2F31%2F111859myvyiivetyftfz2n.jpg&refer=http%3A%2F%2Fattach.bbs.miui.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1622016665&t=b206eda24de25d99b8983f055c5a35c1").into(mGlideView);
    }


    private void setPicasso() {
        Picasso.get().load(R.drawable.ic_test_four).into(mPicaView);
    }

    private void setFres() {
        Uri uri = Uri.parse("http://img.netbian.com/file/2021/0420/00746e3496401bf08fbd3293f3a2f204.jpg");
        Uri uri2 = Uri.parse("res://"+"drawable/"+R.drawable.ic_test_three);
        mFresView.setImageURI(uri);
    }
}