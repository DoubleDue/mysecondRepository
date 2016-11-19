package com.example.sharelp_adapter;

import java.io.File;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sharelp.R;
import com.example.sharelp.SharelpApplication;
import com.example.sharelp_cooperation.Cooperation_Team_DetailsActivity;
import com.example.sharelp_entity.Entity_Personals;
import com.example.sharelp_entity.Entity_Teams;
import com.example.sharelp_utils.Util_Const;
import com.example.sharelp_utils.Util_TransParams;
/**
 * �㿪һ��������ʾ��������
 * @author Administrator
 *
 */
public class Entity_TeamDetailAdapter extends BaseAdapter implements OnClickListener{

	private Entity_Personals[] entity_Personals;
	private Entity_Teams entity_Team;
	private LayoutInflater layoutInflater;
	private Button btn_reqmember;
	private Context context;
	private Handler handler;
	private SharelpApplication sharelpApplication;
	
	 private final int VIEW_TYPE = 3;
	    private final int TYPE_1 = 0;
	    private final int TYPE_2 = 1;
	    private final int TYPE_3 = 2;
	    
	public Entity_TeamDetailAdapter(Context context,Handler handler,SharelpApplication sharelpApplication,Entity_Personals[] entity_Personals,Entity_Teams entity_Team) {
		this.context=context;
		this.sharelpApplication=sharelpApplication;
		this.handler=handler;
		this.entity_Team=entity_Team;
		this.entity_Personals = entity_Personals;
	    layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		
		if (entity_Personals.length<=1) return 2;//��������item
		else return entity_Personals.length+1;
		
		
	}

	public Entity_Personals getItem(int position) {
		if (entity_Personals.length<=0) {
			return null;
		}else {
			return entity_Personals[position-2];
		}
	}

	public long getItemId(int position) {
		return position;
	}

	
	
	public int getItemViewType(int position) {
	       
        if(position == 0)
        return TYPE_1;
        else if( position==1)
            return TYPE_2;
        else return TYPE_3;
        
        
    }
	
	public int getViewTypeCount() {
        return 3;
    }
	
	public View getView(int position, View convertView, ViewGroup arg2) {
		
		  int type = getItemViewType(position);
		  
		  if (convertView == null) {
			  
			  switch (type) {
	           
			  case TYPE_1:
				  convertView=layoutInflater.inflate(R.layout.list_item_teamdetaillist_head, arg2, false);
	            	TextView detail_teamname=(TextView) convertView.findViewById(R.id.detail_teamname);
	            	TextView detail_teamcategory=(TextView) convertView.findViewById(R.id.detail_teamcategory);
	            	TextView detail_teamtutor=(TextView) convertView.findViewById(R.id.detail_teamtutor);
	            	TextView detail_teamintro=(TextView) convertView.findViewById(R.id.detail_teamintro);//�Ŷ�����
	            	TextView detail_teamnumber=(TextView) convertView.findViewById(R.id.detail_teamnumber);
	            	TextView detail_teamhonor=(TextView) convertView.findViewById(R.id.detail_teamhonor);
	            	TextView detail_projectintro=(TextView) convertView.findViewById(R.id.detail_projectintro);
	            	        	
	            	btn_reqmember=(Button) convertView.findViewById(R.id.btn_reqmenberof);
	            
	            	detail_teamname.setText("��Ŀ���ƣ�"+entity_Team.getTeamname());
	            	detail_teamcategory.setText("��Ŀ���"+entity_Team.getCategory());
	            	detail_teamtutor.setText("��Ŀ��ʦ��"+entity_Team.getTeamtutor());
	            	detail_teamintro.setText("��Ŀ����"+entity_Team.getTeamintro());
	            	detail_teamnumber.setText("���ж�Ա��"+entity_Team.getTeamnum());
	            	detail_teamhonor.setText("�ѵ�������"+entity_Team.getHonour());
	            	detail_projectintro.setText("��Ŀ��飺"+entity_Team.getProjectintro());
	            	btn_reqmember.setOnClickListener(this);
	            	
				  break;
	            	
			  case TYPE_2:
				  convertView=layoutInflater.inflate(R.layout.list_item_teamdetaillist_captain, arg2, false);
	            TextView tv21=(TextView) convertView.findViewById(R.id.detail_capname);
	            TextView tv22=(TextView) convertView.findViewById(R.id.detail_capmajor);
	            TextView tv23=(TextView) convertView.findViewById(R.id.detail_capphone);
	            
	    /*        //ƥ��ӳ�
	            for(int i=0;i<entity_Personals.length;i++){
	            	if (entity_Personals[i].getSno().equals(entity_Team.getSno())) {//�ӳ�
						
					}
	            }
	            */
	            tv21.setText("�ӳ�������"+entity_Personals[position-1].getSname());
	            tv22.setText("�ӳ�רҵ��"+entity_Personals[position-1].getMajor());
	            tv23.setText("�ӳ��绰��"+entity_Personals[position-1].getPhonenum());
	          //  tv22.setText(text)
				  break;
	           	
	            case TYPE_3:
	            	convertView = layoutInflater.inflate(R.layout.list_item_teamdetaillist_member, null);
	        		TextView tv_gth_name=(TextView) convertView.findViewById(R.id.detail_memname);
	        		TextView tv_gth_prize=(TextView) convertView.findViewById(R.id.detail_memmajor);
	        		TextView tv_gth_pjone=(TextView) convertView.findViewById(R.id.detail_memphone);
	        		Entity_Personals entity_Personal=entity_Personals[position-1];
	        		tv_gth_name.setText("��Ա������"+entity_Personal.getSno());
	        		tv_gth_prize.setText("��Աרҵ��"+entity_Personal.getMajor());
	        		tv_gth_pjone.setText("��Ա�绰��"+entity_Personal.getPhonenum());
	            	break;
	            }
		  }
		
		return convertView;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	//	Toast.makeText(context, "dianji", 1000).show();
		if (sharelpApplication.isState()) {

			String sno=sharelpApplication.getSno();
			String tname=entity_Team.getTeamname();
			String capsno=entity_Team.getSno();
			
			Util_TransParams.Util_TransParam(sno,tname,capsno, Util_Const.PERSONALTEAM, handler);

		}else Toast.makeText(context, "����û�е�¼", 10).show();
	}
	
}

	





