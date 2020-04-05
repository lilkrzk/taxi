package com.example.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Locale;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {
    private Button findLocationFrom, setLocation, btn_ok;
    private EditText streetFrom, houseFrom, streetTo, houseTo;
    private TextView tv_to, tv_from;

    private LocationManager locationManager;
    private static final int REQUEST_CODE=123;
    private static final String FIND_LOCATION_ERROR = "Can't find location";
    private static final String LANGUAGE_TAG_ENG = "eng";
    private static final String TRY_AGAIN = "Try Again";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        initViews();
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    private void initViews() {
        findLocationFrom = (Button) findViewById(R.id.findLocationFrom);
        findLocationFrom.setOnClickListener(this);
        setLocation = (Button) findViewById(R.id.setLocation);
        setLocation.setOnClickListener(this);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        btn_ok.setOnClickListener(this);
        streetFrom = (EditText) findViewById(R.id.streetFrom);
        houseFrom = (EditText) findViewById(R.id.houseFrom);
        streetTo = (EditText) findViewById(R.id.streetTo);
        houseTo = (EditText) findViewById(R.id.houseTo);
        tv_from = (TextView) findViewById(R.id.tv_from);
        tv_to = (TextView) findViewById(R.id.tv_to);
    }

    private boolean checkPermission()
    {
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED
                && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
                !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,
                    new String[]{
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.BLUETOOTH,
                            Manifest.permission.BLUETOOTH_ADMIN
                    }, REQUEST_CODE);
            return false;
        }
        return true;
    }

    private Address getAddress() {
        if(!checkPermission()){
            Toast.makeText(this, TRY_AGAIN, Toast.LENGTH_SHORT).show();
            return null;
        }

        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        Address address = getAddress(location.getLatitude(), location.getLongitude());
        return address;
    }

    private Address getAddress(double latitude, double longitude){
        Geocoder geocoder = new Geocoder(this, Locale.forLanguageTag(LANGUAGE_TAG_ENG));
        try {
            List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 5);
            for(int i=0;i<addresses.size();i++)
            {
                Address adr = addresses.get(i);
                if (adr.getThoroughfare()!=null && adr.getSubThoroughfare()!=null){
                    return adr;
                }
            }
        }
        catch (Throwable throwable)
        {
            Toast.makeText(this, FIND_LOCATION_ERROR, Toast.LENGTH_SHORT).show();

        }
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.findLocationFrom:
                findLocation();
                break;
            case R.id.setLocation:
                setLocation();
                break;
            case R.id.btn_ok:
                if (streetFrom.getText().toString().length() == 0 || houseFrom.getText().toString().length() == 0 ||
                        streetTo.getText().toString().length() == 0 || houseTo.getText().toString().length() == 0 ) {
                    Toast toast = Toast.makeText(this, "Some of the fields are empty", Toast.LENGTH_SHORT);
                    toast.show();
                } else {
                    Intent intent = new Intent("android.intent.action.second");
                    intent.putExtra("streetFrom", streetFrom.getText().toString());
                    intent.putExtra("houseFrom", houseFrom.getText().toString());
                    intent.putExtra("streetTo", streetTo.getText().toString());
                    intent.putExtra("houseTo", houseTo.getText().toString());
                    setResult(RESULT_OK, intent);
                    finish();
                    break;
                }
        }
    }

  //  private void setPath() {


   // }

    private void setLocation() {
        String latitude = ("0");
        String longitude = ("0");
        Address address = getAddress();
        latitude = String.valueOf(address.getLatitude());
        longitude = String.valueOf(address.getLongitude());
        Intent intent = new Intent(this, MapsActivity.class);
        intent.putExtra("LATITUDE", latitude);
        intent.putExtra("LONGITUDE", longitude);
        startActivityForResult(intent,1);
    }

    private void findLocation() {
        Address address = getAddress();
        if (address != null)
        {
            streetFrom.setText(address.getThoroughfare());
            houseFrom.setText(address.getSubThoroughfare());
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data == null) {
            return;
        }

        try {
            Address address = getAddress(
                    Double.parseDouble(data.getStringExtra("LATITUDE")),
                    Double.parseDouble(data.getStringExtra("LONGITUDE")));
            streetTo.setText(address.getThoroughfare());
            houseTo.setText(address.getSubThoroughfare());
        } catch (Throwable throwable) {
            Toast.makeText(this, FIND_LOCATION_ERROR, Toast.LENGTH_SHORT).show();
        }
    }
}


