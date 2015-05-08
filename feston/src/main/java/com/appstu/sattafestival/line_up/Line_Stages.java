package com.appstu.sattafestival.line_up;

import Objects.Stages;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.appstu.sattafestival.MyDatabase;
import com.appstu.sattafestival.R;

/**
 * Created by Tadas on 7/1/2014.
 */
public class Line_Stages extends ListFragment {

	private ListView lv;
	private StagesListAdapter myListAdapter;

	private Stages[] stages;
	private FragmentActivity activity;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.list, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		activity = getActivity();

		lv = getListView();

		new AsyncGetData().execute();
	}

	private class AsyncGetData extends AsyncTask<Void, Void, Void> {

		Cursor c;

		@Override
		protected Void doInBackground(Void... params) {
			MyDatabase db = new MyDatabase(activity);
			SQLiteDatabase sql = db.getReadableDatabase();

			Cursor cStageId = sql.rawQuery("SELECT stageid FROM stages", null);
			if (cStageId.moveToFirst()) {
				int i = 0;
				stages = new Stages[cStageId.getCount()];
				do {
					c = sql.rawQuery("SELECT scheduleid, subjectid, stages.stageid, stages.title as stagename, artists.title as "
							+ "artisttitle, schedule.title as scheduletitle, artists.image as artistimage, starts, ends, "
							+ "(datetime('now', 'localtime')>=starts AND datetime('now', 'localtime')<ends) as iscurrent FROM schedule, "
							+ "artists, stages WHERE schedule.subjectid=artists.artistid AND schedule.stageid=stages.stageid AND "
							+ "datetime('now', 'localtime')<ends AND stages.stageid=? ORDER BY starts LIMIT 1",
							new String[] { cStageId.getString(cStageId.getColumnIndex("stageid")) });
					if (c.moveToFirst()) {
						stages[i] = new Stages();
						stages[i].setStagename(c.getString(c.getColumnIndex("stagename")));
						stages[i].setArtisttitle(c.getString(c.getColumnIndex("artisttitle")));
						stages[i].setScheduletitle(c.getString(c.getColumnIndex("scheduletitle")));
						stages[i].setArtistimage(c.getString(c.getColumnIndex("artistimage")));
						stages[i].setStart(c.getString(c.getColumnIndex("starts")));
						stages[i].setEnd(c.getString(c.getColumnIndex("ends")));
						stages[i].setStageid(cStageId.getInt(cStageId.getColumnIndex("stageid")));
					}

					i++;
					c.close();
				} while (cStageId.moveToNext());
			}
			cStageId.close();
			sql.close();
			db.close();
			return null;
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			if (stages != null && c.getCount() != 0) {
				myListAdapter = new StagesListAdapter(activity, stages);
				lv.setAdapter(myListAdapter);
			}
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		Stages stage = myListAdapter.getItem(position);
		Intent i = new Intent(getActivity(), Line_StagesInnear.class);

		i.putExtra("Stage", stage);

		startActivity(i);
		getActivity().overridePendingTransition(R.anim.fade_in, R.anim.fade_out);

		super.onListItemClick(l, v, position, id);
	}

}
