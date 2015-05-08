package com.appstu.sattafestival.map;

import java.io.File;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appstu.sattafestival.Config;
import com.appstu.sattafestival.MyDatabase;
import com.appstu.sattafestival.R;

public class Map extends Fragment {

	// WebView webView;
	private TouchImageView iv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		return inflater.inflate(R.layout.maps, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		iv = (TouchImageView) getView().findViewById(R.id.image);
		new AsyncLoad().execute();
	}

	private class AsyncLoad extends AsyncTask<Void, Void, Void> {

		private File imgFile;
		private Bitmap myBitmap;

		@Override
		protected void onPreExecute() {
			getActivity().setProgressBarIndeterminateVisibility(true);
			super.onPreExecute();
		}

		@Override
		protected Void doInBackground(Void... params) {
			MyDatabase db = new MyDatabase(getActivity());
			SQLiteDatabase sql = db.getReadableDatabase();

			Cursor c = sql.rawQuery("SELECT * FROM " + Config.TABLE_MAP, null);
			if (c.moveToFirst()) {
				imgFile = new File(c.getString(c.getColumnIndex("image")));
				if (imgFile.exists()) {
					myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
				}
			}
			sql.close();
			db.close();
			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			if (imgFile.exists()) {
				iv.setImageBitmap(myBitmap);
				iv.setZoom(1.6f);
				iv.setMinZoom(1.6f);
			}
			getActivity().setProgressBarIndeterminateVisibility(false);
			super.onPostExecute(result);
		}
	}
}
