package com.example.zzm.apptest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by zzm on 2017/3/13.
 */

class ServerInfo {
    static String ServerAddr = "http://192.168.249.238/";
}

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameText,passwordText;
    private Button submitBtu;
    Context context;
    Intent intent;
    static String URLstr = ServerInfo.ServerAddr + "tp/admin/index/login";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        submitBtu = (Button)findViewById(R.id.loginBtu);
        usernameText = (EditText)findViewById(R.id.loginUser);
        passwordText = (EditText)findViewById(R.id.loginPass);
        submitBtu.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.loginBtu:
                context = LoginActivity.this;
                intent = new Intent(LoginActivity.this, MainActivity.class);
                LoginService myinfo = new LoginService(context, intent);
                myinfo.execute(URLstr+"?username="+usernameText.getText()+"&password="+passwordText.getText());
               // System.out.println(URLstr+"?"+usernameText.getText()+"&"+passwordText.getText());
                break;
        }
    }
}
