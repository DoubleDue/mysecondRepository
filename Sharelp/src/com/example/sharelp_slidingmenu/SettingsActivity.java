package com.example.sharelp_slidingmenu;

import com.example.sharelp.R;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp.R.layout;
import com.example.sharelp_tab.TabHostActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

public class SettingsActivity extends Activity{
	

	private ListView lv_settings1,lv_settings2;
	private SharelpApplication sharelpApplication;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		lv_settings1=(ListView) findViewById(R.id.lv_settings_1);
		lv_settings2=(ListView) findViewById(R.id.lv_settings_2);
		sharelpApplication=(SharelpApplication) getApplication();
		lv_1Click();
		lv_2Click();
		
	}

	private void lv_1Click() {
		lv_settings1.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				Intent intent ;
				
				switch (position) {
				case 0:
					intent=new Intent(SettingsActivity.this,PersonalDataActivity.class);
					SettingsActivity.this.finish();
					startActivity(intent);
					break;
				case 1:
					intent=new Intent(SettingsActivity .this,CollectionActivity.class);
					SettingsActivity.this.finish();
					startActivity(intent);
			
				
				default:
					break;
				}
				
			}
		});
	}

	private void lv_2Click() {
		lv_settings2.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				
				Intent intent ;
				
				switch (position) {
				//case0为空
				case 1:
					intent=new Intent(SettingsActivity.this,PersonalDataActivity.class);
					SettingsActivity.this.finish();
					startActivity(intent);
					break;
				case 2://退出
					sharelpApplication.setState(false);
					Toast.makeText(SettingsActivity.this, "您已退出", 100).show();
					intent=new Intent(SettingsActivity.this,TabHostActivity.class);
					SettingsActivity.this.finish();
					startActivity(intent);
					
				
				default:
					break;
				}
				
			}
		});
		
	}

	
	
	
	
}
