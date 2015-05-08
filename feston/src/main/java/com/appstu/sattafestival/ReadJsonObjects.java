package com.appstu.sattafestival;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

/**
 * Created by Tadas on 7/3/2014.
 */
public class ReadJsonObjects {

	Context context;

	public ReadJsonObjects(Context context) {
		this.context = context;
	}

	public void ReadArtists(JSONObject json) {
		for (int i = 1; i <= json.length(); i++) {
			try {
				JSONObject jsonArtist = json.getJSONObject(Integer.toString(i));

				MyDatabase db = new MyDatabase(context);
				SQLiteDatabase sql = db.getWritableDatabase();

				ContentValues cv = new ContentValues();
				cv.put("artistid", jsonArtist.getString("artistid"));
				cv.put("title", jsonArtist.getString("title"));
				cv.put("company", jsonArtist.getString("company"));
				cv.put("country", jsonArtist.getString("country"));
				cv.put("city", jsonArtist.getString("city"));
				cv.put("importance", jsonArtist.getString("importance"));
				cv.put("description_lt", jsonArtist.getString("descriptionlt"));
				cv.put("description_en", jsonArtist.getString("descriptionen"));
				cv.put("position", jsonArtist.getString("position"));
				cv.put("image", Config.BASE_URL + jsonArtist.getString("image"));

				Cursor c = sql.query(Config.TABLE_ARTISTS, null, "artistid=?", new String[] { jsonArtist.getString("artistid") }, null, null, null);
				if (c.moveToFirst()) {
					sql.update(Config.TABLE_ARTISTS, cv, "artistid=?", new String[] { jsonArtist.getString("artistid") });
				} else {
					sql.insert(Config.TABLE_ARTISTS, null, cv);
				}

				sql.close();
				db.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public void ReadSchedule(JSONObject json) {
		for (int i = 1; i <= json.length(); i++) {
			try {
				JSONObject jsonSchedule = json.getJSONObject(Integer.toString(i));

				MyDatabase db = new MyDatabase(context);
				SQLiteDatabase sql = db.getWritableDatabase();

				ContentValues cv = new ContentValues();
				cv.put("scheduleid", jsonSchedule.getInt("scheduleid"));
				cv.put("subjectid", jsonSchedule.getString("subjectid"));
				cv.put("title", jsonSchedule.getString("title"));
				cv.put("starts", jsonSchedule.getString("start"));
				cv.put("ends", jsonSchedule.getString("end"));
				cv.put("stageid", jsonSchedule.getString("stageid"));

				Cursor c = sql.query(Config.TABLE_SCHEDULE, null, "scheduleid=?", new String[] { jsonSchedule.getString("scheduleid") }, null, null, null);
				if (c.moveToFirst()) {
					sql.update(Config.TABLE_SCHEDULE, cv, "scheduleid=?", new String[] { jsonSchedule.getString("scheduleid") });
				} else {
					sql.insert(Config.TABLE_SCHEDULE, null, cv);
				}

				sql.close();
				db.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public void ReadStages(JSONObject json) {
		for (int i = 1; i <= json.length(); i++) {
			try {
				JSONObject jsonStage = json.getJSONObject(Integer.toString(i));

				MyDatabase db = new MyDatabase(context);
				SQLiteDatabase sql = db.getWritableDatabase();

				ContentValues cv = new ContentValues();
				cv.put("stageid", jsonStage.getString("stageid"));
				cv.put("title", jsonStage.getString("title"));
				cv.put("subtitle", jsonStage.getString("subtitle"));
				cv.put("description_lt", jsonStage.getString("descriptionlt"));
				cv.put("description_en", jsonStage.getString("descriptionen"));
				cv.put("address", jsonStage.getString("address"));
				cv.put("latitude", jsonStage.getString("latitude"));
				cv.put("longitude", jsonStage.getString("longitude"));

				Cursor c = sql.query(Config.TABLE_STAGES, null, "stageid=?", new String[] { jsonStage.getString("stageid") }, null, null, null);
				if (c.moveToFirst()) {
					sql.update(Config.TABLE_STAGES, cv, "stageid=?", new String[] { jsonStage.getString("stageid") });
				} else {
					sql.insert(Config.TABLE_STAGES, null, cv);
				}

				sql.close();
				db.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public void ReadFaq(JSONObject json) {
		for (int i = 1; i <= json.length(); i++) {
			try {
				JSONObject jsonFaq = json.getJSONObject(Integer.toString(i));

				MyDatabase db = new MyDatabase(context);
				SQLiteDatabase sql = db.getWritableDatabase();

				String answerEn = jsonFaq.getString("answeren");
				String questionEn = jsonFaq.getString("questionen");

				ContentValues cv = new ContentValues();
				cv.put("faqid", jsonFaq.getString("faqid"));
				cv.put("question_lt", jsonFaq.getString("questionlt"));
				cv.put("answer_lt", jsonFaq.getString("answerlt"));
				cv.put("position", jsonFaq.getString("position"));
				cv.put("question_en", jsonFaq.getString("questionen"));
				cv.put("answer_en", jsonFaq.getString("answeren"));
				if (!questionEn.equals("") || !answerEn.equals("")) {
					Cursor c = sql.query(Config.TABLE_FAQ, null, "faqid=?", new String[] { jsonFaq.getString("faqid") }, null, null, null);
					if (c.moveToFirst()) {
						sql.update(Config.TABLE_FAQ, cv, "faqid=?", new String[] { jsonFaq.getString("faqid") });
					} else {
						sql.insert(Config.TABLE_FAQ, null, cv);
					}
				}

				sql.close();
				db.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public void ReadPosts(JSONObject json) {
		for (int i = 1; i <= json.length(); i++) {
			try {
				JSONObject jsonPosts = json.getJSONObject(Integer.toString(i));

				MyDatabase db = new MyDatabase(context);
				SQLiteDatabase sql = db.getWritableDatabase();

				ContentValues cv = new ContentValues();
				cv.put("postid", jsonPosts.getString("postid"));
				cv.put("title_lt", jsonPosts.getString("titlelt"));
				cv.put("title_en", jsonPosts.getString("titleen"));
				cv.put("content_lt", jsonPosts.getString("contentlt"));
				cv.put("content_en", jsonPosts.getString("contenten"));
				cv.put("date", jsonPosts.getString("date"));
				cv.put("position", jsonPosts.getString("position"));
				cv.put("category", jsonPosts.getString("category"));
				cv.put("image", Config.BASE_URL + jsonPosts.getString("image"));

				Cursor c = sql.query(Config.TABLE_POSTS, null, "postid=?", new String[] { jsonPosts.getString("postid") }, null, null, null);
				if (c.moveToFirst()) {
					sql.update(Config.TABLE_POSTS, cv, "postid=?", new String[] { jsonPosts.getString("postid") });
				} else {
					sql.insert(Config.TABLE_POSTS, null, cv);
				}

				sql.close();
				db.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public void ReadSponsors(JSONObject json) {
		for (int i = 1; i <= json.length(); i++) {
			try {
				JSONObject jsonSponsors = json.getJSONObject(Integer.toString(i));

				MyDatabase db = new MyDatabase(context);
				SQLiteDatabase sql = db.getWritableDatabase();

				ContentValues cv = new ContentValues();
				cv.put("sponsorid", jsonSponsors.getString("sponsorid"));
				cv.put("name", jsonSponsors.getString("name"));
				cv.put("picture", Config.BASE_URL + jsonSponsors.getString("picture"));
				cv.put("link", jsonSponsors.getString("link"));
				cv.put("importance", jsonSponsors.getString("importance"));
				cv.put("position", jsonSponsors.getString("position"));
				cv.put("published", jsonSponsors.getString("published"));

				Cursor c = sql.query(Config.TABLE_SPONSORS, null, "sponsorid=?", new String[] { jsonSponsors.getString("sponsorid") }, null, null, null);
				if (c.moveToFirst()) {
					sql.update(Config.TABLE_SPONSORS, cv, "sponsorid=?", new String[] { jsonSponsors.getString("sponsorid") });
				} else {
					sql.insert(Config.TABLE_SPONSORS, null, cv);
				}

				sql.close();
				db.close();
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}

	public void ReadMap(JSONObject json) {
		try {
			InputStream isImage = new java.net.URL(Config.BASE_URL + json.getString("mapimage")).openStream();
			BufferedInputStream bis = new BufferedInputStream(isImage);
			Bitmap bitmapAvatar = BitmapFactory.decodeStream(bis);
			long map = 0;
			String fullPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/SattaF/";
			try {
				File dir = new File(fullPath);
				if (!dir.exists()) {
					dir.mkdirs();
				}
				OutputStream fOut = null;
				map = System.currentTimeMillis() / 1000;
				File file = new File(fullPath, Long.toString(map));

				if (file.exists())
					file.delete();

				file.createNewFile();
				fOut = new FileOutputStream(file);
				bitmapAvatar.compress(Bitmap.CompressFormat.JPEG, 80, fOut);

				fOut.flush();
				fOut.close();
			} catch (Exception e) {
				Log.e("saveToExternalStorage()", e.getMessage());
			}

			MyDatabase db = new MyDatabase(context);
			SQLiteDatabase sql = db.getWritableDatabase();

			ContentValues cv = new ContentValues();
			cv.put("image", fullPath + Long.toString(map));
			sql.insertWithOnConflict(Config.TABLE_MAP, null, cv, SQLiteDatabase.CONFLICT_REPLACE);

			sql.close();
			db.close();
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
