package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_phone, et_name, et_surname;
    private Button btn_register;
    private TextView tv_phone, tv_name, tv_surname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews()
    {
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_name = (EditText) findViewById(R.id.et_name);
        et_surname = (EditText) findViewById(R.id.et_surname);
        btn_register = (Button) findViewById(R.id.btn_register);
        btn_register.setOnClickListener(this);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_surname = (TextView) findViewById(R.id.tv_surname);

    }
    @Override
    public void onClick(View v) {
       if (v.getId() == R.id.btn_register){
                Intent intent = new Intent(this, Main2Activity.class);
                intent.putExtra("Name", et_name.getText().toString() + " " + et_surname.getText().toString());
                intent.putExtra("Number",et_phone.getText().toString() );
                startActivity(intent);
        }

    }
}
