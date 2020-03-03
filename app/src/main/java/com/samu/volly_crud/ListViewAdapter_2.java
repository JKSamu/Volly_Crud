package com.samu.volly_crud;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;



public class ListViewAdapter_2 extends ArrayAdapter<Product_2> {

    //the hero list that will be displayed
    private List<Product_2> product2List;

    //the context object
    private Context mCtx;

    //here we are getting the herolist and context
    //so while creating the object of this adapter class we need to give herolist and context
    public ListViewAdapter_2(List<Product_2> product2List, Context mCtx) {
        super(mCtx, R.layout.list_items_2, product2List);
        this.product2List = product2List;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
               LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.list_items_2, null, true);

        //getting text views
        TextView textid = listViewItem.findViewById(R.id.textViewid);
        TextView textViewName = listViewItem.findViewById(R.id.textViewName);
        TextView textViewprice = listViewItem.findViewById(R.id.textViewprice);

        //Getting the pro for the specified position
        Product_2 pro = product2List.get(position);
        textViewName.setText(pro.getName());
        textViewprice.setText(pro.getPrice());
        //returning the listitem
        return listViewItem;
    }
}
