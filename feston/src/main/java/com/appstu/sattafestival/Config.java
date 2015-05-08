package com.appstu.sattafestival;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.ParseException;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;

/**
 * Created by Tadas on 7/2/2014.
 */
public class Config {
	// JSON GET URL's
	public static String BASE_URL = "http://salsafestival.lt/";
	public static String GET_ARTISTS = BASE_URL + "/json/artists";
	public static String GET_STAGES = BASE_URL + "/json/stages";
	public static String GET_SCHEDULE = BASE_URL + "/json/schedule";
	public static String GET_LAST_UPDATE = BASE_URL + "/json/lastupdate";
	public static String GET_POSTS = BASE_URL + "/json/posts";
	public static String GET_SPONSORS = BASE_URL + "/json/sponsors";
	public static String GET_FAQ = BASE_URL + "/json/faq";
	public static String GET_MAP = BASE_URL + "/json/mapimage";

	public static String DATABASE = "Feston";
	public static String TABLE_ARTISTS = "artists";
	public static String TABLE_FAQ = "faq";
	public static String TABLE_POSTS = "posts";
	public static String TABLE_SCHEDULE = "schedule";
	public static String TABLE_SPONSORS = "sponsors";
	public static String TABLE_STAGES = "stages";
	public static String TABLE_MAP = "map";

	/*************************************************
	 * CHECK INTERNET CONNECTION | TRUE IF USER CONNECTED
	 *************************************************/
	public static boolean hasConnection(Context c) {
		ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiNetwork != null && wifiNetwork.isConnected()) {
			return true;
		}

		NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mobileNetwork != null && mobileNetwork.isConnected()) {
			return true;
		}

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (activeNetwork != null && activeNetwork.isConnected()) {
			return true;
		}

		return false;
	}

	public static int convertDpToPixel(float dp, FragmentActivity activity) {
		DisplayMetrics metrics = activity.getResources().getDisplayMetrics();
		float px = dp * (metrics.densityDpi / 160f);
		return (int) px;
	}

	public static String getDate(String dateString, String format) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
		formatter.setTimeZone(TimeZone.getTimeZone("GMT+3"));
		Date value = null;
		try {
			value = formatter.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		} catch (java.text.ParseException e) {
			e.printStackTrace();
		}
		SimpleDateFormat dateFormatter = new SimpleDateFormat(format, Locale.getDefault());
		dateFormatter.setTimeZone(TimeZone.getDefault());
		String dt = dateFormatter.format(value);

		return dt;
	}
}
