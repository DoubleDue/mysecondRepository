package com.example.sharelp_sharelp;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharelp.R;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp.R.id;
import com.example.sharelp.R.layout;
import com.example.sharelp_entity.Entity_Share;
import com.example.sharelp_utils.Util_Const;

/**
 * 要传到数据库的内容有：name,title，content,photo
 * @author Administrator
 *
 */
public class Sharelp_Share_Edit_Activity extends Activity implements OnClickListener{

	private SharelpApplication sharelpApplication;
	private EditText et_share_title,et_share_content;
	private Button btn_share_submit;
	private String name,title,content,photo;
	private Entity_Share entity_Share;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		setContentView(R.layout.activity_edit_share);
		initWidget();
		btn_share_submit.setOnClickListener(this);
	}



	private void initWidget() {
		et_share_title=(EditText) findViewById(R.id.et_share_title);
		et_share_content=(EditText) findViewById(R.id.et_share_content);
		btn_share_submit=(Button) findViewById(R.id.btn_share_submit);
		sharelpApplication=(SharelpApplication) getApplication();
		
	}



	public void onClick(View arg0) {
		name=sharelpApplication.getSname();
		title=et_share_title.getText().toString();
		content=et_share_content.getText().toString();
		
	
		readNet(Util_Const.EDITSHARE,name,title,content);
	}



	private void readNet(final String url, final String name, final String title, final String content) {
		new Thread(){
			public void run() {
				try {
					HttpPost post=new HttpPost(url);
					List<BasicNameValuePair>list=new ArrayList<>();
					list.add(new BasicNameValuePair("name", name));
					list.add(new BasicNameValuePair("title", title));
					list.add(new BasicNameValuePair("content", content));
					post.setEntity(new UrlEncodedFormEntity(list,"utf-8"));
					HttpClient client=new DefaultHttpClient();
					HttpResponse response=client.execute(post);
					final int ISLINE=response.getStatusLine().getStatusCode();
					
					runOnUiThread(new Runnable() {
						public void run() {
							if (ISLINE==200) {
								
								Toast.makeText(Sharelp_Share_Edit_Activity.this, "发表成功", 1000).show();
								Intent intent=new Intent(Sharelp_Share_Edit_Activity.this, Sharelp_Share_Activity.class);
								Sharelp_Share_Edit_Activity.this.finish();
								startActivity(intent);
								
								return;
							}
							else {
								Toast.makeText(Sharelp_Share_Edit_Activity.this, "发表失败", 1000).show();
								return;
							}
						}
					});
				
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
	}


	

}






