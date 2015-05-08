package com.appstu.sattafestival.news;

import Objects.Posts;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appstu.sattafestival.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class NewsListAdapter extends BaseAdapter {

	private Context context;
	private Posts[] posts;

	public NewsListAdapter(Context context, Posts[] posts) {
		this.context = context;
		this.posts = posts;
	}

	@Override
	public int getCount() {
		return posts.length;
	}

	@Override
	public Posts getItem(int position) {
		return posts[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_item, parent, false);
			holder = new ViewHolder();
			holder.labelTitle = (TextView) convertView.findViewById(R.id.tvTitle);
			holder.labelDate = (TextView) convertView.findViewById(R.id.tvCompany);
			holder.labelImage = (ImageView) convertView.findViewById(R.id.ivArtist);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// caching images with picasso library
		Picasso.with(context).load(posts[position].getImage()).resize(200, 200).into(holder.labelImage, new Callback() {
			@Override
			public void onSuccess() {
			}

			@Override
			public void onError() {
				holder.labelImage.setImageResource(R.drawable.no_image);
			}
		});
		holder.labelTitle.setText(posts[position].getTitle_en());
		holder.labelDate.setText(posts[position].getDate());

		return convertView;
	}

	static class ViewHolder {
		TextView labelTitle;
		TextView labelDate;
		ImageView labelImage;
	}
}
