package com.example.sharelp_utils;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;

public class Dialog_newVersion {

	private static ProgressDialog pBar;

	
	public static void NewVersionShow(final Context context,final Handler handler,String verName,
									String newVerName,final String appname,final String apkname
									,final String introduction) {
		

		StringBuffer sb = new StringBuffer();
		sb.append("当前版本:");
		sb.append(verName);
		sb.append("发现新版本:");
		sb.append(newVerName);
		sb.append("补充：");
		sb.append(introduction);
		sb.append(", 是否更新?");
		Dialog dialog = new AlertDialog.Builder(context)
		.setTitle("软件更新")
		.setMessage(sb.toString())
		// 设置内容
		.setPositiveButton("更新",// 设置确定按钮
				new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog,
					int which) {
				   pBar = new ProgressDialog(context);
				   pBar.setTitle("正在下载");
				   pBar.setMessage("请稍候...");
				  pBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				  pBar.show();
				/*  downFile(Config.UPDATE_SERVER  + Config.UPDATE_APKNAME);*/
				  DownloadApk downloadApk=new DownloadApk(context,handler,appname,apkname,pBar);
				  downloadApk.start();
			}

		})
		.setNegativeButton("暂不更新",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,
					int whichButton) {
				// 点击"取消"按钮之后退出程序
				//finish();
			}
		}).create();// 创建
		// 显示对话框
		dialog.show();
	}

}
