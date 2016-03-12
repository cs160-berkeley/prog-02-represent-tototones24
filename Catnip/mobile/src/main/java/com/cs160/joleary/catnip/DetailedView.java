package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by tototones24 on 3/1/16.
 */
public class DetailedView extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view);

        ImageView detailedImage = (ImageView)findViewById(R.id.imageView100);
        TextView detailedName = (TextView)findViewById(R.id.detailedView69);
        TextView lastDia = (TextView)findViewById(R.id.textView12);
        //TextView detailedEnddate = (TextView)findViewById(R.id.detailedView24);

        Intent intent = getIntent();
        String officialPerson = intent.getStringExtra("person");
        String lastDay = intent.getStringExtra("lastday");
        String urlString = intent.getStringExtra("representpic");

        Picasso.with(this).load(urlString).into(detailedImage);
//        String watchPerson = intent.getStringExtra("CAT_NAME");


        //fot watch to phone
        //String officialPersonTwo = intent.getStringExtra("CAT_NAME");
        //for watch to phone
        //detailedImage.setImageResource(R.drawable.boxer);
        detailedName.setText(officialPerson);
        //detailedEnddate.setText(lastDia);
        lastDia.setText(lastDay);




//        if (officialPerson.equals("Barbara Boxer")) {
//            detailedImage.setImageResource(R.drawable.boxer);
//            detailedName.setText("Barbara Boxer");
//        } else if (officialPerson.equals("Dianne Feinstein")) {
//            detailedImage.setImageResource(R.drawable.dianna);
//            detailedName.setText("Dianne Feinstein");
//        } else {
//            detailedImage.setImageResource(R.drawable.judy);
//            detailedName.setText("Judy Chu");
//
//        }

        //this is for watchtophone
//        if (watchPerson.equals("Barbara Boxer")) {
//            detailedImage.setImageResource(R.drawable.boxer);
//            detailedName.setText("Barbara Boxer");
//        }

    }
}
