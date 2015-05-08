package com.appstu.sattafestival.sponsors;

import Objects.Sponsors;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.appstu.sattafestival.R;
import com.squareup.picasso.Picasso;

public class SponsorsListAdapter extends BaseAdapter {

	private Context context;
	private Sponsors[] sponsors;

	public SponsorsListAdapter(Context context, Sponsors[] sponsors) {
		this.context = context;
		this.sponsors = sponsors;
	}

	@Override
	public int getCount() {
		return sponsors.length;
	}

	@Override
	public Sponsors getItem(int position) {
		return sponsors[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.sponsor_list_item, parent, false);
			holder = new ViewHolder();
			holder.labelImage = (ImageView) convertView.findViewById(R.id.ivImageSpons);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// caching images with picasso library
		Picasso.with(context).load(sponsors[position].getPicture()).into(holder.labelImage);

		return convertView;
	}

	static class ViewHolder {
		ImageView labelImage;
	}
}
