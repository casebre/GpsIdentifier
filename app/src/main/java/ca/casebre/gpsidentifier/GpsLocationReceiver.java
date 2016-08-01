package ca.casebre.gpsidentifier;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by rafael on 01/08/16.
 */
public class GpsLocationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().matches("android.location.PROVIDERS_CHANGED"))
        {

        }
    }
}
