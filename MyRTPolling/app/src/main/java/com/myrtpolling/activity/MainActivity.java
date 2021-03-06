package com.myrtpolling.activity;

import android.app.Activity;
import android.os.Bundle;

import com.myrtpolling.R;
import com.myrtpolling.service.PollingService;
import com.myrtpolling.utils.PollingUtils;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//Start polling service
		System.out.println("Start polling service...");
		PollingUtils.startPollingService(this, 5, PollingService.class, PollingService.ACTION);
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		//Stop polling service
		System.out.println("Stop polling service...");
		PollingUtils.stopPollingService(this, PollingService.class, PollingService.ACTION);
	}

}
