package com.appstu.sattafestival.flashlight;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.appstu.sattafestival.R;

public class Flashlight extends Fragment {

	private RelativeLayout rlMain;
	private boolean mainPressedFlag = false;
	private boolean mainColor = false;
	private Handler hMain;
	private Runnable rMain;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.flashlight, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		rlMain = (RelativeLayout) getView().findViewById(R.id.rlMain);

		Clicks();
	}

	private void Clicks() {

		rlMain.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mainPressedFlag) {
					mainPressedFlag = false;
					hMain.removeMessages(0);
					rlMain.setBackgroundColor(Color.WHITE);
				} else {
					mainPressedFlag = true;
					hMain = new Handler();
					rMain = new Runnable() {
						@Override
						public void run() {
							if (mainColor) {
								mainColor = false;
								rlMain.setBackgroundColor(Color.WHITE);
							} else {
								mainColor = true;
								rlMain.setBackgroundColor(getResources().getColor(R.color.sattaDark));
							}
							hMain.postDelayed(this, 125);
						}
					};
					hMain.post(rMain);
				}
			}
		});
	}

	@Override
	public void onPause() {
		super.onPause();
		if (mainPressedFlag) {
			mainPressedFlag = false;
			hMain.removeMessages(0);
			rlMain.setBackgroundColor(Color.WHITE);
		}
	}
}
