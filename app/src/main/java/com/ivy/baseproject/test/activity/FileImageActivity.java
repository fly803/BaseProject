package com.ivy.baseproject.test.activity;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;

import com.cg.baseproject.utils.android.ScreenShotUtils;
import com.google.android.material.snackbar.Snackbar;
import com.ivy.baseproject.test.R;

public class FileImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_file_image);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ScreenShotUtils.getViewBitmapPath(FileImageActivity.this,view);
                ScreenShotUtils.getViewBitmapPath(FileImageActivity.this,view,"BaseProject","ScreenShot.png");
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            }
        });
    }

}
