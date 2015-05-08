package com.appstu.sattafestival.line_up;

import Objects.Stages;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.appstu.sattafestival.R;

public class Line_StagesInnearDetailsContainer extends ActionBarActivity {

	public Stages stage;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		setContentView(R.layout.fragment_my);
		stage = (Stages) getIntent().getExtras().getSerializable("Stage");
		getSupportActionBar().setTitle(stage.getStagename());

		Line_StagesInnearDetails det = new Line_StagesInnearDetails();
		getSupportFragmentManager().beginTransaction().replace(R.id.fragment, det).commit();
	}

	@Override
	protected void onResume() {
		super.onResume();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;
		}
		return false;
	}

	@Override
	public void finish() {
		Intent i = new Intent();
		i.putExtra("Stage", stage);
		setResult(RESULT_OK, i);
		super.finish();
	}
}
