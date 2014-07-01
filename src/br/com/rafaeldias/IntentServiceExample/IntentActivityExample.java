package br.com.rafaeldias.IntentServiceExample;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by tind on 01/07/14.
 */

public class IntentActivityExample extends Activity {

    public static final String INTENT_EXTRA_NAME = "intent_key";
    Activity mActivity = this;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = (Button) findViewById(R.id.btStartService);
        button.setOnClickListener(new ButtonClickListener());
    }

    private class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(mActivity, IntentServiceExample.class);
            intent.putExtra(INTENT_EXTRA_NAME, getString(R.string.initial_value));
            startService(intent);
            IntentFilter intentFilter = new IntentFilter(IntentServiceExample.BROADCAST_ACTION);
            LocalBroadcastManager.getInstance(mActivity).registerReceiver(new ResponseReceiver(), intentFilter);
        }
    }

    private class ResponseReceiver extends BroadcastReceiver {
        //Avoids instantiation
        private ResponseReceiver() {}
        @Override
        public void onReceive(Context context, Intent intent) {
            TextView tvNome = (TextView)findViewById(R.id.tvResult);
            tvNome.setText(intent.getExtras().getString(INTENT_EXTRA_NAME));
        }
    }
}