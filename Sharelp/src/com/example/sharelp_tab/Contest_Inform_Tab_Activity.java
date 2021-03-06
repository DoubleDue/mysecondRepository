package com.example.sharelp_tab;

import com.example.sharelp.R;
import com.example.sharelp.R.layout;
import com.example.sharelp_contestinform.Inform_Schoolin_Activity;
import com.example.sharelp_contestinform.Inform_Schoolout_Activity;
import com.example.sharelp_sharelp.Sharelp_Help_Activity;
import com.example.sharelp_sharelp.Sharelp_Share_Activity;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
/**
 * chuangqingchun
 * 校内通知&官方通知
 * @author Administrator
 *
 */

public class Contest_Inform_Tab_Activity extends TabActivity{
	
	private TabHost tabHost;
	private Intent intent_share,intent_help;
	private TabHost.TabSpec spec_share,spec_help;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sharelp_tab);//布局不用改

		tabHost=this.getTabHost(); 

		intent_share=new Intent().setClass(this, Inform_Schoolin_Activity.class);  //跳到具体某个activity
		spec_share=tabHost.newTabSpec("tab_share").setIndicator("校内通知").setContent(intent_share);  
		tabHost.addTab(spec_share);
		//tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.a);


		intent_help=new Intent().setClass(this, Inform_Schoolout_Activity.class);  
		spec_help=tabHost.newTabSpec("tab_help").setIndicator("官方通知").setContent(intent_help);  
		tabHost.addTab(spec_help);
		//tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.c);
		tabHost.setOnTabChangedListener(new OnTabChangedListener()); // 选择监听器       
		
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
			return true; // 返回true就不会弹出默认的setting菜单  
		}  

		return false;  
	}  

	/** 
	 * 更新Tab标签的颜色，和字体的颜色 
	 * @param tabHost 
	 */  
	private void updateTab(final TabHost tabHost) {  
		/*for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {  
			if (tabHost.getCurrentTab() == 0) {//选中  
				tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.a);
				tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.c);
			} 
			else {//不选中  
				tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.a);
				tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.c);
			}  
		}  */
		
		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {  
            View view = tabHost.getTabWidget().getChildAt(i);  
            TextView tv = (TextView) tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);  
            tv.setTextSize(16);  
            tv.setTypeface(Typeface.SERIF, 2); // 设置字体和风格  
            if (tabHost.getCurrentTab() == i) {//选中  
            	 tv.setTextSize(20);  
              //  view.setBackgroundDrawable(getResources().getDrawable(R.drawable.nav_1x));//选中后的背景  
                tv.setTextColor(this.getResources().getColorStateList( android.R.color.black));  
            } else {//不选中  
            	 tv.setTextSize(16);  
         //     view.setBackgroundDrawable(getResources().getDrawable(R.drawable.nav_1y));//非选择的背景  
                tv.setTextColor(this.getResources().getColorStateList(android.R.color.darker_gray));  
            }  
        }  
	}  

	
	

}
