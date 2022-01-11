package com.example.android_21_check_current_network;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private TextView wifi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wifi = (TextView)findViewById(R.id.wifi);

        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            wifi.setText("有網路可用");
        } else {
            AlertDialog.Builder alertDialog =
                    new AlertDialog.Builder(MainActivity.this);
            alertDialog.setTitle("通知");
            alertDialog.setMessage("顯示目前沒有網路，是否前往設定？");
            alertDialog.setPositiveButton("設定網路", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent it = new Intent();
                    ComponentName cn = new ComponentName("com.android.settings","com.android.settings.wifi.WifiSettings");
                    it.setComponent(cn);
                    startActivity(it);
                }
            });
            alertDialog.setNegativeButton("不用設定",(dialog, which) -> {
                wifi.setText("目前沒有網路");
            });
            alertDialog.setCancelable(false);
            alertDialog.show();
        }
    }
}