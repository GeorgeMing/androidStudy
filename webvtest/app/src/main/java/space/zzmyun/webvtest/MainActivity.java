package space.zzmyun.webvtest;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.kongqw.rockerlibrary.view.RockerView;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    RockerView rockerView;
    Button btntest;
    TextView mLogRight;
    Socket socket;
    PrintWriter out;
    public Handler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rockerView = (RockerView) findViewById(R.id.rockerView);
        mLogRight = (TextView) findViewById(R.id.mLogRight);
        rockerView.setCallBackMode(RockerView.CallBackMode.CALL_BACK_MODE_STATE_CHANGE);
        WebView webView = (WebView)findViewById(R.id.webview);
        webView.loadUrl("http://192.168.12.1:8080/?action=stream");
        WebSettings webSettings = webView.getSettings();
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setBuiltInZoomControls(false);//support zoom
        webSettings.setUseWideViewPort(true);//關鍵點
        webSettings.setLoadWithOverviewMode(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        MySocket mysocket = new MySocket();
        mysocket.start();
        findViewById(R.id.btntest).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str = "hello";
                Log.d("Test", "MainThread is ready to send msg:" + str);
                mHandler.obtainMessage(1, str).sendToTarget();//发送消息到CustomThread实例
            }
        });
        // 监听摇动角度
        rockerView.setOnAngleChangeListener(new RockerView.OnAngleChangeListener() {
            @Override
            public void onStart() {
                mLogRight.setText(null);
            }

            @Override
            public void angle(double angle) {
                mLogRight.setText("摇动角度 : " + angle);
                mHandler.obtainMessage(0, angle+"").sendToTarget();//发送消息到CustomThread实例
            }

            @Override
            public void onFinish() {
                mLogRight.setText(null);
            }
        });
    }

    @Override
    protected void onResume() {
        /**
         * 设置为横屏
         */
        if(getRequestedOrientation()!=ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        super.onResume();
    }

    class MySocket extends Thread{

        public void run(){
            try {
                socket = new Socket("192.168.199.227", 8082);
                out = new PrintWriter(new BufferedWriter(
                        new OutputStreamWriter(socket.getOutputStream())), true);
                //建立消息循环的步骤
                Looper.prepare();//1、初始化Looper
                mHandler = new Handler(){//2、绑定handler到CustomThread实例的Looper对象
                    public void handleMessage (Message msg) {//3、定义处理消息的方法
                        switch(msg.what) {
                            case 0:
                                Log.d("Test", "CustomThread receive msg:" + (String) msg.obj);
                                out.println((String) msg.obj);
                                break;
                            case 1:
                                try{
                                    out.println("quit");
                                    socket.close();
                                }catch (Exception e){

                                }

                        }
                    }
                };

                Looper.loop();//4、启动消息循环

            }catch (Exception e){
                Log.d("error", "socket error");
            }

        }
    }

    @Override
    public void onStop() {
        super.onStop();
        try{
            out.println("quit");
            socket.close();
        }catch (Exception e){

        }
    }
}
