package com.example.sharelp_show;

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

public class Notification_GP_DetailsActivity extends Activity{
	
	public static String NAME;
	public static String GRADE;
	public static String PHOTO;
	public static String CONTENT;
	public static String TEL;
	private ImageView iv_gp_details;
	private TextView tv_gp_detailname, tv_gp_detailmajor ,tv_gp_detailstory,tv_link;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_gp_details);
		
		initView();
	//	Toast.makeText(Notification_GP_DetailsActivity.this, PHOTO, 200).show();
		readInfo();
		readPic(iv_gp_details,PHOTO);
		
	}


	private void initView() {
		iv_gp_details=(ImageView) findViewById(R.id.iv_gp_details);
		tv_gp_detailname=(TextView) findViewById(R.id.tv_gp_detailname);
		tv_gp_detailmajor=(TextView) findViewById(R.id.tv_gp_detailmajor);
		tv_gp_detailstory=(TextView) findViewById(R.id.tv_gp_detailstory);
		tv_link=(TextView) findViewById(R.id.tv_gpdetail_link);
	}


	private void readInfo() {
		
		tv_gp_detailname.setText(NAME);
		tv_gp_detailmajor.setText("רҵ�꼶��"+GRADE);
		tv_gp_detailstory.setText(CONTENT);
		tv_link.setText("Tel:"+TEL);
		
	}


	private void readPic(final ImageView imageView,final String urString) {
		new Thread(){
			public void run() {
				HttpGet get=new HttpGet(urString);
				HttpClient client=new DefaultHttpClient();
				try {
					HttpResponse response=client.execute(get);
					HttpEntity entity=response.getEntity();
					byte[] data = EntityUtils.toByteArray(entity);
					final String string=data.toString();
					
					final Bitmap bitmap=BitmapFactory.decodeByteArray(data, 0, data.length);
					runOnUiThread(new Runnable() {
						
						public void run() {
							Bitmap bitmap2=toRoundBitmap(bitmap);
							imageView.setImageBitmap(bitmap2);
						}
					});
					
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			};
		}.start();
		
	}
	
	
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
	
	
	
	
	
	
	
	
}
