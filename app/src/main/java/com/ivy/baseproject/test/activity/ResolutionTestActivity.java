package com.ivy.baseproject.test.activity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.ivy.baseproject.test.R;

public class ResolutionTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_resolution_test);
        TextView argumentsTv = (TextView) findViewById(R.id.metrics_argus);
        StringBuilder sb = new StringBuilder();
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        int widthDpi = (int) (metrics.widthPixels / metrics.density);
        int heightDpi = (int) (metrics.heightPixels / metrics.density);

        appendArguments(sb, "screen metrics: \n");

        appendArguments(sb, "屏幕Dpi: " + metrics.densityDpi);
        appendArguments(sb, "逻辑密度: " + metrics.density);
        appendArguments(sb, "缩放密度: " + metrics.scaledDensity);
        appendArguments(sb, "显示屏幕宽度: " + metrics.widthPixels);
        appendArguments(sb, "显示屏幕高度: " + metrics.heightPixels);
        appendArguments(sb, "width dpi: " + widthDpi);
        appendArguments(sb, "height dpi: " + heightDpi);

        if (Build.VERSION.SDK_INT >= 17) {
            sb.append("\n");
            getWindowManager().getDefaultDisplay().getRealMetrics(metrics);
            appendArguments(sb, "真实屏幕宽度: " + metrics.widthPixels);
            appendArguments(sb, "真实屏幕高度: " + metrics.heightPixels);
        }

        argumentsTv.setText(sb.toString());
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (Build.VERSION.SDK_INT >= 19) {
            //在大于19版本时隐藏底部导航栏，View.SYSTEM_UI_FLAG_IMMERSIVE 需要 >= 19
            Configuration configuration = getResources().getConfiguration();
            if (configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                View decorView = getWindow().getDecorView();
                int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
                decorView.setSystemUiVisibility(uiOptions);
            }
        }
    }

    private void appendArguments(StringBuilder sb, String argument) {
        sb.append(argument).append("\n");
    }

//    public void toCalculatePage(View view) {
//        startActivity(new Intent(this, CalculateActivity.class));
//    }
//
//    public void toImmersivePage(View view) {
//        startActivity(new Intent(this, ImmersiveActivity.class));
//    }
}
