package com.ivy.baseproject.test.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.cg.baseproject.utils.android.ResourceUtils;
import com.cg.baseproject.utils.android.ToastUtils;
import com.cg.baseproject.view.nicespinner.NiceSpinner;
import com.cg.baseproject.view.searchview.ICallBack;
import com.cg.baseproject.view.searchview.SearchView;
import com.cg.baseproject.view.searchview.bCallBack;
import com.ivy.baseproject.test.R;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class UiWidgetActivity extends AppCompatActivity {
    private SearchView mSearchView;
    private NiceSpinner mNiceSpinner;

    List<String> dataset  = new LinkedList<>(Arrays.asList("北京市", "东城区", "西城区", "朝阳区", "海淀区", "丰台区", "石景山区", "房山区", "昌平区", "顺义区", "通州区"));
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ui_widget);
        initView();
        setNiceSPinner();
    }

    private void initView(){
        mNiceSpinner = (NiceSpinner) findViewById(R.id.niceSpinner);
    }
    private void setNiceSPinner(){
        mNiceSpinner.setBackgroundResource(R.drawable.shape_spinner_radius);
        mNiceSpinner.setTextSize(ResourceUtils.getDimen(R.dimen.px11));
        mNiceSpinner.setTextColor(ResourceUtils.getColor(R.color.colorTextQueryIndexValue));
        mNiceSpinner.attachDataSource(dataset);
        mNiceSpinner.addOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ToastUtils.showToast(dataset.get(position));
            }
        });
    }
    
    
    private void seachButton(){
        // 3. 绑定组件
//        searchView = (SearchView) findViewById(R.id.search_view);

        // 4. 设置点击键盘上的搜索按键后的操作（通过回调接口）
        // 参数 = 搜索框输入的内容
        mSearchView.setOnClickSearch(new ICallBack() {
            @Override
            public void SearchAciton(String string) {
                System.out.println("我收到了" + string);
            }
        });

        // 5. 设置点击返回按键后的操作（通过回调接口）
        mSearchView.setOnClickBack(new bCallBack() {
            @Override
            public void BackAciton() {
                finish();
            }
        });
    }

}
