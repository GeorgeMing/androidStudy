package com.example.zzm.asyn;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text1);
        asyntest asyntestObj = new asyntest(textView);
        asyntestObj.execute("http://localhost/tp/admin/index/login?username=130201011022&password=123");
    }
}
