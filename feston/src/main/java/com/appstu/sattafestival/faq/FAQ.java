package com.appstu.sattafestival.faq;

import Objects.Faqs;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.appstu.sattafestival.Config;
import com.appstu.sattafestival.MyDatabase;
import com.appstu.sattafestival.R;

public class FAQ extends Fragment {

	private FragmentActivity activity;
	private Faqs[] faq;
	private FaqExpandableListAdapter faqListAdapter;
	private ExpandableListView lv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.faq_listview, null);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		activity = getActivity();

		lv = (ExpandableListView) getView().findViewById(R.id.list);

		new AsyncGetData().execute();
	}

	private class AsyncGetData extends AsyncTask<Void, Void, Void> {
		@Override
		protected Void doInBackground(Void... params) {
			MyDatabase db = new MyDatabase(activity);
			SQLiteDatabase sql = db.getReadableDatabase();
			Cursor c = sql.rawQuery("SELECT * FROM " + Config.TABLE_FAQ, null);
			Log.e("TAG", "get count " + c.getCount());
			if (c.moveToFirst()) {
				faq = new Faqs[c.getCount()];
				int i = 0;
				do {
					faq[i] = new Faqs();
					faq[i].setFaqId(c.getString(c.getColumnIndex("faqid")));
					faq[i].setQuestion_en(c.getString(c.getColumnIndex("question_en")));
					faq[i].setAnswer_en(c.getString(c.getColumnIndex("answer_en")));
					faq[i].setPosition(c.getString(c.getColumnIndex("position")));
					Log.e("TAGAS 1", faq[i].getPosition());
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
			if (faq != null) {
				SortAlgo(faq);
				faqListAdapter = new FaqExpandableListAdapter(activity, faq);
				lv.setAdapter(faqListAdapter);
			}
		}

		private void SortAlgo(Faqs[] faq) {
			for (int i = 0; i < faq.length; i++) {
				for (int j = i + 1; j < faq.length; j++) {
					if (Integer.parseInt(faq[i].getPosition()) > Integer.parseInt(faq[j].getPosition())) {
						Faqs faqTemp = faq[i];
						faq[i] = faq[j];
						faq[j] = faqTemp;
					}
				}
			}
		}

	}
}
