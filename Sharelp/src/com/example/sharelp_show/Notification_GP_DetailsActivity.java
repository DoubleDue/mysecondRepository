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
		tv_gp_detailmajor.setText("专业年级："+GRADE);
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
	
	
	
	
	
	
	
	
}
