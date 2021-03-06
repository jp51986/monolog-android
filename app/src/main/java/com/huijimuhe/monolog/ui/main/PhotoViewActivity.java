package com.huijimuhe.monolog.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ImageView;

import com.huijimuhe.monolog.R;
import com.huijimuhe.monolog.core.AppContext;
import com.huijimuhe.monolog.ui.base.AbstractActivity;

import uk.co.senab.photoview.PhotoViewAttacher;

public class PhotoViewActivity extends AbstractActivity {

    ImageView mImageView;
    PhotoViewAttacher mAttacher;
    String mUrl;

    public static Intent newIntent(String url) {
        Intent intent = new Intent(AppContext.getInstance(), PhotoViewActivity.class);
        intent.putExtra("url", url);
        return intent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("url", mUrl);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            mUrl = savedInstanceState.getString("url");
        } else {
            mUrl = getIntent().getStringExtra("url");
        }
        //toolbar
        setContentView(R.layout.activity_photo_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
       // toolbar.setTitle("图片浏览");
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        // Any implementation of ImageView can be used!
        mImageView = (ImageView) findViewById(R.id.iv_photo);

        // Set the Drawable displayed
        AppContext.getInstance().loadImg(mImageView, mUrl);

        // Attach a PhotoViewAttacher, which takes care of all of the zooming functionality.
        mAttacher = new PhotoViewAttacher(mImageView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contact_list, menu);
        return true;
    }
}
