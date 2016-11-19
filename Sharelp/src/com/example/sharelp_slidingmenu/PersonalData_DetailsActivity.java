package com.example.sharelp_slidingmenu;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.sharelp.R;
import com.example.sharelp.R.id;
import com.example.sharelp.R.layout;
import com.example.sharelp_entity.Entity_Personals;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PersonalData_DetailsActivity extends Activity{
	
	
	private ImageView iv_coo_gp;
	private TextView sno, grade, depart, major, phonenum, email, speciality, selfinfo, 
	experience, intent,isjoin;
	private Entity_Personals entity_Personal;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cooperation_gp_details);
		
		initView();
		addData();
	}


	private void initView() {
		iv_coo_gp=(ImageView) findViewById(R.id.iv_coo_gp_photo);
		sno=(TextView) findViewById(R.id.tv_coo_gp_sno);
		major=(TextView) findViewById(R.id.tv_coo_gp_major);
		phonenum=(TextView) findViewById(R.id.tv_coo_gp_phonenum);
		speciality=(TextView) findViewById(R.id.tv_coo_gp_specialize);
		selfinfo=(TextView) findViewById(R.id.tv_coo_gp_intro);
	}


	private void addData() {
		  entity_Personal= (Entity_Personals)getIntent().getSerializableExtra("personal");  
		  sno.setText(entity_Personal.getSno());
		  major.setText(entity_Personal.getMajor());
		  phonenum.setText(entity_Personal.getPhonenum());
		  speciality.setText(entity_Personal.getSpeciality());
		  selfinfo.setText(entity_Personal.getSelfinfo()+"\n"+entity_Personal.getIntent());
		  
	}


	
}
