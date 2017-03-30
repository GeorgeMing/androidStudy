package com.example.zzm.apptest;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * Created by zzm on 2017/3/30.
 */

public class HomeworkAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> course_names;
    ArrayList<String> homework_contents;
    ArrayList<String> homework_endtimes;
    int count;
    public HomeworkAdapter(Context context, ArrayList<String>course_names, ArrayList<String>homework_contents, ArrayList<String>homework_endtimes, int count){
        this.context = context;
        this.course_names = course_names;
        this.homework_contents = homework_contents;
        this.homework_endtimes = homework_endtimes;
        this.count = count;
    }

    @Override
    public int getCount() {
        return count;
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
        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.HORIZONTAL);
        Button bn = new Button(context);
        bn.setText("课程名:"+course_names.get(i).toString()+"\n作业内容:"+homework_contents.get(i).toString()+"\n截止时间:"+homework_endtimes.get(i).toString());
        bn.setTextColor(0xffffffff);
        bn.setTextSize(25);
        bn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        bn.setBackgroundResource(MainActivity.shap[i%5]);
        linearLayout.addView(bn);
        return linearLayout;
    }
}
