package com.example.android.slotmachine;

import android.media.Image;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        update = new Update();
        handler = new Handler();

        s1.setImageDrawable(getResources().getDrawable(R.drawable.cherry)); // the three default images for the slots
        s2.setImageDrawable(getResources().getDrawable(R.drawable.grape));
        s3.setImageDrawable(getResources().getDrawable(R.drawable.strawberry));

    }

    public class Update implements Runnable {

        private int r1; //the rate at which s1 will change backgrounds
        private int r2; // the rate at which...
        private int r3; // ...

        public Update() {
            Random r = new Random();
            r1 = r.nextInt(501); // A percentage of 1/10th a second.
            r2 = r.nextInt(501); // For example, if r1 = 400, it will update
            r3 = r.nextInt(501); // every 1/10 * (400 / 100) = 4/10ths of a second.
        }

        public void run() {
            // try to think about how to make all of these update at certain times
            // without making multiple different "Update" classes.

        }

        private void updateImage(ImageView i) {

        }
    }



}
