package com.example.sharelp_slidingmenu;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sharelp.R;
import com.example.sharelp.SharelpApplication;

public class CollectionActivity extends Activity {
	
	
	private String[] titles;
	private ListView lv_collection;
	private String username;
	private final String PATH="http://sharelp.ecs11.ectomcat.pw/tiezi";
	private SharelpApplication sharelpApplication;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_collection);
		sharelpApplication=(SharelpApplication) getApplication();
		lv_collection=(ListView) findViewById(R.id.lv_collection);
		judgeState();
		
	}

	private void judgeState() {

		if (sharelpApplication.isState()) {
			//ÒÑµÇÂ¼
			username=sharelpApplication.getSname();
			trans_get_Tiezi(username);
		}else {
			Toast.makeText(CollectionActivity.this, "ÄúÎ´µÇÂ¼»òË¢ÐÂ", 100).show();
		}
	}

	
	
	private void trans_get_Tiezi(final String _username) {

		new Thread(){
			public void run() {
				
				try {
					String name=_username;
					name="name="+name;
					byte [] buffer=name.getBytes("utf-8");
					
					URL url=new URL(PATH);
					HttpURLConnection connection=(HttpURLConnection) url.openConnection();
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
					connection.setRequestMethod("POST");
					connection.setDoOutput(true);
					
					OutputStream os=connection.getOutputStream();
					os.write(buffer);
					os.flush();
					
					if (connection.getResponseCode()!=200) {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(CollectionActivity.this, "¶ÁÈ¡Ê§°Ü", 100).show();	
							}
						});
					}else {
						ObjectMapper om=new ObjectMapper();
						titles=om.readValue(connection.getInputStream(),String[].class);
						final int chang=titles.length;
						runOnUiThread(new Runnable() {
							public void run() {
								//Toast.makeText(CollectionActivity.this,"uguj"+chang, 100).show();
								lv_collection.setAdapter(new ArrayAdapter<>(CollectionActivity.this, android.R.layout.simple_list_item_1, titles));	
								//Toast.makeText(CollectionActivity.this,"uguj"+titles[0], 100).show();	
							}
						});
					
						
					}
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (ProtocolException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			};
			
		}.start();
		
	}

}
