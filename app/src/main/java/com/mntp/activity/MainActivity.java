package com.mntp.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.LinearLayout;

import com.example.administrator.afeimntp.R;
import com.mntp.Presenter.BasicPresenter;
import com.mntp.view.AFeiWaterfall;
import com.xuan.xwaterfall.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {

    String html = null;

    private BasicPresenter bP = null;

    private LinearLayout line_imgs;

    private AFeiWaterfall aFeiWaterfall;

    private List<String> imgStrs = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initalizeView();
        setData();
    }

    /**
     * 初始化控件
     */
    private void initalizeView() {
        line_imgs = (LinearLayout) findViewById(R.id.line_imgs);
        bP = new BasicPresenter();
        aFeiWaterfall = new AFeiWaterfall(this, imgStrs, bP);
        line_imgs.addView(aFeiWaterfall);
    }

    /**
     * 设置控件数据或事件
     */
    private void setData() {
        bP.getByTagImgs(
            list -> {
                imgStrs.addAll((List<String>) list);
                aFeiWaterfall.notifyDataSetChanged();
            },
            () -> {
                ToastUtil.show(MainActivity.this, "分析网页失败");
            }
        );
    }
}
