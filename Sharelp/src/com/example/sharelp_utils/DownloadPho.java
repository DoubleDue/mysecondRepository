package com.example.sharelp_utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.os.Handler;
import android.os.Message;
import android.widget.ImageView;

public class DownloadPho extends Thread{
	
	
	private Handler handler;
	private String url;
	private Bitmap bitmap;
 
	public DownloadPho(final Handler handler,final String url) {

		this.handler=handler;
		this.url=url;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		HttpGet get=new HttpGet(url);
		HttpClient client=new DefaultHttpClient();
		
			try {
				HttpResponse response=client.execute(get);
				HttpEntity entity=response.getEntity();
				byte[] data = EntityUtils.toByteArray(entity);
				final String string=data.toString();

				bitmap=BitmapFactory.decodeByteArray(data, 0, data.length);
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		
			Message msg=Message.obtain();
			msg.obj=bitmap;
			handler.sendMessage(msg);
			
			
	
	}
	
	
	/*private void readPic(final ImageView imageView,final String urString) {
		
		new Thread(){
			public void run() {
				HttpGet get=new HttpGet(urString);
				HttpClient client=new DefaultHttpClient();
				try {
					HttpResponse response=client.execute(get);
					HttpEntity entity=response.getEntity();
					byte[] data = EntityUtils.toByteArray(entity);
					final String string=data.toString();
					
					final Bitmap bitmap=BitmapFactory.decodeByteArray(data, 0, data.length);
					
					Message msg=Message.obtain();
					msg.arg1=Status;
					msg.obj=value;
					handler.sendMessage(msg);
					
					
					runOnUiThread(new Runnable() {
						
						public void run() {
						
							try {
								imageView.setImageBitmap(bitmap);
								ExifInterface exifInterface;
								exifInterface = new ExifInterface(string);
								String string2=exifInterface.getAttribute(ExifInterface.TAG_IMAGE_WIDTH);
								exifInterface.setAttribute(ExifInterface.TAG_IMAGE_WIDTH, "50");
								exifInterface.saveAttributes();
								
								String string3=exifInterface.getAttribute(ExifInterface.TAG_IMAGE_WIDTH);
							} catch (IOException e) {
								e.printStackTrace();
							}
							
							imageView.setImageBitmap(bitmap);
						}
					});
					
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
	}*/


}
