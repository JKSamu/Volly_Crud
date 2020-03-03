package com.samu.volly_crud;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewproductList_2 extends AppCompatActivity {



  //  private static final String JSON_URL = "http://192.168.56.1/android/crudmysql/volleycrud/getAllProduct.php";


    private static final String JSON_URL = "http://10.0.2.2/volleycrud/getAllProduct.php";



    //listview object
    ListView listView;

    //the hero list where we will store all the hero objects after parsing json
    List<Product_2> productList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewlist);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginInsert = new Intent(ViewproductList_2.this, Insert_new.class);
                startActivity(loginInsert);
            }
        });

        //initializing listview and hero list
        listView = (ListView) findViewById(R.id.listview_01);
        productList = new ArrayList<>();

        //this method will fetch and parse the data
        loadHeroList();


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                // Toast.makeText(getApplicationContext(),"HI" +getTitle(),Toast.LENGTH_SHORT).show();


                Intent intent = new Intent(ViewproductList_2.this, UpdateDelete.class);

                int pid=productList.get(position).getId();

                String proname=productList.get(position).getName();
                String proprice=productList.get(position).getPrice();

                intent.putExtra("id",pid);
                intent.putExtra("p_name",proname);
                intent.putExtra("p_price",proprice);


                startActivity(intent);




            }
        });

    }//slb

    private void loadHeroList() {
        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);

        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);


                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);

                            //we have the array named hero inside the object
                            //so here we are getting that json array


                            // JSONArray productArray = obj.getJSONArray("heroes");

                            JSONArray productArray = obj.getJSONArray("result");


                            //now looping through all the elements of the json array
                            for (int i = 0; i < productArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject productObject = productArray.getJSONObject(i);

                                //creating a proo2 object and giving them the values from json object
                                // Product_2 proo2 = new Product_2(productObject.getString("name"), productObject.getString("imageurl"));

                                //change here aac database column
                              // Product_2 proo2 = new Product_2(productObject.getString("name"), productObject.getString("price"));
                                Product_2 proo2 = new Product_2(productObject.getInt("id"),productObject.getString("name"), productObject.getString("price"));


                                //adding the proo2 to herolist
                                productList.add(proo2);
                            }

                            //creating custom adapter object
                            ListViewAdapter_2 adapter = new ListViewAdapter_2(productList, getApplicationContext());

                            //adding the adapter to listview
                            listView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }


    //--------------------------------------











}