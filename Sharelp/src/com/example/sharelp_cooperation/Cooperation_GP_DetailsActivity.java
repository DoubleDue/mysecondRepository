package com.example.sharelp_cooperation;

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

public class Cooperation_GP_DetailsActivity extends Activity{
	
	
	private ImageView iv_coo_gp;
	private TextView sno, sex, departgrade, major, phonenum, email, speciality, selfintro, 
	experience, intent,isjoin;
	private Entity_Personals entity_Personal;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cooperation_gp_details);
		
		initView();
		addData();
	}


	private void initView() {
		sno=(TextView) findViewById(R.id.tv_coo_gp_sno);
		sex=(TextView) findViewById(R.id.tv_coo_gp_sex);
		departgrade=(TextView) findViewById(R.id.tv_coo_gp_departandgrade);
		major=(TextView) findViewById(R.id.tv_coo_gp_major);
		phonenum=(TextView) findViewById(R.id.tv_coo_gp_phonumber);
		email=(TextView) findViewById(R.id.tv_coo_gp_email);
		speciality=(TextView) findViewById(R.id.tv_coo_gp_specialize);
		selfintro=(TextView) findViewById(R.id.tv_coo_gp_intro);
		  intent=(TextView) findViewById(R.id.tv_coo_gp_intent);
		  experience=(TextView) findViewById(R.id.tv_coo_gp_expe);
	}


	private void addData() {
		  entity_Personal= (Entity_Personals)getIntent().getSerializableExtra("personal");  
		  sno.setText("姓名："+entity_Personal.getSname());
		  sex.setText("性别："+entity_Personal.getSex());
		  departgrade.setText("院系年级："+entity_Personal.getDepart()+entity_Personal.getGrade());
		  major.setText("专业："+entity_Personal.getMajor());
		  phonenum.setText("联系方式："+entity_Personal.getPhonenum());
		  email.setText("邮箱："+entity_Personal.getEmail());
		  speciality.setText("个人技能："+entity_Personal.getSpeciality());
		  selfintro.setText("个人简介："+entity_Personal.getSelfinfo());
		  intent.setText("组队意向："+entity_Personal.getIntent());
		  experience.setText("参赛经历："+entity_Personal.getExperience());
	}


	
}
