package com.example.sharelp_cooperation;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.Toast;

import com.example.sharelp.R;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp_adapter.Entity_TeamDetailAdapter;
import com.example.sharelp_entity.Entity_PersonalTeam;
import com.example.sharelp_entity.Entity_Personals;
import com.example.sharelp_entity.Entity_Teams;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.Util_ReadEntity;

/**
 * 显示团队的详细信息；
 * 含有申请加入按钮
 * 传过来了一个team实体
 * @author Administrator
 *
 */
public class Cooperation_Team_DetailActivity extends Activity {


	private Entity_Teams entity_Team;
	private SharelpApplication sharelpApplication;
	private Handler handler;
	private ListView lv_detailteam;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contest_teamlist_detail);

		initView();

		initHandler();
		addData();
	}


	private void initView() {
		sharelpApplication=(SharelpApplication) getApplication();
		lv_detailteam=(ListView) findViewById(R.id.lv_teamlist_detail);
	}


	private void addData() {
		entity_Team= (Entity_Teams)getIntent().getSerializableExtra("team");  	
		String teamname=entity_Team.getTeamname();
	//	Toast.makeText(Cooperation_Team_DetailActivity.this,"dangchu",10000	).show();
		Util_ReadEntity.OLinkParam1(handler,Entity_Personals[].class, Util_Const.TEAM_xiangqing,teamname,10000,2);
	//Toast.makeText(Cooperation_Team_DetailActivity.this,"dangchu2",10000).show();
	}


	private void initHandler() {
		handler = new Handler(){
			public void handleMessage(Message msg) {
				if (msg.arg1==200) {
					if (msg.arg2==233) {//申请发回的响应
						Toast.makeText(Cooperation_Team_DetailActivity.this,"已提交",10000	).show();
					}else {
						lv_detailteam.setAdapter(new Entity_TeamDetailAdapter(
								Cooperation_Team_DetailActivity.this,handler,sharelpApplication,
								(Entity_Personals[])msg.obj, entity_Team));
					}
					
				}else {
					Toast.makeText(Cooperation_Team_DetailActivity.this,"failed",10000	).show();
				}
			}	
		};
	}



}
