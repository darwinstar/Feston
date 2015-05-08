package com.appstu.sattafestival.faq;

import Objects.Faqs;
import android.content.Context;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.appstu.sattafestival.R;

public class FaqExpandableListAdapter extends BaseExpandableListAdapter {

	private Context context;
	private Faqs[] faq;

	public FaqExpandableListAdapter(Context context, Faqs[] faq) {
		this.context = context;
		this.faq = faq;
	}

	@Override
	public Faqs getChild(int groupPosition, int childPosition) {
		return faq[groupPosition];
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return childPosition;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
		final Spanned childText = Html.fromHtml(getChild(groupPosition, childPosition).getAnswer_en());

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.faq_item_child, null);
		}

		TextView txtListChild = (TextView) convertView.findViewById(R.id.tvAnswer);

		txtListChild.setText(childText);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return 1;
	}

	@Override
	public Faqs getGroup(int groupPosition) {
		return faq[groupPosition];
	}

	@Override
	public int getGroupCount() {
		return faq.length;
	}

	@Override
	public long getGroupId(int groupPosition) {
		return groupPosition;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		ViewHolder holder;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(R.layout.faq_item, parent, false);
			holder = new ViewHolder();
			holder.textLabel = (TextView) convertView.findViewById(R.id.tvQuestion);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		final Faqs item = getGroup(groupPosition);

		holder.textLabel.setText(item.getQuestion_en());

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return true;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

	private static final class ViewHolder {
		TextView textLabel;
	}
}
