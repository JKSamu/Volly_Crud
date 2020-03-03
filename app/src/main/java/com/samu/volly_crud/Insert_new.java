package com.samu.volly_crud;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class Insert_new extends AppCompatActivity {

    //private String insertDataURL = "http://192.168.56.1/android/crudmysql/volleycrud/addProduct.php";


    private String insertDataURL = "http://10.0.2.2/volleycrud/addProduct.php";

    private static String TAG = MainActivity.class.getSimpleName();
    private Button insertData, showData;

    private EditText editId, editName,editprice;

    // Progress dialogs
    private ProgressDialog pDialog;

    private TextView txtResponse;

    RequestQueue requestQueue;

    // temporary string to show the parsed response
    private String jsonResponse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inset_new);







        insertData = (Button) findViewById(R.id.button);



        editName = (EditText) findViewById(R.id.editName);
        editprice = (EditText) findViewById(R.id.editprice);


        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Please wait...");
        pDialog.setCancelable(false);




        insertData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // insertDataOnline();

               // insertdatanewway2();
                confirmadddata();
            }
        });







    }//smb


    private void showpDialog() {
        if (!pDialog.isShowing()) pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing()) pDialog.dismiss();
    }




    //---------insert rnd----
    public void insertdatanewway2() {

        showpDialog();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, insertDataURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //progressBar.setVisibility(View.GONE);
                editName.setText("");
                editprice.setText("");
                Toast.makeText(getApplicationContext(), "Data Inserted Successfully", Toast.LENGTH_SHORT).show();


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parameters = new HashMap<String, String>();

                parameters.put("p_name", editName.getText().toString());
                parameters.put("p_price", editprice.getText().toString());
                hidepDialog();

                return parameters;
            }
        };

        AppSingleton1.getInstance(this).addToRequestQueue(stringRequest,TAG);

    }


//---------------------------------

    private void confirmadddata(){

        AlertDialog.Builder
         alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to Add Data?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        insertdatanewway2();
                        startActivity(new Intent(Insert_new.this,ViewproductList_2.class));
                    }
                });

        alertDialogBuilder.setNegativeButton("No",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {

                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }



}//lmb
