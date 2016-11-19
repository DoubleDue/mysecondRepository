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

import com.example.sharelp_slidingmenu.LoginActivity;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class Util_LoginAndSignin {
	
	
	
	public static void Util_Login(String username,String password,String url,Handler handler){
	
		LoginThread loginThread=new LoginThread(username, password,url, handler);
		loginThread.start();
	}
	
	public static void util_Signin(String username,String password,String sname,String url,Handler handler){
		
		SigninThread signinThread=new SigninThread(username, password, sname, url, handler);
		signinThread.start();
	}
	
}



/*
 * 注册线程
 */
class SigninThread extends Thread{
	
	String username;
	String password;
	String sname;
	String url;
	String value;
	int Status;
	Handler handler;
	HttpClient client;
	
	//构造方法
	public SigninThread(String username,String password,String sname,String url,Handler handler){
		this.username=username;
		this.password=password;
		this.sname=sname;
		this.url=url;
		this.handler=handler;
		client=new DefaultHttpClient();
	}
	
	@Override
	public void run() {
		HttpPost httpPost=new HttpPost(url);	
		try {
			List<BasicNameValuePair>list=new ArrayList<>();
			list.add(new BasicNameValuePair("sno",username));
			list.add(new BasicNameValuePair("password", password));
			list.add(new BasicNameValuePair("sname",sname));
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
 * 登录线程
 */
class LoginThread extends Thread{
	
	String username;
	String password;
	String url;
	String value;
	int Status;
	Handler handler;
	HttpClient client;
	
	//构造方法
	public LoginThread(String username,String password,String url,Handler handler){
		this.username=username;
		this.password=password;
		this.url=url;
		this.handler=handler;
		client=new DefaultHttpClient();
	}
	
	@Override
	public void run() {
		HttpPost httpPost=new HttpPost(url);	
		try {
			List<BasicNameValuePair>list=new ArrayList<>();
			list.add(new BasicNameValuePair("sno",username));
			list.add(new BasicNameValuePair("password", password));
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