package com.example.sharelp_tab;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharelp.R;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp_cooperation.Cooperation_Contest_Activity;
import com.example.sharelp_sharelp.Sharelp_Share_Activity;
import com.example.sharelp_sharelp.Sharelp_Share_Edit_Activity;
import com.example.sharelp_slidingmenu.AboutUsActivity;
import com.example.sharelp_slidingmenu.CollectionActivity;
import com.example.sharelp_slidingmenu.LoginActivity;
import com.example.sharelp_slidingmenu.PersonalDataActivity;
import com.example.sharelp_slidingmenu.SettingsActivity;
import com.example.sharelp_utils.CurrentVersion;
import com.example.sharelp_utils.Dialog_newVersion;
import com.example.sharelp_utils.DownloadPho;
import com.example.sharelp_utils.Util_Const;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 本项目暂时取消了sharelp——tab模块的两个功能，直接使用了sharelp的share模块；
 * @author Administrator
 *
 */
public class TabHostActivity extends TabActivity implements OnItemClickListener,OnClickListener{

	private SharelpApplication sharelpApplication;


	/*底部导航栏*/
	private TabHost tabHost;
	private Intent notificationIntent;
	private Intent cooperationIntent;
	private Intent sharelpIntent;	

	/*侧滑栏*/
	private SlidingMenu slidingMenu;

	/* slidingmenue 列表*/
	private ListView listview_slidingmenu;
	private ArrayAdapter<String> adapter_slidingmenu;

	private ImageView iv_icon_slidingmenu;
	private TextView icon_username;
	private TabHost.TabSpec spec_notification,spec_cooperation,spec_sharelp;
	private Handler tabhandler;
	private Bitmap bitmap;

	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tabhost);

		initNavContent();	          /*加载底部导航栏*/			
		initHandler();
			
	
		initSlidingMenu();   /*加载侧滑栏*/
		initWiget();				
		initSlidingMenuContent();     /*加载侧滑栏内容*/
		//检测新版本
				if (!sharelpApplication.isState()) {
					try {
						//检查version信息
						getUpdataVersionJSON(Util_Const.VERJSON, tabhandler);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
		listview_slidingmenu.setOnItemClickListener(this);	
		iv_icon_slidingmenu.setOnClickListener(this);


		//	

	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.share, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		
		Toast.makeText(TabHostActivity.this, "模块功能研发中，敬请期待...", 100).show();

		/*if (sharelpApplication.isState()) {
			Intent intent=new Intent(TabHostActivity.this, Sharelp_Share_Edit_Activity.class);
			startActivity(intent);
		}else {
			AlertDialog alert = new AlertDialog.Builder(TabHostActivity.this).create();
			alert.setTitle("登陆提示");
			alert.setMessage("您还没有登陆无法使用该功能，现在登陆？");

			alert.setButton(DialogInterface.BUTTON_NEGATIVE, "登陆",
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent	 intent=new Intent(TabHostActivity.this, LoginActivity.class);
					finish();
					startActivity(intent);
				}
			});

			alert.setButton(DialogInterface.BUTTON_POSITIVE, "放弃",
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {

				}
			});				
			alert.show();			
		}*/

		return super.onOptionsItemSelected(item);


	}


	private void initHandler() {
		tabhandler = new Handler(){
			public void handleMessage(Message msg) {
				
				if (msg.arg1==0) {//获取到了Jason数据
					
					String info=msg.obj.toString();
					int  newVercode = 0;
					String newVerName = null;
					String appname = null;
					String apkname=null;
					String introduction=null;
					try {
						//解析json
						JSONArray jsonArray=new JSONArray(info);
						if (jsonArray.length()>0) {
							JSONObject object=jsonArray.getJSONObject(0);
								newVercode=Integer.parseInt(object.getString("verCode"));//获取版本号
								newVerName=object.getString("verName");//获取版本名字例1.0.1
								appname=object.getString("appname");//获取app的名字
								apkname=object.getString("apkname");//获取apk的名字
								introduction=object.getString("introduction");
						}

						//检查
						int currentCode=CurrentVersion.getVerCode(TabHostActivity.this);//获取本版本的code
						String verName=CurrentVersion.getVerName(TabHostActivity.this);//获取本版本名字
						if (newVercode>currentCode) {
							
							Dialog_newVersion.NewVersionShow(TabHostActivity.this,tabhandler,verName,newVerName, appname,apkname,introduction);
						//	Toast.makeText(TabHostActivity.this, "new Version", 10).show();
						}
					} catch (NumberFormatException e) {
						e.printStackTrace();
					} catch (NameNotFoundException e) {
						e.printStackTrace();
					} catch (JSONException e) {
						e.printStackTrace();
					}

					
					
				}else {
					bitmap=(Bitmap)(msg.obj);
					iv_icon_slidingmenu.setImageBitmap(bitmap);
				}
				
			}		
		};
	}



	private  void initWiget(){
		//侧滑栏头像，并为其添加了触摸事件
		iv_icon_slidingmenu=(ImageView) findViewById(R.id.iv_icon_slidingmenu);


	//	Bitmap bitmap=	BitmapFactory.decodeResource(getResources(), R.drawable.sliding_touxiang_yezi);
	//	bitmap=toRoundBitmap(bitmap);
		//iv_icon_slidingmenu.setImageBitmap(bitmap);
		iv_icon_slidingmenu.setImageResource(R.drawable.sliding_touxiang_yezi);
		listview_slidingmenu=(ListView) findViewById(R.id.listview_slidingmenu);
		icon_username=(TextView) findViewById(R.id.icon_username);
		//判断是否已登录以修改头像与否
		
		sharelpApplication=(SharelpApplication) getApplication();
		//--------------------------------------判断登陆与否以修改状态
		if(sharelpApplication.isState()){
			icon_username.setText(sharelpApplication.getSname());
			/*bitmap=	BitmapFactory.decodeResource(getResources(), R.drawable.logined);
			bitmap=toRoundBitmap(bitmap);*/
			
			//加载头像
		//	DownloadPho downloadpic=new DownloadPho(tabhandler, sharelpApplication.getPhoto());
		//	downloadpic.start();

			
//			try {
//				Thread.sleep(1000);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		//	iv_icon_slidingmenu.setImageBitmap(bitmap);
		//	Log.e("5555555", sharelpApplication.getPhoto());
		}
	}


	/*Sli加载侧滑栏*/
	public void initSlidingMenu(){	
		slidingMenu=new SlidingMenu(this);
		slidingMenu.setMode(slidingMenu.LEFT);
		slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_width);
		slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		slidingMenu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		//--------------*************加载slidingmenu的地方，全局变量修改头像---------------

		slidingMenu.setMenu(R.layout.activity_slidingmenu);	

	}



	/* 初始化加载底部导航栏*/
	public void initNavContent(){		
		tabHost=this.getTabHost(); 
		Intent intent_notification,intent_cooperation,intent_sharelp;

/*
		intent_notification=new Intent().setClass(this, Notification_Tab_Activity.class);  
		spec_notification=tabHost.newTabSpec("tab_notification").setIndicator("").setContent(intent_notification);  
		tabHost.addTab(spec_notification);
		tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.nav_1y);
*/
		//getChildAt()内的参数由1改为0；
		intent_cooperation=new Intent().setClass(this, Cooperation_Contest_Activity.class);  
		spec_cooperation=tabHost.newTabSpec("tab_cooperation").setIndicator("").setContent(intent_cooperation);  
		tabHost.addTab(spec_cooperation);
		tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.nav_2x);
		
		
		/*
		 * 此处的Sharelp_Share_Activity改为Sharelp_Tab_Activity即可启动两个模块的功能
		 */
	/*	intent_sharelp=new Intent().setClass(this, Sharelp_Share_Activity.class);  
		spec_sharelp=tabHost.newTabSpec("tab_sharelp").setIndicator("").setContent(intent_sharelp);  
		tabHost.addTab(spec_sharelp);		        
		//添加背景图片；
		tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.nav_3x);
		
	*/
		tabHost.setOnTabChangedListener(new OnTabChangedListener()); // 选择监听器    

	}

	/*加载侧滑栏列表项listview*/
	public void initSlidingMenuContent(){		
		adapter_slidingmenu=new ArrayAdapter<String>(this,R.layout.activity_listview_item);		

		listview_slidingmenu.setAdapter(adapter_slidingmenu);
		adapter_slidingmenu.add("		我的通知");
		adapter_slidingmenu.add("		我的帖子");
		adapter_slidingmenu.add("		我的设置");
		adapter_slidingmenu.add("		关于我们");		
	}






	/*添加列表点击事件*/

	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {	
		Intent intent;

		switch (position) {
		case 0://进入策划菜单的个人通知
			if (sharelpApplication.isPersonlized()&&sharelpApplication.isState()) {
				intent=new Intent(TabHostActivity.this,PersonalDataActivity.class);
				startActivity(intent);
			}else Toast.makeText(TabHostActivity.this, "你还未登录或填写简历", 10).show();
			
			break;
			/*	case 1:
			intent=new Intent(TabHostActivity.this,CollectionActivity.class);
			startActivity(intent);
			break;*/
		case 2:
			intent=new Intent(TabHostActivity.this,SettingsActivity.class);
			startActivity(intent);
			break;
			/*	case 3:
			intent=new Intent(TabHostActivity.this,AboutUsActivity.class);
			startActivity(intent);
			break;*/

		default:
			Toast.makeText(TabHostActivity.this, "模块功能研发中，敬请期待...", 100).show();
			break;
		}

	}


	public void onClick(View view) {
		Intent intent;

		switch (view.getId()) {
		case R.id.iv_icon_slidingmenu:
		//	Toast.makeText(TabHostActivity.this, "您目前还不需要此功能，敬请期待...", 100).show();

			//未登录
			if (!sharelpApplication.isState()) {
				intent=new Intent(TabHostActivity.this, LoginActivity.class);
				//finish();
				startActivity(intent);
			}else {
			//	intent=new Intent(TabHostActivity.this, PersonalDataActivity.class);
			//	startActivity(intent);
			}


			break;

		default:
			break;
		}				
	}	



	class OnTabChangedListener implements OnTabChangeListener {  	
		public void onTabChanged(String tabId) {  
			tabHost.setCurrentTabByTag(tabId);  
			//Toast.makeText(TabHostActivity.this, tabId, 100).show();
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
		for (int i = 0; i < tabHost.getTabWidget().getChildCount(); i++) {  
			if (tabHost.getCurrentTab() == 0) {//选中  

				tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.nav_1y);
				tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.nav_2x);
				tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.nav_3x);

			} 
			else if(tabHost.getCurrentTab() == 1) {
				tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.nav_1x);
				tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.nav_2y);
				tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.nav_3x);				
			}
			else {//不选中  
				tabHost.getTabWidget().getChildAt(0).setBackgroundResource(R.drawable.nav_1x);
				tabHost.getTabWidget().getChildAt(1).setBackgroundResource(R.drawable.nav_2x);
				tabHost.getTabWidget().getChildAt(2).setBackgroundResource(R.drawable.nav_3y);
			}  
		}  
	}  

	
	
	
	//获取服务器json数据检查新版本
	
		public void getUpdataVersionJSON(final String serverpath,final Handler handler)throws Exception{


			new Thread(){
				@Override
				public void run() {

					Message msg =Message.obtain();//取一个
					try {
						StringBuilder newverjson=new StringBuilder();
						HttpClient client=new DefaultHttpClient();//xinjian客户端
						HttpParams httpParams=client.getParams();
						HttpConnectionParams.setConnectionTimeout(httpParams, 5000);
						HttpConnectionParams.setSoTimeout(httpParams, 6000);
						//serverpath是ver.json的路径
						HttpResponse response=client.execute(new HttpGet(serverpath));
						HttpEntity entity=response.getEntity();
						if (entity!=null) {
							BufferedReader reader=new  BufferedReader(new InputStreamReader(entity.getContent(), "utf-8"), 8192);
							String line=null;
							while ((line=reader.readLine())!=null) {
								newverjson.append(line+"\n");//按行读取放入stringbuilder中
							}
							reader.close();
						}
						msg.arg1=0;
						msg.obj=newverjson;
						handler.sendMessage(msg);

					} catch (ClientProtocolException e) {
						e.printStackTrace();
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
				};
			}.start();


		}

}
