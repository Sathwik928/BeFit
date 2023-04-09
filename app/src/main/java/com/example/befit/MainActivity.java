package com.example.befit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Animation topAnim, bottomAnim;
    ImageView db;
    TextView title,caption;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        //Animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_anime);
        bottomAnim=AnimationUtils.loadAnimation(this,R.anim.bottom_anime);

        db=findViewById(R.id.logo);
        title=findViewById(R.id.apptitle);
        caption=findViewById(R.id.caption);

        db.setAnimation(topAnim);
        title.setAnimation(bottomAnim);
        caption.setAnimation(bottomAnim);

        //Splash Screen using Handler
        Intent main=new Intent(MainActivity.this,LoginActivity.class);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(main);
                finish();
            }
        }, 4500);

    }
}