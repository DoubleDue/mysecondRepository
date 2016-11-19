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
		
		//��ȡ���������
		Util_ReadEntity.OLinkParam1(handler, Entity_PersonalTeam[].class, Util_Const.TOME, sno, 10000,2);
		
		//��ȡ ������Ķ���
		Util_ReadEntity.OLinkParam1(handler, Entity_PersonalTeam[].class, Util_Const.METO, sno, 10000,1);
	}





	private void initHandler() {
		handler = new Handler(){
			public void handleMessage(Message msg) {
				if (msg.arg1==200) {
					if (msg.arg2==2) {//��������������ַ���
						
						lv_tomyinform.setAdapter(new Entity_PersonalTeamsTomeAdapter(sharelpApplication,handler,PersonalDataActivity.this,
								(Entity_PersonalTeam[])msg.obj, 
								R.layout.activity_listview_item_cometome,lv_tomyinform));
					}else if (msg.arg2==1) {//����������Ķ���ʵ��
						
						lv_myinformto.setAdapter(new Entity_PersonalTeamsAdapter(PersonalDataActivity.this,
								(Entity_PersonalTeam[])msg.obj, 
								R.layout.activity_listview_item_teams,lv_myinformto));
						
					}else  Toast.makeText(PersonalDataActivity.this, "����ɹ�", 10).show(); ;
					 Toast.makeText(PersonalDataActivity.this, "��ȡ�ɹ�", 10).show();
				}else Toast.makeText(PersonalDataActivity.this, "��ȡʧ��", 10).show();
			}		
		};
	}


	private void initView() {
		sharelpApplication=(SharelpApplication) getApplication();
		//��������ҵ�
		lv_tomyinform=(ListView) findViewById(R.id.lv_tomyinform);
		//����������
		lv_myinformto=(ListView) findViewById(R.id.lv_myinformto);
	}
	

}





/*
public Bitmap toRoundBitmap(Bitmap bitmap) {  
	//Բ��ͼƬ���  
	int width = bitmap.getWidth();  
	int height = bitmap.getHeight();  
	//�����εı߳�  
	int r = 0;  
	//ȡ��̱����߳�  
	if(width > height) {  
		r = height;  
	} else {  
		r = width;  
	}  
	//����һ��bitmap  
	Bitmap backgroundBmp = Bitmap.createBitmap(width,  
			height, Config.ARGB_8888);  
	//newһ��Canvas����backgroundBmp�ϻ�ͼ  
	Canvas canvas = new Canvas(backgroundBmp);  
	Paint paint = new Paint();  
	//���ñ�Ե�⻬��ȥ�����  
	paint.setAntiAlias(true);  
	//�����ȣ���������  
	RectF rect = new RectF(0, 0, r, r);  
	//ͨ���ƶ���rect��һ��Բ�Ǿ��Σ���Բ��X�᷽��İ뾶����Y�᷽��İ뾶ʱ��  
	//�Ҷ�����r/2ʱ����������Բ�Ǿ��ξ���Բ��  
	canvas.drawRoundRect(rect, r/2, r/2, paint);  
	//���õ�����ͼ���ཻʱ��ģʽ��SRC_INΪȡSRCͼ���ཻ�Ĳ��֣�����Ľ���ȥ��  
	paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
	//canvas��bitmap����backgroundBmp��  
	canvas.drawBitmap(bitmap, null, rect, paint);  
	//�����Ѿ��滭�õ�backgroundBmp  
	return backgroundBmp;  
}  
*/

