package com.mntp.utils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;

import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EncodingUtils;

public class BasicUtils {
	/**
	 * 判断空
	 * 
	 * @param rawData
	 * @return
	 */
	public static boolean isNotNull(String rawData) {
		if (null != rawData && !"".equals(rawData) && !"null".equals(rawData))
			return true;
		else
			return false;
	}

	/**
	 * 格式化数据
	 * 
	 * @param s
	 * @return
	 */
	public static String stringTrim(String s) {
		return isNotNull(s) ? s.trim() : s;
	}
	
	/**
	 * 如果服务器不支持中文路径的情况下需要转换url的编码。
	 * 
	 * @param string
	 * @return
	 */
	public static String encodeGB(String string) {
		// 转换中文编码
		String split[] = string.split("/");
		for (int i = 1; i < split.length; i++) {
			try {
				split[i] = URLEncoder.encode(split[i], "UTF-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
			split[0] = split[0] + "/" + split[i];
		}
		split[0] = split[0].replaceAll("\\+", "%20");// 处理空格
		split[0] = split[0].replaceAll("\\{", "%7B");
		split[0] = split[0].replaceAll("\\}", "%7D");
		split[0] = split[0].replaceAll("%3A", "\\:");
		return split[0];
	}
	
	/**
	 * dip转像素
	 * 
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}

	/**
	 * 像素转dip
	 * 
	 * @param context
	 * @param pxValue
	 * @return
	 */
	public static int px2dip(Context context, float pxValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (pxValue / scale + 0.5f);
	}
	  /** 
     * 将px值转换为sp值，保证文字大小不变 
     *  
     * @param pxValue 
     * @param fontScale 
     *            （DisplayMetrics类中属性scaledDensity） 
     * @return 
     */ 
    public static int px2sp(Context context, float pxValue) {  
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (pxValue / fontScale + 0.5f);  
    }  
    /** 
     * 将sp值转换为px值，保证文字大小不变 
     *  
     * @param spValue 
     * @param fontScale 
     *            （DisplayMetrics类中属性scaledDensity） 
     * @return 
     */ 
    public static int sp2px(Context context, float spValue) {  
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
        return (int) (spValue * fontScale + 0.5f);  
    }  

	/**
	 * 获取当前时间
	 */
	public static String getNowTime() {
		String time = null;
		Date now = new Date();
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		time = temp.format(now);
		return time;
	}
	/**
	 * 获取当前日期
	 */
	public static String getNowDate() {
		String time = null;
		Date now = new Date();
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd");
		time = temp.format(now);
		return time;
	}
	/**
	 * 获取3天前的日期
	 */
	public static String get3DaysAgoDate() {
		Calendar cal = Calendar.getInstance();
		String time = null;
		SimpleDateFormat temp = new SimpleDateFormat("yyyy-MM-dd");
		cal.setTime(new Date());
		cal.set(Calendar.DATE, cal.get(Calendar.DATE) -3);
		time = temp.format(cal.getTime());
		return time;
	}

	public static String getHtmlString(String urlString) {
		try {
			URL url = new URL(urlString);
			URLConnection ucon = url.openConnection();
			InputStream instr = ucon.getInputStream();
			BufferedInputStream bis = new BufferedInputStream(instr);
			ByteArrayBuffer baf = new ByteArrayBuffer(500);
			int current = 0;
			while ((current = bis.read()) != -1) {
				baf.append((byte) current);
			}
			return EncodingUtils.getString(baf.toByteArray(), "UTF-8");
		} catch (Exception e) {
			return "";
		}
	}


}
