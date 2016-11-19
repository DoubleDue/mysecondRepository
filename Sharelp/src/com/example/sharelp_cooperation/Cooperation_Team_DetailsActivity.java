package com.example.sharelp_cooperation;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.example.sharelp.R;
import com.example.sharelp.R.id;
import com.example.sharelp.R.layout;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp_adapter.Entity_PersonalsAdapter;
import com.example.sharelp_entity.Entity_Personals;
import com.example.sharelp_entity.Entity_Teams;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.Util_TransParams;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

/**
 * fei
 * 显示团队的详细信息；
 * 含有申请加入按钮
 * 传过来了一个team实体
 * @author Administrator
 *
 */
public class Cooperation_Team_DetailsActivity extends Activity implements OnClickListener{


	private TextView teamname, teamintro, honor, projectinfo;
	private Entity_Teams entity_Team;
	private Button btn_reqmember;
	private SharelpApplication sharelpApplication;
	private String tname,tcategory,sno,capsno;
	private Handler handler;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contest_teamlist_details);

		initView();
		addData();
		initHandler();
	}


	private void initView() {
		sharelpApplication=(SharelpApplication) getApplication();
		teamname=(TextView) findViewById(R.id.tv_teamname);
		teamintro=(TextView) findViewById(R.id.tv_teamintro);
		honor=(TextView) findViewById(R.id.tv_honor);
		projectinfo=(TextView) findViewById(R.id.tv_projectinfo);
		btn_reqmember=(Button) findViewById(R.id.btn_bemember);
		btn_reqmember.setOnClickListener(this);
	}


	private void addData() {
		entity_Team= (Entity_Teams)getIntent().getSerializableExtra("team");  
		tname=entity_Team.getTeamname();
		capsno=entity_Team.getSno();
		teamname.setText(entity_Team.getTeamname());
		
	}


	private void initHandler() {
		handler = new Handler(){
			public void handleMessage(Message msg) {
				if (msg.arg1==200) {
					if (((String)msg.obj).equals("success")) {
						Toast.makeText(Cooperation_Team_DetailsActivity.this, "已提交申请", 10).show();
					}else {
						Toast.makeText(Cooperation_Team_DetailsActivity.this, "您已申请过或由您创建", 10).show();
					}
				
				}else Toast.makeText(Cooperation_Team_DetailsActivity.this, "申请失败", 10).show();
				
			}	
		};
	}


	@Override
	public void onClick(View v) {

		if (sharelpApplication.isState()) {

			sno=sharelpApplication.getSno();
			
			Util_TransParams.Util_TransParam(sno,tname,capsno, Util_Const.PERSONALTEAM, handler);

		}else Toast.makeText(Cooperation_Team_DetailsActivity.this, "please loginin ", 10).show();
	}



}
