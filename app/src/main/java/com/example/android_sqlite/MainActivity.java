package com.example.android_sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private static  int DURATION_BEFORE_QUITTING = 3000;

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);

        // quit the page after the delay time
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this, HomePageActivity.class);
                Pair[] pairs = new Pair[1];// the number ellemenst to animate

                pairs[0] = new Pair<View,String>(imageView,"image_transition");


                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions activityOptions =
                            ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);

                    startActivity(intent,activityOptions.toBundle());
                }
            }
        },DURATION_BEFORE_QUITTING);




    }
}