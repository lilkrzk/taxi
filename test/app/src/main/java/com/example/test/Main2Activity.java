package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {
private Button btn_path;
private TextView tv_name, tv_number, tv_path;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        initViews();
        Intent intent = getIntent();
        tv_name.setText(intent.getStringExtra("Name"));
        tv_number.setText(intent.getStringExtra("Number"));
        //tv_path.setText(intent.getStringExtra("From " + "To"));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
       // if (data == null) {
         //   return;
        //}
        String str1 = data.getStringExtra("streetFrom");
        String str2 = data.getStringExtra("houseFrom");
        String str3 = "From: " + str1 +  " street, house " + str2;
        str1 = data.getStringExtra("streetTo");
        str2 = data.getStringExtra("houseTo");
        String str4 = "To: " + str1 +  " house " + str2;
        String message = str3 + "\n" + str4;
        tv_path.setText(message);
    }


    private void initViews() {
        btn_path = (Button) findViewById(R.id.btn_path);
        btn_path.setOnClickListener(this);
        tv_name = (TextView) findViewById(R.id.tv_name);
        tv_number = (TextView) findViewById(R.id.tv_number);
        tv_path = (TextView) findViewById(R.id.tv_path);
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_path) {
            Intent intent = new Intent(this, Main3Activity.class);
            startActivityForResult(intent, 3);
        }
    }
}
