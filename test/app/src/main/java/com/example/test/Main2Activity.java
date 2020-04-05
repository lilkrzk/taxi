package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.view.inputmethod.InputMethodSubtype;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_path;
    private TextView tv_name, tv_number, tv_path;
    private String streetFrom, houseFrom, streetTo, houseTo;


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
        if (data.getStringExtra("streetFrom") == "") {
            Toast.makeText(this, "Incorrect data", Toast.LENGTH_SHORT).show();
        }
        // if (data == null) {
        //   return;
        //}
        streetFrom = data.getStringExtra("streetFrom");
        houseFrom = data.getStringExtra("houseFrom");
        String str3 = "From: " + streetFrom +  " street, house " + houseFrom;
        streetTo = data.getStringExtra("streetTo");
        houseTo = data.getStringExtra("houseTo");
        String str4 = "To: " + streetTo +  " house " + houseTo;
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

        /*InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        InputMethodSubtype ims = imm.getCurrentInputMethodSubtype();
        String localeString = ims.getLocale();
        Locale locale = new Locale(localeString);
        String currentLanguage = locale.getDisplayLanguage();
        EditText languageEditText = (EditText)findViewById(R.id.etNewItem);
        Toast.makeText(getApplicationContext(), currentLanguage, Toast.LENGTH_SHORT).show();*/
    }
}
