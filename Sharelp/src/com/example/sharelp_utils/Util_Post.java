package com.example.sharelp_utils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * 待定封装的postString类
 * @author Administrator
 *
 */


public class Util_Post {
	
	public static <T> void PostString(String message,String url,Class<T> cls){
		PostStringThread postStringThread=new PostStringThread(message, url,cls);
		postStringThread.start();
	}
	

}


class PostStringThread<T> extends Thread{
	
	String message;
	String url;
	int status;
	HttpURLConnection connection;
	
	public <T> PostStringThread(String message,String url,Class<T> cls) {
		this.message=message;
		this.url=url;

	}
	
	@Override
	public void run() {
		
		try {
			byte[] data=message.getBytes("utf-8");
			URL urll=new URL(url);
			connection=(HttpURLConnection) urll.openConnection();
			connection.setConnectTimeout(5000);
			connection.setReadTimeout(5000);
			connection.setRequestMethod("POST");
			connection.setDoOutput(true);
			OutputStream os=connection.getOutputStream();
			os.write(data);
			os.flush();
			os.close();
			status=connection.getResponseCode();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
		if (status!=200) {
			
		}else {
			
			ObjectMapper om = new ObjectMapper();
		
			
			
			
		}
		
	}
	
}