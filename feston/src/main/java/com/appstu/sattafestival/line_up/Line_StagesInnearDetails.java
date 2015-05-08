package com.appstu.sattafestival.line_up;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appstu.sattafestival.R;
import com.appstu.sattafestival.Config;
import com.appstu.sattafestival.MyDatabase;
import com.manuelpeinado.fadingactionbar.compat.FadingActionBarHelper;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class Line_StagesInnearDetails extends Fragment {

	FragmentActivity activity;
	private TextView tvTitle, tvDate, tvDescription;
	private ImageView ivImage;
	private MenuItem cross, tick;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		activity = getActivity();
		// ((Line_StagesInnearDetailsContainer)
		// getActivity().getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		FadingActionBarHelper helper = new FadingActionBarHelper().actionBarBackground(R.drawable.ab_solid_satta).headerLayout(R.layout.line_artist_details_header)
				.contentLayout(R.layout.line_artist_details);
		helper.initActionBar(activity);
		View view = helper.createView(inflater);

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		tvTitle = (TextView) getView().findViewById(R.id.tvTitle);
		tvDate = (TextView) getView().findViewById(R.id.tvCompany);
		tvDescription = (TextView) getView().findViewById(R.id.tvDescription);
		ivImage = (ImageView) getView().findViewById(R.id.ivImage);

		tvTitle.setText(((Line_StagesInnearDetailsContainer) getActivity()).stage.getScheduletitle());
		String start = Config.getDate(((Line_StagesInnearDetailsContainer) getActivity()).stage.getStart(), "EE HH.mm");
		String end = Config.getDate(((Line_StagesInnearDetailsContainer) getActivity()).stage.getEnd(), "HH.mm");

		tvDate.setText(((Line_StagesInnearDetailsContainer) getActivity()).stage.getStagename() + " / " + start + "-" + end);
		tvDescription.setText(Html.fromHtml(((Line_StagesInnearDetailsContainer) getActivity()).stage.getDescription_en()));
		Picasso.with(activity).load(((Line_StagesInnearDetailsContainer) getActivity()).stage.getArtistimage()).into(ivImage, new Callback() {
			@Override
			public void onSuccess() {
			}

			@Override
			public void onError() {
				ivImage.setImageResource(R.drawable.no_image);
			}
		});
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.details, menu);
		tick = menu.findItem(R.id.action_tick);
		cross = menu.findItem(R.id.action_cross);
		super.onCreateOptionsMenu(menu, inflater);
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		SelectedOrNot();
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.action_cross:
			tick.setVisible(true);
			cross.setVisible(false);
			ToDatabase(0);
			break;
		case R.id.action_tick:
			tick.setVisible(false);
			cross.setVisible(true);
			ToDatabase(1);
			break;
		}
		return false;
	}

	private void ToDatabase(int select) {
		((Line_StagesInnearDetailsContainer) getActivity()).stage.setArtistSelected(select);

		MyDatabase db = new MyDatabase(activity);
		SQLiteDatabase sql = db.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put("selected", select);
		sql.update(Config.TABLE_ARTISTS, cv, "artistid=?", new String[] { ((Line_StagesInnearDetailsContainer) getActivity()).stage.getArtistid() });

		sql.close();
		db.close();
	}

	private void SelectedOrNot() {
		if (((Line_StagesInnearDetailsContainer) getActivity()).stage.getArtistSelected() == 1) {
			tick.setVisible(false);
			cross.setVisible(true);
		} else if (((Line_StagesInnearDetailsContainer) getActivity()).stage.getArtistSelected() == 0) {
			tick.setVisible(true);
			cross.setVisible(false);
		}
	}
}
