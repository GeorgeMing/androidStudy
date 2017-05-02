package com.example.zzm.apptest;

import android.content.Context;
import android.content.res.Resources;
import android.database.DataSetObserver;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by zzm on 2017/3/30.
 */

public class FileAdapter extends BaseExpandableListAdapter{
    ArrayList<String> file_types = new ArrayList<String>();
    ArrayList<Map<String,String>> file_names = new ArrayList<Map<String, String>>();
    private String[] fileTypes = new String[]{"课程资料","课程作业","竞赛资料","毕业设计","课程设计","答辩记录","实习报告"};
    private String[][] files = new String[][]{
            {"期末复习.docx","期中复习.pdf","C语言基础.pdf"},
            {},
            {},
            {},
            {},
            {},
            {}
    };
    Context context;
    int count;
    public FileAdapter(Context context, ArrayList<String> file_types, ArrayList<Map<String,String>> file_names){
        this.context = context;
        this.file_names = file_names;
        this.file_types = file_types;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

    }

    @Override
    public int getGroupCount() {
        return file_types.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return file_names.size();
    }

    @Override
    public Object getGroup(int i) {
        return file_types.get(i);
    }

    @Override
    public Object getChild(int i, int i1) {
        if(file_names.get(i1).get("file_type").equals((String) getGroup(i))){
            return file_names.get(i1).get("file_name");
        }
        return "";
    }

    public Object getURL(int i, int i1) {
        if(file_names.get(i1).get("file_type").equals((String) getGroup(i))){
            return file_names.get(i1).get("file_path");
        }
        return "";
    }

    @Override
    public long getGroupId(int i) {
        return i;
    }

    @Override
    public long getChildId(int i, int i1) {
        return i1;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    //组选项外观
    @Override
    public View getGroupView(int i, boolean b, View view, ViewGroup viewGroup) {
        LinearLayout ll = new LinearLayout(context);
        ll.setOrientation(LinearLayout.HORIZONTAL);
        TextView groupText = new TextView(context);
        groupText.setText(file_types.get(i));
        groupText.setTextSize(25);
        ll.setBackgroundColor(0xFFC6E2FF);
        groupText.setPadding(25,20,20,20);
        groupText.setEllipsize(TextUtils.TruncateAt.END);
        ll.addView(groupText);
        return ll;
    }

    //子选项的外观
    @Override
    public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
        Resources resources = context.getResources();
        DisplayMetrics dm = resources.getDisplayMetrics();
        int screenwidth = dm.widthPixels;
        RelativeLayout cl = new RelativeLayout(context);
        cl.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        TextView fileTextView = new TextView(context);
        fileTextView.setId(R.id.my_view);
        Button downloadBtn = new Button(context);
        downloadBtn.setBackgroundResource(R.drawable.shape2);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "test", Toast.LENGTH_SHORT).show();
            }
        });
        fileTextView.setTextSize(25);
        fileTextView.setPadding(45,10,10,10);
        String btntext = getChild(i,i1).toString();
        if(!btntext.equals("")) {
            fileTextView.setText(btntext);
            fileTextView.setMovementMethod(ScrollingMovementMethod.getInstance());
            fileTextView.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            fileTextView.setSingleLine(true);
            fileTextView.setMarqueeRepeatLimit(6);
            downloadBtn.setText("下载");
            downButton downbutton = new downButton((String)getURL(i,i1), btntext, context);
            downloadBtn.setOnClickListener(downbutton);
            cl.addView(fileTextView);
            cl.addView(downloadBtn);
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) downloadBtn.getLayoutParams();
            params.height = 120;
            params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
            cl.setLayoutParams(params);
        }
        return cl;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return true;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void onGroupExpanded(int i) {

    }

    @Override
    public void onGroupCollapsed(int i) {

    }

    @Override
    public long getCombinedChildId(long l, long l1) {
        return 0;
    }

    @Override
    public long getCombinedGroupId(long l) {
        return 0;
    }


}

class downButton implements View.OnClickListener{
    String url;
    String file_name;
    Context context;
    public downButton(String url, String file_name, Context context){
        this.url = url;
        this.file_name = file_name;
        this.context = context;
    }

    @Override
    public void onClick(View view) {
        DataDownload dd = new DataDownload(url, file_name, context);
    }
}

