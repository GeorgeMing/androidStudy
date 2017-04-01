package com.example.zzm.apptest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by zzm on 2017/3/25.
 */


public class RegisterActivity extends AppCompatActivity {
    String name, no, sex = "男", college, specialty, grade, banji;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button registerbtn = (Button) findViewById(R.id.registerBtn);
        final TextView nameText = (TextView)findViewById(R.id.name);
        final TextView noText = (TextView)findViewById(R.id.no);
        final RadioGroup radioGroup = (RadioGroup)findViewById(R.id.sex);
        final Spinner schoolSpinneer = (Spinner)findViewById(R.id.schoolSpinner);
        final Spinner majorSpinner = (Spinner)findViewById(R.id.majorSpinner);
        final Spinner classSpinner = (Spinner)findViewById(R.id.classSpinner);
        final Spinner yearSpinner = (Spinner)findViewById(R.id.yearSpinner);
        final RadioButton man = (RadioButton)findViewById(R.id.sexman);
        final RadioButton woman = (RadioButton)findViewById(R.id.sexwoman);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                if(checkedId == man.getId()){
                    sex = "男";
                }
                if(checkedId == woman.getId()){
                    sex = "女";
                }
            }
        });
        registerbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name = nameText.getText().toString();
                no = noText.getText().toString();
                college = schoolSpinneer.getSelectedItem().toString();
                specialty = majorSpinner.getSelectedItem().toString();
                grade = yearSpinner.getSelectedItem().toString();
                banji = classSpinner.getSelectedItem().toString();
                System.out.println(name+" "+no+" "+sex+" "+college+" "+specialty+" "+grade+" "+banji+" ");
                System.out.println(ServerAction.ServerAddr+"tp/index.php/admin/index/register?name="+name+"&no="+no+"&sex="+sex+"&college="+college+"&specialty="+specialty+"&grade="+grade+"&class="+banji);
                if(name.length()!=0 && no.length()!=0){
                    RegisterService RS = new RegisterService(RegisterActivity.this);
                    RS.execute(ServerAction.ServerAddr+"tp/index.php/admin/index/register?name="+name+"&no="+no+"&sex="+sex+"&college="+college+"&specialty="+specialty+"&grade="+grade+"&class="+banji);
                }
                else{
                    Toast.makeText(RegisterActivity.this, "信息不全", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
