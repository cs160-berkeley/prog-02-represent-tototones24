package com.cs160.joleary.catnip;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by tototones24 on 2/27/16.
 */
public class OfficialList extends ListActivity{

//    ListView list_view;//delete if errors
    public String[] officials = {"Barbara Boxer", "Dianne Feinstein", "Judy Chu"};
    public int[] images = {R.drawable.boxer, R.drawable.dianna, R.drawable.judy};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.official_list_view);

        ListView list_view;
        list_view = (ListView) findViewById(android.R.id.list);//this no longer errors YESSS!


        ListAdapter adapter = new ListAdapter(this, officials, images);
        list_view.setAdapter(adapter);

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

                intent.putExtra("corresponding_text", officials[pos]);
                startActivity(intent);
            }
        });
    }





}
