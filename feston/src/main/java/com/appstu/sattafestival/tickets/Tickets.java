package com.appstu.sattafestival.tickets;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.appstu.sattafestival.R;

public class Tickets extends Fragment {

	private ImageView tiketa, tickets, ticketpro;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.tickets, container, false);
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);

		tiketa = (ImageView) getView().findViewById(R.id.tiketa);
		tickets = (ImageView) getView().findViewById(R.id.tickets);
		ticketpro = (ImageView) getView().findViewById(R.id.ticketpro);

		clicks();
	}

	private void clicks() {
		tiketa.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String url = "http://www.tiketa.lt/satta_festival_2014_bilietas_galioja_trims_dienoms_49207";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});

		tickets.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String url = "http://www.residentadvisor.net/event.aspx?584372";
				Intent i = new Intent(Intent.ACTION_VIEW);
				i.setData(Uri.parse(url));
				startActivity(i);
			}
		});

		ticketpro.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AlertDialog.Builder build = new AlertDialog.Builder(getActivity());
				build.setTitle("Select language");
				build.setItems(R.array.langs, new OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (which == 0) {
							String url = "http://www.bilesuserviss.lv/eng/tickets/festivals/visi./satta-festival-2014-145888/";
							Intent i = new Intent(Intent.ACTION_VIEW);
							i.setData(Uri.parse(url));
							startActivity(i);
						} else if (which == 1) {
							String url = "http://www.piletilevi.ee/eng/tickets/festival/all/satta-festival-2014-145888/";
							Intent i = new Intent(Intent.ACTION_VIEW);
							i.setData(Uri.parse(url));
							startActivity(i);
						} else if (which == 2) {
							String url = "http://www.bileti.by/rus/bileti/festivals/all./satta-festival-2014-145888/";
							Intent i = new Intent(Intent.ACTION_VIEW);
							i.setData(Uri.parse(url));
							startActivity(i);
						}
					}
				});

				build.show();
			}
		});

	}
}
