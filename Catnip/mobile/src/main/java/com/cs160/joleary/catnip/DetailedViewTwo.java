package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tototones24 on 3/3/16.
 */
public class DetailedViewTwo extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view_two);

        ImageView detailedImage = (ImageView)findViewById(R.id.imageView101);
        TextView detailedName = (TextView)findViewById(R.id.textView71);

        Intent intent = getIntent();


        String watchPerson = intent.getStringExtra("CAT_NAME");


        //fot watch to phone
        //String officialPersonTwo = intent.getStringExtra("CAT_NAME");
        //for watch to phone

        if (watchPerson.equals("Barbara")) {
            detailedImage.setImageResource(R.drawable.boxer);
            detailedName.setText("Barbara Boxer");
        } else if (watchPerson.equals("Dianne")) {
            detailedImage.setImageResource(R.drawable.dianna);
            detailedName.setText("Dianne Feinstein");
        } else {
            detailedImage.setImageResource(R.drawable.judy);
            detailedName.setText("Judy Chu");

        }



    }
}
