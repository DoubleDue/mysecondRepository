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
		sb.append("��ǰ�汾:");
		sb.append(verName);
		sb.append("�����°汾:");
		sb.append(newVerName);
		sb.append("���䣺");
		sb.append(introduction);
		sb.append(", �Ƿ����?");
		Dialog dialog = new AlertDialog.Builder(context)
		.setTitle("�������")
		.setMessage(sb.toString())
		// ��������
		.setPositiveButton("����",// ����ȷ����ť
				new DialogInterface.OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog,
					int which) {
				   pBar = new ProgressDialog(context);
				   pBar.setTitle("��������");
				   pBar.setMessage("���Ժ�...");
				  pBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
				  pBar.show();
				/*  downFile(Config.UPDATE_SERVER  + Config.UPDATE_APKNAME);*/
				  DownloadApk downloadApk=new DownloadApk(context,handler,appname,apkname,pBar);
				  downloadApk.start();
			}

		})
		.setNegativeButton("�ݲ�����",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,
					int whichButton) {
				// ���"ȡ��"��ť֮���˳�����
				//finish();
			}
		}).create();// ����
		// ��ʾ�Ի���
		dialog.show();
	}

}
