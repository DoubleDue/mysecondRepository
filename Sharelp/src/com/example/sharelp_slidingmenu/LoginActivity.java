package com.example.sharelp_slidingmenu;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
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

import com.example.sharelp.R;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp.R.id;
import com.example.sharelp.R.layout;
import com.example.sharelp_adapter.Entity_CommentAdapter;
import com.example.sharelp_entity.Entity_Comments;
import com.example.sharelp_sharelp.Sharelp_Share_DerailsActivity;
import com.example.sharelp_tab.TabHostActivity;
import com.example.sharelp_utils.LoginDatabase;
import com.example.sharelp_utils.MD5Security;
import com.example.sharelp_utils.NetworkMonitorActivity;
import com.example.sharelp_utils.ShowToast;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.Util_LoginAndSignin;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class LoginActivity extends Activity implements OnClickListener {

	
	private SharelpApplication sharelpApplication;
	private EditText et_username,et_password;
	private Button btn_login,btn_signin;
	private SQLiteDatabase dbRead;
	private HttpClient client;
	private String RESULT;
	private Handler loginHandler;

	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		sharelpApplication=(SharelpApplication) getApplication();//�õ�applicationʵ��
		initLoginHandler();
		client=new DefaultHttpClient();                //-------------------------
		initWiget();		
		btn_login.setOnClickListener(this);
		btn_signin.setOnClickListener(this);
	}

	
	
	
	/**
	 * LoginActivity's Handler
	 */
	
	private void initLoginHandler() {
		loginHandler = new Handler(){
			public void handleMessage(Message msg) {
				if (msg.arg1==200) {
					String reString=(String)(msg.obj);
					String info[] = reString.split("\\|");
					if(info[0].equals("success")) {
						sharelpApplication.setState(true);
						sharelpApplication.setSno(info[1]);
						sharelpApplication.setSname(info[2]);
						sharelpApplication.setPhoto(info[3]);
						if (info[4].equals("personed")) {//����д����
							sharelpApplication.setPersonlized(true);
						}else {
							sharelpApplication.setPersonlized(false);
						}
						Toast.makeText(LoginActivity.this, info[1]+" ��¼&�ɹ�", 10).show();
						et_username.setText("");
						et_password.setText("");
						Intent intent =new Intent(LoginActivity.this,TabHostActivity.class);
						LoginActivity.this.finish();
						startActivity(intent);
						
					}//+username+"|"+photo+"|"+phonum+"|"+email);
					else Toast.makeText(LoginActivity.this, "��¼&ʧ��", 10).show();
				}
				
			}		
		};
	}


	private void initWiget(){
		et_username=(EditText) findViewById(R.id.et_username);
		et_password=(EditText) findViewById(R.id.et_password);
		btn_login=(Button) findViewById(R.id.btn_login);
		btn_signin=(Button) findViewById(R.id.btn_signin);	
		dbRead=new LoginDatabase(this).getReadableDatabase();
	}


	public void onClick(View view) {

		Intent intent;
		String sno;
		String password;
		switch(view.getId()){

		case R.id.btn_login:
			sno=et_username.getText().toString();
			password=et_password.getText().toString();
			password=MD5Security.MD5(password);
			//��username�Ž�ȫ�ֱ���--------------------------------------
			sharelpApplication.setSno(sno);

			//�������ݿ��ж�
		/*	 Cursor cursor=dbRead.rawQuery("select * from list where Username like ? and Password=?", new String[]{username,password});
			if(cursor.moveToNext()==false){
				intent =new Intent(LoginActivity.this,Failed_LoginActivity.class);
				startActivity(intent);
			}
			else {
				intent =new Intent(LoginActivity.this,Suc_LoginActivity.class);
				startActivity(intent);
			}*/
			
			
			//��ȡ����״̬
			boolean networkState = NetworkMonitorActivity.detect(LoginActivity.this);  
			if (!networkState) {
				Toast.makeText(LoginActivity.this, "��ǰ���粻����", Toast.LENGTH_SHORT).show();
				showAlert(LoginActivity.this, "ѯ��", "�Ƿ�ȥ�������磿");
			
			}else {
			//	readNet_Login(Util_Const.LOGIN, username, password);
			//ShowToast.showToast(getApplicationContext(), "jijianglanjie");
			Util_LoginAndSignin.Util_Login(sno, password,Util_Const.LOGIN, loginHandler);
			}
			break;

		case R.id.btn_signin://ע����ͣʹ��
			intent=new Intent(LoginActivity.this,SigninActivity.class);
			LoginActivity.this.finish();
			startActivity(intent);
			break;

		}

	}
	
	
	
	//��ʾ�Ի���
	public  void showAlert(Context context,String title,String message){
		
		AlertDialog alert = new AlertDialog.Builder(context).create();
		alert.setTitle(title);
		alert.setMessage(message);
		
		alert.setButton(DialogInterface.BUTTON_NEGATIVE, "����",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		
		alert.setButton(DialogInterface.BUTTON_POSITIVE, "�ǵ�",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						Intent intent=new Intent();			
						intent.setClassName("com.android.settings", "com.android.settings.Settings");		
						startActivity(intent);
					}
				});				
		alert.show();	
	
	
	}
	
public  void showAlert2(Context context,String title,String message){
		
	AlertDialog alert = new AlertDialog.Builder(context).create();
	alert.setTitle(title);
	alert.setMessage(message);
	
	alert.setButton(DialogInterface.BUTTON_NEGATIVE, "����",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
				}
			});
	alert.setButton(DialogInterface.BUTTON_POSITIVE, "�ǵ�",
			new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					finish();
				}
			});
	alert.show();
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public void readNet_Login(String url,String in,String intwo) {
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
				final int ISLINE= response.getStatusLine().getStatusCode();
				runOnUiThread(new Runnable() {
					public void run() {
						if (ISLINE!=200) {
							Toast.makeText(LoginActivity.this, "����ʧ��", 1000).show();
							return;
						}
					}
				});
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
			
			//RESULT�Ƿ������ķ���ֵ
			RESULT=result.toString();
			if (RESULT.equals("success")) {
				
				sharelpApplication.setState(true);
				Toast.makeText(LoginActivity.this, "��ϲ�� ��½�ɹ�", Toast.LENGTH_LONG).show();
				et_username.setText("");
				et_password.setText("");
				Intent intent =new Intent(LoginActivity.this,TabHostActivity.class);
				startActivity(intent);
				
			
			}
			else {
				//----------------------------------����ȫ�ֱ���
				sharelpApplication.setState(false);
				Toast.makeText(LoginActivity.this, "�Բ��� ��½ʧ��", Toast.LENGTH_LONG).show();
				et_username.setText("");
				et_password.setText("");
			}
			super.onPostExecute(result);
		}
	}.execute(url,in,intwo);

}


*/



}
