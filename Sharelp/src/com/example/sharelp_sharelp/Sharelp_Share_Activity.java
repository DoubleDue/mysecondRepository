package com.example.sharelp_sharelp;

/**
 * 网络读取数据
 */

import java.io.File;
import java.util.Timer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.actionbarsherlock.internal.view.View_HasStateListenerSupport;
import com.example.sharelp.R;
import com.example.sharelp_adapter.Entity_ShareAdapter;
import com.example.sharelp_entity.Entity_Share;
import com.example.sharelp_utils.RefreshableView;
import com.example.sharelp_utils.RefreshableView.PullToRefreshListener;
import com.example.sharelp_utils.TimerTaskRefresh;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.Util_ReadEntity;

public class Sharelp_Share_Activity extends Activity implements OnItemClickListener,View.OnClickListener{

	private RefreshableView refreshableView;//刷新
	private ProgressBar progressBar;
	private ListView listView;
	private File cache;//缓存
	private Handler handler;
	private ImageView iv_sorry;
	private boolean ISFINISHED=false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sharelp_share);
		initView();
		initHandler();
		Util_ReadEntity.OLink(handler, Entity_Share[].class,Util_Const.GETSHARE,10000 );
		initListener();
		//开始计时,10秒之后停止
		Timer timer=new Timer();
		timer.schedule(new TimerTaskRefresh(handler), 10 * 1000);
	}

	private void initListener() {//刷新操作
		refreshableView.setOnRefreshListener(new PullToRefreshListener() {
			public void onRefresh() {
				try {
					Util_ReadEntity.OLink(handler, Entity_Share[].class,Util_Const.GETSHARE,10000);
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshableView.finishRefreshing();
			}
		}, 0);

	}

	private void initView() {
		cache = new File(Environment.getExternalStorageDirectory(), "cache");
		if(!cache.exists()) cache.mkdirs();//不存在则创建
		refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
		listView = (ListView) this.findViewById(R.id.lv_share);
		listView.setOnItemClickListener(this);
		iv_sorry=(ImageView) findViewById(R.id.iv_failed);
		iv_sorry.setOnClickListener(Sharelp_Share_Activity.this);
	}


	private void initHandler() {
		handler = new Handler(){
			public void handleMessage(Message msg) {
				if (msg.arg1==200) {
					ISFINISHED=true;//用于判断是否已经加载好了
				//	ShowToast.showToast(Sharelp_Share_Activity.this, msg.arg2+"");
					progressBar=(ProgressBar) findViewById(R.id.loadlistviewpage);
					progressBar.setVisibility(View.GONE);
					listView.setAdapter(new Entity_ShareAdapter(Sharelp_Share_Activity.this,(Entity_Share[])msg.obj, 
							R.layout.activity_list_item_share, cache));
				}else if (msg.arg1==10) {
					
					if (!ISFINISHED) {
						progressBar=(ProgressBar) findViewById(R.id.loadlistviewpage);
						progressBar.setVisibility(View.GONE);
						iv_sorry.setVisibility(View.VISIBLE);
					}
					
				}else {
					Toast.makeText(Sharelp_Share_Activity.this, "连接服务端失败", 2000).show();
				}

			}		
		};
	}

	
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {

		Entity_Share entity_Share=(Entity_Share) parent.getItemAtPosition(position);
		//	Toast.makeText(this, entity_Share.getName()+entity_Share.getTitle(), 10).show();
		Intent intent = new Intent( Sharelp_Share_Activity.this  , Sharelp_Share_DerailsActivity.class );   
		Bundle mBundle = new Bundle();   
		mBundle.putString("share", entity_Share.getName()+"&"+entity_Share.getPhoto()+"&"+
				entity_Share.getTitle()+"&"+entity_Share.getContent()); 
		intent.putExtras(mBundle); 
		startActivity(intent);
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.iv_failed:
			progressBar.setVisibility(View.VISIBLE);
			iv_sorry.setVisibility(View.GONE);
			Util_ReadEntity.OLink(handler, Entity_Share[].class,Util_Const.GETSHARE,10000 );
			Timer timer=new Timer();
			timer.schedule(new TimerTaskRefresh(handler), 10 * 1000);
			break;

		default:
			break;
		}
	}
}



















/*

private void Link() {
	new Thread(){
		public void run() {

			Message msg =Message.obtain();//取一个
			StringBuilder sb=new StringBuilder(Util_Const.GETSHARE);
			try {
				URL url=new URL(sb.toString());
				HttpURLConnection conn=(HttpURLConnection)url.openConnection();
				conn.setReadTimeout(10000);
				conn.setConnectTimeout(10000);
				conn.setRequestMethod("GET");

				if(conn.getResponseCode()!=200){
					msg.arg1=conn.getResponseCode();
					
					runOnUiThread(new Runnable() {
						public void run() {
							Toast.makeText(Sharelp_Share_Activity.this, "连接服务端失败", 2000).show();
							return ;
						}
					});

				}
				else{

//						ObjectMapper om=new ObjectMapper();
//					entity_Shares=om.readValue(conn.getInputStream(), Entity_Share[].class);
//
//					Message msg =Message.obtain();//取一个
//					msg.obj=entity_Shares;
//					handler.sendMessage(msg);
					 
					ObjectMapper om=new ObjectMapper();
					msg.arg1=200;
					msg.obj=om.readValue(conn.getInputStream(), Entity_Share[].class);
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

}*/


