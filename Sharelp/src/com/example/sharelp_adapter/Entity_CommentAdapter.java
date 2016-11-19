package com.example.sharelp_adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.sharelp.R;
import com.example.sharelp_entity.Entity_Comments;
import com.example.sharelp_entity.Entity_Gth;
import com.example.sharelp_utils.MD5;

public class Entity_CommentAdapter extends BaseAdapter{
	
	private Entity_Comments[] entity_Commentses;
	private int listviewItem;
	private File cache;
	private LayoutInflater layoutInflater;
	private Uri uri;
	
	public Entity_CommentAdapter(Context context,Entity_Comments[] entity_Commentses, int listviewItem, File cache) {
		this.entity_Commentses = entity_Commentses;
		this.listviewItem = listviewItem;
		this.cache = cache;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}

	public int getCount() {
		return entity_Commentses.length;
	}

	public Entity_Comments getItem(int position) {
		return entity_Commentses[position];
	}

	public long getItemId(int position) {
		return position;
	}

	

	public View getView(int position, View convertView, ViewGroup arg2) {
		convertView = layoutInflater.inflate(listviewItem, null);
		TextView tv_commentname=(TextView) convertView.findViewById(R.id.tv_commentname);
		TextView tv_commentcontent=(TextView) convertView.findViewById(R.id.tv_commentcontent);
		ImageView iv_commentphoto=(ImageView) convertView.findViewById(R.id.iv_commentphoto);
		Entity_Comments entity_Comments=entity_Commentses[position];
		tv_commentname.setText(entity_Comments.getName());
		tv_commentcontent.setText(entity_Comments.getComment());
		asyncImageLoad(iv_commentphoto, entity_Comments.getPhoto());
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
