package com.example.dell.icutrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Showdata extends AppCompatActivity {
    TextView tView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showdata);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
      //  getSupportActionBar().e
        getSupportActionBar().setIcon(R.drawable.cancel);
        tView=findViewById(R.id.tData);

        String data=getIntent().getStringExtra("data");
        tView.setText(data);
    }
}
