package com.lerx.sys.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.TimerTask;

public class MyTimerTask extends TimerTask {
	private String taskUrl;
	
	public String getTaskUrl() {
		return taskUrl;
	}
	public void setTaskUrl(String taskUrl) {
		this.taskUrl = taskUrl;
	}

	@Override
	public void run() {
		try {
//			System.out.println("测试点333--:"+taskUrl);
			URL url = new URL(taskUrl);
			
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			if (connection!=null){
				connection.connect();
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));
				reader.close();
				connection.disconnect();
			}
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
