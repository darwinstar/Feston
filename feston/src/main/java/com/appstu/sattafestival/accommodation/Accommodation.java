package com.appstu.sattafestival.accommodation;

import Objects.Posts;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appstu.sattafestival.Config;
import com.appstu.sattafestival.MyDatabase;
import com.appstu.sattafestival.R;
import com.squareup.picasso.Picasso;

import fading.FadingActionBarHelper1;

public class Accommodation extends Fragment {

	private FragmentActivity activity;
	private TextView tvDescription, tvTitle, tvCompany;
	private View vLine;
	private ImageView ivImage;
	private Posts[] posts;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		FadingActionBarHelper1 helper = new FadingActionBarHelper1().actionBarBackground(R.drawable.ab_solid_satta).headerLayout(R.layout.line_artist_details_header)
				.contentLayout(R.layout.line_artist_details);
		helper.initActionBar(activity);
		View view = helper.createView(inflater);

		tvDescription = (TextView) view.findViewById(R.id.tvDescription);
		ivImage = (ImageView) view.findViewById(R.id.ivImage);
		tvCompany = (TextView) view.findViewById(R.id.tvCompany);
		tvTitle = (TextView) view.findViewById(R.id.tvTitle);
		vLine = (View) view.findViewById(R.id.vLine);

		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		activity = getActivity();
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		tvCompany.setVisibility(View.GONE);
		tvTitle.setVisibility(View.GONE);
		vLine.setVisibility(View.GONE);

		new GetData().execute();
	}

	private class GetData extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			MyDatabase db = new MyDatabase(activity);
			SQLiteDatabase sql = db.getReadableDatabase();

			Cursor c = sql.query(Config.TABLE_POSTS, null, "category=?", new String[] { "Accomodation" }, null, null, null);
			if (c.moveToFirst()) {
				int i = 0;
				posts = new Posts[c.getCount()];
				do {
					posts[i] = new Posts();
					posts[i].setImage(c.getString(c.getColumnIndex("image")));
					posts[i].setContent_en(c.getString(c.getColumnIndex("content_en")));
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

			if (posts != null) {
				tvDescription.setText(Html.fromHtml(posts[0].getContent_en()));
				Picasso.with(activity).load(posts[0].getImage()).into(ivImage);
			}
		}
	}
}
