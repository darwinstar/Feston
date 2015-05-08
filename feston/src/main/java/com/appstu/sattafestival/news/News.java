package com.appstu.sattafestival.news;

import Objects.Posts;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

/**
 * Created by Tadas on 7/1/2014.
 */
public class News extends ListFragment {

	private ListView lv;
	private NewsListAdapter myListAdapter;

	private Posts[] posts;
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
		@Override
		protected Void doInBackground(Void... params) {
			MyDatabase db = new MyDatabase(activity);
			SQLiteDatabase sql = db.getReadableDatabase();
			Cursor c = sql.query(Config.TABLE_POSTS, null, "category=? ORDER by position", new String[] { "News" }, null, null, null);
			Log.e("TAG", "get count " + c.getCount());
			if (c.moveToFirst()) {
				posts = new Posts[c.getCount()];
				int i = 0;
				do {
					posts[i] = new Posts();
					posts[i].setDate(c.getString(c.getColumnIndex("date")));
					posts[i].setContent_en(c.getString(c.getColumnIndex("content_en")));
					posts[i].setTitle_en(c.getString(c.getColumnIndex("title_en")));
					posts[i].setImage(c.getString(c.getColumnIndex("image")));
					Log.e("TAGAS", "position: " + i + ", title: " + posts[i].getTitle_en() + ", image: " + posts[i].getImage());
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
			if (posts != null) {
				myListAdapter = new NewsListAdapter(activity, posts);
				lv.setAdapter(myListAdapter);
			}
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		Posts post = myListAdapter.getItem(position);
		Intent i = new Intent(activity, News_details_container.class);

		i.putExtra("Post", post);

		startActivity(i);
		activity.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
		super.onListItemClick(l, v, position, id);
	}

}
