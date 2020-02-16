package com.example.mobiletracker;

import androidx.fragment.app.FragmentActivity;

import android.content.Context;
import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener;
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class maptrack extends FragmentActivity implements
        OnMyLocationButtonClickListener,
        OnMyLocationClickListener,
        OnMapReadyCallback, LocationListener,
        ActivityCompat.OnRequestPermissionsResultCallback {

    private GoogleMap mMap;
    protected LocationManager locationManager;

    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;

    double lat = 50;
    double lon=50;

    private boolean mPermissionDenied = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maptrack);

        View decorView = getWindow().getDecorView();
//sets screen size to full screen
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        //ask user for permission to use location
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }

        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);


            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }

        //when map is ready the locattion and marker is set
        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            mMap.setOnMyLocationButtonClickListener(this);
            mMap.setOnMyLocationClickListener(this);
            enableMyLocation();


            // Add a marker in Sydney, Australia, and move the camera.
            LatLng sydney = new LatLng(lat, lon);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in BCC"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));



        }
    //as soon as update button is clicked we retrieve lat and lon and set markers and camera
        public void update(View view){

            //retrieve info from  database
            Firebase.setAndroidContext(this);

            Firebase myFirebaseRef = new Firebase("https://mobiletracker-d4f90.firebaseio.com/");



            // cellphone = (EditText) findViewById(R.id.editcellphone);
            // schoolemail = (EditText) findViewById(R.id.editschoolemail);

            myFirebaseRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {


                    System.out.println(dataSnapshot.child("123").child("lat").getValue());
                    System.out.println(dataSnapshot.child("123").child("lon").getValue());
                    lat = Double.parseDouble(dataSnapshot.child("123").child("lat").getValue().toString());
                    lon = Double.parseDouble(dataSnapshot.child("123").child("lon").getValue().toString());
                    LatLng sydney = new LatLng(lat, lon);
                    mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in BCC"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));


                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                    Toast.makeText(getApplicationContext(),"error uploading to server!!", Toast.LENGTH_SHORT).show();

                }


            });



        }
    //enables the location of person when permission granted
        private void enableMyLocation() {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                // Permission to access the location is missing.
                PermissionUtils.requestPermission(this, LOCATION_PERMISSION_REQUEST_CODE,
                        Manifest.permission.ACCESS_FINE_LOCATION, true);
            } else if (mMap != null) {
                // Access to the location has been granted to the app.
                mMap.setMyLocationEnabled(true);
            }
        }

        @Override
        public boolean onMyLocationButtonClick() {
            return false;
        }
//as soon as location is clicked we retrieve lat and lon and set markers and camera
        @Override
        public void onMyLocationClick(@NonNull Location location) {

            System.out.println("lat:" + location.getLatitude());
            System.out.println(location.getLongitude());
            lat = location.getLatitude();
            lon = location.getLongitude();

            LatLng sydney = new LatLng(lat, lon);
            mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in BCC"));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        }

        @Override
        public void onLocationChanged(Location location) {


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
    }


