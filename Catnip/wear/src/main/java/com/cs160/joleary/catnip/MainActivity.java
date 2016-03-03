package com.cs160.joleary.catnip;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.wearable.view.GridViewPager;
import android.support.wearable.view.WatchViewStub;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.GridView;

public class MainActivity extends Activity {

    private TextView mTextView;
    private Button mFeedBtn;
    private ImageView img;
    private TextView officialName;
    private TextView detailedView;
    String currentOfficial;
    GridView pager;


    public String[] officials = {"Barbara Boxer", "Dianne Feinstein", "Judy Chu"};
    public int[] images = {R.drawable.boxer, R.drawable.dianna, R.drawable.judy};
    public String[] parties = {"Dem", "Dem", "Dem"}; //added new


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//
//        mFeedBtn = (Button) findViewById(R.id.feed_btn);
//        img = (ImageView) findViewById(R.id.imageViewWatch);
//        detailedView = (TextView) findViewById(R.id.textView2);
//        officialName = (TextView)findViewById(R.id.textView);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();


        //new stuff
        final GridViewPager pager = (GridViewPager) findViewById(R.id.gridviewpager);
        //pager.setAdapter(new MyGridViewPager(this, officials, parties, images));


        //new stuff end

        if (extras != null) {
            String catName = extras.getString("CAT_NAME");
            //mFeedBtn.setText("Feed " + catName);
            if (catName.equals("Barbara")) {
                pager.setAdapter(new MyGridViewPager(this, officials, parties, images));
            } else if (catName.equals("Dianne")) {
                pager.setAdapter(new MyGridViewPager(this, officials, parties, images));
            } else {
                pager.setAdapter(new MyGridViewPager(this, officials, parties, images));
            }
        }







//        if (extras != null) {
//            String catName = extras.getString("CAT_NAME");
////            mFeedBtn.setText("Feed " + catName);
//
//            //i can send an intent here where it can lead me to the swipable page
//
//
//            if (catName.equals("Barbara")) {
//                currentOfficial = catName;
//                img.setImageResource(R.drawable.boxer);
//                officialName.setText(catName + " Boxer");
//                detailedView.setText("Dem");
//                mFeedBtn.setText(catName);
//
//
//            } else if (catName.equals("Dianne")) {
//                currentOfficial = catName;
//                img.setImageResource(R.drawable.dianna); //this does not change to dianna picture...why???
//                officialName.setText(catName + " Feinstein");
//                detailedView.setText("Dem");
//                mFeedBtn.setText(catName);
//
//            } else {
//                currentOfficial = catName;
//                img.setImageResource(R.drawable.judy);
//                officialName.setText(catName + " Chu");
//                detailedView.setText("Dem");
//                mFeedBtn.setText(catName);
//
//            }
//            //img.setImageResource(R.drawable.boxer);
//        }

//        mFeedBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (currentOfficial.equals("Barbara")) {
//                    Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                    Intent secondIntent = new Intent(getBaseContext(), VoteView.class);
//                    startService(sendIntent);
//                    startActivity(secondIntent);
//
////                    sendIntent.putExtra("OFFICIAL_NAME", currentOfficial);
////                    startService(sendIntent);
//
//                } else if (currentOfficial.equals("Dianne")) {
//                    Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                    Intent secondIntent = new Intent(getBaseContext(), VoteView.class);
//                    startActivity(secondIntent);
//                    startService(sendIntent);
//
////                    sendIntent.putExtra("OFFICIAL_NAME", currentOfficial);
////                    startService(sendIntent);
//
//
//                } else {
//                    Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
//                    Intent secondIntent = new Intent(getBaseContext(), VoteView.class);
//                    startActivity(secondIntent);
//                    startService(sendIntent);
//
////
////                    sendIntent.putExtra("OFFICIAL_NAME", currentOfficial);
////                    startService(sendIntent);
//
//
//                }
//
////                Intent sendIntent = new Intent(getBaseContext(), WatchToPhoneService.class);
////                startService(sendIntent);
//
//
//            }
//        });
    }



    public void watchVote (View v) {
        Intent secondIntent = new Intent(getBaseContext(), VoteView.class);
        startActivity(secondIntent);
    }
}
