package com.example.zzm.apptest;

import android.database.DataSetObserver;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private View view1, view2, view3, view4;
    private ViewPager viewPager;  //对应的viewPager
    private List<View> viewList;//view数组
    private TextView personalText,scheduleText,homeworkText,dataText;
    private ImageView personalImg,scheduleImg,homeworkImg,dataImg;
    private LinearLayout personalLayout, scheduleLayout, homeworkLayout, dataLayout;
    private WebView webview;

    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String APP_CACAHE_DIRNAME = "/webcache";

    private int shap[] = new int[]{R.drawable.shape,R.drawable.shape2,R.drawable.shape3,R.drawable.shape4,R.drawable.shape5};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        personalLayout = (LinearLayout)findViewById(R.id.personalLayout);
        scheduleLayout = (LinearLayout)findViewById(R.id.scheduleLayout);
        homeworkLayout = (LinearLayout)findViewById(R.id.homeworkLayout);
        dataLayout = (LinearLayout)findViewById(R.id.dataLayout);

        personalText = (TextView)findViewById(R.id.personalText);
        scheduleText = (TextView)findViewById(R.id.scheduleText);
        homeworkText = (TextView)findViewById(R.id.homeworkText);
        dataText = (TextView)findViewById(R.id.dataText);

        personalImg = (ImageView)findViewById(R.id.personalImg);
        scheduleImg = (ImageView)findViewById(R.id.scheduleImg);
        homeworkImg = (ImageView)findViewById(R.id.homeworkImg);
        dataImg = (ImageView)findViewById(R.id.dataImg);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater=getLayoutInflater();
        view1 = inflater.inflate(R.layout.layout1, null);
        view2 = inflater.inflate(R.layout.layout2,null);
        view3 = inflater.inflate(R.layout.layout3, null);
        view4 = inflater.inflate(R.layout.layout4, null);



        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        viewList.add(view4);

        clearWebViewCache();

        //显示课表
        WebView webView = (WebView)view2.findViewById(R.id.webview);
        webView.loadUrl("http://zzmyun.space/Syllabus");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        PagerAdapter pagerAdapter = new PagerAdapter() {

            @Override
            public boolean isViewFromObject(View arg0, Object arg1) {
                // TODO Auto-generated method stub
                return arg0 == arg1;
            }

            @Override
            public int getCount() {
                // TODO Auto-generated method stub
                return viewList.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // TODO Auto-generated method stub
                container.removeView(viewList.get(position));
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                // TODO Auto-generated method stub
                container.addView(viewList.get(position));


                return viewList.get(position);
            }
        };
        resetButton();
        personalImg.setImageResource(R.drawable.ic_account_circle_black);
        personalText.setTextColor(Color.parseColor("#000000"));
        viewPager.setCurrentItem(0);
        personalLayout.setOnClickListener(this);
        scheduleLayout.setOnClickListener(this);
        homeworkLayout.setOnClickListener(this);
        dataLayout.setOnClickListener(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);

    }


    public void clearWebViewCache(){

        //清理Webview缓存数据库
        try {
            deleteDatabase("webview.db");
            deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //WebView 缓存文件
        File appCacheDir = new File(getFilesDir().getAbsolutePath()+APP_CACAHE_DIRNAME);
        Log.e(TAG, "appCacheDir path="+appCacheDir.getAbsolutePath());

        File webviewCacheDir = new File(getCacheDir().getAbsolutePath()+"/webviewCache");
        Log.e(TAG, "webviewCacheDir path="+webviewCacheDir.getAbsolutePath());

        //删除webview 缓存目录
        if(webviewCacheDir.exists()){
            webviewCacheDir.delete();
        }
        //删除webview 缓存 缓存目录
        if(appCacheDir.exists()){
            appCacheDir.delete();
        }
    }

    public void resetButton(){
        personalText.setTextColor(Color.parseColor("#DEDEDE"));
        scheduleText.setTextColor(Color.parseColor("#DEDEDE"));
        homeworkText.setTextColor(Color.parseColor("#DEDEDE"));
        dataText.setTextColor(Color.parseColor("#DEDEDE"));
        personalImg.setImageResource(R.drawable.ic_account_circle_grey);
        scheduleImg.setImageResource(R.drawable.ic_account_balance_grey);
        homeworkImg.setImageResource(R.drawable.ic_tab_grey);
        dataImg.setImageResource(R.drawable.ic_dns_grey);
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int arg0) {
        resetButton();

        switch(arg0) {
            case 0:
                personalText.setTextColor(Color.parseColor("#000000"));
                personalImg.setImageResource(R.drawable.ic_account_circle_black);
                break;
            case 1:
                scheduleText.setTextColor(Color.parseColor("#000000"));
                scheduleImg.setImageResource(R.drawable.ic_account_balance_black);

                break;
            case 2:
                homeworkText.setTextColor(Color.parseColor("#000000"));
                homeworkImg.setImageResource(R.drawable.ic_tab_black);
                showView3();
                break;
            case 3:
                dataText.setTextColor(Color.parseColor("#000000"));
                dataImg.setImageResource(R.drawable.ic_dns_black);
                showView4();
                break;
        }
    }

    public void onClick(View v){
        resetButton();
        switch (v.getId()){
            case R.id.personalLayout:
                personalText.setTextColor(Color.parseColor("#000000"));
                personalImg.setImageResource(R.drawable.ic_account_circle_black);
                viewPager.setCurrentItem(0);
                break;
            case R.id.scheduleLayout:
                scheduleText.setTextColor(Color.parseColor("#000000"));
                scheduleImg.setImageResource(R.drawable.ic_account_balance_black);
                viewPager.setCurrentItem(1);
                break;
            case R.id.homeworkLayout:
                homeworkText.setTextColor(Color.parseColor("#000000"));
                homeworkImg.setImageResource(R.drawable.ic_tab_black);
                viewPager.setCurrentItem(2);
                showView3();
                break;
            case R.id.dataLayout:
                dataText.setTextColor(Color.parseColor("#000000"));
                dataImg.setImageResource(R.drawable.ic_dns_black);
                viewPager.setCurrentItem(3);
                showView4();
                break;
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }

    //view3显示函数
    public void showView3(){
        ListView list = (ListView)findViewById(R.id.view3list);
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
                bn.setText("课程：C语言\n内容：1.求水仙花数 2.最小公倍数\n截止日期：2017-5-24");
                bn.setTextColor(0xffffffff);
                bn.setTextSize(25);
                bn.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

                bn.setBackgroundResource(shap[i%5]);

                linearLayout.addView(bn);
                return linearLayout;
            }
        };
        list.setAdapter(adapter);
    }

    //view4显示函数
    public void showView4(){
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
                LinearLayout ll = new LinearLayout(MainActivity.this);
                ll.setOrientation(LinearLayout.HORIZONTAL);
                TextView groupText = new TextView(MainActivity.this);
                groupText.setText(fileTypes[i]);
                groupText.setTextSize(35);
                groupText.setPadding(25,10,10,10);
                ll.addView(groupText);

                return ll;
            }

            //子选项的外观
            @Override
            public View getChildView(int i, int i1, boolean b, View view, ViewGroup viewGroup) {
                RelativeLayout cl = new RelativeLayout(MainActivity.this);
                cl.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                TextView fileTextView = new TextView(MainActivity.this);
                fileTextView.setId(R.id.my_view);
                Button downloadBtn = new Button(MainActivity.this);
                downloadBtn.setBackgroundResource(R.drawable.shape2);
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
        ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.fileListVIew);
        expandableListView.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        clearWebViewCache();
    }
}
