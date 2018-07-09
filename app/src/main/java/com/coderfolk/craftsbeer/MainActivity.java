package com.coderfolk.craftsbeer;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private static final String JSON_URL = "http://starlord.hackerearth.com/beercraft";
    ListView beerlistview, cartlistview;
    List<ModelClass> beerlist;
    static List<ModelClass> cartlist2;
    EditText searchbar;
    BeerListAdapter adapter;
    LinearLayout layouthome, layoutcart;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    layouthome = (LinearLayout) findViewById(R.id.homelayout);
                    layoutcart = (LinearLayout) findViewById(R.id.cartlayout);
                    layouthome.setVisibility(View.VISIBLE);
                    layoutcart.setVisibility(View.GONE);
                    return true;
                case R.id.navigation_cart:
                    layouthome = (LinearLayout) findViewById(R.id.homelayout);
                    layoutcart = (LinearLayout) findViewById(R.id.cartlayout);
                    layouthome.setVisibility(View.GONE);
                    layoutcart.setVisibility(View.VISIBLE);
                    final ProgressBar progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
                    progressBar2.setVisibility(View.VISIBLE);
                    cartlist2 = BeerListAdapter.checkoutlist;
                    cartlistview = (ListView) findViewById(R.id.listView2);
                    CartListAdapter cartListAdapter = new CartListAdapter(getApplicationContext(), cartlist2);
                    cartlistview.setAdapter(cartListAdapter);
                    progressBar2.setVisibility(View.GONE);
                    //   mTextMessage.setText(R.string.title_dashboard);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        beerlistview = (ListView) findViewById(R.id.listView);
        beerlist = new ArrayList<>();
        searchbar = (EditText) findViewById(R.id.search);
        // addtocart = (ImageButton) findViewById(R.id.addbutton);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        loadBeerList();
        beerlistview.setTextFilterEnabled(true);
//        searchbar.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//                Log.d("Text",charSequence.toString());
//               MainActivity.this.adapter.getFilter().filter(charSequence);
//               MainActivity.this.adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void afterTextChanged(Editable editable) {
//
//            }
//        });
    }

    private void loadBeerList() {

        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        beerlistview.setVisibility(View.GONE);
        searchbar.setVisibility(View.GONE);
        StringRequest getjson = new StringRequest(Request.Method.GET, JSON_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    //getting the whole json object from the response
                    // JSONObject obj = new JSONObject(response);

                    //we have the array named hero inside the object
                    //so here we are getting that json array
                    JSONArray beerArray = new JSONArray(response);

                    //now looping through all the elements of the json array
                    for (int i = 0; i < beerArray.length(); i++) {
                        //getting the json object of the particular index inside the array
                        JSONObject beerObject = beerArray.getJSONObject(i);
                        ModelClass modelClassobj = new ModelClass(beerObject.getString("abv"), beerObject.getString("ibu"),
                                beerObject.getInt("id"), beerObject.getString("name"),
                                beerObject.getString("style"), (float) beerObject.getDouble("ounces")
                        );
                        //adding the hero to herolist
                        beerlist.add(modelClassobj);
                    }

                    //creating custom adapter object
                    adapter = new BeerListAdapter(getApplicationContext(), beerlist);

                    //adding the adapter to listview
                    beerlistview.setAdapter(adapter);
                    progressBar.setVisibility(View.GONE);
                    beerlistview.setVisibility(View.VISIBLE);
                    searchbar.setVisibility(View.VISIBLE);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                beerlistview.setVisibility(View.GONE);
                TextView errortext = (TextView) findViewById(R.id.error);
                errortext.setVisibility(View.VISIBLE);
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(getjson);
    }

}
