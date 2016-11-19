package com.example.sharelp_cooperation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import com.example.sharelp.R;
import com.example.sharelp.R.id;
import com.example.sharelp.R.layout;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp_utils.Util_Const;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

/**
 * 申请加入项目成员中
 * @author Administrator
 *
 */
public class Contest_Write_OneTeamActivity extends Activity implements OnClickListener{

	private SharelpApplication sharelpApplication;
	private Spinner sp_tzb;
	private Button btn_sub_team;
	private EditText et_team_name,et_team_intro,et_team_num,et_team_tutor,et_team_honour,et_pro_intro;
	private String sno,category,depart,teamname,teamintro,teamnum,teamtutor,honour,projectintro;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contest_write_oneteam);
		sharelpApplication=(SharelpApplication) getApplication();
		
		initWidget();
		initListener();
		

	}

	private void initListener() {
		
		sp_tzb.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
		    	category=parent.getItemAtPosition(pos).toString();  
		    	//Toast.makeText(Contest_Write_MemberDetailsActivity.this, "你点击的是:"+grade, 2000).show();
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // Another interface callback
		    }
		});
	
		
	}

	private void initWidget() {
		
		sp_tzb=(Spinner) findViewById(R.id.sp_tzb);
		btn_sub_team=(Button) findViewById(R.id.btn_submitteam);
		
		et_team_name=(EditText) findViewById(R.id.et_team_name);
		et_team_intro=(EditText) findViewById(R.id.et_team_intro);
		et_team_num=(EditText) findViewById(R.id.et_team_num);
		et_team_tutor=(EditText) findViewById(R.id.et_team_tutor);
		et_team_honour=(EditText) findViewById(R.id.et_team_honour);
		et_pro_intro=(EditText) findViewById(R.id.et_pro_intro);
		btn_sub_team.setOnClickListener(this);
		
	}

	public void onClick(View view) {

		sno=sharelpApplication.getSno();
		teamname=et_team_name.getText().toString();
		teamintro=et_team_intro.getText().toString();
		teamnum=et_team_num.getText().toString();
		teamtutor=et_team_tutor.getText().toString();
		honour=et_team_honour.getText().toString();
		projectintro=et_pro_intro.getText().toString();

	//	Toast.makeText(Contest_Write_OneTeamActivity.this, sno+teamname+depart, 500).show();
		sendPersonal(sno, category, teamname, teamintro, teamnum, teamtutor, honour, projectintro);


	}

	//传送个人信息
	private void sendPersonal( final String sno,final String category,final String teamname,
			final String teamintro, final String teamnum,
			final String teamtutor,final String honour,final String projectintro) {

		new Thread(){

			public void run() {

				try {
					HttpPost httpPost=new HttpPost(Util_Const.SUB_TEAM);
					List<BasicNameValuePair>list=new ArrayList<>();
					list.add(new BasicNameValuePair("sno", sno));//----所属的那个比赛
					list.add(new BasicNameValuePair("category", category));
					list.add(new BasicNameValuePair("teamname", teamname));
					list.add(new BasicNameValuePair("teamintro", teamintro));
					list.add(new BasicNameValuePair("teamnum", teamnum));
					list.add(new BasicNameValuePair("teamtutor", teamtutor));
					list.add(new BasicNameValuePair("honour", honour));
					list.add(new BasicNameValuePair("projectintro", projectintro));

					httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
					HttpClient client=new DefaultHttpClient();
					HttpResponse response=client.execute(httpPost);


					//-----------判断是否连接成功
					final int ISLINE=response.getStatusLine().getStatusCode();
					runOnUiThread(new Runnable() {
						public void run() {

							if (ISLINE==200) {
								Toast.makeText(Contest_Write_OneTeamActivity.this, "创建成功", 1000).show();
								Intent intent=new Intent(Contest_Write_OneTeamActivity.this,
										Cooperation_CreateTeam.class);
								startActivity(intent);
								return;
							}else {
								Toast.makeText(Contest_Write_OneTeamActivity.this, "提交失败", 1000).show();
								return;
							}

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

	
	
}
