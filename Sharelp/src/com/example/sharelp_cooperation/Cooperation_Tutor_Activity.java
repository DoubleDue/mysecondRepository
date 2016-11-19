package com.example.sharelp_cooperation;

import java.util.ArrayList;
import java.util.HashMap;

import com.example.sharelp.R;
import com.example.sharelp.R.drawable;
import com.example.sharelp.R.id;
import com.example.sharelp.R.layout;
import com.example.sharelp_slidingmenu.SigninActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Cooperation_Tutor_Activity extends Activity {

	private String texts[] = null;
	private int images[] = null;
	private GridView gridview;
	private   ArrayList<HashMap<String, Object>> hashMaps = new ArrayList<HashMap<String, Object>>();
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cooperation_tutor);
		initData();
		gridview = (GridView) findViewById(R.id.gridview);
		addData();
		addAdapter();
	}

	private void addAdapter() {
		SimpleAdapter saImageItems = new SimpleAdapter(Cooperation_Tutor_Activity.this, 
				hashMaps,             // ����Դ
				R.layout.activity_grid_item_tutor,       // ��ʾ����
				new String[] { "itemImage", "itemText" }, 
				new int[] { R.id.iv_department, R.id.tv_departmentname }); 
		gridview.setAdapter(saImageItems);
		gridview.setOnItemClickListener(new ItemClickListener());

	}

	private void addData() {
		for (int i = 0; i < 9; i++) {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("itemImage", images[i]);
			map.put("itemText", texts[i]);
			hashMaps.add(map);
		}


	}

	private void initData() {
		images=new int[]{ R.drawable.all,R.drawable.xinxii, R.drawable.all,
			
				R.drawable.all,R.drawable.lunji, R.drawable.all,
				R.drawable.hanghaii, R.drawable.fa,R.drawable.renwen};
		texts = new String[]{  
				"�����ѧԺ","��ϢѧԺ",   "���˼ѧԺ",
				"��ѧϵ" ,"�ֻ�ѧԺ","����ϵ",
				"����ѧԺ",	  "��ѧԺ","����ѧԺ"
				
		};


	}

	class ItemClickListener implements OnItemClickListener {
	
		public void onItemClick(AdapterView<?> parent, View view, int position, long rowid) {
			
			HashMap<String, Object> item = (HashMap<String, Object>) parent.getItemAtPosition(position);
			//��ȡ����Դ������ֵ
			String departname=(String)item.get("itemText");
			Object object=item.get("itemImage");
			Cooperation_Tutor_Teamlist.tutor_depart=departname;
			
		//	Toast.makeText(Cooperation_Tutor_Activity.this, departname, Toast.LENGTH_LONG).show();
			startActivity(new Intent(Cooperation_Tutor_Activity.this, Cooperation_Tutor_Teamlist.class));//������һ��Activity

		

		}
	}
}
