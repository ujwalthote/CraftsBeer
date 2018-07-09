package com.coderfolk.craftsbeer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class CartListAdapter extends ArrayAdapter<ModelClass> {
    Context context;
    List<ModelClass> cartlist=new ArrayList<>();
    public CartListAdapter(@NonNull Context context, List<ModelClass> cartlist) {
        super(context, R.layout.cart_list, cartlist);
        this.context=context;
        this.cartlist=cartlist;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View listViewItem = inflater.inflate(R.layout.cart_list, null, true);
        TextView abv = listViewItem.findViewById(R.id.abv2);
        TextView ibu = listViewItem.findViewById(R.id.ibu2);
        TextView uid = listViewItem.findViewById(R.id.uid2);
        TextView name = listViewItem.findViewById(R.id.name2);
        TextView style = listViewItem.findViewById(R.id.style2);
        TextView ounces = listViewItem.findViewById(R.id.ounces2);
        ModelClass beerdetails = cartlist.get(position);
        abv.setText("Alcoholic content: "+beerdetails.getAbv());
        ibu.setText("IBU: "+beerdetails.getIbu());
        uid.setText("Id: "+String.valueOf(beerdetails.getId()));
        name.setText(beerdetails.getName());
        style.setText("Style: "+beerdetails.getStyle());
        ounces.setText("Ounces: "+String.valueOf(beerdetails.getOunces()));
        return listViewItem;
    }
}
