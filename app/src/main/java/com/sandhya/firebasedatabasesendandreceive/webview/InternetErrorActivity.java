package com.sandhya.firebasedatabasesendandreceive.webview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.sandhya.firebasedatabasesendandreceive.R;

public class InternetErrorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_error);

//        if (!isOnline()){
////            Intent intent = new Intent(InternetErrorActivity.this,WebActivity.class);
////            startActivity(intent);
//        }else {
//            Intent intent = new Intent(InternetErrorActivity.this,WebActivity.class);
//            startActivity(intent);
//        }
    }
    public boolean isOnline(){
        ConnectivityManager manager = (ConnectivityManager)getSystemService(WebActivity.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info!=null&& info.isConnected());

    }
}