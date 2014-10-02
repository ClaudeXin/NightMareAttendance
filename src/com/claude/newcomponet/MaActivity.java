package com.claude.newcomponet;

import java.util.ArrayList;
import java.util.List;

import com.claude.mainactivity.R;
import com.claude.newcomponet.SlideView.OnSlideListener;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MaActivity extends Activity implements OnItemClickListener,
		OnClickListener, OnSlideListener {

	private static final String TAG = "MaActivity";

	private ListViewCompat mListView;

	private List<MessageItem> mMessageItems = new ArrayList<MaActivity.MessageItem>();

	private SlideView mLastSlideViewWithStatusOn;
	private int cur_position;

	/**
	 * test
	 */

	private ArrayList<String> tmp = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main3);
		tmp.add("Claude");
		tmp.add("Kim");
		tmp.add("Session");
		tmp.add("RongSong");
		initView();
	}

	private void initView() {
		mListView = (ListViewCompat) findViewById(R.id.list);

		int index = 0;
		while (index < tmp.size()) {
			MessageItem item = new MessageItem();
			item.iconRes = R.drawable.wechat_icon;
			item.title = "hyit";
			item.msg = tmp.get(index);
			item.time = "20:03";
			mMessageItems.add(item);
			index++;
		}
		mListView.setAdapter(new SlideAdapter());
		mListView.setOnItemClickListener(this);
	}

	private class SlideAdapter extends BaseAdapter {

		private LayoutInflater mInflater;

		SlideAdapter() {
			super();
			mInflater = getLayoutInflater();
		}

		@Override
		public int getCount() {
			return mMessageItems.size();
		}

		@Override
		public Object getItem(int position) {
			return mMessageItems.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder holder;
			SlideView slideView = (SlideView) convertView;
			if (slideView == null) {
				View itemView = mInflater.inflate(R.layout.list_item, null);

				slideView = new SlideView(MaActivity.this);
				slideView.setContentView(itemView);

				holder = new ViewHolder(slideView);
				slideView.setOnSlideListener(MaActivity.this);
				slideView.setTag(holder);
			} else {
				holder = (ViewHolder) slideView.getTag();
			}
			MessageItem item = mMessageItems.get(position);
			item.slideView = slideView;
			item.slideView.shrink();

			holder.icon.setImageResource(item.iconRes);
			holder.title.setText(item.title);
			holder.msg.setText(item.msg);
			holder.time.setText(item.time);
			holder.deleteHolder.setOnClickListener(MaActivity.this);

			return slideView;
		}

	}

	public class MessageItem {
		public int iconRes;
		public String title;
		public String msg;
		public String time;
		public SlideView slideView;
	}

	private static class ViewHolder {
		public ImageView icon;
		public TextView title;
		public TextView msg;
		public TextView time;
		public ViewGroup deleteHolder;

		ViewHolder(View view) {
			icon = (ImageView) view.findViewById(R.id.icon);
			title = (TextView) view.findViewById(R.id.title);
			msg = (TextView) view.findViewById(R.id.msg);
			time = (TextView) view.findViewById(R.id.time);
			deleteHolder = (ViewGroup) view.findViewById(R.id.holder);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		cur_position = position;
		Log.e(TAG, "onItemClick position=" + position);
		Toast.makeText(getApplicationContext(), tmp.get(position),
				Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onSlide(View view, int status) {
		if (mLastSlideViewWithStatusOn != null
				&& mLastSlideViewWithStatusOn != view) {
			mLastSlideViewWithStatusOn.shrink();
		}

		if (status == SLIDE_STATUS_ON) {
			mLastSlideViewWithStatusOn = (SlideView) view;
		}
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.holder) {
			Toast.makeText(getApplicationContext(),
					"del" + tmp.get(cur_position), Toast.LENGTH_SHORT).show();
		}
	}
}
