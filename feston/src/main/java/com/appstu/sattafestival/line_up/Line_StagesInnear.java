package com.appstu.sattafestival.line_up;

import Objects.Stages;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.appstu.sattafestival.R;
import com.appstu.sattafestival.MyDatabase;

/**
 * Created by Tadas on 7/1/2014.
 */
public class Line_StagesInnear extends ActionBarActivity {

	private ListView lv;
	private StagesInnearListAdapter myListAdapter;

	private Stages stage;
	private Stages[] stages;
	private int STAGE_CODE = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list);

		stage = (Stages) getIntent().getExtras().getSerializable("Stage");
		lv = (ListView) findViewById(android.R.id.list);

		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Stages stage = myListAdapter.getItem(position);
				Intent i = new Intent(getApplicationContext(), Line_StagesInnearDetailsContainer.class);

				stage.setPositionAdapter(position);
				i.putExtra("Stage", stage);

				startActivityForResult(i, STAGE_CODE);
				overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
			}
		});

		getSupportActionBar().setTitle(stage.getStagename());
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		new AsyncGetData().execute();
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == STAGE_CODE && resultCode == Activity.RESULT_OK) {

			Stages st = (Stages) data.getExtras().getSerializable("Stage");

			stages[st.getPositionAdapter()].setArtistSelected(st.getArtistSelected());
			myListAdapter.addSelected(st.getPositionAdapter(), st.getArtistSelected());
			myListAdapter.notifyDataSetChanged();
		}
	}

	private class AsyncGetData extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			MyDatabase db = new MyDatabase(getApplicationContext());
			SQLiteDatabase sql = db.getReadableDatabase();

			Cursor c = sql.rawQuery(
					"SELECT scheduleid, subjectid, artists.description_en as descrp ,artists.selected as sel , stages.stageid,artists.artistid as artid, stages.title as stagename, artists.title as "
							+ "artisttitle, schedule.title as scheduletitle, artists.image as artistimage, starts, ends, "
							+ "(datetime('now', 'localtime')>=starts AND datetime('now', 'localtime')<ends) as iscurrent FROM schedule, "
							+ "artists, stages WHERE schedule.subjectid=artists.artistid AND schedule.stageid=stages.stageid AND "
							+ "datetime('now', 'localtime')<ends AND stages.stageid=? ORDER BY starts", new String[] { Integer.toString(stage.getStageid()) });
			Log.e("inner count", c.getCount() + "");
			if (c.moveToFirst()) {
				int i = 0;
				stages = new Stages[c.getCount()];
				do {
					stages[i] = new Stages();
					stages[i].setStagename(c.getString(c.getColumnIndex("stagename")));
					stages[i].setArtisttitle(c.getString(c.getColumnIndex("artisttitle")));
					stages[i].setScheduletitle(c.getString(c.getColumnIndex("scheduletitle")));
					stages[i].setArtistimage(c.getString(c.getColumnIndex("artistimage")));
					stages[i].setStart(c.getString(c.getColumnIndex("starts")));
					stages[i].setEnd(c.getString(c.getColumnIndex("ends")));
					stages[i].setDescription_en(c.getString(c.getColumnIndex("descrp")));
					stages[i].setArtistid(c.getString(c.getColumnIndex("artid")));
					stages[i].setArtistSelected(c.getInt(c.getColumnIndex("sel")));
					i++;
				} while (c.moveToNext());
			}

			c.close();
			sql.close();
			db.close();
			return null;
		}

		@Override
		protected void onPostExecute(Void aVoid) {
			super.onPostExecute(aVoid);
			if (stages != null) {
				myListAdapter = new StagesInnearListAdapter(getApplicationContext(), stages);
				lv.setAdapter(myListAdapter);
			}
		}
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected void onResume() {
		super.onResume();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}

}
