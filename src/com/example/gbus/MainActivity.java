package com.example.gbus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;

public class MainActivity extends Activity implements OnClickListener{

	private RelativeLayout car1 ;
	private RelativeLayout car2 ;
	private RelativeLayout car3 ;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		init();
	}

	private void init() {
		car1 = (RelativeLayout)findViewById(R.id.car1);
		car2 = (RelativeLayout)findViewById(R.id.car2);
		car3 = (RelativeLayout)findViewById(R.id.car3);
		car1.setOnClickListener(this);
		car2.setOnClickListener(this);
		car3.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.car1:
			Intent intent = new Intent(MainActivity.this,BusActivity.class);
			startActivity(intent);
			break;
		case R.id.car2:
			Intent intent2 = new Intent(MainActivity.this,BusActivity2.class);
			startActivity(intent2);
			break;
		case R.id.car3:
			Intent intent3 = new Intent(MainActivity.this,BusActivity3.class);
			startActivity(intent3);
			break;
		default:
			break;
		}
	}

}
