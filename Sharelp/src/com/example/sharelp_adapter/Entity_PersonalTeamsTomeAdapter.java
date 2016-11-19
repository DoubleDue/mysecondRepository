package com.example.sharelp_adapter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.sharelp.R;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp.R.id;
import com.example.sharelp_cooperation.Cooperation_GP_DetailsActivity;
import com.example.sharelp_cooperation.Cooperation_Team_DetailsActivity;
import com.example.sharelp_entity.Entity_Gth;
import com.example.sharelp_entity.Entity_PersonalTeam;
import com.example.sharelp_entity.Entity_Personals;
import com.example.sharelp_entity.Entity_Share;
import com.example.sharelp_entity.Entity_Teams;
import com.example.sharelp_slidingmenu.PersonalDataActivity;
import com.example.sharelp_slidingmenu.PersonalData_DetailsActivity;
import com.example.sharelp_utils.MD5;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.Util_ReadEntity;
import com.example.sharelp_utils.Util_TransParams;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
/**
 * 作为优秀作品的实体
 * @author Administrator
 *
 */
public class Entity_PersonalTeamsTomeAdapter extends BaseAdapter implements OnItemClickListener{

	private Entity_PersonalTeam[] entity_PersonalTeams;
	private int listviewItem;
	private LayoutInflater layoutInflater;
	private Context context;
	private ListView listView;
	private Handler handler;
	TextView btn_ac,btn_re;
	private SharelpApplication sharelpApplication;


	public Entity_PersonalTeamsTomeAdapter(SharelpApplication sharelpApplication,Handler handler,Context context, Entity_PersonalTeam[] entity_PersonalTeams, 
			int listviewItem, ListView listView) {
		this.sharelpApplication=sharelpApplication;
		this.handler=handler;
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
		TextView tv_teams_name=(TextView) convertView.findViewById(R.id.tv_tome_name);
		final TextView tv_teams_state=(TextView) convertView.findViewById(R.id.tv_tome_state);
		btn_ac=(TextView) convertView.findViewById(R.id.btn_accept);
		btn_re=(TextView) convertView.findViewById(R.id.btn_reject);
		ImageView iv_teams_pic=(ImageView) convertView.findViewById(R.id.iv_teams_pic);
		Entity_PersonalTeam entity_PersonalTeam=entity_PersonalTeams[position];
		//向我来申请的人
		String statees=entity_PersonalTeam.getTeamstate();//目前的状态
		final String msno=entity_PersonalTeam.getSno();//目前的申请队员的学号
		final String steamname=entity_PersonalTeam.getTeamname();
			tv_teams_name.setText(entity_PersonalTeam.getSno());
			
			
	//接受按钮
			btn_ac.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					Util_TransParams.Util_TransParam(msno, steamname, Util_Const.ACCEPT, handler);
				//	Toast.makeText(context, "jieshou", 10).show();
					tv_teams_state.setText("已同意");
					btn_ac.setVisibility(View.GONE);
					btn_re.setVisibility(View.GONE);
				}
			});
	//拒绝按钮
			btn_re.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Util_TransParams.Util_TransParam(msno, steamname, Util_Const.REJECT, handler);
					tv_teams_state.setText("已拒绝");
					btn_re.setVisibility(View.GONE);
					btn_ac.setVisibility(View.GONE);
					//Toast.makeText(context, "jujue", 10).show();
				}
			});
			
			
			if (statees.equals("N")) {
				tv_teams_state.setText("申请中...");
				
			}else if (statees.equals("Y")) {
				tv_teams_state.setText("已同意");
				btn_ac.setVisibility(View.GONE);
				btn_re.setVisibility(View.GONE);
			}else{
				
				tv_teams_state.setText("已拒绝");
				btn_ac.setVisibility(View.GONE);
				btn_re.setVisibility(View.GONE);
				
			}
			
		
		listView.setOnItemClickListener(Entity_PersonalTeamsTomeAdapter.this);
		return convertView;
	}

	//添加点击事件
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long arg3) {
		// TODO Auto-generated method stub
		/*Entity_PersonalTeam entity_PersonalTeam=entity_PersonalTeams[position];
		String sno=entity_PersonalTeam.getSno();
		Intent mIntent = new Intent(context,PersonalData_DetailsActivity.class);     
        Bundle mBundle = new Bundle();     
        mBundle.putString("sno", sno);    
        mIntent.putExtras(mBundle);     
       context.startActivity(mIntent); */
	}

	

	
	

}






