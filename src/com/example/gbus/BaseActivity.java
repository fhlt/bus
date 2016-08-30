package com.example.gbus;

import java.util.Map;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request.Method;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.gbus.dialog.BaseProgressDialog;

public abstract class BaseActivity extends Activity implements OnClickListener,
		OnItemClickListener{
	// 按两次退出程序
	protected int backCount = 0;
	// 默认为0 0代表不会退出 1代表按两次退出 2代表弹出提示框
	protected int backMode = 0;
	protected static final int BACK_MODE_2COUNT = 1;
	protected static final int BACK_MODE_DIALOG = 2;
	private BaseProgressDialog progressDialog = null;
	protected Context mContext;
	private HttpCallBack httpCallBack;
	
//	private static final String httpUrl = "http://125.87.34.9:8080/bus/";
	private static final String httpUrl = "http://1524n5m974.iok.la:44152/BF/";
	protected static final String Login = "login_getbus.action";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN
						| WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mContext = this;
		progressDialog = new BaseProgressDialog(this);
		progressDialog.setCanceledOnTouchOutside(false);
	}
	/**
	 * 设置进度对话框消息
	 * @param message
	 */
	public void setProgressDialogMessage(String message) {
		progressDialog.setMessageText(message);
	}

	/**
	 * 显示进度对话框
	 */
	public void showProgressDialog() {
		showProgressDialog("加载中");
	}

	/**
	 * 显示进度对话框,带有消息
	 */
	public void showProgressDialog(String message) {
		setProgressDialogMessage(message);
		progressDialog.show();
		progressDialog.startAnim();
	}

	/**
	 * 关闭进度对话框
	 */
	public void closeProgressDialog() {
		if (progressDialog.isShowing()) {
			progressDialog.dismiss();
		}
	}

	/**
	 * 显示短提示的消息
	 * 
	 * @param message
	 */
	public void showShortToast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT)
				.show();
	}

	/**
	 * 显示长提示的消息
	 * 
	 * @param message
	 */
	public void showLongToast(String message) {
		Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG)
				.show();
	}

	@Override
	public void onClick(View v) {
	}
	
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		
	}
	/**
	 * 设置退出模式
	 * 
	 * @param mode
	 *            BACK_MODE_2COUNT--按两次退出 BACK_MODE_DIALOG--弹出提示框退出
	 */
	public void setBackMode(int mode) {
		backMode = mode;
	}

	// 监听返回键
	public boolean dispatchKeyEvent(KeyEvent event) {
		if (event.getAction() == KeyEvent.ACTION_DOWN
				&& event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			if (backMode == BACK_MODE_2COUNT) {
				exitByCount();
			} else if (backMode == BACK_MODE_DIALOG) {
				exitByDialog();
			}
			// 默认
			else if (backMode == 0) {
				return super.dispatchKeyEvent(event);
			}
			return false;
		}

		return super.dispatchKeyEvent(event);
	};

	// 通过连续按两次退出程序
	public void exitByCount() {
		backCount++;
		if (backCount >= 2) {
			finish();
		} else {
			Toast.makeText(getApplicationContext(), "再按一次回到桌面",
					Toast.LENGTH_SHORT).show();
			new Thread() {
				public void run() {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					backCount = 0;
				};
			}.start();
		}
	}

	// 通过弹出返回对话框退出程序
	public void exitByDialog() {
		AlertDialog.Builder builder = new Builder(BaseActivity.this);
		builder.setMessage("确定要退出程序");
		builder.setIcon(android.R.drawable.ic_dialog_alert);
		builder.setTitle("提示");
		builder.setPositiveButton("确定",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
						finish();
						System.exit(0);
					}
				});
		builder.setNegativeButton("取消",
				new android.content.DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.create().show();
	}

	public interface HttpCallBack{
		public void Success(String respose);
		public void Fail(String error);
	}
	
	
	public void asyHttp(String method,final Map<String,String> map,HttpCallBack callBack){
		this.httpCallBack = callBack;
		StringRequest request = new StringRequest(Method.POST, httpUrl+method, new Response.Listener<String>(){
			@Override
			public void onResponse(String response) {
				httpCallBack.Success(response);
				System.out.println("success:"+response);
			}
		}, new ErrorListener(){
			@Override
			public void onErrorResponse(VolleyError error) {
				httpCallBack.Fail(error.toString());
				System.out.println("error:"+error);
			}
			
		}){
			@Override
			protected Map<String, String> getParams() throws AuthFailureError {
				return map;
			}
		};
		RequestQueue mQueue = Volley.newRequestQueue(getApplicationContext());  
		mQueue.add(request); 
	}

	

	

}
