package com.example.sharelp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.sharelp_tab.TabHostActivity;

public class WelcomeActivity extends Activity {




	private static final int TIME=2000;
	private static final int GO_HOME=1000;




	private Handler mHandler=new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch(msg.what){
			
			case GO_HOME:
				goHome();
				break;

			default: 
				return;

			}
		};
	};


	private void goHome(){

		Intent intent=new Intent(WelcomeActivity.this,TabHostActivity.class);
		WelcomeActivity.this.finish();
		startActivity(intent);
		
	}








	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		mHandler.sendEmptyMessageDelayed(GO_HOME, TIME);

	}





}
