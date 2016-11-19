package com.example.sharelp_show;


import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sharelp.R;
import com.example.sharelp_adapter.Entity_GPAdapter;
import com.example.sharelp_cooperation.Cooperation_Team_DetailActivity;
import com.example.sharelp_entity.Entity_GP;
import com.example.sharelp_utils.OfRefreshableGridView;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.Util_ReadEntity;
/**
 *个人展示页面
 * @author yijian cheng
 *
 */
public class Notification_GP_Activity extends Activity implements OnItemClickListener{
	//private ListView listView;
	private GridView  gridView;
	private File cache;//缓存
	private Entity_GP[] entity_GPs;	
	private OfRefreshableGridView refreshablegridView;//刷新
	private ProgressBar progressBar;
	
	private Handler handler;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_gp);

		initView();
		initHandler();
		Util_ReadEntity.OLink(handler, Entity_GP[].class,Util_Const.PERSON,10000);
		initListen();

	}


	private void initListen() {
		//刷新操作
				refreshablegridView.setOnRefreshListener(new OfRefreshableGridView.PullToRefreshListener() {

					@Override
					public void onRefresh() {
						try {
							Util_ReadEntity.OLink(handler, Entity_GP[].class,Util_Const.PERSON,10000);
							
							Thread.sleep(1500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						refreshablegridView.finishRefreshing();
					}

				},0);
		
	}





	private void initView() {
		
		refreshablegridView = (OfRefreshableGridView) findViewById(R.id.refreshable_gridview);
		gridView = (GridView) this.findViewById(R.id.lv_gp);
		gridView.setOnItemClickListener(this);
		
		cache = new File(Environment.getExternalStorageDirectory(), "cache");
		if(!cache.exists()) cache.mkdirs();//不存在则创建
		
	}

	/*添加列表点击事件*/

	public void onItemClick(AdapterView<?> parent, View view , int position, long arg3) {	

		Entity_GP entity_GP=(Entity_GP) parent.getItemAtPosition(position);


		/*  Intent mIntent = new Intent(Notification_GP_Activity,Cooperation_Team_DetailActivity.class);     
	        Bundle mBundle = new Bundle();     
	        mBundle.putSerializable("team",entity_Team);     
	        mIntent.putExtras(mBundle);     
	       context.startActivity(mIntent);   
		
		*/
		
		Notification_GP_DetailsActivity.NAME=entity_GP.getName();
		Notification_GP_DetailsActivity.PHOTO=entity_GP.getPhoto();
		Notification_GP_DetailsActivity.GRADE=entity_GP.getGrade();
		Notification_GP_DetailsActivity.CONTENT=entity_GP.getContent();
		Notification_GP_DetailsActivity.TEL=entity_GP.getTel();
		//	Toast.makeText(Notification_GP_Activity.this, gpname, 200).show();
		Intent intent=new Intent(Notification_GP_Activity.this, Notification_GP_DetailsActivity.class);
		startActivity(intent);

		/*	Intent intent=new Intent(Notification_GP_Activity.this, LoginActivity.class);
		startActivity(intent);*/
	}


	private void initHandler() {
		handler = new Handler(){
			public void handleMessage(Message msg) {
				if (msg.arg1==200) {
					progressBar=(ProgressBar) findViewById(R.id.loadgridviewpage);
					progressBar.setVisibility(View.GONE);
					//	Toast.makeText(Notification_GP_Activity.this,((Entity_GP[]) msg.obj)[1].getContent(), 2000).show();
					gridView.setAdapter(new Entity_GPAdapter(Notification_GP_Activity.this,(Entity_GP[])msg.obj, 
							R.layout.activity_list_item_gp, cache));
				}else {
					Toast.makeText(Notification_GP_Activity.this, "连接服务端失败", 2000).show();
				}

			}		
		};
	}


}









/*
	Util_ReadEntity.OLink(handler, Entity_GP[].class,Util_Const.PERSON);替代
private void Link() {
	new Thread(){
		public void run() {

			StringBuilder sb=new StringBuilder(Util_Const.PERSON);
			try {
				URL url=new URL(sb.toString());
				HttpURLConnection conn=(HttpURLConnection)url.openConnection();
				conn.setReadTimeout(10000);
				conn.setConnectTimeout(10000);
				conn.setRequestMethod("GET");

				if(conn.getResponseCode()!=200){
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(Notification_GP_Activity.this, "连接服务端失败", 2000).show();
							return ;
						}
					});

				}
				else{

					ObjectMapper om=new ObjectMapper();
					entity_GPs=om.readValue(conn.getInputStream(), Entity_GP[].class);

					Message msg =Message.obtain();//取一个
					msg.obj=entity_GPs;
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
 */






















