package com.example.sharelp_adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.sharelp.R;
import com.example.sharelp.R.id;
import com.example.sharelp_cooperation.Cooperation_GP_DetailsActivity;
import com.example.sharelp_cooperation.Cooperation_Team_DetailActivity;
import com.example.sharelp_cooperation.Cooperation_Team_DetailsActivity;
import com.example.sharelp_entity.Entity_Gth;
import com.example.sharelp_entity.Entity_Personals;
import com.example.sharelp_entity.Entity_Share;
import com.example.sharelp_entity.Entity_Teams;
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
public class Entity_TeamsAdapter extends BaseAdapter implements OnItemClickListener{

	private Entity_Teams[] entity_Teams;
	private LayoutInflater layoutInflater;
	private Context context;
	private ListView listView;



	public Entity_TeamsAdapter(Context context, Entity_Teams[] entity_Teams, 
			 File cache,ListView listView) {
		this.listView=listView;
		this.context=context;
		this.entity_Teams = entity_Teams;
		layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {

		return entity_Teams.length;


	}

	public Entity_Teams getItem(int position) {

		return entity_Teams[position];

	}

	public long getItemId(int position) {
		return position;
	}






	public View getView(int position, View convertView, ViewGroup arg2) {

		convertView = layoutInflater.inflate(R.layout.activity_listview_item_teams, null);
		TextView tv_teams_name=(TextView) convertView.findViewById(R.id.tv_teams_name);
		TextView tv_teams_grade=(TextView) convertView.findViewById(R.id.tv_teams_grade);
		ImageView iv_teams_pic=(ImageView) convertView.findViewById(R.id.iv_teams_pic);
		Entity_Teams entity_Team=entity_Teams[position];
		tv_teams_name.setText(entity_Team.getTeamname());
		tv_teams_grade.setText(entity_Team.getCategory());
		listView.setOnItemClickListener(Entity_TeamsAdapter.this);
		return convertView;
	}

	//添加点击事件
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
		// TODO Auto-generated method stub
		Entity_Teams entity_Team=entity_Teams[position];
		
		  Intent mIntent = new Intent(context,Cooperation_Team_DetailActivity.class);     
	        Bundle mBundle = new Bundle();     
	        mBundle.putSerializable("team",entity_Team);     
	        mIntent.putExtras(mBundle);     
	       context.startActivity(mIntent);   
		
		//Toast.makeText(context, entity_Personal.getSno(), 10).show();
	}

	
	

	

}






