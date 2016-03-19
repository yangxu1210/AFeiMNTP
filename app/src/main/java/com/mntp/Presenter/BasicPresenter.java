package com.mntp.Presenter;

import com.mntp.incident.FailIncident;
import com.mntp.incident.SucceedIncident;
import com.mntp.model.HtmlModel;
import com.mntp.utils.URLTool;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by AFei on 2015/8/25.
 * 处理图片逻辑代码
 */
public class BasicPresenter {


    public void getByTagImgs(final SucceedIncident Succeed,final FailIncident fail){
        HtmlModel htmlModel=new HtmlModel();
        Observable<List<String>> observable=htmlModel.getImgUrl(URLTool.getUrl());
        observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    if (list.size() > 0) {
                        Succeed.onSucceed(list);
                    } else {
                        fail.onfail();
                    }
    });
    }

}
