package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText et_phone, et_name, et_surname;
    private Button btn_register;
    private TextView tv_phone, tv_name, tv_surname;
    SharedPreferences sPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        loadText();
    }

    private void saveText() {
        sPref = getPreferences(MODE_PRIVATE);
        SharedPreferences.Editor editor = sPref.edit();
        editor.putString("et_phone", et_phone.getText().toString());
        editor.putString("et_name", et_name.getText().toString());
        editor.putString("et_surname", et_surname.getText().toString());
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        saveText();
    }

    private void loadText() {
        sPref = getPreferences(MODE_PRIVATE);
        String phone = sPref.getString("et_phone", "");
        String name = sPref.getString("et_name", "");
        String surname = sPref.getString("et_surname", "");
        et_phone.setText(phone);
        et_name.setText(name);
        et_surname.setText(surname);

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
            if (et_phone.getText().toString().length() == 0 || et_name.getText().toString().length() == 0 || et_surname.getText().toString().length() == 0) {
                Toast toast = Toast.makeText(this, "Some of the fields are empty", Toast.LENGTH_SHORT);
                toast.show();
            } else {
                Intent intent = new Intent(this, Main2Activity.class);
                intent.putExtra("Name", et_name.getText().toString() + " " + et_surname.getText().toString());
                intent.putExtra("Number",et_phone.getText().toString() );
                startActivity(intent);
            }

        }

    }
}
