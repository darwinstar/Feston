package com.appstu.sattafestival.line_up;

import Objects.Artists;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.appstu.sattafestival.R;
import com.appstu.sattafestival.swipe_list.SwipeListView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class ArtistsListAdapter extends BaseAdapter {

	private Context context;
	private Artists[] artists;

	public ArtistsListAdapter(Context context, Artists[] artists) {
		this.context = context;
		this.artists = artists;
	}

	public void setArtistSelected(Artists artist) {
		for (int i = 0; i < artists.length; i++) {
			if (artists[i].getArtistId() == artist.getArtistId()) {
				artists[i].setSelected(artist.getSelected());
				break;
			}
		}
	}

	@Override
	public int getCount() {
		return artists.length;
	}

	@Override
	public Artists getItem(int position) {
		return artists[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	public void addSelected(int pos, int select) {
		artists[pos].setSelected(select);
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.line_artists_item, parent, false);
			holder = new ViewHolder();
			holder.labelTitle = (TextView) convertView.findViewById(R.id.tvTitle);
			holder.labelCompany = (TextView) convertView.findViewById(R.id.tvCompany);
			holder.labelImage = (ImageView) convertView.findViewById(R.id.ivArtist);
			holder.labelSelected = (ImageView) convertView.findViewById(R.id.ivCheck);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		((SwipeListView) parent).recycle(convertView, position);
		Picasso.with(context).load(artists[position].getImage()).resize(200, 200).into(holder.labelImage, new Callback() {
			@Override
			public void onSuccess() {
			}

			@Override
			public void onError() {
				Log.e("ERROR", "ERROR LOADING___" + artists[position].getTitle() + "___");
				holder.labelImage.setImageResource(R.drawable.no_image);
			}
		});

		holder.labelTitle.setText(artists[position].getTitle());
		holder.labelCompany.setText(artists[position].getCompany());
		if (artists[position].getSelected() == 1) {
			holder.labelSelected.setVisibility(View.VISIBLE);
		} else {
			holder.labelSelected.setVisibility(View.INVISIBLE);
		}

		return convertView;
	}

	static class ViewHolder {
		TextView labelTitle;
		TextView labelCompany;
		ImageView labelImage;
		ImageView labelSelected;
	}
}
