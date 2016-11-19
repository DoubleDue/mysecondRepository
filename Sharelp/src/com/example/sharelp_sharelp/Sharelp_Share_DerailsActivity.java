package com.example.sharelp_sharelp;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.codehaus.jackson.map.ObjectMapper;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sharelp.R;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp_adapter.AllEntity_Adapter;
import com.example.sharelp_entity.Entity_AllContent;
import com.example.sharelp_entity.Entity_Comments;
import com.example.sharelp_entity.Entity_Share;
import com.example.sharelp_entity.Entity_Shares;
import com.example.sharelp_slidingmenu.LoginActivity;
import com.example.sharelp_utils.RefreshableView;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.RefreshableView.PullToRefreshListener;

public class Sharelp_Share_DerailsActivity extends Activity implements OnClickListener{


	private  SharelpApplication sharelpApplication;

	private  String TITLE;

	private RefreshableView refreshableView;//刷新
	private ProgressBar progressBar;
	private ListView listView;
	private AllEntity_Adapter adapter;
	private File cache;//缓存
	private Entity_Comments[] entity_Comments;	
	private Handler handler;
	private ImageButton ib_zan,ib_input,ib_sendcomment;
	private EditText et_input;
	private Entity_Share entity_Share;



	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sharelp_share_derails);

		initData();
		initHandler();
		initWidget();
		Link();
		//initListener();
	}
	
	private void initListener() {//刷新操作

		refreshableView.setOnRefreshListener(new PullToRefreshListener() {
			@Override
			public void onRefresh() {
				try {
					Link();
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshableView.finishRefreshing();
			}
		}, 0);

	}



	
	private void initData() {

		cache = new File(Environment.getExternalStorageDirectory(), "cache");
		if(!cache.exists()) cache.mkdirs();//不存在则创建

		
	//	refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
		Bundle bundle = getIntent().getExtras();
		String [] shares=bundle .getString("share").split("&");

		TITLE=shares[2];
        entity_Share=new Entity_Share();
		entity_Share.setTitle(shares[2]);
		entity_Share.setName(shares[0]);
		entity_Share.setPhoto(shares[1]);
		entity_Share.setContent(shares[3]);

	}


	private void initWidget() {
		sharelpApplication=(SharelpApplication) getApplication();
		listView=(ListView) findViewById(R.id.ulti);
		ib_input=(ImageButton) findViewById(R.id.ib_input);
		ib_zan=(ImageButton) findViewById(R.id.ib_zan);
		et_input=(EditText) findViewById(R.id.et_comment_insert);
		ib_sendcomment=(ImageButton) findViewById(R.id.ib_sendcomment);
		ib_input.setOnClickListener(this);
		ib_sendcomment.setOnClickListener(this);
		ib_zan.setOnClickListener(this);

	}


	private void initHandler() {
		handler = new Handler(){
			public void handleMessage(Message msg) {
				//Toast.makeText(Notification_GTH_Activity.this,((Entity_Gth[]) msg.obj)[0].getIntroduction(), 2000).show();
			//	progressBar=(ProgressBar) findViewById(R.id.loadlistviewpage_share_detail);
			//	progressBar.setVisibility(View.GONE);
				adapter=new AllEntity_Adapter(Sharelp_Share_DerailsActivity.this,entity_Share,(Entity_Comments[])msg.obj, cache);
				listView.setAdapter(adapter);
			}		
		};
	}


	private void Link() {
		new Thread(){
			public void run() {
				try {
					String title=TITLE;
					title="title="+title;
					byte[] buffer=title.getBytes("utf-8");

					URL url=new URL(Util_Const.DETAILALL);
					HttpURLConnection conn=(HttpURLConnection)url.openConnection();

					conn.setConnectTimeout(5000);
					conn.setReadTimeout(5000);
					conn.setDoOutput(true);
					conn.setRequestMethod("POST");

					OutputStream os=conn.getOutputStream();
					os.write(buffer);
					os.flush();

					if(conn.getResponseCode()!=200){
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(Sharelp_Share_DerailsActivity.this, "连接服务端失败", 2000).show();
								return ;
							}
						});

					}
					else{

						ObjectMapper om=new ObjectMapper();
						entity_Comments=om.readValue(conn.getInputStream(), Entity_Comments[].class);
						Message msg =Message.obtain();//取一个
						msg.obj=entity_Comments;
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

	}


	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.ib_input:

			et_input.setVisibility(View.VISIBLE);
			ib_sendcomment.setVisibility(View.VISIBLE);
			et_input.setFocusable(true);
			ib_input.setVisibility(View.GONE);
			ib_zan.setVisibility(View.GONE);

			break;

		case R.id.ib_zan:
			postZan();
			break;

		case R.id.ib_sendcomment:

			if (sharelpApplication.isState()) {

				String username=sharelpApplication.getSname();
				String comment=et_input.getText().toString();
				//		Toast.makeText(getApplicationContext(), username+comment, 10).show();
				postComment(username,comment);



			}else {

				AlertDialog alert = new AlertDialog.Builder(Sharelp_Share_DerailsActivity.this).create();
				alert.setTitle("登陆提示");
				alert.setMessage("您还没有登陆无法评论，现在登陆？");

				alert.setButton(DialogInterface.BUTTON_NEGATIVE, "登陆",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent	 intent=new Intent(Sharelp_Share_DerailsActivity.this, LoginActivity.class);
						startActivity(intent);
					}
				});

				alert.setButton(DialogInterface.BUTTON_POSITIVE, "放弃",
						new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

					}
				});				
				alert.show();			

			}


			break;


		default:
			break;
		}
	}



	private void postZan() {
		new Thread(){
			public void run() {

				try {
					HttpPost post=new HttpPost(Util_Const.POSTZAN);
					List<BasicNameValuePair>list=new ArrayList<>();
					list.add(new BasicNameValuePair("title", TITLE));
					post.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
					HttpClient client=new DefaultHttpClient();
					HttpResponse response=client.execute(post);
					if (response.getStatusLine().getStatusCode()!=200) {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(Sharelp_Share_DerailsActivity.this, "error!", 100).show();
								return;
							}
						});
					}else {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(Sharelp_Share_DerailsActivity.this, "+1", 100).show();
								return;
							}
						});

					}

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			};

		}.start();
	}



	private void postComment(final String username, final String comment) {
		new Thread(){
			public void run() {

				try {
					HttpPost post=new HttpPost(Util_Const.POSTCONMMET);
					List<BasicNameValuePair>list=new ArrayList<>();
					list.add(new BasicNameValuePair("title", TITLE));
					list.add(new BasicNameValuePair("name", username));
					list.add(new BasicNameValuePair("comment", comment));
					post.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
					HttpClient client=new DefaultHttpClient();
					HttpResponse response=client.execute(post);
					int ISLINE=response.getStatusLine().getStatusCode();

					if (ISLINE==200) {
						runOnUiThread(new Runnable() {
							public void run() {
								Toast.makeText(Sharelp_Share_DerailsActivity.this, "评论成功", 100).show();
								return;
							}
						});
					}else {
						Toast.makeText(Sharelp_Share_DerailsActivity.this, "评论失败", 100).show();
						return;
					}
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			};

		}.start();
	}


}
