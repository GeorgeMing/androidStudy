package com.example.zzm.asyn;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;



/**
 * Created by zzm on 2017/3/10.
 */

public class asyntest extends AsyncTask<Void, Integer, Void>{
    private TextView textView;
    private ProgressBar progressBar;

    public asyntest(TextView textView, ProgressBar progressBar) {
        this.textView = textView;
        this.progressBar = progressBar;
    }



    @Override
    protected Void doInBackground(Void... voids) {
        NetOperator netOperator = new NetOperator();
        int i;
        for (i = 10; i <= 100; i+=10) {
            netOperator.operator();
            publishProgress(i);
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
        textView.setText("asyn进程结束");
    }


    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        int vlaue = values[0];
        progressBar.setProgress(vlaue);
    }
}
