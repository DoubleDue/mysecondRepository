package com.example.sharelp_contestinform;

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
import com.example.sharelp_entity.Entity_Gth;
import com.example.sharelp_entity.Entity_Personals;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Bitmap.Config;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Inform_DetailsActivity extends Activity{

	private Entity_Gth entity_Gth;
	private ImageView iv_gth_details;
	private TextView inform_details_title, inform_details_author ,inform_details_time,inform_details_content;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_informdetails);
	
		initView();
		initData();
	}

	
	private void initView() {
		iv_gth_details=(ImageView) findViewById(R.id.picture);
		inform_details_title=(TextView) findViewById(R.id.inform_details_title);
		 inform_details_author=(TextView) findViewById(R.id.inform_details_author);
		 inform_details_time=(TextView) findViewById(R.id.inform_details_time);
		 inform_details_content=(TextView) findViewById(R.id.inform_details_content);
	}
	


	private void initData() {
		
		  entity_Gth= (Entity_Gth)getIntent().getSerializableExtra("gth"); 


		    //ѹ�������ڽ�ʡBITMAP�ڴ�ռ�--���BUG�Ĺؼ�����   
		     BitmapFactory.Options opts = new BitmapFactory.Options();  
		    opts.inSampleSize = 2;    //�����ֵѹ���ı�����2��������������ֵԽС��ѹ����ԽС��ͼƬԽ����   
		      
		    //����ԭͼ����֮���bitmap����   
		  Bitmap   bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon_cqc, opts);  
		  
		  iv_gth_details.setImageBitmap(bitmap);
		  inform_details_title.setText("���⣺"+entity_Gth.getName());
		  inform_details_author.setText("����:"+entity_Gth.getAuthor());
		  inform_details_time.setText("����ʱ�䣺"+entity_Gth.getPrize());
		  inform_details_content.setText("���ݣ�"+entity_Gth.getIntroduction());
		
	}

	
	

}
