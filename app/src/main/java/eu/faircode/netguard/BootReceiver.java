package eu.faircode.netguard;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.VpnService;
import android.util.Log;

public class BootReceiver extends BroadcastReceiver {
    private static final String TAG = "NetGuard.Boot";

    @Override
    public void onReceive(final Context context, Intent intent) {
        Log.i(TAG, "Received " + intent);

        if (VpnService.prepare(context) == null) {
            Intent service = new Intent(context, BlackHoleService.class);
            service.putExtra(BlackHoleService.EXTRA_COMMAND, BlackHoleService.Command.start);
            context.startService(service);
        }
    }
}