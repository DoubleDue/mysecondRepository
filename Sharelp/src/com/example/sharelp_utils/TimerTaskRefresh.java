package com.example.sharelp_utils;

import java.util.TimerTask;

import android.os.Handler;
import android.os.Message;

public class TimerTaskRefresh extends TimerTask{
	
	private Handler handler;
	
	public TimerTaskRefresh(Handler handler) {
		this.handler=handler;
	}

	public void run() {
		Message msg =Message.obtain();//È¡Ò»¸ö
		msg.arg1=10;
		handler.sendMessage(msg);
		System.out.println("Time's up!!!!");
	}
}
