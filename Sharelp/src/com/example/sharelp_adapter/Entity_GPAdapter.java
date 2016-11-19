package com.example.sharelp_adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sharelp.R;
import com.example.sharelp_entity.Entity_GP;
import com.example.sharelp_utils.MD5;
public class Entity_GPAdapter extends BaseAdapter{

	private Entity_GP[] entity_GPs;
	private int listviewItem;
	private File cache;
	private LayoutInflater layoutInflater;
	private Uri uri;
	private Activity activity;
	private Bitmap bitmap;
//	private DowmloadPicThread dowmloadPicThread;
	private Handler handler;
	private ImageView iv_gp_photo;
	
	public Entity_GPAdapter(Context context,Entity_GP[] entity_GPs, int listviewItem, File cache) {
		this.entity_GPs = entity_GPs;
		this.listviewItem = listviewItem;
		this.cache = cache;
	//	initHandler();
		activity=(Activity) context;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	public int getCount() {
		return entity_GPs.length;
	}

	public Entity_GP getItem(int position) {
		return entity_GPs[position];
	}

	public long getItemId(int position) {
		return position;
	}

	

	public View getView(int position, View convertView, ViewGroup arg2) {
		convertView = layoutInflater.inflate(listviewItem, null);
		TextView tv_gp_name=(TextView) convertView.findViewById(R.id.tv_gp_name);
		TextView tv_gp_grade=(TextView) convertView.findViewById(R.id.tv_gp_grade);
		iv_gp_photo=(ImageView) convertView.findViewById(R.id.iv_gp_photo);
		Entity_GP entity_GP=entity_GPs[position];
		tv_gp_name.setText(entity_GP.getName());
		tv_gp_grade.setText(entity_GP.getGrade());
		Log.e("dsfa", "asfkjl");
		//dowmloadPicThread=new DowmloadPicThread(iv_gp_photo, entity_GP.getPhoto(),handler);
	//	dowmloadPicThread.start();
		asyncImageLoad(iv_gp_photo, entity_GP.getPhoto());
		return convertView;
	}
	
	
	
	
	
	
/*
	private void initHandler() {
		handler = new Handler(){
			public void handleMessage(Message msg) {
				Log.e("nis","ues");
			byte[] buffer=(byte[]) msg.obj;
		Bitmap bitmap=	BitmapFactory.decodeByteArray(buffer, 0, buffer.length);
		iv_gp_photo.setImageBitmap(bitmap);
	//	iv_gp_photo.setImageResource(R.drawable.hanghaii);
			}		
		};
	}
*/
	
	
	
	private void asyncImageLoad(ImageView imageView, String path) {
		AsyncImageTask asyncImageTask = new AsyncImageTask(imageView);
		asyncImageTask.execute(path);


	}
	
	private final class AsyncImageTask extends AsyncTask<String, Integer, Uri>{
		private ImageView imageView;
		public AsyncImageTask(ImageView imageView) {
			this.imageView = imageView;
		}
		protected Uri doInBackground(String... params) {//子线程中执行的
			try {
				File localFile = new File(cache, MD5.getMD5(params[0])+ params[0].substring(params[0].lastIndexOf(".")));			
				if(localFile.exists()){
					return Uri.fromFile(localFile);
				}
				else{

					HttpURLConnection conn = (HttpURLConnection) new URL(params[0]).openConnection();
					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");
					if(conn.getResponseCode() == 200){
						FileOutputStream outStream = new FileOutputStream(localFile);
						InputStream inputStream = conn.getInputStream();
						byte[] buffer = new byte[1024];
						int len = 0;
						while( (len = inputStream.read(buffer)) != -1){
							outStream.write(buffer, 0, len);
						}
						inputStream.close();
						outStream.close();
						return Uri.fromFile(localFile);
					}

				}

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}
		protected void onPostExecute(Uri result) {//运行在主线程
			if(result!=null && imageView!= null)
							
				imageView.setImageURI(result);
		}	
	}
	


}

//未调用
	/*class DowmloadPicThread extends Thread{
		
		ImageView imageView;
		String path;
		byte[] buffer;
		Handler handler;
		
		public  DowmloadPicThread(ImageView imageView, String path,Handler handler){
			this.imageView=imageView;
			this.path=path;
			this.handler=handler;
			Log.e("iii", "iii");
			
		}
	
			public void run() {
				
				Log.e("dyyyy", "iii");
				try {
					HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
					conn.setConnectTimeout(5000);
					conn.setRequestMethod("GET");
					Log.e("wwww", "iii");
					if(conn.getResponseCode() == 200){
						Log.e("jjjj", "iii");
						InputStream inputStream = conn.getInputStream();
						buffer = new byte[1024];
						int len = 0;
						while( (len = inputStream.read(buffer)) != -1){
							
						}
						inputStream.close();
					 }
					Log.e("dsf", "af");
					Message msg =Message.obtain();//取一个
					msg.obj=buffer;
					handler.sendMessage(msg);

					
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (ProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
	
	}
}*/