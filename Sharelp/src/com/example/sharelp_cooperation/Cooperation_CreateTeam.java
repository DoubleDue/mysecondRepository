package com.example.sharelp_cooperation;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.sharelp.R;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp_tab.Contest_Inform_Tab_Activity;
import com.example.sharelp_tab.News_Tab_Activity;

/**
 * chuangqingchun
 * 内含四个模块
 * 个人展示,团队展示，个人简历，创建队伍
 * @author Administrator
 *
 */
public class Cooperation_CreateTeam extends Activity implements OnItemClickListener{

	private SharelpApplication sharelpApplication;
	private GridView gv_createteam;

	private String texts[] = null;
	private int images[] = null;
	private   ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<HashMap<String, Object>>();


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cooperation_createteam);
		initView();
		initData();
		addData();
		addAdapter();
	}


	private void initView() {
		sharelpApplication=(SharelpApplication) getApplication();
		gv_createteam=(GridView) findViewById(R.id.gv_createteam);
		gv_createteam.setOnItemClickListener(this);	

	}

	private void initData() {
		images=new int[]{ R.drawable.icon_personal,R.drawable.icon_teamdisplay,
				R.drawable.icon_writeperson,R.drawable.icon_createteam};
		texts = new String[]{  "个人展示","团队展示",   "填写简历","创建队伍" };
	}

	private void addData() {
		for (int i = 0; i < 4; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", images[i]);
			map.put("itemText", texts[i]);
			hashMaps.add(map);
		}
	}
	private void addAdapter() {
		SimpleAdapter saImageItems = new SimpleAdapter(Cooperation_CreateTeam.this, 
				hashMaps,             // 数据源
				R.layout.activity_grid_item_tutor,       // 显示布局
				new String[] { "itemImage", "itemText" }, 
				new int[] { R.id.iv_department, R.id.tv_departmentname }); 
		gv_createteam.setAdapter(saImageItems);


	}


	//listitem点击事件
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {

		//获取所点的编号
		int itemid=(int) parent.getItemIdAtPosition(position);
		Intent intent;
		switch (itemid) {
		case 0:
			//个人简历
			intent=new Intent(Cooperation_CreateTeam.this, Cooperation_Personal_Activity.class);
			startActivity(intent);
			break;
		case 1://团队展示
			intent=new Intent(Cooperation_CreateTeam.this, Cooperation_Team_Activity.class);
			startActivity(intent);
			break;
		case 2:
			//填写简历,判断是否登录
			if (sharelpApplication.isState()) {
				intent=new Intent(Cooperation_CreateTeam.this, Contest_Write_MemberDetailsActivity.class);
				startActivity(intent);
			}else Toast.makeText(Cooperation_CreateTeam.this, "您还没有登录", 10).show();
			break;
		case 3:
			//创建队伍
			if (sharelpApplication.isState()) {
				intent=new Intent(Cooperation_CreateTeam.this, Contest_Write_OneTeamActivity.class);
				startActivity(intent);
			}else Toast.makeText(Cooperation_CreateTeam.this, "您还没有登录", 10).show();
			
			break;
		default:
			break;
		}

	}





}
