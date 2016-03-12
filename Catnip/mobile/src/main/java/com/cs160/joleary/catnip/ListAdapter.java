package com.cs160.joleary.catnip;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

/**
 * Created by tototones24 on 2/29/16.
 */
public class ListAdapter extends ArrayAdapter<String> {
     Context context;
    String[] catNames;
    int[] catPhotos;
    LayoutInflater inflater;
    String[] picassoPics;

    public ListAdapter(Context context, String[] catNames, int[] catPhotos, String[] picStrings) {
        super(context, R.layout.model, catNames);
        this.context = context;
        this.catNames = catNames;
        this.catPhotos = catPhotos;
        this.picassoPics = picStrings;
    }

    public class ViewHolder {
        TextView name;
        ImageView image;
    }

    public View getView(int position, View convertView, ViewGroup vgroup) {
        if (convertView == null) {
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.model, null);
        }

        final ViewHolder holder = new ViewHolder();

        holder.name = (TextView) convertView.findViewById(R.id.textView6);
        holder.image = (ImageView) convertView.findViewById(R.id.imageView2);

        //holder.image.setImageResource(catPhotos[position]);
        Picasso.with(context).load(picassoPics[position]).into(holder.image);


        holder.name.setText(catNames[position]);

        //adding stuff from picasso
//        String url = getItem(position);


        return convertView;
    }


}
