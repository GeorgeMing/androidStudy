package com.example.zzm.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by zzm on 2017/4/1.
 */

public class ChangePasswordActivity extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        Button changepassbtn = (Button)findViewById(R.id.changepassbtn);
        changepassbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText oldpass = (EditText)findViewById(R.id.oldpassword);
                EditText newpass = (EditText)findViewById(R.id.newpassword);
                EditText newpass1 = (EditText)findViewById(R.id.newpassword1);
                String oldpasstext  = oldpass.getText().toString();
                String newpasstext = newpass.getText().toString();
                String newpass1text = newpass1.getText().toString();
                if(oldpasstext.length()==0||newpasstext.length()==0||newpass1text.length()==0){
                    Toast.makeText(ChangePasswordActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                }else if(!newpasstext.equals(newpass1text)){
                    Toast.makeText(ChangePasswordActivity.this, "两次输入的新密码不同", Toast.LENGTH_SHORT).show();
                }else{
                    changePassword cp = new changePassword(ChangePasswordActivity.this);
                    cp.execute(oldpasstext, newpasstext, newpass1text);
                }
            }
        });
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK )
        {
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
        }

        return false;

    }

    @Override
    public void onStop(){
        super.onStop();
        finish();
    }
}
