package com.example.zzm.apptest;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by zzm on 2017/3/10.
 */

public class GetData extends AsyncTask<String, Void, String> {
    private Context context;

    public GetData(Context context){
        this.context = context;
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
