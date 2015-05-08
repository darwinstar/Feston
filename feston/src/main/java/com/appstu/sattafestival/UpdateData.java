package com.appstu.sattafestival;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by Tadas on 7/2/2014.
 */
public class UpdateData {

	private long lastUpdatedJson;
	private long lastUpdatedPrefs;
	private ReadJsonObjects read;

	HttpClient httpClient;

	public void update(Context context) {
		httpClient = new DefaultHttpClient();
		SharedPreferences prefs = context.getSharedPreferences(context.getString(R.string.GET_PREFS), Context.MODE_PRIVATE);
		lastUpdatedPrefs = prefs.getLong(context.getString(R.string.Last_updated), 0);

		if (LastUpdate() > lastUpdatedPrefs) {
			read = new ReadJsonObjects(context);
			ArtistsUpdate();
			StagesUpdate();
			ScheduleUpdate();
			PostsUpdate();
			SponsorsUpdate();
			FaqUpdate();
			MapUpdate();
		}
	}

	private long LastUpdate() {
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Config.GET_LAST_UPDATE));

			HttpEntity entity = response.getEntity();
			String stringResponse = EntityUtils.toString(entity);

			Log.e("TAG", "Response LastUpdate: " + stringResponse);

			JSONObject json = new JSONObject(stringResponse);
			lastUpdatedJson = json.getLong("lastupdated");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return lastUpdatedJson;
	}

	private void ArtistsUpdate() {
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Config.GET_ARTISTS));

			HttpEntity entity = response.getEntity();
			String stringResponse = EntityUtils.toString(entity);

			Log.e("TAG", "Response Artists: " + stringResponse);

			JSONObject json = new JSONObject(stringResponse);
			read.ReadArtists(json);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void StagesUpdate() {
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Config.GET_STAGES));

			HttpEntity entity = response.getEntity();
			String stringResponse = EntityUtils.toString(entity);
			Log.e("TAG", "Response Stages: " + stringResponse);

			JSONObject json = new JSONObject(stringResponse);
			read.ReadStages(json);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void ScheduleUpdate() {
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Config.GET_SCHEDULE));

			HttpEntity entity = response.getEntity();
			String stringResponse = EntityUtils.toString(entity);

			Log.e("TAG", "Response Schedule: " + stringResponse);

			JSONObject json = new JSONObject(stringResponse);
			read.ReadSchedule(json);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void PostsUpdate() {
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Config.GET_POSTS));

			HttpEntity entity = response.getEntity();
			String stringResponse = EntityUtils.toString(entity);

			Log.e("TAG", "Response Posts: " + stringResponse);

			JSONObject json = new JSONObject(stringResponse);
			read.ReadPosts(json);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void SponsorsUpdate() {
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Config.GET_SPONSORS));

			HttpEntity entity = response.getEntity();
			String stringResponse = EntityUtils.toString(entity);
			Log.e("TAG", "Response Sponsors: " + stringResponse);

			JSONObject json = new JSONObject(stringResponse);
			read.ReadSponsors(json);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void FaqUpdate() {
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Config.GET_FAQ));

			HttpEntity entity = response.getEntity();
			String stringResponse = EntityUtils.toString(entity);
			Log.e("TAG", "Response Faq: " + stringResponse);

			JSONObject json = new JSONObject(stringResponse);
			read.ReadFaq(json);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	private void MapUpdate() {
		try {
			HttpResponse response = httpClient.execute(new HttpGet(Config.GET_MAP));

			HttpEntity entity = response.getEntity();
			String stringResponse = EntityUtils.toString(entity);
			Log.e("TAG", "Response Map: " + stringResponse);

			JSONObject json = new JSONObject(stringResponse);
			read.ReadMap(json);
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
