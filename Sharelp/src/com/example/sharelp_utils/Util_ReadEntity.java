package com.example.sharelp_utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;

import android.os.Handler;
import android.os.Message;

import com.example.sharelp_entity.Entity_Share;


/**
 * 用于根据地址和实体类型来获取实体
 * 利用handler通过msg.obj进行回送
 * @author Administrator
 *
 */
public class Util_ReadEntity {
	
	public static  <T> T OLink(final Handler handler,final Class<T> entityclass,final String url,final int time){
		
		new Thread(){
			public void run() {

				Message msg =Message.obtain();//取一个
				StringBuilder sb=new StringBuilder(url);
				try {
					URL url=new URL(sb.toString());
					HttpURLConnection conn=(HttpURLConnection)url.openConnection();
					conn.setReadTimeout(time);
					conn.setConnectTimeout(time);
					conn.setRequestMethod("GET");

					if(conn.getResponseCode()!=200){
					//连接失败
						msg.arg1=conn.getResponseCode();
					}
					else{

						ObjectMapper om=new ObjectMapper();
						msg.arg1=200;
						//msg.arg2=222;
						msg.obj=om.readValue(conn.getInputStream(), entityclass);
						handler.sendMessage(msg);

					}
				}
				catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};

		}.start();

	return null;
		
	}
	
	//通过1个参数，进行实体的获取；
	public static  <T> T OLinkParam1(final Handler handler,final Class<T> entityclass,
			final String url,final String param,final int time,final int dir){
		
		new Thread(){
			public void run() {
				
				Message msg =Message.obtain();//取一个
				try {
					String tag=param;
					tag="param="+tag;
					byte[] data=tag.getBytes("utf-8");
					
					
					if (dir==1) msg.arg2=1;//如果dir==1，则说明是我请求的队伍
					else msg.arg2=2;//如果dir==2，则说明是请求加入我的队
					
					StringBuilder sb=new StringBuilder(url);
					URL url=new URL(sb.toString());
					HttpURLConnection conn=(HttpURLConnection)url.openConnection();
					conn.setReadTimeout(time);
					conn.setConnectTimeout(time);
					conn.setRequestMethod("GET");
					conn.setDoOutput(true);
					OutputStream os=conn.getOutputStream();
					os.write(data);
					
					if(conn.getResponseCode()!=200){
					//连接失败
						msg.arg1=conn.getResponseCode();
					}
					else{
						ObjectMapper om=new ObjectMapper();
						msg.arg1=200;
						msg.obj=om.readValue(conn.getInputStream(), entityclass);
					}
					handler.sendMessage(msg);
				}
				catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};

		}.start();

	return null;
		
	}
	
	
	

	

}
