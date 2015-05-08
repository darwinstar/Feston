package com.appstu.sattafestival.line_up;

import Objects.Artists;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.appstu.sattafestival.R;
import com.appstu.sattafestival.Config;
import com.appstu.sattafestival.MyActivity;
import com.appstu.sattafestival.MyDatabase;
import com.appstu.sattafestival.swipe_list.BaseSwipeListViewListener;
import com.appstu.sattafestival.swipe_list.SwipeListView;

public class Line_Schedule extends Fragment {
	private int checkTolarancePx;
	private int listViewItemWidth;
	private SwipeListView swipeListView;
	private ScheduleListAdapter myListAdapter;
	private ImageView ivCheck;

	private Artists[] artists;
	private ProgressDialog progressDialog;
	private final int ARTIST_CODE = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		return inflater.inflate(R.layout.line_artists, container, false);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		// how many pixels until we want to colour our swipe view
		checkTolarancePx = Config.convertDpToPixel(90, getActivity());
		// calculate screen width or whatever width your listview item is
		Display display = getActivity().getWindowManager().getDefaultDisplay();
		listViewItemWidth = display.getWidth();
		swipeListView = (SwipeListView) getView().findViewById(R.id.list);
		swipeListView.setSwipeListViewListener(itemSwipeListener);
		// offset swipeview by list item width so it bounces back
		swipeListView.setOffsetRight(listViewItemWidth);

		new AsyncGetData().execute();
	}

	private class AsyncGetData extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			MyDatabase db = new MyDatabase(getActivity());
			SQLiteDatabase sql = db.getReadableDatabase();
			Cursor c = sql.rawQuery(
					"SELECT schedule.subjectid, schedule.stageid,stages.stageid, artists.artistid as artid, artists.title as arttitle, artists.company as artcompany,"
							+ "artists.description_en as artdesc, artists.image as artimg, artists.selected as artselect, stages.title as stagename, starts, ends,"
							+ "(datetime('now', 'localtime')>=starts AND datetime('now', 'localtime')<ends) as iscurrent FROM schedule, artists, stages "
							+ "WHERE schedule.subjectid=artists.artistid AND schedule.stageid=stages.stageid AND artists.selected=1 ORDER BY starts", null);
			Log.e("TAG", "get count " + c.getCount());
			if (c.moveToFirst()) {
				Log.e("TAGAS", "CIA");
				artists = new Artists[c.getCount()];
				int i = 0;
				do {
					artists[i] = new Artists();
					artists[i].setArtistId(c.getString(c.getColumnIndex("artid")));
					artists[i].setTitle(c.getString(c.getColumnIndex("arttitle")));
					artists[i].setCompany(c.getString(c.getColumnIndex("artcompany")));
					artists[i].setDescription_en(c.getString(c.getColumnIndex("artdesc")));
					artists[i].setImage(c.getString(c.getColumnIndex("artimg")));
					artists[i].setSelected(c.getInt(c.getColumnIndex("artselect")));
					artists[i].setStarts(c.getString(c.getColumnIndex("starts")));
					artists[i].setEnds(c.getString(c.getColumnIndex("ends")));
					artists[i].setStageTitle(c.getString(c.getColumnIndex("stagename")));
					i++;
				} while (c.moveToNext());
			} else {
				artists = new Artists[0];
			}
			c.close();
			sql.close();
			db.close();
			return null;
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			if (artists != null) {
				myListAdapter = new ScheduleListAdapter(getActivity(), artists);
				swipeListView.setAdapter(myListAdapter);
			}
		}
	}

	private BaseSwipeListViewListener itemSwipeListener = new BaseSwipeListViewListener() {

		private RelativeLayout rlBack;
		private ImageView ivTick, ivCross;
		// disallow more than one call to saving checked state
		private boolean checkFlag = false;
		private Artists artist;

		@Override
		public void onStart(int position, int action, boolean right, View view) {
			rlBack = (RelativeLayout) view.findViewById(R.id.back);
			ivCheck = (ImageView) view.findViewById(R.id.ivCheck);
			ivTick = (ImageView) view.findViewById(R.id.ivTick);
			ivCross = (ImageView) view.findViewById(R.id.ivCross);

			if (view != null && view.getParent() != null) {
				// get currently swiping item from adapter
				artist = myListAdapter.getItem(position);
				if (artist.getSelected() == 1) {
					ivCross.setVisibility(View.VISIBLE);
					ivTick.setVisibility(View.INVISIBLE);
				} else {
					ivCross.setVisibility(View.INVISIBLE);
					ivTick.setVisibility(View.VISIBLE);
				}
			}
		}

		@Override
		public void onMove(int position, float x, View view) {
			if (view != null) {
				// do something when we swiped more than checkTolarancePx
				if (x > checkTolarancePx) {
					if (!checkFlag) {
						checkFlag = true;
						rlBack.setBackgroundColor(getResources().getColor(R.color.actionBarBackground));
					}
				} else if (checkFlag && x < checkTolarancePx) {
					rlBack.setBackgroundColor(getResources().getColor(R.color.sattaBackground));
					checkFlag = false;
				}
			}
		}

		@Override
		public void onEnd(View view) {
			if (view != null) {
				if (checkFlag && artist != null) {
					// do somehitng when animation ends
					rlBack.setBackgroundColor(getResources().getColor(R.color.sattaBackground));
					if (artist.getSelected() == 1) {
						ArtistSelected(false);
					} else {
						ArtistSelected(true);
					}
				}
				checkFlag = false;
			}
		}

		private void ArtistSelected(boolean b) {
			if (b) {
				ivCheck.setVisibility(View.VISIBLE);
				artist.setSelected(1);
			} else {
				ivCheck.setVisibility(View.INVISIBLE);
				artist.setSelected(0);
			}
			myListAdapter.setArtistSelected(artist);
			MyDatabase db = new MyDatabase(getActivity());
			SQLiteDatabase sql = db.getWritableDatabase();

			ContentValues cv = new ContentValues();
			cv.put("selected", artist.getSelected());
			Log.e("TAGAS", artist.getArtistId() + " artist id");
			sql.update(Config.TABLE_ARTISTS, cv, "artistid=?", new String[] { artist.getArtistId() });

			sql.close();
			db.close();
		}

		@Override
		public void onClickFrontView(int position, AdapterView adapterView) {
			Artists artist = myListAdapter.getItem(position);
			Intent i = new Intent(getActivity(), Schedule_details_container.class);

			artist.setPositionAdapter(position);
			Log.e("POSITION", "POSITION" + position);
			i.putExtra("Artist", artist);

			startActivityForResult(i, ARTIST_CODE);
			getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		}
	};

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == ARTIST_CODE && resultCode == Activity.RESULT_OK) {

			Artists artist = (Artists) data.getExtras().getSerializable("Artist");
			Log.e("TAGAS", "selected " + artist.getSelected());
			Log.e("TAGAS", "position " + artist.getPositionAdapter());

			artists[artist.getPositionAdapter()].setSelected(artist.getSelected());

			myListAdapter.addSelected(artist.getPositionAdapter(), artist.getSelected());
			myListAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		super.setUserVisibleHint(isVisibleToUser);
		if (isVisibleToUser) {
			if (((MyActivity) getActivity()).isUpdated()) {
				new AsyncGetData().execute();
			}
		}
	}
}
