package com.example.sharelp_adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.sharelp.R;
import com.example.sharelp_entity.Entity_Contest;

public class Entity_ContestAdapter extends BaseAdapter {

	private Entity_Contest[] entity_Contests;
	private LayoutInflater layoutInflater;
	
	 private final int VIEW_TYPE = 2;
	    private final int TYPE_1 = 0;
	    private final int TYPE_2 = 1;
	
	public Entity_ContestAdapter(Context context,Entity_Contest[] entity_Contests) {
		
		this.entity_Contests = entity_Contests;
	    layoutInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	public int getCount() {
		
		if (entity_Contests.length<=0) return 1;
		else return entity_Contests.length+1;
		
		
	}

	public Entity_Contest getItem(int position) {
		if (entity_Contests.length<=0) {
			return null;
		}else {
			return entity_Contests[position-1];
		}
	}

	public long getItemId(int position) {
		return position;
	}

	
	
	public int getItemViewType(int position) {
	       
        if(position == 0)
        return TYPE_1;
        else
            return TYPE_2;
        
        
    }
	
	public int getViewTypeCount() {
        return 2;
    }
	
	

	public View getView(int position, View convertView, ViewGroup arg2) {
		
		  int type = getItemViewType(position);
		  
		  if (convertView == null) {
			  
			  switch (type) {
	           
			  case TYPE_1:
				  convertView=layoutInflater.inflate(R.layout.activity_slideshow_contest, arg2, false);
	            	break;
	            	
	            case TYPE_2:
	            	convertView = layoutInflater.inflate(R.layout.activity_list_item_contests, null);
	        		TextView tv_title=(TextView) convertView.findViewById(R.id.tv_contest_title);
	        		Entity_Contest entity_Contest=entity_Contests[position-1];
	        		tv_title.setText(entity_Contest.getTitle());
	            	break;
	            }
		  }
		return convertView;
	}

	
	
	
}

	










