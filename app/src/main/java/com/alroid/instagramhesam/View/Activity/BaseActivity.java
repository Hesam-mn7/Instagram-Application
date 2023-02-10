package com.alroid.instagramhesam.View.Activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.alroid.instagramhesam.Const;
import com.alroid.instagramhesam.R;

public class BaseActivity extends AppCompatActivity {

    WifiBroadcast wifiBroadcast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        wifiBroadcast = new WifiBroadcast();
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(wifiBroadcast,new IntentFilter(Const.Action_CONNECTIVITY));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiBroadcast);
    }
    AlertDialog alertDialog;

    class WifiBroadcast extends BroadcastReceiver {

        public WifiBroadcast() {
            View alert= LayoutInflater.from(BaseActivity.this).inflate(R.layout.allert_dialog_wifi,null);

            AlertDialog.Builder builder=new AlertDialog.Builder(BaseActivity.this);
            builder.setView(alert);
            builder.setCancelable(false);

            alertDialog=builder.create();
            alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.trans)));

            TextView btnwifi = alert.findViewById(R.id.btnwifi);
            btnwifi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));
                }
            });
            TextView btndata = alert.findViewById(R.id.btndata);
            btndata.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(Settings.ACTION_NETWORK_OPERATOR_SETTINGS));

                }
            });
        }

        private boolean isNetworkAvailable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

        @Override
        public void onReceive(Context context, Intent intent) {

            if (isNetworkAvailable()){
                alertDialog.dismiss();
            }
            else{
                alertDialog.show();
            }
        }
    }
}
