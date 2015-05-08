package com.appstu.sattafestival.line_up;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.util.Log;
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

public class Schedule_details extends Fragment {

	FragmentActivity activity;
	private TextView tvTitle, tvCompany, tvDescription;
	private ImageView ivImage;
	private MenuItem cross, tick;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		activity = getActivity();
		((Schedule_details_container) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

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
		tvCompany = (TextView) getView().findViewById(R.id.tvCompany);
		tvDescription = (TextView) getView().findViewById(R.id.tvDescription);
		ivImage = (ImageView) getView().findViewById(R.id.ivImage);

		tvTitle.setText(((Schedule_details_container) getActivity()).artist.getTitle() + " / " + ((Schedule_details_container) getActivity()).artist.getCompany());
		String start = Config.getDate(((Schedule_details_container) getActivity()).artist.getStarts(), "EE HH.mm");
		String end = Config.getDate(((Schedule_details_container) getActivity()).artist.getEnds(), "HH.mm");

		tvCompany.setText(((Schedule_details_container) getActivity()).artist.getStageTitle() + " / " + start + "-" + end);

		if (((Schedule_details_container) getActivity()).artist.getDescription_en() != null) {
			tvDescription.setText(Html.fromHtml(((Schedule_details_container) getActivity()).artist.getDescription_en()));
		}
		Picasso.with(activity).load(((Schedule_details_container) getActivity()).artist.getImage()).into(ivImage, new Callback() {
			@Override
			public void onSuccess() {
			}

			@Override
			public void onError() {
				Log.e("ERROR", "ERROR LOADING___" + ((Schedule_details_container) getActivity()).artist.getTitle() + "___");
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
		Log.e("TAGAS", "CLICK");
		switch (item.getItemId()) {
		case R.id.action_cross:
			tick.setVisible(true);
			cross.setVisible(false);
			ToDatabase(0);
			break;
		case R.id.action_tick:
			Log.e("TICK", "TICK");
			tick.setVisible(false);
			cross.setVisible(true);
			ToDatabase(1);
			break;
		case android.R.id.home:
			getActivity().finish();
			break;
		}
		return false;
	}

	private void ToDatabase(int select) {
		((Schedule_details_container) getActivity()).artist.setSelected(select);

		MyDatabase db = new MyDatabase(activity);
		SQLiteDatabase sql = db.getWritableDatabase();

		ContentValues cv = new ContentValues();
		cv.put("selected", select);
		Log.e("TAGAS", ((Schedule_details_container) getActivity()).artist.getArtistId() + " artist id");
		sql.update(Config.TABLE_ARTISTS, cv, "artistid=?", new String[] { ((Schedule_details_container) getActivity()).artist.getArtistId() });

		sql.close();
		db.close();
	}

	private void SelectedOrNot() {
		if (((Schedule_details_container) getActivity()).artist.getSelected() == 1) {
			tick.setVisible(false);
			cross.setVisible(true);
		} else if (((Schedule_details_container) getActivity()).artist.getSelected() == 0) {
			tick.setVisible(true);
			cross.setVisible(false);
		}
	}
}
