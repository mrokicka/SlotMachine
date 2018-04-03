package com.example.android.slotmachine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class RulesActivity extends AppCompatActivity {

    private TextView pointText;

    @Override
    protected void onCreate(Bundle savedInstancedState) {
        super.onCreate(savedInstancedState);
        setContentView(R.layout.activity_rules);

        pointText = findViewById(R.id.points);

        Intent i = getIntent();
        pointText.setText(i.getStringExtra("POINTS_TEXT"));
    }
}
