package com.example.sharelp_tab;


import com.example.sharelp.R;
import com.example.sharelp.R.layout;
import com.example.sharelp_show.Notification_GP_Activity;
import com.example.sharelp_show.Notification_GTH_Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class Notification_Tab_Activity extends TabActivity{

	private TabHost tabHost;
	private Intent intent_goodperson,intent_goodthing;
	private TabHost.TabSpec spec_goodperson,spec_goodthing;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_tab);
	
		tabHost=this.getTabHost(); 

		intent_goodperson=new Intent().setClass(this, Notification_GP_Activity.class);  
		spec_goodperson=tabHost.newTabSpec("tab_goodperson").setIndicator("�������").setContent(intent_goodperson);  
		tabHost.addTab(spec_goodperson);
		//tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.nav_up_1y);


		intent_goodthing=new Intent().setClass(this, Notification_GTH_Activity.class);  
		spec_goodthing=tabHost.newTabSpec("tab_goodthing").setIndicator("������Ʒ").setContent(intent_goodthing);  
		tabHost.addTab(spec_goodthing);
		//tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.nav_up_2x);
		tabHost.setOnTabChangedListener(new OnTabChangedListener()); // ѡ�������       
		
	}



	class OnTabChangedListener implements OnTabChangeListener {  	
		public void onTabChanged(String tabId) {  
			tabHost.setCurrentTabByTag(tabId);         
			updateTab(tabHost);  
		}  
	}  

	public boolean onKeyDown(int keyCode, KeyEvent event) {  
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {  
			System.exit(0);  
			return false;  
		} else if (keyCode == KeyEvent.KEYCODE_MENU  
				&& event.getRepeatCount() == 0) {  
			return true; // ����true�Ͳ��ᵯ��Ĭ�ϵ�setting�˵�  
		}  

		return false;  
	}  

	/** 
	 * ����Tab��ǩ����ɫ�����������ɫ 
	 * @param tabHost 
	 */  
	private void updateTab(final TabHost tabHost) {  
		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {  

			
			if (tabHost.getCurrentTab() == 0) {//ѡ��  
				
			//	tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.nav_up_1y);
			//	tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.nav_up_2x);
				
			
			} 
			else {//��ѡ��  
		
			//	tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.nav_up_1x);
			//	tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.nav_up_2y);

			}  
		}  
	}  

	
	
}







