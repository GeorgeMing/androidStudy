package com.example.zzm.apptest;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.TooManyListenersException;

/**
 * Created by zzm on 2017/3/10.
 */

public class LoginService extends AsyncTask<String, Integer, String> {

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
