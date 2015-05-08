package com.appstu.sattafestival.news;

import Objects.Posts;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;

import com.appstu.sattafestival.R;

public class News_details_container extends ActionBarActivity {

	private Posts post;

	@Override
	protected void onCreate(Bundle arg0) {
		super.onCreate(arg0);

		setContentView(R.layout.fragment_my);
		post = (Posts) getIntent().getExtras().getSerializable("Post");
		getSupportActionBar().setTitle(post.getTitle_en());

		News_details det = new News_details();
		Bundle b = new Bundle();
		b.putSerializable("Post", post);
		det.setArguments(b);
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
		return true;
	}
}
