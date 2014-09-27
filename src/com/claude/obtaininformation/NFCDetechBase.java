package com.claude.obtaininformation;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import android.app.PendingIntent;
import android.content.Intent;
import android.nfc.NfcAdapter;
import android.nfc.tech.NfcA;

public class NFCDetechBase extends Activity {

	protected NfcAdapter nfc_adapter;
	protected PendingIntent nfc_intent;
	protected String[][] nfc_filter = new String[][] { new String[] { NfcA.class
			.getName() } };

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			nfc_adapter = NfcAdapter.getDefaultAdapter(getApplicationContext());
			if (nfc_adapter == null) {
				Toast.makeText(getApplicationContext(), "No such adapter",
						Toast.LENGTH_SHORT).show();
			} else {
				nfc_intent = PendingIntent.getActivity(getApplicationContext(),
						0, new Intent(getApplicationContext(), getClass())
								.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
			}
		} catch (Exception e) {
			Toast.makeText(getApplicationContext(), "No such adapter",
					Toast.LENGTH_SHORT).show();
		}
	}

	protected String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		char[] buffer = new char[2];
		for (int i = 0; i < src.length; i++) {
			buffer[0] = Character.forDigit((src[i] >>> 4) & 0x0F, 16);
			buffer[1] = Character.forDigit(src[i] & 0x0F, 16);
			stringBuilder.append(buffer);
		}
		return stringBuilder.toString();
	}

	protected void onResume() {
		super.onResume();
		nfc_adapter.enableForegroundDispatch(this, nfc_intent, null, nfc_filter);
	}
}