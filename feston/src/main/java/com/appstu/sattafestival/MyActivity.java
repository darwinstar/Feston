package com.appstu.sattafestival;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import com.appstu.sattafestival.accommodation.Accommodation;
import com.appstu.sattafestival.faq.FAQ;
import com.appstu.sattafestival.flashlight.Flashlight;
import com.appstu.sattafestival.getting_there.Getting_there;
import com.appstu.sattafestival.line_up.Line_FragmentContainer;
import com.appstu.sattafestival.map.Map;
import com.appstu.sattafestival.news.News;
import com.appstu.sattafestival.sponsors.SponsorsList;
import com.appstu.sattafestival.tickets.Tickets;

public class MyActivity extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;

	private boolean updated = false;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	private ActionBar actionBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);

		mTitle = getString(R.string.app_name);

		mNavigationDrawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout));

		actionBar = getSupportActionBar();
	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by replacing fragments
		FragmentManager fragmentManager = getSupportFragmentManager();
		fragmentManager.beginTransaction().replace(R.id.container, PlaceholderFragment.newInstance(position + 1)).commit();
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 1:
			mTitle = getString(R.string.LINE_UP);
			getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in_frag, R.anim.fade_out_frag).replace(R.id.fragment, new Line_FragmentContainer())
					.commit();
			break;
		case 2:
			mTitle = getString(R.string.MAP);
			getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in_frag, R.anim.fade_out_frag).replace(R.id.fragment, new Map()).commit();
			break;
		case 3:
			mTitle = getString(R.string.FLASHLIGHT);
			getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in_frag, R.anim.fade_out_frag).replace(R.id.fragment, new Flashlight()).commit();
			break;
		case 4:
			mTitle = getString(R.string.TICKETS);
			getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in_frag, R.anim.fade_out_frag).replace(R.id.fragment, new Tickets()).commit();
			break;
		case 5:
			mTitle = getString(R.string.GETTING_THERE);
			getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in_frag, R.anim.fade_out_frag).replace(R.id.fragment, new Getting_there()).commit();
			break;
		case 6:
			mTitle = getString(R.string.ACCOMMODATION);
			getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in_frag, R.anim.fade_out_frag).replace(R.id.fragment, new Accommodation()).commit();
			break;
		case 7:
			mTitle = getString(R.string.FAQ);
			getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in_frag, R.anim.fade_out_frag).replace(R.id.fragment, new FAQ()).commit();
			break;
		case 8:
			mTitle = getString(R.string.NEWS);
			getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in_frag, R.anim.fade_out_frag).replace(R.id.fragment, new News()).commit();
			break;
		case 9:
			mTitle = getString(R.string.SPONSORS);
			getSupportFragmentManager().beginTransaction().setCustomAnimations(R.anim.fade_in_frag, R.anim.fade_out_frag).replace(R.id.fragment, new SponsorsList()).commit();
			break;
		}
	}

	public void restoreActionBar() {
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.my, menu);
			restoreActionBar();
			return true;
		}
		return false;
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		/**
		 * The fragment argument representing the section number for this
		 * fragment.
		 */
		private static final String ARG_SECTION_NUMBER = "section_number";

		/**
		 * Returns a new instance of this fragment for the given section number.
		 */
		public static PlaceholderFragment newInstance(int sectionNumber) {
			PlaceholderFragment fragment = new PlaceholderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_my, container, false);
			return rootView;
		}

		@Override
		public void onAttach(Activity activity) {
			super.onAttach(activity);
			((MyActivity) activity).onSectionAttached(getArguments().getInt(ARG_SECTION_NUMBER));
		}
	}

	public boolean isUpdated() {
		return updated;
	}

	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	@Override
	protected void onResume() {
		super.onResume();
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}
}
