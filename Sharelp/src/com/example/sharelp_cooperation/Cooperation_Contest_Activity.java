package com.example.sharelp_cooperation;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharelp.R;
import com.example.sharelp_adapter.Entity_ContestAdapter;
import com.example.sharelp_cooperation.Cooperation_Tutor_Activity.ItemClickListener;
import com.example.sharelp_entity.Entity_Contest;
import com.example.sharelp_sharelp.Sharelp_Share_Activity;
import com.example.sharelp_show.Notification_GP_Activity;
import com.example.sharelp_show.Notification_GTH_Activity;
import com.example.sharelp_tab.Contest_Inform_Tab_Activity;
import com.example.sharelp_tab.News_Tab_Activity;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.Util_ReadEntity;
/**
 * @chuangqingchun
 * 首页
 * 赛事主页面，包含图片轮播及赛事列表
 *  包含新闻、赛事通知，组队、赛事指导，导师，优秀个人，优秀作品，论坛等模块
 * @author Administrator
 *
 */
public class Cooperation_Contest_Activity extends Activity  implements OnItemClickListener{

	//private ListView lv_contest;
	private GridView gv_contest;
	private Handler handler_contest;

	private String texts[] = null;
	private int images[] = null;
	private   ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<HashMap<String, Object>>();
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cooperation_contest);
		initView();
	//	Util_ReadEntity.OLink(handler_contest, Entity_Contest[].class, Util_Const.CONTESTLIST,10000);
		initData();
		addData();
		addAdapter();
	}




	private void initView() {
		gv_contest=(GridView) findViewById(R.id.gv_contest);
		gv_contest.setOnItemClickListener(this);	

	}




	private void initData() {

		images=new int[]{ R.drawable.icon_news,R.drawable.icon_notification, R.drawable.icon_goodthing,
				R.drawable.icon_zudui,R.drawable.icon_guide,
				 R.drawable.icon_tutor,R.drawable.icon_goodperson, 
					R.drawable.icon_share};
		texts = new String[]{  "新闻","赛事通知",  "优秀作品", "组队","赛事指导" ,"导师","优秀个人","论坛"};
	}
	

	private void addData() {
		for (int i = 0; i < 8; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", images[i]);
			map.put("itemText", texts[i]);
			hashMaps.add(map);
		}
	}
	private void addAdapter() {
		SimpleAdapter saImageItems = new SimpleAdapter(Cooperation_Contest_Activity.this, 
				hashMaps,             // 数据源
				R.layout.activity_grid_item_tutor,       // 显示布局
				new String[] { "itemImage", "itemText" }, 
				new int[] { R.id.iv_department, R.id.tv_departmentname }); 
		gv_contest.setAdapter(saImageItems);
		

	}


	//退出应用
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	//	imageView.setImageBitmap(null);//释放
	//	linearLayout.setBackground(null);

		System.gc();//通知进行回收
	}


	//listitem点击事件
	/**
	 * 一定注意oom，，及时进行内存的回收
	 */
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {

		//获取所点的编号
		int itemid=(int) parent.getItemIdAtPosition(position);
		Intent intent;
		switch (itemid) {
		case 0:
			//新闻频道
			intent=new Intent(Cooperation_Contest_Activity.this, News_Tab_Activity.class);
			System.gc();//通知进行回收
			startActivity(intent);
			break;
		case 1:
			intent=new Intent(Cooperation_Contest_Activity.this, Contest_Inform_Tab_Activity.class);
			System.gc();//通知进行回收
			startActivity(intent);
			//赛事通知频道
			break;
		case 2:
			//优秀作品
			intent=new Intent(Cooperation_Contest_Activity.this, Notification_GTH_Activity.class);
			System.gc();//通知进行回收
			startActivity(intent);
			break;
		case 3:
			//Toast.makeText(Cooperation_Contest_Activity.this, "模块功能研发中，敬请期待...", 100).show();
			//组队频道
			intent=new Intent(Cooperation_Contest_Activity.this, Cooperation_CreateTeam.class);
			System.gc();//通知进行回收
			startActivity(intent);
			break;
		
			/*	case 4:
			//赛事指导频道
			intent=new Intent(Cooperation_Contest_Activity.this, Cooperation_Tutor_Activity.class);
			System.gc();//通知进行回收
			startActivity(intent);
			break;
		case 5://导师
			intent=new Intent(Cooperation_Contest_Activity.this, Cooperation_Tutor_Activity.class);
			System.gc();//通知进行回收
			startActivity(intent);
			break;
		case 6:
			//优秀个人
			intent=new Intent(Cooperation_Contest_Activity.this, Notification_GP_Activity.class);
			System.gc();//通知进行回收
			startActivity(intent);
			break;
		
		case 7:
			//论坛
			intent=new Intent(Cooperation_Contest_Activity.this, Sharelp_Share_Activity.class);
			System.gc();//通知进行回收
			startActivity(intent);
			break;*/
		default:
			Toast.makeText(Cooperation_Contest_Activity.this, "模块功能研发中，敬请期待...", 100).show();
			break;
		}
		
	}






}
