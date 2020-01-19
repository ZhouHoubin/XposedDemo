package com.dzone.xposed.demo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MMBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e(getClass().getSimpleName(), "MMBroadcast.onReceive " + intent.getAction());
    }
}
