package com.claude.mainactivity;
/**
 * test activity
 */
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.os.Bundle;
import android.widget.Toast;

import com.claude.obtaininformation.NFCDetechBase;

public class TestNfcAdapter extends NFCDetechBase {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_test_nfc);
	}

	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
		String id = tag.getId().toString();
		Toast.makeText(getApplicationContext(), id, Toast.LENGTH_SHORT).show();
	}

}
