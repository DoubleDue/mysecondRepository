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

public class Notification_Gth_DetailsActivity extends Activity{
	
	public static String NAME;
	public static String PATH;
	public static String PRIZE;
	public static String AUTHOR;
	public static String INTRODUCTION;
	private ImageView iv_gth_details;
	private TextView tv_gth_detailname, tv_gth_detailprize ,tv_gth_introduction,tv_gth_author;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_notification_gth_details);
		
		initView();
		initData();
		initPic(iv_gth_details,PATH);
	}

	

	private void initPic(final ImageView iv_gth_details2, final String pATH2) {

		new Thread(){
			public void run() {
				HttpGet get=new HttpGet(pATH2);
				HttpClient client=new DefaultHttpClient();
				try {
					HttpResponse response=client.execute(get);
					HttpEntity entity=response.getEntity();
					byte[] data = EntityUtils.toByteArray(entity);
					final String string=data.toString();
					
					final Bitmap bitmap=BitmapFactory.decodeByteArray(data, 0, data.length);
					runOnUiThread(new Runnable() {
						
						public void run() {
							//Bitmap bitmap2=toRoundBitmap(bitmap);
							iv_gth_details2.setImageBitmap(bitmap);
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



	private void initData() {
		tv_gth_detailname.setText("作品名称："+NAME);
		tv_gth_author.setText("作者:"+AUTHOR);
		tv_gth_detailprize.setText("获得奖项："+PRIZE);
		tv_gth_introduction.setText(INTRODUCTION);
		
	}

	private void initView() {
		iv_gth_details=(ImageView) findViewById(R.id.iv_gth_details);
		 tv_gth_detailname=(TextView) findViewById(R.id.tv_gth_detailname);
		 tv_gth_detailprize=(TextView) findViewById(R.id.tv_gth_detailprize);
		 tv_gth_introduction=(TextView) findViewById(R.id.tv_gth_detailintroduction);
		 tv_gth_author=(TextView) findViewById(R.id.tv_gth_detailauthor);
	}
	
	
	public Bitmap toRoundBitmap(Bitmap bitmap) {  
		int width = bitmap.getWidth();  
		int height = bitmap.getHeight();  
		int r = 0;  
		if(width > height) {  
			r = height;  
		} else {  
			r = width;  
		}  
		Bitmap backgroundBmp = Bitmap.createBitmap(width,  
				height, Config.ARGB_8888);  
		Canvas canvas = new Canvas(backgroundBmp);  
		Paint paint = new Paint();  
		paint.setAntiAlias(true);  
		RectF rect = new RectF(0, 0, r, r);  
		canvas.drawRoundRect(rect, r/2, r/2, paint);  
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));  
		canvas.drawBitmap(bitmap, null, rect, paint);  
		return backgroundBmp;  
	}  
	

}
