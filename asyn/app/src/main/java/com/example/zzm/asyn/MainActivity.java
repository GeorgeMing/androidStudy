package com.example.zzm.asyn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text1);
        asyntest asyntestObj = new asyntest(textView);
        asyntestObj.execute("http://192.168.249.238/tp/admin/index/login?username=130201011022&password=123");
    }
}
