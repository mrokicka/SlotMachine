package com.example.android.slotmachine;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView s1; // slot 1
    private ImageView s2; // slot 2
    private ImageView s3; // slot 3
    private Handler handler;
    private Update update;
    private Drawable[] a;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        update = new Update();
        handler = new Handler();

        s1 = findViewById(R.id.slot1);
        s2 = findViewById(R.id.slot2);
        s3 = findViewById(R.id.slot3);

        a = new Drawable[4];
        a[0] = getResources().getDrawable(R.drawable.cherry);
        a[1] = getResources().getDrawable(R.drawable.grape);
        a[2] = getResources().getDrawable(R.drawable.strawberry);
        a[3] = getResources().getDrawable(R.drawable.pear);

        s1.setImageDrawable(a[0]);
        s2.setImageDrawable(a[1]);
        s3.setImageDrawable(a[2]);

    }

    public class Update implements Runnable {

        public void run() {
            

        }
    }



}
