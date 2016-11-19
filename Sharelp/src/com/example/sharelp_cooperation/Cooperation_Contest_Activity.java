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
 * ��ҳ
 * ������ҳ�棬����ͼƬ�ֲ��������б�
 *  �������š�����֪ͨ����ӡ�����ָ������ʦ��������ˣ�������Ʒ����̳��ģ��
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
		texts = new String[]{  "����","����֪ͨ",  "������Ʒ", "���","����ָ��" ,"��ʦ","�������","��̳"};
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
				hashMaps,             // ����Դ
				R.layout.activity_grid_item_tutor,       // ��ʾ����
				new String[] { "itemImage", "itemText" }, 
				new int[] { R.id.iv_department, R.id.tv_departmentname }); 
		gv_contest.setAdapter(saImageItems);
		

	}


	//�˳�Ӧ��
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	//	imageView.setImageBitmap(null);//�ͷ�
	//	linearLayout.setBackground(null);

		System.gc();//֪ͨ���л���
	}


	//listitem����¼�
	/**
	 * һ��ע��oom������ʱ�����ڴ�Ļ���
	 */
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {

		//��ȡ����ı��
		int itemid=(int) parent.getItemIdAtPosition(position);
		Intent intent;
		switch (itemid) {
		case 0:
			//����Ƶ��
			intent=new Intent(Cooperation_Contest_Activity.this, News_Tab_Activity.class);
			System.gc();//֪ͨ���л���
			startActivity(intent);
			break;
		case 1:
			intent=new Intent(Cooperation_Contest_Activity.this, Contest_Inform_Tab_Activity.class);
			System.gc();//֪ͨ���л���
			startActivity(intent);
			//����֪ͨƵ��
			break;
		case 2:
			//������Ʒ
			intent=new Intent(Cooperation_Contest_Activity.this, Notification_GTH_Activity.class);
			System.gc();//֪ͨ���л���
			startActivity(intent);
			break;
		case 3:
			//Toast.makeText(Cooperation_Contest_Activity.this, "ģ�鹦���з��У������ڴ�...", 100).show();
			//���Ƶ��
			intent=new Intent(Cooperation_Contest_Activity.this, Cooperation_CreateTeam.class);
			System.gc();//֪ͨ���л���
			startActivity(intent);
			break;
		
			/*	case 4:
			//����ָ��Ƶ��
			intent=new Intent(Cooperation_Contest_Activity.this, Cooperation_Tutor_Activity.class);
			System.gc();//֪ͨ���л���
			startActivity(intent);
			break;
		case 5://��ʦ
			intent=new Intent(Cooperation_Contest_Activity.this, Cooperation_Tutor_Activity.class);
			System.gc();//֪ͨ���л���
			startActivity(intent);
			break;
		case 6:
			//�������
			intent=new Intent(Cooperation_Contest_Activity.this, Notification_GP_Activity.class);
			System.gc();//֪ͨ���л���
			startActivity(intent);
			break;
		
		case 7:
			//��̳
			intent=new Intent(Cooperation_Contest_Activity.this, Sharelp_Share_Activity.class);
			System.gc();//֪ͨ���л���
			startActivity(intent);
			break;*/
		default:
			Toast.makeText(Cooperation_Contest_Activity.this, "ģ�鹦���з��У������ڴ�...", 100).show();
			break;
		}
		
	}






}
