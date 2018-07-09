package com.coderfolk.craftsbeer;

import android.content.Context;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BeerListAdapter extends ArrayAdapter<ModelClass> {

    private List<ModelClass> beerlist;
    static List<ModelClass> checkoutlist=new ArrayList<>();
    private Context context;
    public BeerListAdapter(@NonNull Context context, List<ModelClass> beerlist) {
        super(context, R.layout.list_beer, beerlist);
        this.context=context;
        this.beerlist=beerlist;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View listViewItem = inflater.inflate(R.layout.list_beer, null, true);
        TextView abv = listViewItem.findViewById(R.id.abv);
        TextView ibu = listViewItem.findViewById(R.id.ibu);
        TextView uid = listViewItem.findViewById(R.id.uid);
        TextView name = listViewItem.findViewById(R.id.name);
        TextView style = listViewItem.findViewById(R.id.style);
        TextView ounces = listViewItem.findViewById(R.id.ounces);
        ModelClass beerdetails = beerlist.get(position);
        abv.setText("Alcoholic content: "+beerdetails.getAbv());
        ibu.setText(beerdetails.getIbu());
        uid.setText(String.valueOf(beerdetails.getId()));
        name.setText(beerdetails.getName());
        style.setText(beerdetails.getStyle());
        ounces.setText(String.valueOf(beerdetails.getOunces()));
        ImageButton addtocart=listViewItem.findViewById(R.id.addbutton);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkoutlist.add(beerlist.get(position));
                Toast.makeText(context, "Added to cart", Toast.LENGTH_SHORT).show();
            }
        });

        return listViewItem;
    }

}
