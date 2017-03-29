package com.example.zzm.apptest;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
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

//服务器地址
public class ServerAction {
    static String ServerAddr = "http://192.168.249.77/";
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


//操作sqlite数据库
class DataBaseController{
    Context context;

    public DataBaseController(Context context){
        this.context = context;
    }

    public void delectData(){
        /*数据删除*/
        //获得可写的SQLiteDatabase对象
        // 创建DatabaseHelper对象

        DatabaseHelper databaseHelper = new DatabaseHelper(context, "user_db");
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        // 得到一个可写的SQLiteDatabase对象
        sqLiteDatabase = databaseHelper.getWritableDatabase();
        //调用SQLiteDatabase对象的delete方法进行删除操作
        //第一个参数String：表名
        //第二个参数String：条件语句
        //第三个参数String[]：条件值
        sqLiteDatabase.delete("user", "id=?", new String[]{"1"});
    }

    //把token写进sqlite数据库
    public void writeToken(String token, String idNumber){
        Log.d("writeToke", token);
        ContentValues values = new ContentValues();
        // 向该对象中插入键值对，其中键是列名，值是希望插入到这一列的值，值必须和数据库当中的数据类型一致
        values.put("id", 1);
        values.put("idNumber", idNumber);
        values.put("token", token);
        DatabaseHelper databaseHelper = new DatabaseHelper(context, "user_db");
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        // 创建ContentValues对象

        sqLiteDatabase.insert("user", null, values);
    }

    public String readToken(){
        String token = "";
        DatabaseHelper databaseHelper = new DatabaseHelper(context, "user_db");
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("user", new String[] { "id", "idNumber","token" }, "id=?", new String[] { "1" }, null, null, null);
        System.out.println(token);
        while (cursor.moveToNext()) {

            token = cursor.getString(cursor.getColumnIndex("token"));
        }
        return token;
    }

    public String readidNumber(){
        String idNumber = "";
        DatabaseHelper databaseHelper = new DatabaseHelper(context, "user_db");
        SQLiteDatabase sqLiteDatabase = databaseHelper.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query("user", new String[] { "id", "idNumber","token" }, "id=?", new String[] { "1" }, null, null, null);

        while (cursor.moveToNext()) {

            idNumber = cursor.getString(cursor.getColumnIndex("idNumber"));
        }
        return idNumber;
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
            return null;
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
                    String token = jsonObject.getString("token");
                    String idNumber = jsonObject.getString("idNumber");
                    /*
                    * 登录成功把token写进sqlite
                    * */

                    DataBaseController db = new DataBaseController(context);
                    db.writeToken(token, idNumber);
                    context.startActivity(intent);
                }else{
                    Toast.makeText(context, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                }
            }catch (Exception e){
                Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(context, "服务器错误", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

    }
}

//**************************View1拿用户信息*****************************//
class GetData extends AsyncTask<String, Void, String> {
    private Context context;
    private View view;
    private String token, idNumber;
    public GetData(Context context, View view){
        this.context = context;
        this.view = view;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection httpURLConnection;
        try{
            this.token = new DataBaseController(context).readToken();
            this.idNumber = new DataBaseController(context).readidNumber();
            Log.d("#######token", token);
            URL url = new URL(ServerAction.ServerAddr+"tp/index.php/admin/Index/getInfo?idNumber="+idNumber+"&token="+token);
            httpURLConnection = (HttpURLConnection)url.openConnection();
            if(httpURLConnection.getResponseCode() == 200){
                InputStream is = httpURLConnection.getInputStream();
                byte[] buffer = new byte[1024];
                String str = "";
                while(is.read(buffer)!=-1){
                    str += new String(buffer);
                    buffer = new byte[1024];
                }
                Log.d("res",str);
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
                String student_name = jsonObject.getString("student_name");
                String student_college = jsonObject.getString("student_college");
                String student_specialty = jsonObject.getString("student_specialty");
                String student_grade = jsonObject.getString("student_grade");
                String student_class = jsonObject.getString("student_class");
                String student_number = jsonObject.getString("student_number");
                TextView nameText = (TextView)view.findViewById(R.id.name);
                TextView collegeText = (TextView)view.findViewById(R.id.college);
                TextView specialtyText = (TextView)view.findViewById(R.id.specialty);
                TextView gradeText = (TextView)view.findViewById(R.id.grade);
                TextView classnumText = (TextView)view.findViewById(R.id.classnum);
                TextView numberText = (TextView)view.findViewById(R.id.number);
                nameText.setText(student_name);
                collegeText.setText(student_college);
                specialtyText.setText(student_specialty);
                gradeText.setText(student_grade);
                classnumText.setText(student_class);
                numberText.setText(student_number);

            }catch (Exception e){
                Toast.makeText(context, "未登录", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context,LoginActivity.class);
                context.startActivity(intent);
            }

        }
    }
}


//**************************View3拿作业信息*****************************//
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

//**************************View4拿文件信息*****************************//
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


