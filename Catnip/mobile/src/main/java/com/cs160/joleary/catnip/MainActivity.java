package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity {
    //there's not much interesting happening. when the buttons are pressed, they start
    //the PhoneToWatchService with the cat name passed in.

    private Button mFredButton;
    private Button mLexyButton;
    private Button goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mFredButton = (Button) findViewById(R.id.fred_btn);
//        mLexyButton = (Button) findViewById(R.id.lexy_btn);
//
//        mFredButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntent.putExtra("CAT_NAME", "Fred");
//                startService(sendIntent);
//            }
//        });
//
//        mLexyButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//                sendIntent.putExtra("CAT_NAME", "Lexy");
//                startService(sendIntent);
//            }
//        });


    }

    public void pressGo(View view) {
        String buttonText;
        buttonText = ((Button) view).getText().toString();
        if (buttonText.equals("GO")) {
//            Intent intent = new Intent(this, OfficialList.class);
//            startActivity(intent);

            //start a second intent that would send somethign to phonetowatchservices.java
            //remove this if code doesnt work
//            Intent secondIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//            secondIntent.putExtra("CAT_NAME", "Fred");
//            startService(secondIntent);
            //remove this if code doesnt work
            Intent intent = new Intent(getBaseContext(), OfficialList.class);

            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);

            startActivity(intent);

        }

        if (buttonText.equals("Current Location")) {
//            Intent secondIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//            secondIntent.putExtra("CAT_NAME", "Fred");
//            startService(secondIntent);
            //remove this if code doesnt work
            Intent intent = new Intent(getBaseContext(), OfficialList.class);
            startActivity(intent);
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
