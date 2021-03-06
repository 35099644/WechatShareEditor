package com.wudi.wechatshareeditor;

import com.flurry.android.FlurryAgent;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {

	private Context context;
	private EditText titleEditText, imgUrlEditText, sourceNameEditText,
			sourceDisplayNameEditText, uploadLinkEditText, uploadWidthEditText,
			uploadHeightEditText, uploadSouceEditText, laText, loText;
	public String title, image_url, source_display_username, source_username,
			upload_height, upload_link, upload_source, upload_width;
	private Button confirm, launch;
	private SettingsHelper settingsHelper;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		context = this;
		settingsHelper = new SettingsHelper(context);
		titleEditText = (EditText) this.findViewById(R.id.title);
		imgUrlEditText = (EditText) this.findViewById(R.id.image_url);
		sourceDisplayNameEditText = (EditText) this
				.findViewById(R.id.source_display_username);
		sourceNameEditText = (EditText) this.findViewById(R.id.source_username);
		uploadHeightEditText = (EditText) this.findViewById(R.id.upload_height);
		uploadLinkEditText = (EditText) this.findViewById(R.id.upload_link);
		uploadSouceEditText = (EditText) this.findViewById(R.id.upload_source);
		uploadWidthEditText = (EditText) this.findViewById(R.id.upload_width);
		laText = (EditText) this.findViewById(R.id.location_latitude);
		loText = (EditText) this.findViewById(R.id.location_longitude);

		init();

		confirm = (Button) this.findViewById(R.id.confirm);

		confirm.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				FlurryAgent.logEvent("Save");

				title = titleEditText.getText().toString();
				settingsHelper.setString("title", title);

				image_url = imgUrlEditText.getText().toString();
				settingsHelper.setString("image_url", image_url);

				source_display_username = sourceDisplayNameEditText.getText()
						.toString();
				settingsHelper.setString("source_display_username",
						source_display_username);

				source_username = sourceNameEditText.getText().toString();
				settingsHelper.setString("source_username", source_username);

				upload_height = uploadHeightEditText.getText().toString();
				settingsHelper.setString("upload_height", upload_height);

				upload_link = uploadLinkEditText.getText().toString();
				settingsHelper.setString("upload_link", upload_link);

				upload_source = uploadSouceEditText.getText().toString();
				settingsHelper.setString("upload_source", upload_source);

				upload_width = uploadWidthEditText.getText().toString();
				settingsHelper.setString("upload_width", upload_width);

				settingsHelper.setString("latitude", laText.getText()
						.toString());
				settingsHelper.setString("longitude", loText.getText()
						.toString());

				Toast.makeText(context, R.string.toast, Toast.LENGTH_LONG)
						.show();
			}
		});

		launch = (Button) this.findViewById(R.id.launch);

		launch.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				Intent LaunchIntent = getPackageManager()
						.getLaunchIntentForPackage("com.tencent.mm");
				if (LaunchIntent == null) {
					FlurryAgent.logEvent("Launch_fail");
					Toast.makeText(context, R.string.wechatnotfound,
							Toast.LENGTH_LONG).show();
				} else {
					FlurryAgent.logEvent("Launch_wechat");
					startActivity(LaunchIntent);
				}

			}

		});
	}

	private void init() {

		title = settingsHelper.getString("title", "");
		image_url = settingsHelper.getString("image_url", "");
		source_display_username = settingsHelper.getString(
				"source_display_username", "");
		source_username = settingsHelper.getString("source_username", "");
		upload_height = settingsHelper.getString("upload_height", "");
		upload_link = settingsHelper.getString("upload_link", "");
		upload_source = settingsHelper.getString("upload_source", "");
		upload_width = settingsHelper.getString("upload_width", "");

		titleEditText.setText(title);
		imgUrlEditText.setText(image_url);
		sourceDisplayNameEditText.setText(source_display_username);
		sourceNameEditText.setText(source_username);

		uploadHeightEditText.setText(upload_height);
		uploadLinkEditText.setText(upload_link);
		uploadSouceEditText.setText(upload_source);
		uploadWidthEditText.setText(upload_width);
		laText.setText(settingsHelper.getString("latitude", ""));
		loText.setText(settingsHelper.getString("longitude", ""));
	}

	@Override
	protected void onStart() {
		super.onStart();
		FlurryAgent.onStartSession(this, "M8KFV7ZV3VT97QJTQDMZ");
	}

	@Override
	protected void onStop() {
		super.onStop();
		FlurryAgent.onEndSession(this);
	}

}
