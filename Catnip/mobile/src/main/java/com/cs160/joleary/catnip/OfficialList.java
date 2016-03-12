package com.cs160.joleary.catnip;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by tototones24 on 2/27/16.
 */
public class OfficialList extends ListActivity{

//    ListView list_view;//delete if errors
    public String[] officials = {"Barbara Boxer", "Dianne Feinstein", "Judy Chu"};
    public int[] images = {R.drawable.boxer, R.drawable.dianna, R.drawable.judy};

    public String[] actualNames;
    String[] websites;
    String[] emails;
    String[] twitter;
    String[] endDateArray;
    String urlArgument = "http://congress.api.sunlightfoundation.com/legislators/locate?";
    String longitude;
    String latitude;
    String zipCode;
    public URL actualUrl;
    HttpURLConnection urlConnection;
    URL url;
    String jsonInfo;
    //these two arrays will be used for picasso
    String[] picUrls;
    String[] bioGuideId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.official_list_view);

        ListView list_view;
//        list_view = (ListView) findViewById(android.R.id.list);//this no longer errors YESSS!
//        //ListAdapter adapter = new ListAdapter(this, officials, images);
//        ListAdapter adapter = new ListAdapter(this, actualNames, images);
//        list_view.setAdapter(adapter);


        /**
         *   this is needed in order to get information from the sunlight api
         */

        Bundle extras = getIntent().getExtras();
        longitude = extras.getString("lon");
        latitude = extras.getString("lat");
        zipCode = extras.getString("zip");
        if (zipCode != null) {
            urlArgument = urlArgument + "zip=" + zipCode + "&apikey=532097779e8a4b2e91612edaaea2ede3";
        } else {
            urlArgument = urlArgument + "latitude=" + latitude + "&longitude=" + longitude + "&apikey=532097779e8a4b2e91612edaaea2ede3";
        }

        RetrieveFeedTask task = (RetrieveFeedTask)new RetrieveFeedTask().execute(urlArgument);
        try {
            task.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        fillInfo(jsonInfo);


        fillInPics(bioGuideId);

        list_view = (ListView) findViewById(android.R.id.list);//this no longer errors YESSS!
        //ListAdapter adapter = new ListAdapter(this, officials, images);
        ListAdapter adapter = new ListAdapter(this, actualNames, images, picUrls);
        list_view.setAdapter(adapter);



        //json info now has the information that we want so we can go ahead and parse through it now.



        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int pos, long id) {
//                Toast.makeText(getApplicationContext(), officials[pos], Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getBaseContext(), CongressionalView.class);
//                startActivity(intent);

//                if (pos == 0) {
//                    //Toast.makeText(getApplicationContext(), officials[pos], Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(getBaseContext(), CongressionalView.class);
//                    startActivity(intent);
//                } else if (pos == 1) {
//                    Intent intent = new Intent(getBaseContext(), Dianne.class);
//                    //
////                    intent.putExtra("corresponding_text", officials[pos]);
//                    startActivity(intent);
//                } else {
//                    Intent intent = new Intent(getBaseContext(), Judy.class);
//                    startActivity(intent);
//                }




                Intent intent = new Intent(getBaseContext(), CongressionalView.class);

                //intent.putExtra("corresponding_text", officials[pos]);
                intent.putExtra("corresponding_text", actualNames[pos]);

                //send the twitter tag, email, and website, end date
                intent.putExtra("corresponding_email", emails[pos]);
                intent.putExtra("corresponding_twitter", twitter[pos]);
                intent.putExtra("corresponding_website", websites[pos]);
                intent.putExtra("corresponding_enddate", endDateArray[pos]);
                intent.putExtra("corresponding_picurl", picUrls[pos]);
                startActivity(intent);
            }
        });
    }


    /**
     * this method will be in charge of filling in the array with all the right names.
     * @param jsonString
     */
    public void fillInfo(String jsonString) {
        try {
            JSONObject object = (JSONObject) new JSONTokener(jsonString).nextValue();
            JSONArray  array = object.getJSONArray("results");
            actualNames = new String[array.length()];
            websites = new String[array.length()];
            emails = new String[array.length()];
            twitter = new String[array.length()];
            endDateArray = new String[array.length()];
            bioGuideId = new String[array.length()];

            for (int i = 0; i < array.length(); i++) {
                JSONObject subObject = array.getJSONObject(i);
                String name = subObject.getString("first_name") + " " + subObject.getString("last_name");
                actualNames[i] = name;

                //i will now get the information for the emails, websites, and twitter tag
                String webSiteName = subObject.getString("website");
                websites[i] = webSiteName;

                String emailName = subObject.getString("oc_email");
                emails[i] = emailName;

                String twitterTag = subObject.getString("twitter_id");
                twitter[i] = twitterTag;

                String finalDate = subObject.getString("term_end");
                endDateArray[i] = finalDate;

                String bio = subObject.getString("bioguide_id");
                bioGuideId[i] = bio;
            }
        } catch (Exception e) {
            Toast.makeText(OfficialList.this, "Errored Somewhere", Toast.LENGTH_SHORT).show();
            return;
        }
    }


    public void fillInPics(String[] bioIds) {
            picUrls = new String[bioIds.length];
        for (int i = 0; i < bioIds.length; i++) {
            String urlPic = "https://theunitedstates.io/images/congress/225x275/" + bioGuideId[i] + ".jpg";
            picUrls[i] = urlPic;
        }
    }




    class RetrieveFeedTask extends AsyncTask<String, Void, String> {

        private Exception exception;

        protected String doInBackground(String... urls) {
            try {
                url = new URL(urlArgument);
                urlConnection = (HttpURLConnection)url.openConnection();
                //try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                    jsonInfo = stringBuilder.toString();
                    //return stringBuilder.toString();
                return jsonInfo;

            }
            catch(Exception e) {
                Log.e("ERROR", e.getMessage(), e);
                return null;
            }

        }






        protected void onPostExecute(String feed) {
            // TODO: check this.exception
            // TODO: do something with the feed
        }
    }




}
