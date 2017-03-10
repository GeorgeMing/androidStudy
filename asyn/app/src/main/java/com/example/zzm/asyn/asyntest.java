package com.example.zzm.asyn;

import android.os.AsyncTask;
import android.widget.TextView;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Created by zzm on 2017/3/10.
 */

public class asyntest extends AsyncTask<String, Integer, Void>{
    private TextView textView;
    private String responseBody;

    public asyntest(TextView textView) {
        this.textView = textView;
    }


    @Override
    protected Void doInBackground(String... strings) {
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
                responseBody = str;//html内容
            }
        }catch (Exception e){
            publishProgress(-1);
        }
        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        this.textView.setText("asyn进程开启");

    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        textView.setText(responseBody);
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int vlaue = values[0];
        if(vlaue < 0){
            textView.setText("http error");
        }

    }
}
