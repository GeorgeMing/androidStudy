package com.example.zzm.apptest;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zzm on 2017/3/13.
 */

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText usernameText,passwordText;
    private Button submitBtu;
    private TextView forgetPasswordBtn, registerBtn;
    Context context;
    Intent intent;
    static String URLstr = ServerAction.ServerAddr + "tp/admin/index/login";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        forgetPasswordBtn = (TextView)findViewById(R.id.forgetPassworBtn);
        registerBtn = (TextView)findViewById(R.id.registerBtn);
        submitBtu = (Button)findViewById(R.id.loginBtu);
        usernameText = (EditText)findViewById(R.id.loginUser);
        passwordText = (EditText)findViewById(R.id.loginPass);
        submitBtu.setOnClickListener(this);
        forgetPasswordBtn.setOnClickListener(this);
        registerBtn.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.loginBtu:
                context = LoginActivity.this;
                intent = new Intent(LoginActivity.this, MainActivity.class);
                this.startActivity(intent);
                LoginService myinfo = new LoginService(context, intent);
                myinfo.execute(URLstr+"?username="+usernameText.getText()+"&password="+passwordText.getText());
                break;
            case R.id.forgetPassworBtn:
                Toast.makeText(this ,"请联系管理员", Toast.LENGTH_SHORT).show();
                break;
            case R.id.registerBtn:
                intent = new Intent(LoginActivity.this, RegisterActivity.class);
                this.startActivity(intent);
                break;
        }
    }
}
