package com.appstu.sattafestival.line_up;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Tadas on 7/1/2014.
 */
public class LinePagerAdapter extends FragmentStatePagerAdapter {

	public LinePagerAdapter(FragmentManager fm) {
		super(fm);
	}

	final int PAGE_COUNT = 3;

	/**
	 * Constructor of the class
	 * 
	 * @param cONTENT
	 */

	/**
	 * This method will be invoked when a page is requested to create
	 */

	@Override
	public Fragment getItem(int position) {

		switch (position) {
		case 0:
			Line_Artists artists = new Line_Artists();
			return artists;
		case 1:
			Line_Stages stages = new Line_Stages();
			return stages;
		case 2:
			Line_Schedule schedule = new Line_Schedule();
			return schedule;
		}

		return null;
	}

	@Override
	public CharSequence getPageTitle(int position) {
		String title = "";
		switch (position) {
		case 0:
			title = "Artists";
			break;
		case 1:
			title = "Stages";
			break;
		case 2:
			title = "My Schedule";
			break;
		}
		return title;
	}

	@Override
	public int getCount() {
		return PAGE_COUNT;
	}

}
