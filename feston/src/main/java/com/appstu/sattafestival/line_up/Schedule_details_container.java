package com.appstu.sattafestival.line_up;

import Objects.Artists;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.appstu.sattafestival.R;

public class Schedule_details_container extends ActionBarActivity {

	public Artists artist;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		setContentView(R.layout.fragment_my);
		artist = (Artists) getIntent().getExtras().getSerializable("Artist");
		getSupportActionBar().setTitle(artist.getTitle());

		Schedule_details det = new Schedule_details();
		Bundle b = new Bundle();
		b.putSerializable("Artist", artist);
		det.setArguments(b);
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment, det).commit();
	}

	@Override
	public void finish() {
		Intent i = new Intent();
		Log.e("TAGAS", "selected: " + artist.getSelected());
		i.putExtra("Artist", artist);
		setResult(RESULT_OK, i);
		super.finish();
	}

	@Override
	protected void onResume() {
		super.onResume();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
}
