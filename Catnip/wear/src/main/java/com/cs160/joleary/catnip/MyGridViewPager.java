package com.cs160.joleary.catnip;

import android.support.wearable.view.GridPagerAdapter;
import android.content.Context;
import android.graphics.pdf.PdfRenderer.Page;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by tototones24 on 3/3/16.
 */
public class MyGridViewPager extends GridPagerAdapter {
    private Context context;
    String[] names;
    String[] parties;
    int[] images;
    Page[][] pages;

    public MyGridViewPager(Context contxt, String[] name, String[] party, int[] image ) {
        context = contxt;
        names = name;
        parties = party;
        images = image;
        pages = new Page[names.length][1];
        fillPages();
    }

    public void fillPages() {
        for (int i = 0; i < names.length; i++) {
            Page page = new Page();
            page.naming = names[i];
            page.parties = parties[i];
            page.imagess = images[i];
            pages[i][0] = page;
        }
    }

    public class Page {
        String naming;
        String parties;
        int imagess;
    }

    @Override
    public int getRowCount() {
        return pages.length;

    }

    @Override
    public int getColumnCount(int i) {
        return pages[i].length;
    }

    public class ViewHolder {
        ImageView imgView;
        TextView textName;
        TextView party;

    }

    @Override
    public Object instantiateItem(ViewGroup viewGroup, int row, int column) {
        View v = View.inflate(context, R.layout.page_model, null);

        ViewHolder holder = new ViewHolder();
        holder.imgView = (ImageView)v.findViewById(R.id.modelPic);
        holder.textName = (TextView)v.findViewById(R.id.modelName);
        holder.party = (TextView)v.findViewById(R.id.modelParty);


        holder.imgView.setImageResource(pages[row][column].imagess);
        holder.textName.setText(pages[row][column].naming);
        holder.party.setText(pages[row][column].parties);

        viewGroup.addView(v);

        return v;
    }

    @Override
    public void destroyItem(ViewGroup viewGroup, int i, int i1, Object o) {
        viewGroup.removeView((View)o);
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view.equals(o);
    }

}


