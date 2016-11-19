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
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.sharelp.R;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp_utils.Util_Const;

/**
 * 填写自己的简历
 * @author Administrator
 *
 */
public class Contest_Write_MemberDetailsActivity extends Activity implements OnClickListener{

	private SharelpApplication sharelpApplication;
	private Spinner sp_grade,sp_depart;
	private Button btn_sub_member;
	private RadioGroup radioGroup;
	private EditText et_per_major,et_per_phonenum,et_per_email,et_per_speciality,
	et_per_intro,et_per_experence,et_per_intent;
	private String sno,grade,depart,major,phonenum,email,speciality,intro,experence,intent,sex;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contest_write_memberdetails);
		sharelpApplication=(SharelpApplication) getApplication();
		
		initWidget();
		initListener();
		

	}

	private void initListener() {
		
		sp_grade.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
		    
		        //String[] languages = getResources().getStringArray(R.array.languages);
		    	grade=parent.getItemAtPosition(pos).toString();  
		    	//Toast.makeText(Contest_Write_MemberDetailsActivity.this, "你点击的是:"+grade, 2000).show();
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // Another interface callback
		    }
		});
		
		sp_depart.setOnItemSelectedListener(new OnItemSelectedListener() {
		    @Override
		    public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
		    
		        //String[] languages = getResources().getStringArray(R.array.languages);
		    	depart=parent.getItemAtPosition(pos).toString();  
		    	//Toast.makeText(Contest_Write_MemberDetailsActivity.this, "你点击的是:"+grade, 2000).show();
		    }
		    @Override
		    public void onNothingSelected(AdapterView<?> parent) {
		        // Another interface callback
		    }
		});
		
		
	}

	private void initWidget() {
		
		sex="男";
		sp_grade=(Spinner) findViewById(R.id.sp_grade);
		sp_depart=(Spinner) findViewById(R.id.sp_depart);
		
		radioGroup=(RadioGroup) findViewById(R.id.sex_choose);
		
		btn_sub_member=(Button) findViewById(R.id.btn_submitmember);
		et_per_major=(EditText) findViewById(R.id.et_per_major);
		et_per_phonenum=(EditText) findViewById(R.id.et_per_phonenum);
		et_per_email=(EditText) findViewById(R.id.et_per_email);
		et_per_speciality=(EditText) findViewById(R.id.et_per_speciality);
		et_per_intro=(EditText) findViewById(R.id.et_per_intro);
		et_per_experence=(EditText) findViewById(R.id.et_per_experience);
		et_per_intent=(EditText) findViewById(R.id.et_per_intent);
		
		btn_sub_member.setOnClickListener(this);
		
		
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				// TODO Auto-generated method stub
				//获取变更后的选中项的ID
				  int radioButtonId = arg0.getCheckedRadioButtonId();
				 //根据ID获取RadioButton的实例
				  RadioButton rb = (RadioButton)Contest_Write_MemberDetailsActivity.this.findViewById(radioButtonId);
				 //更新文本内容，以符合选中项
				 // tv.setText("您的性别是：" + rb.getText());
				  sex=rb.getText().toString();
			}
		});
		
		
		
	}

	public void onClick(View view) {

		sno=sharelpApplication.getSno();
		major=et_per_major.getText().toString();
		phonenum=et_per_phonenum.getText().toString();
		email=et_per_email.getText().toString();
		speciality=et_per_speciality.getText().toString();
		intro=et_per_intro.getText().toString();
		experence=et_per_experence.getText().toString();
		intent=et_per_intent.getText().toString();
		
		//Toast.makeText(Contest_Write_MemberDetailsActivity.this,sex, 10).show();
		//Toast.makeText(Contest_Write_MemberDetailsActivity.this, grade+depart, 500).show();
		sendPersonal(sno,grade,depart,major,phonenum,email,speciality,intro,experence,intent,sex);


	}

	//传送个人信息
	private void sendPersonal( final String sno,final String grade,final String depart,
			final String major, final String phonenum,
			final String email, final String speciality,
			final String intro, final String experience,
			final String intent,final String sex
			) {

		new Thread(){

			public void run() {

				try {
					HttpPost httpPost=new HttpPost(Util_Const.SUB_PERSON);
					List<BasicNameValuePair>list=new ArrayList<>();
					list.add(new BasicNameValuePair("sno", sno));//----所属的那个比赛
					list.add(new BasicNameValuePair("grade", grade));
					list.add(new BasicNameValuePair("depart", depart));
					list.add(new BasicNameValuePair("phonenum", phonenum));
					list.add(new BasicNameValuePair("major", major));
					list.add(new BasicNameValuePair("email", email));
					list.add(new BasicNameValuePair("selfintro", intro));
					list.add(new BasicNameValuePair("intent", intent));
					list.add(new BasicNameValuePair("sex", sex));
					list.add(new BasicNameValuePair("speciality", speciality));
					list.add(new BasicNameValuePair("experience", experience));

					httpPost.setEntity(new UrlEncodedFormEntity(list, "utf-8"));
					HttpClient client=new DefaultHttpClient();
					HttpResponse response=client.execute(httpPost);
					final String value=EntityUtils.toString(response.getEntity());//entity从互联网中读取的数据

					//-----------判断是否连接成功
					final int ISLINE=response.getStatusLine().getStatusCode();
					runOnUiThread(new Runnable() {
						public void run() {

							if (ISLINE==200) {
									Toast.makeText(Contest_Write_MemberDetailsActivity.this, "提交成功", 1000).show();
									sharelpApplication.setPersonlized(true);
									Intent intent =new Intent(Contest_Write_MemberDetailsActivity.this,
											Cooperation_CreateTeam.class);
									Contest_Write_MemberDetailsActivity.this.finish();
									startActivity(intent);
								return;
							}else {
								Toast.makeText(Contest_Write_MemberDetailsActivity.this, "提交失败", 1000).show();
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
