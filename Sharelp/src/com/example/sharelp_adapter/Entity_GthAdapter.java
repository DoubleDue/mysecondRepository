package com.example.sharelp_adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.sharelp.R;
import com.example.sharelp.R.id;
import com.example.sharelp_entity.Entity_Gth;
import com.example.sharelp_utils.MD5;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
/**
 * 作为优秀作品的实体
 * @author Administrator
 *
 */
public class Entity_GthAdapter extends BaseAdapter {

	private Entity_Gth[] sthEntities;
	private int listviewItem;
	private File cache;
	private LayoutInflater layoutInflater;
	private Uri uri;
	
	 private final int VIEW_TYPE = 2;
	    private final int TYPE_1 = 0;
	    private final int TYPE_2 = 1;
	
	public Entity_GthAdapter(Context context,Entity_Gth[] sthEntities, int listviewItem, File cache) {
		
		this.sthEntities = sthEntities;
		this.listviewItem = listviewItem;
		this.cache = cache;
	    layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		
		if (sthEntities.length<=0) return 1;
		else return sthEntities.length+1;
		
		
	}

	public Entity_Gth getItem(int position) {
		if (sthEntities.length<=0) {
			return null;
		}else {
			return sthEntities[position-1];
		}
	}

	public long getItemId(int position) {
		return position;
	}

	
	
	public int getItemViewType(int position) {
	       
        if(position == 0)
        return TYPE_1;
        else
            return TYPE_2;
        
        
    }
	
	public int getViewTypeCount() {
        return 2;
    }
	
	

	public View getView(int position, View convertView, ViewGroup arg2) {
		
		  int type = getItemViewType(position);
		  
		  if (convertView == null) {
			  
			  switch (type) {
	           
			  case TYPE_1:
				  convertView=layoutInflater.inflate(R.layout.activity_slideshow_gth, arg2, false);
	            	break;
	            	
	            case TYPE_2:
	            	convertView = layoutInflater.inflate(listviewItem, null);
	        		TextView tv_gth_name=(TextView) convertView.findViewById(R.id.tv_gth_name);
	        		TextView tv_gth_prize=(TextView) convertView.findViewById(R.id.tv_gth_prize);
	        		ImageView iv_gth_pic=(ImageView) convertView.findViewById(R.id.iv_gth_pic);
	        		Entity_Gth sthEntity=sthEntities[position-1];
	        		tv_gth_name.setText(sthEntity.getName());
	        		tv_gth_prize.setText(sthEntity.getPrize());
	        	//	asyncImageLoad(iv_gth_pic, sthEntity.getPath());
	            	break;
	            }
		  }
		
		return convertView;
	}

	
	
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

	

/*private void asyncImageLoad(final ImageView imageView, final String path) {
final Handler handler = new Handler(){
	public void handleMessage(Message msg) {//运行在主线程中
		Uri uri = (Uri)msg.obj;
		if(uri!=null && imageView!= null)
			imageView.setImageURI(uri);
	}
};

Runnable runnable = new Runnable() {			
	public void run() {
			
			 try {
				File localFile = new File(cache, MD5.getMD5(path)+ path.substring(path.lastIndexOf(".")));			
				 if(localFile.exists()){
						uri= Uri.fromFile(localFile);
					}else{
						
						HttpURLConnection conn = (HttpURLConnection) new URL(path).openConnection();
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
							uri=Uri.fromFile(localFile);
					}
				
				handler.sendMessage(handler.obtainMessage(10, uri));
}
			} catch (MalformedURLException e) {
			e.printStackTrace();
			} catch (ProtocolException e) {
				e.printStackTrace();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
}
	
};
new Thread(runnable).start();
}
*/







