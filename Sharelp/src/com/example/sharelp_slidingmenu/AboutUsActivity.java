package com.example.sharelp_slidingmenu;

import com.example.sharelp.R;
import com.example.sharelp.R.layout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class AboutUsActivity extends Activity {
	
	
	
	private ListView lv_aboutus1,lv_aboutus2;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutus);
		lv_aboutus1=(ListView) findViewById(R.id.lv_aboutus1);
		lv_aboutus2=(ListView) findViewById(R.id.lv_aboutus2);
		
		lv_1Click();
		lv_2Click();
		
		
		
	}

	private void lv_1Click() {
		lv_aboutus1.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				switch (arg2) {
				case 1:
					Toast.makeText(AboutUsActivity.this, "18840821861", 100).show();
					break;

				default:
					break;
				}
				
				
			}
		});
	}

	private void lv_2Click() {
		lv_aboutus2.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				switch (arg2) {
				case 1:
					Toast.makeText(AboutUsActivity.this, "��֪ͨ������������������̣������Ѿ������ṩ��һ�����ķ���������������Ŀ�����е�����Ҳ�ܹ����˰�����", 100).show();
					break;
					
				case 2:
					Toast.makeText(AboutUsActivity.this, "����֪ͨ����λ���������", 100).show();
					break;
					
				case 3:
					Toast.makeText(AboutUsActivity.this, "861350580@qq.com", 100).show();
					break;

				default:
					break;
				}
				
				
			}
		});
		
	}


	
}
