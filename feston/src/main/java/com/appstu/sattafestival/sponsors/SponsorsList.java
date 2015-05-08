package com.appstu.sattafestival.sponsors;

import Objects.Sponsors;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appstu.sattafestival.R;
import com.appstu.sattafestival.Config;
import com.appstu.sattafestival.MyDatabase;

public class SponsorsList extends ListFragment {

	private FragmentActivity activity;
	private Sponsors[] sponsors;
	private ListView lv;
	private SponsorsListAdapter myListAdapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		activity = getActivity();
		lv = getListView();

		new GetData().execute();
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		String url = myListAdapter.getItem(position).getLink();
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);
		activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		super.onListItemClick(l, v, position, id);
	}

	private class GetData extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			MyDatabase db = new MyDatabase(activity);
			SQLiteDatabase sql = db.getReadableDatabase();

			Cursor c = sql.rawQuery("SELECT * FROM " + Config.TABLE_SPONSORS + " WHERE published=? Order BY importance, position", new String[] { "1" });
			if (c.moveToFirst()) {
				int i = 0;
				Log.e("TAGAS", c.getCount() + " count");
				sponsors = new Sponsors[c.getCount()];
				do {
					sponsors[i] = new Sponsors();
					sponsors[i].setSponsorid(c.getInt(c.getColumnIndex("sponsorid")));
					sponsors[i].setName(c.getString(c.getColumnIndex("name")));
					sponsors[i].setPicture(c.getString(c.getColumnIndex("picture")));
					sponsors[i].setLink(c.getString(c.getColumnIndex("link")));
					sponsors[i].setImportance(c.getString(c.getColumnIndex("importance")));
					sponsors[i].setPosition(c.getString(c.getColumnIndex("position")));
					sponsors[i].setPublished(c.getString(c.getColumnIndex("published")));
					Log.e("TAGAS", c.getString(c.getColumnIndex("picture")) + " picture");
					i++;
				} while (c.moveToNext());
			}

			sql.close();
			db.close();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			super.onPostExecute(result);

			if (sponsors != null) {
				myListAdapter = new SponsorsListAdapter(activity, sponsors);
				lv.setAdapter(myListAdapter);
			}

		}
	}
}
