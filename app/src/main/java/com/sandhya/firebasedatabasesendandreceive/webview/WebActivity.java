package com.sandhya.firebasedatabasesendandreceive.webview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.airbnb.lottie.LottieAnimationView;
import com.sandhya.firebasedatabasesendandreceive.R;

import im.delight.android.webview.AdvancedWebView;

public class WebActivity extends AppCompatActivity {
    AdvancedWebView webView;
    ProgressBar progressBar;
    LottieAnimationView error;
    SwipeRefreshLayout swiper;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        error = findViewById(R.id.error);
//        swiper = findViewById(R.id.swiper);
        webView = findViewById(R.id.webView);
        progressBar = findViewById(R.id.progressBar);
        webView.loadUrl("https://chat.openai.com/chat");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                progressBar.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });
        webView.setWebChromeClient(new WebChromeClient() {

        });

        if (!isOnline()) {
            Intent intent = new Intent(WebActivity.this, InternetErrorActivity.class);
            startActivity(intent);
            finish();
//            error.setVisibility(View.VISIBLE);

        } else {
//            error.setVisibility(View.GONE);

        }




    }

    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(WebActivity.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        return (info != null && info.isConnected());

    }


    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) webView.goBack();
        else super.onBackPressed();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        webView.onActivityResult(requestCode, resultCode, intent);
    }


}