package com.example.android.slotmachine;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Random;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {

    private ImageView s1; // slot 1
    private ImageView s2; // slot 2
    private ImageView s3; // slot 3
    private Handler handler;
    private Drawable[] a;
    private TextView pointText;
    private Button startStop;
    private SeekBar seek;
    private Update update1;
    private Update update2;
    private Update update3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        s1 = findViewById(R.id.slot1);
        s2 = findViewById(R.id.slot2);
        s3 = findViewById(R.id.slot3);
        startStop = findViewById(R.id.startStopButton);
        pointText = findViewById(R.id.points);

        if(savedInstanceState != null) {
            pointText.setText(savedInstanceState.getString("POINTS_TEXT"));
        }

        seek = findViewById(R.id.seek);
        seek.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        if(startStop.getText().toString().equals("STOP")) { // then the slots are spinning
                            update1.modifyRate(progress);
                            update2.modifyRate(progress);
                            update3.modifyRate(progress);
                        }
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                }
        );

        a = new Drawable[4];
        a[0] = getResources().getDrawable(R.drawable.cherry);
        a[1] = getResources().getDrawable(R.drawable.grape);
        a[2] = getResources().getDrawable(R.drawable.strawberry);
        a[3] = getResources().getDrawable(R.drawable.pear);

        s1.setImageDrawable(a[0]);
        s2.setImageDrawable(a[1]);
        s3.setImageDrawable(a[2]);
    }


    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString("POINTS_TEXT", pointText.getText().toString());
    }


    public class Update implements Runnable {

        private ImageView i;
        private int current;
        private int rate;
        private int initRate;

        public Update(ImageView i, int current) {
            this.i = i;
            this.current = current;

            Random r = new Random();
            rate = (r.nextInt(101) + 100); //rate can be 100 to 200ms
            initRate = rate; //made an initRate so that when I change the slider from 2 to 1, and then back to 2, I'll still have the speed I originally had at 2.

            rate += (2 - seek.getProgress()) * 200;
            /* So for this:
            if seek.getProgress() = 2:  (2 - 2) = 0 -> rate + 0= [100, 200] fast speed
            if seek.getProgress() = 1: (2 - 1) = 1 -> rate + (1 * 200) = [200, 400] medium speed
            if seek.getProgress() = 0: (2 - 0) = 2 -> rate + (2 * 200) = [750, 1150] slow speed
             */


        }

        public void run() {
            current = (current + 1) % 4;
            i.setImageDrawable(a[current]);
            handler.postDelayed(this, rate);
        }

        public int getCurrent() {
            return this.current;
        }

        public void modifyRate(int x) {
            this.rate = initRate + (2 - x) * 200; // works similar to the stuff commented in initializor.

        }


    }

    public void startSpinning(View v) {
        if(startStop.getText().toString().equals("START")) {
            updateScore(0); // resets the score back to 0.
            update1 = new Update(s1, 0);
            update2 = new Update(s2, 1);
            update3 = new Update(s3, 2);
            handler.post(update1);
            handler.post(update2);
            handler.post(update3);
            startStop.setText("STOP");
        } else {
            handler.removeCallbacksAndMessages(null);
            startStop.setText("START");
            updateScore();

        }


    }

    public void updateScore() {
        int points = 0;

        //Check again if s1.getDrawable().equals(a[0]) works.
        Update[] a = {update1, update2, update3};
        if(a[0].getCurrent() == 0 && a[1].getCurrent() == 0 && a[2].getCurrent() == 0) {
            points = 200;
        } else {
            for(int i = 0; i < a.length; i++) {
                if (a[i].getCurrent() == 0) {
                    points -= 50;
                } else if (a[i].getCurrent() == 1) {
                    points += 25;
                } else if (a[i].getCurrent() == 2) {
                    points += 30;
                } else {
                    points += 40;
                }
            }

        }
        //awejgb;lkawhn;lbwen
        if(points >= 100) {
            Toast.makeText(this, "YOU WIN!!", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(this, "You lose. Aim for 100 points to win.", Toast.LENGTH_LONG).show();
        }
        pointText.setText("Points: " + points);
    }

    public void updateScore(int x) {
        pointText.setText("Points: " + 0);
    }

    public void rulesClick(View v) {
        Intent i = new Intent(this, RulesActivity.class);
        i.putExtra("POINTS_TEXT",  pointText.getText());
        startActivity(i);
    }
}
