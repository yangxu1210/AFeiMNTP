package com.mntp.model;

import com.mntp.utils.BasicUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by afei on 2015/11/4.
 */
public class HtmlModel {
    /**
     * 获取网页中的img标签链接
     */
    public Observable<List<String>> getImgUrl(final String url){
       return Observable.create((Subscriber<? super List<String>> sub) ->{
                        if (!BasicUtils.isNotNull(url))
                            return;
                        Document html= null;
                        try {
                            html = Jsoup.connect(url).timeout(10000).post();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Elements urls = html.select("img[src$=.jpg]");
                        List<String> imgUrls=new ArrayList<String>();
                        for(Element eUrl :urls){
                            String src=eUrl.attr("src");
                            if(BasicUtils.isNotNull(src))
                                imgUrls.add(src);
                        }
                        sub.onNext(imgUrls);
                        sub.onCompleted();
                }).subscribeOn(Schedulers.io());
    }
}
