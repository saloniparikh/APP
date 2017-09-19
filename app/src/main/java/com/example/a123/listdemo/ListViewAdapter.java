package com.example.a123.listdemo;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 123 on 9/19/2017.
 */

class ListViewAdapter extends ArrayAdapter<Demo> {
    private List<Demo> heroList;


    private Context mCtx;

    //so while creating the object of this adapter class we need to give demolist and context
    public ListViewAdapter(List<Demo> heroList, Context mCtx) {
        super(mCtx, R.layout.list_items, heroList);
        this.heroList = heroList;
        this.mCtx = mCtx;
    }

//
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_items, null, true);

        //getting text views
        TextView txtno = (TextView) listViewItem.findViewById(R.id.no);
        TextView txtname =(TextView) listViewItem.findViewById(R.id.name);

        //Getting the hero for the specified position
        Demo demo = heroList.get(position);

        //setting hero values to textviews
        txtno.setText(demo.getlelveno());
        txtname.setText(demo.getlevelname());

        //returning the listitem
        return listViewItem;    }

}
