package com.appstu.sattafestival.news;

import Objects.Posts;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.appstu.sattafestival.R;
import com.manuelpeinado.fadingactionbar.compat.FadingActionBarHelper;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class News_details extends Fragment {

	FragmentActivity activity;
	private Posts post;
	private TextView tvTitle, tvDate, tvDescription;
	private ImageView ivImage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		activity = getActivity();
		((News_details_container) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);

		FadingActionBarHelper helper = new FadingActionBarHelper().actionBarBackground(R.drawable.ab_solid_satta).headerLayout(R.layout.line_artist_details_header)
				.contentLayout(R.layout.line_artist_details);
		helper.initActionBar(activity);
		View view = helper.createView(inflater);

		return view;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		post = (Posts) getArguments().getSerializable("Post");

		tvTitle = (TextView) getView().findViewById(R.id.tvTitle);
		tvDate = (TextView) getView().findViewById(R.id.tvCompany);
		tvDescription = (TextView) getView().findViewById(R.id.tvDescription);
		ivImage = (ImageView) getView().findViewById(R.id.ivImage);

		tvTitle.setText(post.getTitle_en());
		tvDate.setText(post.getDate());
		tvDescription.setText(Html.fromHtml(post.getContent_en()));
		Picasso.with(activity).load(post.getImage()).into(ivImage, new Callback() {
			@Override
			public void onSuccess() {
			}

			@Override
			public void onError() {
				ivImage.setImageResource(R.drawable.no_image);
			}
		});
	}

}
