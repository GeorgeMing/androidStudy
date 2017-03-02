package space.zzmyun.helloworld;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

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




//        WebView webView = (WebView)findViewById(R.id.webview);
//        webView.loadUrl("http://www.baidu.com");
//        webView.setWebViewClient(new WebViewClient(){
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                // TODO Auto-generated method stub
//                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
//                view.loadUrl(url);
//                return true;
//            }
//        });

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
                break;
            case 3:
                dataText.setTextColor(Color.parseColor("#000000"));
                dataImg.setImageResource(R.drawable.ic_dns_black);
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
                break;
            case R.id.dataLayout:
                dataText.setTextColor(Color.parseColor("#000000"));
                dataImg.setImageResource(R.drawable.ic_dns_black);
                viewPager.setCurrentItem(3);
                break;
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
