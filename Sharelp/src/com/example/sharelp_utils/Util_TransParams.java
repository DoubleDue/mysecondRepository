package com.example.sharelp_utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.os.Handler;
import android.os.Message;

public class Util_TransParams {

	
	
	
	public static void Util_TransParam(String param1,String param2,String param3,String url,Handler handler){
	
		TransThread transThread=new TransThread(param1, param2,param3,url, handler);
		transThread.start();
	}
	
	public static void Util_TransParam(String param1,String param2,String url,Handler handler){
		
		TransThread2 transThread2=new TransThread2(param1, param2,url, handler);
		transThread2.start();
	}
	
	public static void Util_TransParam(String param1,String url,Handler handler){
		
		TransThread1 transThread1=new TransThread1(param1,url, handler);
		transThread1.start();
	}


}

/*
 * 1参数
 */
class TransThread1 extends Thread{
	
	String param1;
	String param2;
	String param3;
	String url;
	String value;
	int Status;
	Handler handler;
	HttpClient client;
	
	//构造方法
	public TransThread1(String param1,String url,Handler handler){
		this.param1=param1;
		this.url=url;
		this.handler=handler;
		client=new DefaultHttpClient();
	}
	
	@Override
	public void run() {
		HttpPost httpPost=new HttpPost(url);	
		try {
			List<BasicNameValuePair>list=new ArrayList<>();
			list.add(new BasicNameValuePair("param1",param1));
			httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
			HttpResponse response=client.execute(httpPost);
			
			value=EntityUtils.toString(response.getEntity());//entity从互联网中读取的数据      
			Status=response.getStatusLine().getStatusCode();//==200连接成功
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Message msg=Message.obtain();
		msg.arg1=Status;
		msg.obj=value;
		handler.sendMessage(msg);
		
		
	}
	
}



/*
 * 2参数
 */
class TransThread2 extends Thread{
	
	String param1;
	String param2;
	String url;
	String value;
	int Status;
	Handler handler;
	HttpClient client;
	
	//构造方法
	public TransThread2(String param1,String param2,String url,Handler handler){
		this.param1=param1;
		this.param2=param2;
		this.url=url;
		this.handler=handler;
		client=new DefaultHttpClient();
	}
	
	@Override
	public void run() {
		HttpPost httpPost=new HttpPost(url);	
		try {
			List<BasicNameValuePair>list=new ArrayList<>();
			list.add(new BasicNameValuePair("param1",param1));
			list.add(new BasicNameValuePair("param2", param2));
			httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
			HttpResponse response=client.execute(httpPost);
			
			value=EntityUtils.toString(response.getEntity());//entity从互联网中读取的数据      
			Status=response.getStatusLine().getStatusCode();//==200连接成功
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Message msg=Message.obtain();
		msg.arg1=Status;
		msg.arg2=202;
		msg.obj=value;
		handler.sendMessage(msg);
		
	}

}



/*
 * 3参数
 */
class TransThread extends Thread{
	
	String param1;
	String param2;
	String param3;
	String url;
	String value;
	int Status;
	Handler handler;
	HttpClient client;
	
	//构造方法
	public TransThread(String param1,String param2,String param3,String url,Handler handler){
		this.param1=param1;
		this.param2=param2;
		this.param3=param3;
		this.url=url;
		this.handler=handler;
		client=new DefaultHttpClient();
	}
	
	@Override
	public void run() {
		HttpPost httpPost=new HttpPost(url);	
		try {
			List<BasicNameValuePair>list=new ArrayList<>();
			list.add(new BasicNameValuePair("param1",param1));
			list.add(new BasicNameValuePair("param2", param2));
			list.add(new BasicNameValuePair("param3", param3));
			httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
			HttpResponse response=client.execute(httpPost);
			
			value=EntityUtils.toString(response.getEntity());//entity从互联网中读取的数据      
			Status=response.getStatusLine().getStatusCode();//==200连接成功
			
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Message msg=Message.obtain();
		msg.arg1=Status;
		msg.arg2=233;
		msg.obj=value;
		handler.sendMessage(msg);
		
	}
	
}
