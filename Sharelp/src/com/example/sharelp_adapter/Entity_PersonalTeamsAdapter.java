package com.example.sharelp_adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.sharelp.R;
import com.example.sharelp.R.id;
import com.example.sharelp_cooperation.Cooperation_GP_DetailsActivity;
import com.example.sharelp_cooperation.Cooperation_Team_DetailsActivity;
import com.example.sharelp_entity.Entity_Gth;
import com.example.sharelp_entity.Entity_PersonalTeam;
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
public class Entity_PersonalTeamsAdapter extends BaseAdapter implements OnItemClickListener{

	private Entity_PersonalTeam[] entity_PersonalTeams;
	private int listviewItem;
	private LayoutInflater layoutInflater;
	private Context context;
	private ListView listView;




	public Entity_PersonalTeamsAdapter(Context context, Entity_PersonalTeam[] entity_PersonalTeams, 
			int listviewItem, ListView listView) {
	
		this.listView=listView;
		this.context=context;
		this.entity_PersonalTeams = entity_PersonalTeams;
		this.listviewItem = listviewItem;
		layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {

		return entity_PersonalTeams.length;


	}

	public Entity_PersonalTeam getItem(int position) {

		return entity_PersonalTeams[position];

	}

	public long getItemId(int position) {
		return position;
	}






	public View getView(int position, View convertView, ViewGroup arg2) {

		convertView = layoutInflater.inflate(listviewItem, null);
		TextView tv_teams_name=(TextView) convertView.findViewById(R.id.tv_teams_name);
		TextView tv_teams_grade=(TextView) convertView.findViewById(R.id.tv_teams_grade);
		ImageView iv_teams_pic=(ImageView) convertView.findViewById(R.id.iv_teams_pic);
		Entity_PersonalTeam entity_PersonalTeam=entity_PersonalTeams[position];
		//我去申请的团队
		String states=entity_PersonalTeam.getTeamstate();
		
			tv_teams_name.setText(entity_PersonalTeam.getTeamname());
			
			if (states.equals("Y")) tv_teams_grade.setText("已被同意");
			else if (states.equals("N")) tv_teams_grade.setText("申请中...");
			else tv_teams_grade.setText("已被拒绝");	
			
		
		listView.setOnItemClickListener(Entity_PersonalTeamsAdapter.this);
		return convertView;
	}

	//添加点击事件
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
		// TODO Auto-generated method stub
	/*	Entity_Teams entity_Team=entity_Teams[position];
		
		  Intent mIntent = new Intent(context,Cooperation_Team_DetailsActivity.class);     
	        Bundle mBundle = new Bundle();     
	        mBundle.putSerializable("team",entity_Team);     
	        mIntent.putExtras(mBundle);     
	       context.startActivity(mIntent);   */
		
		//Toast.makeText(context, entity_Personal.getSno(), 10).show();
	}

	
	

	

}






