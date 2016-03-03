package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by tototones24 on 2/28/16.
 */
public class CongressionalView extends Activity{
    Button moreInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.congressional_layout);

        moreInfo = (Button)findViewById(R.id.button3);

        Intent intent = getIntent();
        final String officialPerson = intent.getStringExtra("corresponding_text");

//        if (officialPerson.equals("Barbara Boxer")) {
//            Toast.makeText(getApplicationContext(), "hahahaha", Toast.LENGTH_SHORT).show();
//        }
//        Bundle extras = getIntent().getExtras();
//        String officialName = extras.getString("corresponding_text");
        ImageView img = (ImageView) findViewById(R.id.imageView);
        TextView name = (TextView)findViewById(R.id.textView2);
        TextView email = (TextView)findViewById(R.id.textView4);
        TextView website = (TextView)findViewById(R.id.textView3);




        if (officialPerson.equals("Barbara Boxer")) {
            img.setImageResource(R.drawable.boxer);
            name.append("Barbara Boxer");
            email.append("boxer.ca.gov");
            website.append("bboxer@ca.gov");


            //start of phone to watch communication
            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
//            sendIntent.putExtra("CAT_NAME", "Fred");
//            startService(sendIntent);
            sendIntent.putExtra("CAT_NAME", "Barbara");
            startService(sendIntent);
            //end of phone to watch communication




        } else if (officialPerson.equals("Dianne Feinstein")) {
            img.setImageResource(R.drawable.dianna);
            name.append("Dianne Feinstein");
            email.append("feinstein.ca.gov");
            website.append("feinstein@ca.gov");


            //start of phone to watch communication
            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
            sendIntent.putExtra("CAT_NAME", "Dianne");
            startService(sendIntent);


            //end of phone to watch communication


        } else {
            img.setImageResource(R.drawable.judy);
            name.append("Judy Chu");
            email.append("chu.ca.gov");
            website.append("chu@ca.gov");

            //start of phone to watch communication
            Intent sendIntent = new Intent(getBaseContext(), PhoneToWatchService.class);
            sendIntent.putExtra("CAT_NAME", "Judy");
            startService(sendIntent);

            //end of phone to watch communication





        }

        moreInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent detailedIntent = new Intent(getBaseContext(), DetailedView.class);
                if (officialPerson.equals("Barbara Boxer")) {
                    detailedIntent.putExtra("person", "Barbara Boxer");
//                    Toast.makeText(getApplicationContext(), "boxer", Toast.LENGTH_SHORT).show();
                    startActivity(detailedIntent);
//                    Toast.makeText(getApplicationContext(), "got passed start activity", Toast.LENGTH_SHORT).show();
                } else if (officialPerson.equals("Dianne Feinstein")) {
                    detailedIntent.putExtra("person", "Dianne Feinstein");
                    //Toast.makeText(getApplicationContext(), "dianne", Toast.LENGTH_SHORT).show();
                    startActivity(detailedIntent);
                } else {
                    detailedIntent.putExtra("person", "Judy Chu");
                    //Toast.makeText(getApplicationContext(), "chu", Toast.LENGTH_SHORT).show();
                    startActivity(detailedIntent);
                }

               // Toast.makeText(getApplicationContext(), "hahahaha", Toast.LENGTH_SHORT).show();
            }
        });




    }
}
