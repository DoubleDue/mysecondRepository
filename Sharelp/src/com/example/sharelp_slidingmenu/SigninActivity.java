package com.example.sharelp_slidingmenu;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sharelp.R;
import com.example.sharelp_utils.MD5Security;
import com.example.sharelp_utils.NetworkMonitorActivity;
import com.example.sharelp_utils.ShowToast;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.Util_LoginAndSignin;
import com.example.sharelp_utils.copyright;
/**
 * ��Sign_Login�鿴���ݿ��Ƿ��Ѵ���ĳ�û���
 * ����SigninServlet��ע����Ϣ�������ݿ�
 * @author Administrator
 *
 */
public class SigninActivity extends Activity implements OnClickListener {

	private EditText et_username_sign,et_password_sign,et_sname;
	private Button btn_submit,btn_cancel,btn_copyright;
	
	private Handler sign_loginHandler;
	private Handler signHandler;
	private LoginActivity loginActivity;
	private SQLiteDatabase dbWrite;
	private HttpClient client;
	private String RESULT;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		
		initsignHandler();
		initsign_loginHandler();
		client=new DefaultHttpClient();
		initWiget();
	}


	
	private void initsign_loginHandler() {
		sign_loginHandler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				
				String sname=et_sname.getText().toString();
				String sno=et_username_sign.getText().toString();
				String password=et_password_sign.getText().toString();
				password=MD5Security.MD5(password);
				ShowToast.showToast(getApplicationContext(),password);
				String reString=(String) msg.obj;
				if (msg.arg1==200) {
					if (reString.equals("failed")) {
						//û��������
						Util_LoginAndSignin.util_Signin(sno, password,sname,Util_Const.SIGNIN, signHandler);
					}else {
						ShowToast.showToast(getApplicationContext(), "�û��Ѵ���");
					}
					
				}else ShowToast.showToast(getApplicationContext(), "δȡ������");
			}
			
		};
		
	}





	private void initsignHandler() {
		signHandler=new Handler(){
			@Override
			public void handleMessage(Message msg) {
				if (msg.arg1==200) {
					ShowToast.showToast(getApplicationContext(), "ע��ɹ�");
					Intent intent=new Intent(SigninActivity.this, LoginActivity.class);
					SigninActivity.this.finish();
					startActivity(intent);
				}else {
					ShowToast.showToast(getApplicationContext(), "ע��ʧ��");
				}
			}
		};
	}



	private void initWiget(){
		loginActivity=new LoginActivity();
		btn_submit=(Button) findViewById(R.id.btn_submit);
		btn_cancel=(Button) findViewById(R.id.btn_canncel);
		btn_copyright=(Button) findViewById(R.id.btn_copyright);
		et_username_sign=(EditText) findViewById(R.id.et_username_sign);
		et_password_sign=(EditText) findViewById(R.id.et_password_sign);	
		et_sname=(EditText) findViewById(R.id.et_signin_sname);
		btn_submit.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
		btn_copyright.setOnClickListener(this);
	}
	
	

	
	

	public void onClick(View view) {
		Intent  intent;
		switch (view.getId()) {
		case R.id.btn_submit:
			String username=et_username_sign.getText().toString();
			String password=et_password_sign.getText().toString();
			String sname=et_sname.getText().toString();
			
			/*���浽�������ݿ�
			 * String sql="insert into list(Username,Password)values(?,?)";
			dbWrite.execSQL(sql, new Object[]{username,password});
			
*/			//��ȡ����״̬
			boolean networkState = NetworkMonitorActivity.detect(SigninActivity.this);  
			if (!networkState) {
				//������
				Toast.makeText(SigninActivity.this, "��ǰ���粻����", Toast.LENGTH_SHORT).show();
				loginActivity.showAlert(SigninActivity.this, "ѯ��", "�Ƿ�ȥ�������磿");
			
			}else {
				//������
				
				if (username.equals("")||password.equals("")) {
					Toast.makeText(SigninActivity.this, "�û��������벻��Ϊ��", Toast.LENGTH_SHORT).show();
					
				}else {
					//readNet_Login("http://sharelp.ecs11.ectomcat.pw/sign_login", username, password);
					Util_LoginAndSignin.util_Signin(username, password,sname, Util_Const.SIGN_LOGIN,sign_loginHandler );
				}
			}
			break;

		case R.id.btn_canncel:
			loginActivity.showAlert2(getApplicationContext(), "ѯ��", "ȷ��ȡ����");
			break;
			
		case R.id.btn_copyright:
			intent=new Intent(SigninActivity.this, copyright.class);
			SigninActivity.this.finish();
			startActivity(intent);
		}
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void readNet_Login(String url,String in,String intwo) {
		new AsyncTask<String, Void, String>() {

			protected String doInBackground(String... arg0) {
				String urlsString=arg0[0];

				HttpPost post=new HttpPost(urlsString);

				try {
					List<BasicNameValuePair>list=new ArrayList<>();
					list.add(new BasicNameValuePair("username", arg0[1]));
					list.add(new BasicNameValuePair("password", arg0[2]));
					post.setEntity(new UrlEncodedFormEntity(list));

					HttpResponse response=client.execute(post);
					String value=EntityUtils.toString(response.getEntity());
					//<<<<<<<������Ϊ�������û��������
					
					return value;

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(String result) {
				RESULT=result.toString();
				String username=et_username_sign.getText().toString();
				String password=et_password_sign.getText().toString();
				
				if (RESULT.equals("failed")) {
					
					readNet_Signin("http://sharelp.ecs11.tomcats.pw/signin",username,password);
					Toast.makeText(SigninActivity.this, "ע��ɹ�", Toast.LENGTH_SHORT).show();
					Intent intent=new Intent(SigninActivity.this, LoginActivity.class);
					SigninActivity.this.finish();
					startActivity(intent);
				}
				else {
					Toast.makeText(SigninActivity.this, "�Բ��� ���û����Ѵ���", Toast.LENGTH_SHORT).show();
					et_username_sign.setText("");
					et_password_sign.setText("");
				}
				super.onPostExecute(result);
			}
		}.execute(url,in,intwo);

	}
	
	
	public void readNet_Signin(String url,String in,String intwo) {
		new AsyncTask<String, Void, String>() {

			protected String doInBackground(String... arg0) {
				String urlsString=arg0[0];

				HttpPost post=new HttpPost(urlsString);

				try {
					List<BasicNameValuePair>list=new ArrayList<>();
					list.add(new BasicNameValuePair("username", arg0[1]));
					list.add(new BasicNameValuePair("password", arg0[2]));
					post.setEntity(new UrlEncodedFormEntity(list));

					HttpResponse response=client.execute(post);
					String value=EntityUtils.toString(response.getEntity());//entity�а����˴ӻ������ж�ȡ�������ݣ�

					//<<<<<<<������Ϊ�������û��������

					return value;

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (ParseException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return null;
			}

			protected void onPostExecute(String result) {
				super.onPostExecute(result);
			}
		}.execute(url,in,intwo);

	}



}
