package com.example.zzm.asyn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView = (TextView)findViewById(R.id.text);
        Aysntest aysntest = new Aysntest(textView);
        aysntest.execute("http://zzmyun.space/160722/index.php/appadmin/orderadmin/");

    }
}
