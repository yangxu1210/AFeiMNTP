package com.mntp.application;

import android.app.Application;

import cn.bmob.v3.Bmob;

/**
 * Created by AFei on 2015/12/3.
 */
public class AFeiMNTPApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // 初始化 Bmob SDK
        Bmob.initialize(this, "67d2166c37031faed7f6cd5d1683fe2c");
    }
}
