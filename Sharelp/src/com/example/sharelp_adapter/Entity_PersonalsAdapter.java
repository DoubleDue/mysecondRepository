package com.example.sharelp_adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.sharelp.R;
import com.example.sharelp.R.id;
import com.example.sharelp_cooperation.Cooperation_GP_DetailsActivity;
import com.example.sharelp_entity.Entity_Gth;
import com.example.sharelp_entity.Entity_Personals;
import com.example.sharelp_entity.Entity_Share;
import com.example.sharelp_utils.MD5;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 作为优秀作品的实体
 * @author Administrator
 *
 */
public class Entity_PersonalsAdapter extends BaseAdapter implements OnItemClickListener{

	private Entity_Personals[] entity_Personals;
	private LayoutInflater layoutInflater;
	private Context context;
	private ListView listView;



	public Entity_PersonalsAdapter(Context context,Entity_Personals[] entity_Personals, 
		 File cache,ListView listView) {
		this.listView=listView;
		this.context=context;
		this.entity_Personals = entity_Personals;
		layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {

		return entity_Personals.length;


	}

	public Entity_Personals getItem(int position) {

		return entity_Personals[position];

	}

	public long getItemId(int position) {
		return position;
	}






	public View getView(int position, View convertView, ViewGroup arg2) {

		convertView = layoutInflater.inflate(R.layout.activity_listview_item_personals, null);
		TextView tv_personal_name=(TextView) convertView.findViewById(R.id.tv_personals_name);
		TextView tv_personal_grade=(TextView) convertView.findViewById(R.id.tv_personals_grade);
		TextView tView=(TextView) convertView.findViewById(R.id.tv_personals_special);
		ImageView iv_gth_pic=(ImageView) convertView.findViewById(R.id.iv_personals_pic);
		Entity_Personals entity_Personal=entity_Personals[position];
		tv_personal_name.setText(entity_Personal.getSname());
		tv_personal_grade.setText(entity_Personal.getGrade());
		tView.setText(entity_Personal.getMajor());
		listView.setOnItemClickListener(Entity_PersonalsAdapter.this);
		return convertView;
	}

	//添加点击事件
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
		// TODO Auto-generated method stub
		Entity_Personals entity_Personal=entity_Personals[position];
		
		  Intent mIntent = new Intent(context,Cooperation_GP_DetailsActivity.class);     
	        Bundle mBundle = new Bundle();     
	        mBundle.putSerializable("personal",entity_Personal);     
	        mIntent.putExtras(mBundle);     
	       context.startActivity(mIntent);   
		
		//Toast.makeText(context, entity_Personal.getSno(), 10).show();
	}

	
	

	

}






