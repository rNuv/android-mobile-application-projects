package com.example.gpsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    TextView longitudeText;
    TextView latitudeText;
    TextView addressText;
    TextView totalTraveled;
    LocationManager locationManager;
    LocationListener locationListener;
    List<Address> addressList;
    Geocoder geocoder;
    Location prevLocation;
    Double runningSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        longitudeText = findViewById(R.id.id_long);
        latitudeText = findViewById(R.id.id_lat);
        addressText = findViewById(R.id.id_add);
        totalTraveled = findViewById(R.id.id_totalTraveled);
        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        addressList = new ArrayList<>();
        geocoder = new Geocoder(this, Locale.US);
        prevLocation = null;
        runningSum = 0.0;

        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(Location location) {
                longitudeText.setText("Longitude: " + location.getLongitude() + "");
                latitudeText.setText("Latitude: " + location.getLatitude() + "");

                try {
                    addressList = geocoder.getFromLocation(location.getLatitude(),
                            location.getLongitude(), 1);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                addressText.setText(addressList.get(0).getAddressLine(0));

                if(prevLocation != null){
                    double distance = prevLocation.distanceTo(location);
                    runningSum += distance;
                    totalTraveled.setText("Total Dist.: " + runningSum);
                }

                prevLocation = location;
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {

            }
        };

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);
        } else {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    3000, 5, locationListener);
        }
    }

    @SuppressLint("MissingPermission")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                            3000, 5, locationListener);
                }
                return;
            }
        }
    }
}
