package com.example.zzm.expandablelistview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;

import java.util.ArrayList;
import java.util.List;

import static com.example.zzm.expandablelistview.MainActivity.childArray;

/**
 * Created by zzm on 2017/3/24.
 */

public class ExpandableAdapter extends BaseExpandableListAdapter{

    Activity activity;
    ArrayList<String> groupArray = new ArrayList<String>();
    ArrayList<List<String>> childArray = new ArrayList<List<String>>();

    public ExpandableAdapter(Activity activity){
        this.activity = activity;
        groupArray.add("第一行");
        groupArray.add("第二行");
        List<String> tempArray = new ArrayList<String>();
        tempArray.add("第一条");
        tempArray.add("第二条");
        tempArray.add("第三条");
        for(int i = 0; i < groupArray.size(); i++){
            childArray.add(tempArray);
        }
    }
    @Override
    public int getGroupCount() {
        return 0;
    }

    @Override
    public int getChildrenCount(int i) {
        return 0;
    }

    @Override
    public Object getGroup(int i) {
        return null;
    }

    @Override
    public Object getChild(int i, int i1) {
        return  childArray.get(i).get(i1);
    }

    @Override
    public long getGroupId(int i) {
        return 0;
    }

    @Override
    public long getChildId(int i, int i1) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        return null;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }
}
