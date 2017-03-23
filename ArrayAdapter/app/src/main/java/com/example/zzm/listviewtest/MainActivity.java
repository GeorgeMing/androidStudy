package com.example.zzm.listviewtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list1 = (ListView)findViewById(R.id.list1);
        String[] arr = {"233", "123", "235234"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.array_item, arr);
        list1.setAdapter(adapter);
    }
}
