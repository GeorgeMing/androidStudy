package space.zzmyun.helloworld;

import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ViewPager.OnPageChangeListener{

    private View view1, view2, view3;
    private ViewPager viewPager;  //对应的viewPager

    private List<View> viewList;//view数组

    private TextView test1,test2,test3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        test1 = (TextView)findViewById(R.id.test1);
        test2 = (TextView)findViewById(R.id.test2);
        test3 = (TextView)findViewById(R.id.test3);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        LayoutInflater inflater=getLayoutInflater();
        view1 = inflater.inflate(R.layout.layout1, null);
        view2 = inflater.inflate(R.layout.layout2,null);
        view3 = inflater.inflate(R.layout.layout3, null);

        viewList = new ArrayList<View>();// 将要分页显示的View装入数组中
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);


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
        test1.setTextColor(0xff1B940A);
        viewPager.setCurrentItem(0);
        test1.setOnClickListener(this);
        test2.setOnClickListener(this);
        test3.setOnClickListener(this);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);

    }

    public void onClick(View v){
        test1.setTextColor(Color.parseColor("#000000"));
        test2.setTextColor(Color.parseColor("#000000"));
        test3.setTextColor(Color.parseColor("#000000"));
        switch (v.getId()){
            case R.id.test1:
                test1.setTextColor(0xff1B940A);
                viewPager.setCurrentItem(0);
                break;
            case R.id.test2:
                test2.setTextColor(0xff1B940A);
                viewPager.setCurrentItem(1);
                break;
            case R.id.test3:
                test3.setTextColor(0xff1B940A);
                viewPager.setCurrentItem(2);
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int arg0) {
        Log.d("onPageSelected", String.valueOf(arg0));
        test1.setTextColor(Color.parseColor("#000000"));
        test2.setTextColor(Color.parseColor("#000000"));
        test3.setTextColor(Color.parseColor("#000000"));
        switch(arg0) {
            case 0:
                test1.setTextColor(0xff1B940A);
                break;
            case 1:
                test2.setTextColor(0xff1B940A);
                break;
            case 2:
                test3.setTextColor(0xff1B940A);
                break;
        }
    }


    @Override
    public void onPageScrollStateChanged(int state) {

    }


}
