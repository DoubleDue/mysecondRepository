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
import com.example.sharelp_adapter.Entity_TeamsAdapter;
import com.example.sharelp_entity.Entity_Gth;
import com.example.sharelp_entity.Entity_Personals;
import com.example.sharelp_entity.Entity_Teams;
import com.example.sharelp_show.Notification_Gth_DetailsActivity;
import com.example.sharelp_utils.RefreshableView;
import com.example.sharelp_utils.RefreshableView.PullToRefreshListener;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.Util_ReadEntity;

/**
 *�Ŷ���Ŀ�б�
 * @author Administrator
 *
 */

public class Cooperation_Team_Activity extends Activity{

	private RefreshableView refreshableView;//ˢ��
	private ProgressBar progressBar;
	private ListView listView;
	private File cache;//����
	private Handler handler;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cooperation_team);

		initView();
		initHandler();
		Util_ReadEntity.OLink(handler, Entity_Teams[].class,Util_Const.TEAMLIST,10000);
		initListen();
		
	}
	


	private void initListen() {
		//ˢ�²���
		refreshableView.setOnRefreshListener(new PullToRefreshListener() {
			@Override
			public void onRefresh() {
				try {
					Util_ReadEntity.OLink(handler, Entity_Teams[].class,Util_Const.TEAMLIST,10000);
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				refreshableView.finishRefreshing();
			}
		}, 0);

		
	}


	private void initView() {
		refreshableView = (RefreshableView) findViewById(R.id.refreshable_teams);
		listView = (ListView) this.findViewById(R.id.lv_teams);
	}


	private void initHandler() {
		handler = new Handler(){
			public void handleMessage(Message msg) {
				if (msg.arg1==200) {
					progressBar=(ProgressBar) findViewById(R.id.loadlistviewpage_teams);
					progressBar.setVisibility(View.GONE);	
					listView.setAdapter(new Entity_TeamsAdapter(Cooperation_Team_Activity.this,(Entity_Teams[])msg.obj, 
							 cache,listView));
				}else {
					Toast.makeText(Cooperation_Team_Activity.this, "���ӷ����ʧ��", 2000).show();
				}
			
			}		
		};
	}





}