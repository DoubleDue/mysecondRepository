package com.example.sharelp_utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class LoginDatabase extends SQLiteOpenHelper {
	
	private static String name="login_db.db";

	public LoginDatabase(Context context) {
		super(context, name, null,1);
	}

	public void onCreate(SQLiteDatabase db) {
		String sql="create table list(_id integer primary key autoincrement,Username varchar,Password varchar)";
		db.execSQL(sql);
		
	}

	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
		
	}

}
