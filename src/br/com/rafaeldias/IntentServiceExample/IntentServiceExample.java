package br.com.rafaeldias.IntentServiceExample;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;

/**
 * Created by tind on 01/07/14.
 */
public class IntentServiceExample extends IntentService {

    public static final String BROADCAST_ACTION = "br.com.rafaeldias.IntentServiceExample.BROADCAST";

    /**
     * An IntentService must always have a constructor that calls the super constructor. The
     * string supplied to the super constructor is used to give a name to the IntentService's
     * background thread.
     */
    public IntentServiceExample() {
        super("IntentServiceExample");
    }


    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle bundle = intent.getExtras();
        String valor = bundle.getString(IntentActivityExample.INTENT_EXTRA_NAME);
        valor = valor.concat(getString(R.string.added_by_intent_service));

        Intent localIntent = new Intent(BROADCAST_ACTION).putExtra(IntentActivityExample.INTENT_EXTRA_NAME, valor);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
}
