package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends Activity {

    private TextView mTextView;
    private Button mFeedBtn;
    private ImageView img;
    private TextView officialName;
    String currentOfficial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFeedBtn = (Button) findViewById(R.id.feed_btn);
        img = (ImageView) findViewById(R.id.imageViewWatch);
        officialName = (TextView)findViewById(R.id.textView);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        //remove if breaks


        //
//        final String currentOfficial;

        if (extras != null) {
            String catName = extras.getString("CAT_NAME");
//            mFeedBtn.setText("Feed " + catName);

            if (catName.equals("Barbara")) {
                currentOfficial = catName;
                img.setImageResource(R.drawable.boxer);
                officialName.setText(catName + " Boxer");
                mFeedBtn.setText(catName);


            } else if (catName.equals("Dianne")) {
                currentOfficial = catName;
                img.setImageResource(R.drawable.dianna); //this does not change to dianna picture...why???
                officialName.setText(catName + " Feinstein");
                mFeedBtn.setText(catName);

            } else {
                currentOfficial = catName;
                img.setImageResource(R.drawable.judy);
                officialName.setText(catName + " Chu");
                mFeedBtn.setText(catName);

            }
            //img.setImageResource(R.drawable.boxer);
        }

        mFeedBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentOfficial.equals("Barbara")) {
                    Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                    Intent secondIntent = new Intent(getBaseContext(), VoteView.class);
                    startService(sendIntent);
                    startActivity(secondIntent);

//                    sendIntent.putExtra("OFFICIAL_NAME", currentOfficial);
//                    startService(sendIntent);

                } else if (currentOfficial.equals("Dianne")) {
                    Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                    Intent secondIntent = new Intent(getBaseContext(), VoteView.class);
                    startActivity(secondIntent);
                    startService(sendIntent);

//                    sendIntent.putExtra("OFFICIAL_NAME", currentOfficial);
//                    startService(sendIntent);


                } else {
                    Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
                    Intent secondIntent = new Intent(getBaseContext(), VoteView.class);
                    startActivity(secondIntent);
                    startService(sendIntent);

//
//                    sendIntent.putExtra("OFFICIAL_NAME", currentOfficial);
//                    startService(sendIntent);


                }

//                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                startService(sendIntent);


            }
        });
    }
}
