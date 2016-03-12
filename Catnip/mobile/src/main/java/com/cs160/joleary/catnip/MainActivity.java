package com.cs160.joleary.catnip;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.wearable.Wearable;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;
import io.fabric.sdk.android.Fabric;

public class MainActivity extends Activity implements
        GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    // Note: Your consumer key and secret should be obfuscated in your source code before shipping.
    private static final String TWITTER_KEY = "	6FSFgyS8yurhHxYEFwpqtGE0J";
    private static final String TWITTER_SECRET = "	K4FvRBEMa7qRG7XTaUYEaSH8b48SNMdr0kA98dOeCnRZh4MLtx ";



//    private GoogleApiClient mGoogleApiClient;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        mGoogleApiClient = new GoogleApiClient.Builder(this)
//                .addApi(LocationServices.API)
//                .addApi(Wearable.API)  // used for data layer API
//                .addConnectionCallbacks(this)
//                .addOnConnectionFailedListener(this)
//                .build();
//    }

    @Override
    protected void onResume() {
        super.onResume();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mGoogleApiClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {}

    @Override
    public void onConnectionSuspended(int i) {}

    @Override
    public void onConnectionFailed(ConnectionResult connResult) {}


        //there's not much interesting happening. when the buttons are pressed, they start
    //the PhoneToWatchService with the cat name passed in.

    private Button mFredButton;
    private Button mLexyButton;
    private Button goButton;
    public EditText zipCode;
    Bundle bun;

    public String mLatitudeText;
    public String mLongitudeText;

    private GoogleApiClient mGoogleApiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TwitterAuthConfig authConfig = new TwitterAuthConfig(TWITTER_KEY, TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
        setContentView(R.layout.activity_main);



        //adding on create information from apiclient
        super.onCreate(savedInstanceState);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addApi(Wearable.API)  // used for data layer API
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    public void pressGo(View view) {
        String buttonText;
        buttonText = ((Button) view).getText().toString();
        if (buttonText.equals("GO")) {

            zipCode = (EditText) findViewById(R.id.editText); //this will get the zipcode that was inputted
            if (zipCode.length() != 5) {
                Toast.makeText(getBaseContext(), "This is an invalid zipcode", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(getBaseContext(), OfficialList.class);
                intent.putExtra("zip", zipCode.getText().toString());
                //String actualzipCode = zipCode.getText().toString();




//            Intent intent = new Intent(getBaseContext(), OfficialList.class);
                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
                startActivity(intent);
//                startService(sendIntent);
            }
//            Intent intent = new Intent(getBaseContext(), OfficialList.class);
//            intent.putExtra("zip", zipCode.getText().toString());
//            //String actualzipCode = zipCode.getText().toString();
//
//
//
//
////            Intent intent = new Intent(getBaseContext(), OfficialList.class);
//            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//            startActivity(intent);
//            startService(sendIntent);


            /**
             * this will be used to send the zipcode to the official list so that i have access to the information
             * from parsing
             */
//            Intent intent = new Intent(getBaseContext(), OfficialList.class);
//            intent.putExtra("zipcode", zip.getText());
//            startActivity(intent);


        }

        if (buttonText.equals("Current Location")) {
//            Intent intent = new Intent(getBaseContext(), OfficialList.class);
//            startActivity(intent);

            Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);
            if (mLastLocation != null) {
//                mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
//                mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));
                mLatitudeText = String.valueOf(mLastLocation.getLatitude());
                mLongitudeText = String.valueOf(mLastLocation.getLongitude());



                //this is where you would send the latitude and longitude informatio in order parse through the api

                Toast.makeText(getBaseContext(), mLatitudeText, Toast.LENGTH_SHORT).show();
                Toast.makeText(getBaseContext(), mLongitudeText, Toast.LENGTH_SHORT).show();


                /**
                 * This will be used in ordet to send the long and lat information in officialist.java
                 */
                Intent intent = new Intent(getBaseContext(), OfficialList.class);
                intent.putExtra("lat", String.valueOf(mLastLocation.getLatitude()));
                intent.putExtra("lon", String.valueOf(mLastLocation.getLongitude()));
                startActivity(intent);


                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    //            }urn;
                }

            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
