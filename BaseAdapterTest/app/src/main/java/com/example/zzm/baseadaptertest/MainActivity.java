package com.example.zzm.baseadaptertest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
    private int shap[] = new int[]{R.drawable.shap,R.drawable.shap1,R.drawable.shap3,R.drawable.shap4};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = (ListView)findViewById(R.id.mylist);
        BaseAdapter adapter = new BaseAdapter() {
            @Override
            public int getCount() {
                return 10;
            }

            @Override
            public Object getItem(int i) {
                return null;
            }

            @Override
            public long getItemId(int i) {
                return i;
            }

            @Override
            public View getView(int i, View view, ViewGroup viewGroup) {
                LinearLayout linearLayout = new LinearLayout(MainActivity.this);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                Button bn = new Button(MainActivity.this);
                bn.setText("test");
                bn.setTextColor(0xffffffff);
                bn.setTextSize(25);
                bn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 400));

                bn.setBackgroundResource(shap[i%4]);

                linearLayout.addView(bn);
                return linearLayout;
            }
        };
        list.setAdapter(adapter);
    }
}
