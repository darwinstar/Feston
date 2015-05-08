package com.appstu.sattafestival.line_up;

import Objects.Stages;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appstu.sattafestival.R;
import com.appstu.sattafestival.Config;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class StagesInnearListAdapter extends BaseAdapter {

	private Context context;
	private Stages[] stages;

	public StagesInnearListAdapter(Context context, Stages[] stages) {
		this.context = context;
		this.stages = stages;
	}

	@Override
	public int getCount() {
		return stages.length;
	}

	@Override
	public Stages getItem(int position) {
		return stages[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addSelected(int pos, int select) {
		stages[pos].setArtistSelected(select);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.list_item, parent, false);
			holder = new ViewHolder();
			holder.labelTitle = (TextView) convertView.findViewById(R.id.tvTitle);
			holder.labelDate = (TextView) convertView.findViewById(R.id.tvCompany);
			holder.labelImage = (ImageView) convertView.findViewById(R.id.ivArtist);
			holder.labelCheck = (ImageView) convertView.findViewById(R.id.ivCheck);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// caching images with picasso library

		Picasso.with(context).load(stages[position].getArtistimage()).into(holder.labelImage, new Callback() {

			@Override
			public void onSuccess() {
			}

			@Override
			public void onError() {
				holder.labelImage.setImageResource(R.drawable.no_image);
			}
		});

		holder.labelTitle.setText(stages[position].getScheduletitle());

		String start = Config.getDate(stages[position].getStart(), "EE HH.mm");
		String end = Config.getDate(stages[position].getEnd(), "HH.mm");

		holder.labelDate.setText(start + "-" + end);
		if (stages[position].getArtistSelected() == 1) {
			holder.labelCheck.setVisibility(View.VISIBLE);
		} else {
			holder.labelCheck.setVisibility(View.INVISIBLE);
		}

		return convertView;
	}

	static class ViewHolder {
		TextView labelTitle;
		TextView labelDate;
		ImageView labelImage;
		ImageView labelCheck;
	}
}
