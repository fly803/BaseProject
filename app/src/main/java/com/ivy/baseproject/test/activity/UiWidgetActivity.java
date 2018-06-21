package com.ivy.baseproject.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cg.baseproject.view.searchview.ICallBack;
import com.cg.baseproject.view.searchview.SearchView;
import com.cg.baseproject.view.searchview.bCallBack;
import com.ivy.baseproject.test.R;

public class UiWidgetActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_widget);
        seachButton();
    }

    SearchView searchView;
    private void seachButton(){
        // 3. 绑定组件
        searchView = (SearchView) findViewById(R.id.search_view);

        // 4. 设置点击键盘上的搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        searchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                System.out.println("我收到了" + string);
            }
        });

        // 5. 设置点击返回按键后的操作（通过回调接口）
        searchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });
    }

}
