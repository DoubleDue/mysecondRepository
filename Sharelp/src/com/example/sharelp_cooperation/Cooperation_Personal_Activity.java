package com.example.sharelp_cooperation;

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
import com.example.sharelp_adapter.Entity_PersonalsAdapter;
import com.example.sharelp_entity.Entity_Gth;
import com.example.sharelp_entity.Entity_Personals;
import com.example.sharelp_show.Notification_Gth_DetailsActivity;
import com.example.sharelp_utils.RefreshableView;
import com.example.sharelp_utils.RefreshableView.PullToRefreshListener;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.Util_ReadEntity;

/**
 * 简历列表
 * @author yijaincheng
 *
 */

public class Cooperation_Personal_Activity extends Activity{

	private RefreshableView refreshableView;//刷新
	private ProgressBar progressBar;
	private ListView listView;
	private File cache;//缓存
	private Handler handler;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cooperation_personal);

		initView();
		initHandler();
		Util_ReadEntity.OLink(handler, Entity_Personals[].class,Util_Const.PERSONAL,10000);
		initListen();
	}
	

	private void initListen() {
		//刷新操作
		refreshableView.setOnRefreshListener(new PullToRefreshListener() {
			@Override
			public void onRefresh() {
				try {
					Util_ReadEntity.OLink(handler,Entity_Personals[].class,Util_Const.PERSONAL,10000);
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshableView.finishRefreshing();
			}
		}, 0);

		
	}


	private void initView() {
		refreshableView = (RefreshableView) findViewById(R.id.refreshable_personal);
		listView = (ListView) this.findViewById(R.id.lv_personal);
	}


	private void initHandler() {
		handler = new Handler(){
			public void handleMessage(Message msg) {
				if (msg.arg1==200) {
					progressBar=(ProgressBar) findViewById(R.id.loadlistviewpage_personal);
					progressBar.setVisibility(View.GONE);	
					listView.setAdapter(new Entity_PersonalsAdapter(Cooperation_Personal_Activity.this,(Entity_Personals[])msg.obj, 
							 cache,listView));
				}else {
					Toast.makeText(Cooperation_Personal_Activity.this, "连接服务端失败", 2000).show();
				}
			
			}		
		};
	}





}