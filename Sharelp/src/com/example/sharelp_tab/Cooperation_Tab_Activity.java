package com.example.sharelp_tab;

import com.example.sharelp.R;
import com.example.sharelp.R.layout;
import com.example.sharelp_cooperation.Cooperation_Contest_Activity;
import com.example.sharelp_cooperation.Cooperation_Tutor_Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;

public class Cooperation_Tab_Activity extends TabActivity{
	
	
	private TabHost tabHost;
	private Intent intent_contest,intent_tutor;
	private TabHost.TabSpec spec_contest,spec_tutor;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cooperation_tab);

		tabHost=this.getTabHost(); 

		

		intent_contest=new Intent().setClass(this, Cooperation_Contest_Activity.class);  
		spec_contest=tabHost.newTabSpec("tab_contest").setIndicator("����").setContent(intent_contest);  
		tabHost.addTab(spec_contest);
		//tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.c);
		
		
		intent_tutor=new Intent().setClass(this, Cooperation_Tutor_Activity.class);  
		spec_tutor=tabHost.newTabSpec("tab_tutor").setIndicator("��ʦ").setContent(intent_tutor);  
		tabHost.addTab(spec_tutor);
		//tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.a);

		
		
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
				
//				tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.a);
//				tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.c);
				
			
			} 
			else {//��ѡ��  
		
//				tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.a);
//				tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.c);

			}  
		}  
	}  


}
