package com.example.sharelp_cooperation;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharelp.R;
import com.example.sharelp.R.id;
import com.example.sharelp.R.layout;
import com.example.sharelp_entity.Entity_Tutor;
import com.example.sharelp_utils.Util_Const;

public class Cooperation_Tutor_Teamlist extends Activity{


	public static String tutor_depart;
	private ListView lv_tutorlist;
	private String DEPART;
	private Entity_Tutor[] entity_Tutors;	
	private Entity_Tutor entity_Tutor;
	private EntityAdapter entityAdapter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coopration_tutorteam);
		lv_tutorlist=(ListView) findViewById(R.id.lv_tutorlist);

		StringBuffer sb=new StringBuffer();
		sb.append(tutor_depart);
		DEPART=sb.toString();

		readTutorInfo();
	}

	private void readTutorInfo() {

		new Thread(){
			public void run() {

				try {
					String tag=DEPART;
					tag="tag="+tag;
					byte[] buffer=tag.getBytes("utf-8");

					URL url=new URL(Util_Const.TUTOR);
					HttpURLConnection connection=(HttpURLConnection) url.openConnection();
					connection.setConnectTimeout(5000);
					connection.setReadTimeout(5000);
					connection.setRequestMethod("POST");
					connection.setDoOutput(true);

					OutputStream os=connection.getOutputStream();
					os.write(buffer);
					os.flush();

					if (connection.getResponseCode()!=200) {
						Toast.makeText(Cooperation_Tutor_Teamlist.this, "yes", 300).show();
						return;
					}
					else {
						ObjectMapper om=new ObjectMapper();
						entity_Tutors=om.readValue(connection.getInputStream(), Entity_Tutor[].class);
						runOnUiThread(new Runnable() {
							public void run() {
								lv_tutorlist=(ListView) findViewById(R.id.lv_tutorlist);//通过适配器来适配数据
								entityAdapter=new EntityAdapter();
								lv_tutorlist.setAdapter(entityAdapter);//将适配器和listview关联起来------
								Toast.makeText(Cooperation_Tutor_Teamlist.this, "yes", 300).show();
								return;
							}
						});
					}

				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				} catch (MalformedURLException e) {
					e.printStackTrace();
				} catch (ProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			};

		}.start();

	}



	//建立适配器
	//实现抽象方法；getCount（）和getView（）必须要实现
	class EntityAdapter extends BaseAdapter{

		public int getCount() {
			return entity_Tutors.length;//集合的长度，决定会循环几次创建每一个横向列表
		}

		public Entity_Tutor getItem(int position) {
			return entity_Tutors[position];//返回position位置出的item
		}

		public long getItemId(int position) {
			return position;
		}



		public View getView(int position, View view, ViewGroup arg2) {
			View layoutView=View.inflate(Cooperation_Tutor_Teamlist.this, R.layout.activity_list_item_tutor, null);//最后一个参数是说此布局是否还包含在某个布局文件里
			TextView tv_tutorname=(TextView) layoutView.findViewById(R.id.tv_tutorname);
			TextView tv_tutorlink=(TextView) layoutView.findViewById(R.id.tv_tutorlink);
			Entity_Tutor bean=entity_Tutors[position];
			tv_tutorname.setText(bean.getName());
			tv_tutorlink.setText("Tel "+bean.getTel());
			return layoutView;
		}


	}












}
