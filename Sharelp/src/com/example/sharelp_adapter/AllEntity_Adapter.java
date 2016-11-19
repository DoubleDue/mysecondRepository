package com.example.sharelp_adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.sharelp.R;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp_entity.Entity_AllContent;
import com.example.sharelp_entity.Entity_Comments;
import com.example.sharelp_entity.Entity_Share;
import com.example.sharelp_utils.MD5;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AllEntity_Adapter extends BaseAdapter{

	
	
		private Entity_Comments[] entity_Comments;
		private Entity_Share entity_Share;
		private File cache;
		private Uri uri;
		private Context mContext;
	    private LinearLayout linearLayout = null;
	    private LayoutInflater inflater;
	    private TextView tex;
	    
	    
	    private final int VIEW_TYPE = 3;
	    private final int TYPE_1 = 0;
	    private final int TYPE_2 = 1;
	    private final int TYPE_3 = 2;
	
	    
	    public AllEntity_Adapter(Context context,Entity_Share entity_Share,Entity_Comments[] entity_Comments,File cahe) {
	        this.mContext = context;
	        this.cache=cahe;
	        this.entity_Comments=entity_Comments;
	        this.entity_Share=entity_Share;
	        inflater = LayoutInflater.from(mContext);
	    }
	
	
	    
	    //控制循环几次
	@Override
	public int getCount() {
		if (entity_Comments.length<=0) {
			return 2;
		}else {
			return entity_Comments.length+2;
		}

	}

	@Override
	public Object getItem(int position) {
		if (entity_Comments.length<=0) {
			return entity_Share;
		}else {
			return entity_Comments[position-2];
		}
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	
	
	public int getItemViewType(int position) {
       
		//设置不同item样式的条件
		//int viewtype = position%6;
        if(position == 0)
        return TYPE_1;
        else if(position ==1)
            return TYPE_2;
        else
            return TYPE_3;
        
    }
	
	public int getViewTypeCount() {
        return 3;
    }
     

	@Override
	 public View getView(int position, View convertView, ViewGroup parent) {
        viewHolder1 holder1 = null;
        viewHolder2 holder2 = null;
        viewHolder3 holder3 = null;
        
        int type = getItemViewType(position);
 
        // 无convertView，需要new出各个控件
        if (convertView == null) {
            // 按当前所需的样式，确定new的布局
            switch (type) {
            case TYPE_1:
                convertView = inflater.inflate(R.layout.activity_comment_list_item_author, parent,false);
                holder1 = new viewHolder1();
                holder1.iv_author = (ImageView) convertView.findViewById(R.id.iv_share_detailphoto);
                holder1.tv_authorname = (TextView) convertView.findViewById(R.id.tv_share_detailname);
                Log.e("convertView = ", "布局样式一");
                convertView.setTag(holder1);
               
                break;
            case TYPE_2:
                convertView = inflater.inflate(R.layout.activity_comment_list_item_content, parent,false);
                holder2 = new viewHolder2();
                holder2.tv_title = (TextView) convertView.findViewById(R.id.tv_share_detailtitle);
                holder2.tv_content=(TextView) convertView.findViewById(R.id.tv_share_detailcontent);
                Log.e("convertView = ", "布局样式二");
                convertView.setTag(holder2);
                break;
            case TYPE_3:
                convertView = inflater.inflate(R.layout.activity_comment_list_item_critic, parent,false);
                holder3 = new viewHolder3();
                holder3.tv_criticname = (TextView) convertView.findViewById(R.id.tv_commentname);
                holder3.tv_critic=(TextView) convertView.findViewById(R.id.tv_commentcontent);
                holder3.iv_criticphoto = (ImageView) convertView.findViewById(R.id.iv_commentphoto);
                Log.e("convertView = ", "布局样式三");
                convertView.setTag(holder3);
                break;
            }
        } else {
            // 有convertView，按样式，取得不用的布局
            switch (type) {
            case TYPE_1:
                holder1 = (viewHolder1) convertView.getTag();
                Log.e("convertView= ", "布局样式一");
                break;
            case TYPE_2:
                holder2 = (viewHolder2) convertView.getTag();
                Log.e("convertView= ", "布局样式二");
                break;
            case TYPE_3:
                holder3 = (viewHolder3) convertView.getTag();
                Log.e("convertView= ", "布局样式三");
                break;
            }
        }
 
        // 设置资源
        switch (type) {
        case TYPE_1:
            holder1.tv_authorname.setText(entity_Share.getName());
           // asyncImageLoad(holder1.iv_author, entity_Share.getPhoto());
            break;
        case TYPE_2:
           holder2.tv_title.setText(entity_Share.getTitle());
            holder2.tv_content.setText(entity_Share.getContent());
            break;
        case TYPE_3:
      
        		try {
    				holder3.tv_criticname.setText(entity_Comments[position-2].getName());
                    holder3.tv_critic.setText(entity_Comments[position-2].getComment());
                 //   asyncImageLoad(holder3.iv_criticphoto, entity_Comments[position-2].getPhoto());
    			} catch (Exception e) {
    				e.printStackTrace();
    			}

            break;
        }
 
        return convertView;
    }
     
    // 各个布局的控件资源
    class viewHolder1 {
        ImageView iv_author;
        TextView tv_authorname;
    }
 
    class viewHolder2 {
        TextView tv_title;
        TextView tv_content;
    }
 
    class viewHolder3 {
        ImageView iv_criticphoto;
        TextView tv_critic;
        TextView tv_criticname;
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
