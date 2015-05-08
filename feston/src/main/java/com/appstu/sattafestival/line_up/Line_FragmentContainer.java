package com.appstu.sattafestival.line_up;

import java.lang.reflect.Field;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.appstu.sattafestival.R;
import com.viewpagerindicator.TabPageIndicator;

public class Line_FragmentContainer extends Fragment {

	private FragmentActivity activity;
	private LinePagerAdapter adapter;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		return inflater.inflate(R.layout.line_fragmentcontainer, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		activity = getActivity();

		final TabPageIndicator indicator = (TabPageIndicator) getView().findViewById(R.id.indicator);
		final ViewPager pager = (ViewPager) getView().findViewById(R.id.pager);

		try {
			Field mScroller;
			mScroller = ViewPager.class.getDeclaredField("mScroller");
			mScroller.setAccessible(true);
			FixedSpeedScroller scroller = new FixedSpeedScroller(pager.getContext());
			mScroller.set(pager, scroller);
		} catch (NoSuchFieldException e) {
		} catch (IllegalArgumentException e) {
		} catch (IllegalAccessException e) {
		}

		adapter = new LinePagerAdapter(activity.getSupportFragmentManager());
		pager.setAdapter(adapter);

		indicator.setViewPager(pager);

		indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				// adapter.notifyDataSetChanged();
				indicator.notifyDataSetChanged();
				pager.setCurrentItem(position, true);
			}

			@Override
			public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
			}

			@Override
			public void onPageScrollStateChanged(int state) {
			}
		});

	}

}
