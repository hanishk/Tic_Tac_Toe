package com.xyz.tictactoe;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class HomePage extends AppCompatActivity {

    private AdView mAdView;
private TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        MobileAds.initialize(this, "ca-app-pub-3355224362612000~2584094991");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        txtView=findViewById(R.id.touch);
        txtView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HomePage.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }
}


//    private static int SPLASH_TIME_OUT=2000; //IN MILLISECONDS 4000=4SEC
// new Handler().postDelayed(new Runnable() {
//@Override
//public void run() {
//        Intent intent=new Intent(HomePage.this,MainActivity.class);
//        startActivity(intent);
//        finish();
//        }
//        },SPLASH_TIME_OUT);
//



//        // hiding the status bar
//        View decorView = getWindow().getDecorView();
//        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
//        decorView.setSystemUiVisibility(uiOptions);

//        TextView txt=findViewById(R.id.textViewname);
