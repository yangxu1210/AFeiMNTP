package com.mntp.view;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.administrator.afeimntp.R;
import com.mntp.Presenter.BasicPresenter;
import com.mntp.utils.URLTool;
import com.xuan.xwaterfall.XWaterFall;
import com.xuan.xwaterfall.util.ToastUtil;

import java.util.List;

/**
 * Created by AFei on 2015/9/7.
 */
public class AFeiWaterfall extends XWaterFall{

    private Context context;
    private List<String> imgs;
    private LayoutInflater inflater;
    private BasicPresenter bP;
    private static final int ITEM_IMG_TAG=0x0001;
    private static boolean REFRESH=true;

    public AFeiWaterfall(Context context, List<String> imgs,BasicPresenter bP) {
        super(context,3,0);//设置waterfall的context、列数、宽度
        // TODO Auto-generated constructor stub
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.imgs = imgs;
        this.bP=bP;
    }

    @Override
    public View getView(int position) {
        // TODO Auto-generated method stub
        View view = inflater.inflate(R.layout.imglist_item, null);
        return view;
    }

    @Override
    public int getContentChildCount() {
        // TODO Auto-generated method stub
        return imgs.size();
    }

    @Override
    public int getNewChildNum() {
        // TODO Auto-generated method stub
        return 12;
    }

    @Override
    public void onScrollToBottom() {
       if(REFRESH){
           REFRESH=false;
           URLTool.pagesAdd();
           bP.getByTagImgs(
                   list -> {
                       imgs.addAll((List<String>) list);
                       notifyDataSetChanged();
                   },
                   () -> {
                       ToastUtil.show(context, "分析网页失败");
                   }
           );
       }
    }

    @Override
    public Object getMark(int position) {
        // TODO Auto-generated method stub
        return imgs.get(position);
    }

    @Override
    protected void onRecycleView(int position, View view) {
        ImageView iv = (ImageView) view.findViewById(R.id.item_img);
        Glide.clear(iv);
        android.view.ViewGroup.LayoutParams params = iv.getLayoutParams();
        params.height = iv.getHeight();
        params.width = iv.getWidth();
        iv.setLayoutParams(params);
    }

    @Override
    protected void onResumeView(int position, View view) {
        ImageView ImageView=(ImageView)view.findViewById(R.id.item_img);
        Glide.with(context)
                .load(imgs.get(position))
//                .centerCrop()
                .placeholder(Color.parseColor("#87CEFA"))
//                .crossFade()
                .into(ImageView);
    }

    @Override
    public int getChildHeight(int position) {
        // TODO Auto-generated method stub
        return 0;
    }
}
