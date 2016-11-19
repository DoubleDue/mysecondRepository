package com.example.sharelp_cooperation_news;

import java.io.File;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sharelp.R;
import com.example.sharelp_adapter.Entity_GthAdapter;
import com.example.sharelp_contestinform.Inform_DetailsActivity;
import com.example.sharelp_contestinform.Inform_Schoolout_Activity;
import com.example.sharelp_entity.Entity_Gth;
import com.example.sharelp_show.Notification_GTH_Activity;
import com.example.sharelp_show.Notification_Gth_DetailsActivity;
import com.example.sharelp_utils.RefreshableView;
import com.example.sharelp_utils.RefreshableView.PullToRefreshListener;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.Util_ReadEntity;

/**
 *校内通知
 * @author Administrator
 *
 */


public class News_Schoolout_Activity extends Activity implements OnItemClickListener{

	private RefreshableView refreshableView;//刷新
	private ProgressBar progressBar;
	private ListView listView;
	private File cache;//缓存
	private Handler handler;

	//	public final static String PATH="http://sharelp.ecs11.tomcats.pw/gth";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_gth);

		initView();
		initHandler();
		Util_ReadEntity.OLink(handler, Entity_Gth[].class,Util_Const.News_out,10000);
		initListen();
	}


	private void initListen() {
		//刷新操作
		refreshableView.setOnRefreshListener(new PullToRefreshListener() {
			@Override
			public void onRefresh() {
				try {
					Util_ReadEntity.OLink(handler, Entity_Gth[].class,Util_Const.News_out,10000);
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshableView.finishRefreshing();
			}
		}, 0);

		
	}


	private void initView() {
		refreshableView = (RefreshableView) findViewById(R.id.refreshable_view);
		listView = (ListView) this.findViewById(R.id.lv_gth);
		listView.setOnItemClickListener(this);

		cache = new File(Environment.getExternalStorageDirectory(), "cache");
		if(!cache.exists()) cache.mkdirs();//不存在则创建

		
	}


	private void initHandler() {
		handler = new Handler(){
			public void handleMessage(Message msg) {
				if (msg.arg1==200) {
					progressBar=(ProgressBar) findViewById(R.id.loadlistviewpage_gth);
					progressBar.setVisibility(View.GONE);	
					listView.setAdapter(new Entity_GthAdapter(News_Schoolout_Activity.this,(Entity_Gth[])msg.obj, 
							R.layout.activity_listview_item_gth, cache));
				}else {
					Toast.makeText(News_Schoolout_Activity.this, "连接服务端失败", 2000).show();
				}
			
			}		
		};
	}


	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {


		Entity_Gth entity_Gth=(Entity_Gth) parent.getItemAtPosition(position);
		
		Intent mIntent = new Intent(News_Schoolout_Activity.this,Inform_DetailsActivity.class);     
        Bundle mBundle = new Bundle();     
        mBundle.putSerializable("gth",entity_Gth);     
        mIntent.putExtras(mBundle);     
       startActivity(mIntent);   

	


	}






}