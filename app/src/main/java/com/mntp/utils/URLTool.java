package com.mntp.utils;

import android.util.Log;

/**
 * Created by AFei on 2015/9/1.
 * 获取网址
 */
public class URLTool {
    private static final String HEAD_URL="http://www.dbmeinv.com/dbgroup/show.htm";
    private static final String CID_HEAD="?cid=";
    private static final String PAGES_HEAD="&pager_offset=";


    public static final int  CID_SUOYOU=0;//所有
    public static final int  CID_DAXIONG=2;//大胸妹
    public static final int  CID_MEITUI=3;//美腿控
    public static final int  CID_QINGXIN=4;//小清新
    public static final int  CID_ZHAHUI=5;//大杂烩
    public static final int  CID_QIAOTUN=6;//小翘臀
    public static final int  CID_SIWA=7;//黑丝袜

    public static int CID=0;
    private static int FORMERLY_CID=-1;//上一次的CID
    private static int PAGES=1;


    /**
     * 获取URl页面（默认在原来的基础页数上加 1）
     */
    public static String getUrl(){
        if(FORMERLY_CID!=-1&&FORMERLY_CID!=CID){
            PAGES=1;
        }
//        Log.e("URL",HEAD_URL+CID_HEAD+CID+PAGES_HEAD+PAGES);
        return HEAD_URL+CID_HEAD+CID+PAGES_HEAD+PAGES;
    }
    public static void pagesAdd(){
        PAGES++;
    }
}
