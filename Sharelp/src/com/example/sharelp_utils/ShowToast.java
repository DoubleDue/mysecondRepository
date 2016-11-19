package com.example.sharelp_utils;

import android.content.Context;
import android.widget.Toast;

public class ShowToast {
	
	public static void showToast(Context context ,String string){
		Toast.makeText(context, string,10).show();
	}

}
