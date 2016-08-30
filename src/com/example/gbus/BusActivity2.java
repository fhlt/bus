package com.example.gbus;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import org.json.JSONException;
import org.json.JSONObject;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
public class BusActivity2 extends BaseActivity {
	Timer timer;
	TextView position;
	ImageView imageView1;
	//ImageView imageView2;
	ImageView imageView3;
	ImageView imageView4;
//	ImageView imageView5;
	ImageView imageView6;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bus2);
		init();
		new Thread(){
			public void run() {
				getInfo();
				getBusInfo();
			};
		}.start();
		showProgressDialog("获取位置中...");
	}
	private void init() {
		position = (TextView)findViewById(R.id.position2);
		imageView1 = (ImageView) findViewById(R.id.imageView1);
		//imageView2 = (ImageView) findViewById(R.id.imageView2);
		imageView3 = (ImageView) findViewById(R.id.imageView3);
		imageView4 = (ImageView) findViewById(R.id.imageView4);
		//imageView5 = (ImageView) findViewById(R.id.imageView5);
		imageView6 = (ImageView) findViewById(R.id.imageView6);
	}
	private void getBusInfo() {
		timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				getInfo();
			}
		}, 30 * 1000, 30 * 1000);
	}
	private void getInfo() {
		Map<String, String> map = new HashMap<String, String>();  
	    map.put("code", "233B");  
		asyHttp(Login, map, new HttpCallBack(){
			@Override
			public void Success(String respose) {
				parseStr(respose);
			}
			@Override
			public void Fail(String error) {
				parseStr(error);
			}
		});
	}
	private void parseStr(String respose) {
		System.out.println("返回数据："+respose);
		closeProgressDialog();
		try {
			JSONObject jsonObject = new JSONObject(respose);
			String result = jsonObject.getString("result");
			String pos;
			if ("1".equals(result)) {
				pos = jsonObject.getString("position");
				position.setText("当前位置：   " + pos);
			} else if ("0".equals(result)) {
				showShortToast("获取车辆位置失败！");
				return;
			} else {
				showShortToast("无当前车辆位置信息！");
				return;
			}
			if ("李家沱大桥北".equals(pos)) {
				imageView1.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(R.drawable.stop));
				/*imageView2.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));*/
				imageView3.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				imageView4.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
			/*	imageView5.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));*/
				imageView6.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
			} else if ("李家沱大桥南".equals(pos)) {
				imageView1.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				/*imageView2.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(R.drawable.stop));*/
				imageView3.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				imageView4.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				/*imageView5.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));*/
				imageView6.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
			} else if ("红光".equals(pos)) {
				imageView1.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				/*imageView2.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));*/
				imageView3.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(R.drawable.stop));
				imageView4.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				/*imageView5.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));*/
				imageView6.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
			} else if ("重庆理工大学".equals(pos)) {
				imageView1.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				/*imageView2.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));*/
				imageView3.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				imageView4.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(R.drawable.stop));
				/*imageView5.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));*/
				imageView6.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
			} else if ("清华中学".equals(pos)) {
				imageView1.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				/*imageView2.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));*/
				imageView3.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				imageView4.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				/*imageView5.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(R.drawable.stop));*/
				imageView6.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
			} else if ("土桥".equals(pos)) {
				imageView1.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				/*imageView2.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));*/
				imageView3.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				imageView4.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));
				/*imageView5.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(
								R.drawable.one_status_circle));*/
				imageView6.setImageDrawable(getApplicationContext()
						.getResources().getDrawable(R.drawable.stop));
			}
			findViewById(R.id.view2).setVisibility(View.VISIBLE);
			findViewById(R.id.all).setVisibility(View.VISIBLE);
			position.setVisibility(View.VISIBLE);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
	
}
