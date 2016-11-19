package com.example.sharelp_utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;

public class DownloadApk extends Thread{

	private String appname;
	private String apkname;
	private ProgressDialog pbar;
	private Handler handler;
	private Context context;

	public DownloadApk(Context context,Handler handler,String appname,String apkname,ProgressDialog pbar) {
		this.appname=appname;
		this.pbar=pbar;
		this.handler=handler;
		this.context=context;
		this.apkname=apkname;

	}

	public void run() {
		HttpClient client = new DefaultHttpClient();
		HttpGet get = new HttpGet(Util_Const.APK);
		HttpResponse response;
		try {
			response = client.execute(get);
			HttpEntity entity = response.getEntity();
			long length = entity.getContentLength();
			InputStream is = entity.getContent();
			FileOutputStream fileOutputStream = null;
			if (is != null) {

				File file = new File(
						Environment.getExternalStorageDirectory(),
						apkname);
				fileOutputStream = new FileOutputStream(file);

				byte[] buf = new byte[1024];
				int ch = -1;
				int count = 0;
				while ((ch = is.read(buf)) != -1) {
					fileOutputStream.write(buf, 0, ch);
					count += ch;
					if (length > 0) {
					}
				}

			}
			fileOutputStream.flush();
			if (fileOutputStream != null) {
				fileOutputStream.close();
			}
			down();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	void down() {
		handler.post(new Runnable() {
			public void run() {
				pbar.cancel();
				update();
			}
		});

	}

	void update() {

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(Uri.fromFile(new File(Environment
				.getExternalStorageDirectory(),apkname)),
				"application/vnd.android.package-archive");
		context.startActivity(intent);
	}


}
