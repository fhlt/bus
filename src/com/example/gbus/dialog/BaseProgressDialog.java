/**************************************************************************************
 * [Project]
 *       MyProgressDialog
 * [Package]
 *       com.lxd.widgets
 * [FileName]
 *       CustomProgressDialog.java
 * [Copyright]
 *       Copyright 2012 LXD All Rights Reserved.
 * [History]
 *       Version          Date              Author                        Record
 *--------------------------------------------------------------------------------------
 *       1.0.0           2012-4-27         lxd (rohsuton@gmail.com)        Create
 **************************************************************************************/

package com.example.gbus.dialog;

import com.example.gbus.R;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.TextView;

public class BaseProgressDialog extends Dialog {

	private ImageView loadingImg = null;
	private TextView loadingText = null;
	private AnimationDrawable animationDrawable;

	public BaseProgressDialog(Context context) {
		super(context, R.style.base_progress_dialog_style);
		setContentView(R.layout.base_pd);
		getWindow().getAttributes().gravity = Gravity.CENTER;
		loadingImg = (ImageView) findViewById(R.id.loadingImg);
		animationDrawable = (AnimationDrawable) loadingImg.getDrawable();

		loadingText = (TextView) findViewById(R.id.loadingMsg);
	}

	public void startAnim() {
		animationDrawable.start();
	}

	public void setMessageText(String msg) {
		if (loadingText != null) {
			loadingText.setText(msg);
		}
	}
}
