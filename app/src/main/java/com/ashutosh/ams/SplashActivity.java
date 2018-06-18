package com.ashutosh.ams;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        imageView =findViewById(R.id.splash_image);
        textView = findViewById(R.id.splash_text);

//        Animation animation= AnimationUtils.loadAnimation(this,R.anim.mytransaction);
        final Intent i = new Intent(this,LoginActivity.class);
//        imageView.startAnimation(animation);
//        textView.startAnimation(animation);
        Thread timer=new Thread()
        {
            public void run()
            {
                try
                {
                    sleep(1500);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    startActivity(i);
                    finish();
                }
            }
        };
        timer.start();
    }
}
