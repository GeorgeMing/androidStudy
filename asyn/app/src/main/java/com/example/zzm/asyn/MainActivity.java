package com.example.zzm.asyn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text1);
        progressBar = (ProgressBar)findViewById(R.id.progressBar02);
        asyntest asyntestObj = new asyntest(textView, progressBar);
        asyntestObj.execute();
    }
}
