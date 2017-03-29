package com.example.zzm.apptest;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zzm on 2017/3/10.
 */

public class ServerAction {
    static String ServerAddr = "http://zzmyun.space/";
}

//注册方法
class RegisterService extends AsyncTask<String, Integer, String> {

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
    }
}

//登录方法
class LoginService extends AsyncTask<String, Integer, String> {

    private Context context;
    private Intent intent;
    public LoginService(Context context, Intent intent){
        this.context = context;
        this.intent = intent;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection httpURLConnection;
        try{
            URL url = new URL(strings[0]);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            if(httpURLConnection.getResponseCode() == 200){
                InputStream is = httpURLConnection.getInputStream();
                byte[] buffer = new byte[1024];
                String str = "";
                while(is.read(buffer)!=-1){
                    str += new String(buffer);
                    buffer = new byte[1024];
                }
                return str;
            }
        }catch (Exception e){
            publishProgress(-1);
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
        if(str!=null){
            try{
                JSONObject jsonObject = new JSONObject(String.valueOf(str));
                String resStr = jsonObject.getString("status");
                if(resStr.equals("scusses")){
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();
            }

        }
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }
}

//View1拿用户信息
class GetData extends AsyncTask<String, Void, String> {
    private Context context;
    private View view;

    public GetData(Context context, View view){
        this.context = context;
        this.view = view;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection httpURLConnection;
        try{
            URL url = new URL(strings[0]);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            if(httpURLConnection.getResponseCode() == 200){
                InputStream is = httpURLConnection.getInputStream();
                byte[] buffer = new byte[1024];
                String str = "";
                while(is.read(buffer)!=-1){
                    str += new String(buffer);
                    buffer = new byte[1024];
                }
                return str;
            }
        }catch (Exception e){
            return null;
        }
        return null;

    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
        if(str!=null){
            try{
                JSONObject jsonObject = new JSONObject(String.valueOf(str));
                String resStr = jsonObject.getString("status");

                if(resStr.equals("scusses")){

                }else{
                    Toast.makeText(context, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();
            }

        }
    }
}


//View3拿到作业信息
class GetHomeworkData extends AsyncTask<String, Void, String>{
    Context context;
    View view;

    public GetHomeworkData(Context context, View view){
        this.context = context;
        this.view  = view;
    }
    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
        ListView list = (ListView)view.findViewById(R.id.view3list);
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
                LinearLayout linearLayout = new LinearLayout(context);
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);
                Button bn = new Button(context);
                bn.setText("课程：C语言\n内容：1.求水仙花数 2.最小公倍数\n截止日期：2017-5-24");
                bn.setTextColor(0xffffffff);
                bn.setTextSize(25);
                bn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                bn.setBackgroundResource(MainActivity.shap[i%5]);

                linearLayout.addView(bn);
                return linearLayout;
            }
        };
        list.setAdapter(adapter);
    }
}

//View4拿文件信息
class GetFileData extends AsyncTask<String, Void, String> {
    Context context;
    View view;

    public GetFileData(Context context, View view){
        this.context = context;
        this.view = view;
    }

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(String str) {
        super.onPostExecute(str);
        ExpandableListAdapter adapter = new ExpandableListAdapter() {

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

            @Override
            public void registerDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public void unregisterDataSetObserver(DataSetObserver dataSetObserver) {

            }

            @Override
            public int getGroupCount() {
                return fileTypes.length;
            }

            @Override
            public int getChildrenCount(int i) {
                return files[i].length;
            }

            @Override
            public Object getGroup(int i) {
                return fileTypes[i];
            }

            @Override
            public Object getChild(int i, int i1) {
                return files[i][i1];
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
                groupText.setText(fileTypes[i]);
                groupText.setTextSize(35);
                groupText.setPadding(25,10,10,10);
                ll.addView(groupText);
                return ll;
            }

            //子选项的外观
            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
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
                fileTextView.setText(getChild(i,i1).toString());
                downloadBtn.setText("下载");
                cl.addView(fileTextView);
                cl.addView(downloadBtn);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)downloadBtn.getLayoutParams();
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
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
        };
        ExpandableListView expandableListView = (ExpandableListView)view.findViewById(R.id.fileListVIew);
        expandableListView.setAdapter(adapter);
    }
}



