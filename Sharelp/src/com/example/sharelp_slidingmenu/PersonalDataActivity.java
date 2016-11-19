package com.example.sharelp_slidingmenu;

import com.example.sharelp.R;
import com.example.sharelp.R.layout;
import com.example.sharelp_adapter.Entity_PersonalTeamsAdapter;
import com.example.sharelp_adapter.Entity_PersonalTeamsTomeAdapter;
import com.example.sharelp_adapter.Entity_TeamsAdapter;
import com.example.sharelp_cooperation.Cooperation_Team_Activity;
import com.example.sharelp_entity.Entity_PersonalTeam;
import com.example.sharelp_entity.Entity_Teams;
import com.example.sharelp_utils.DownloadPho;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.Util_ReadEntity;
import com.example.sharelp_utils.Util_TransParams;
import com.example.sharelp.SharelpApplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class PersonalDataActivity extends Activity {
	
	private SharelpApplication sharelpApplication;
	private Handler handler;
	private ListView lv_tomyinform,lv_myinformto;
	private String sno;
	
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personaldata);
		
		initView();
		initHandler();
		initData();
		
	}
	
	

	private void initData() {
		// TODO Auto-generated method stub
		sno=sharelpApplication.getSno();
		
		//获取向我请求的
		Util_ReadEntity.OLinkParam1(handler, Entity_PersonalTeam[].class, Util_Const.TOME, sno, 10000,2);
		
		//获取 我请求的队伍
		Util_ReadEntity.OLinkParam1(handler, Entity_PersonalTeam[].class, Util_Const.METO, sno, 10000,1);
	}





	private void initHandler() {
		handler = new Handler(){
			public void handleMessage(Message msg) {
				if (msg.arg1==200) {
					if (msg.arg2==2) {//返回向我请求的字符串
						
						lv_tomyinform.setAdapter(new Entity_PersonalTeamsTomeAdapter(sharelpApplication,handler,PersonalDataActivity.this,
								(Entity_PersonalTeam[])msg.obj, 
								R.layout.activity_listview_item_cometome,lv_tomyinform));
					}else if (msg.arg2==1) {//返回我请求的队伍实体
						
						lv_myinformto.setAdapter(new Entity_PersonalTeamsAdapter(PersonalDataActivity.this,
								(Entity_PersonalTeam[])msg.obj, 
								R.layout.activity_listview_item_teams,lv_myinformto));
						
					}else  Toast.makeText(PersonalDataActivity.this, "抉择成功", 10).show(); ;
					 Toast.makeText(PersonalDataActivity.this, "读取成功", 10).show();
				}else Toast.makeText(PersonalDataActivity.this, "读取失败", 10).show();
			}		
		};
	}


	private void initView() {
		sharelpApplication=(SharelpApplication) getApplication();
		//申请加入我的
		lv_tomyinform=(ListView) findViewById(R.id.lv_tomyinform);
		//我申请加入的
		lv_myinformto=(ListView) findViewById(R.id.lv_myinformto);
	}
	

}





/*
public Bitmap toRoundBitmap(Bitmap bitmap) {  
	//圆形图片宽高  
	int width = bitmap.getWidth();  
	int height = bitmap.getHeight();  
	//正方形的边长  
	int r = 0;  
	//取最短边做边长  
	if(width > height) {  
		r = height;  
	} else {  
		r = width;  
	}  
	//构建一个bitmap  
	Bitmap backgroundBmp = Bitmap.createBitmap(width,  
			height, Config.ARGB_8888);  
	//new一个Canvas，在backgroundBmp上画图  
	Canvas canvas = new Canvas(backgroundBmp);  
	Paint paint = new Paint();  
	//设置边缘光滑，去掉锯齿  
	paint.setAntiAlias(true);  
	//宽高相等，即正方形  
	RectF rect = new RectF(0, 0, r, r);  
	//通过制定的rect画一个圆角矩形，当圆角X轴方向的半径等于Y轴方向的半径时，  
	//且都等于r/2时，画出来的圆角矩形就是圆形  
	canvas.drawRoundRect(rect, r/2, r/2, paint);  
	//设置当两个图形相交时的模式，SRC_IN为取SRC图形相交的部分，多余的将被去掉  
	paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
	//canvas将bitmap画在backgroundBmp上  
	canvas.drawBitmap(bitmap, null, rect, paint);  
	//返回已经绘画好的backgroundBmp  
	return backgroundBmp;  
}  
*/

