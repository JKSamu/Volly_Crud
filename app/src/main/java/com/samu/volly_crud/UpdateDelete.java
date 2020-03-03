package com.samu.volly_crud;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class UpdateDelete extends AppCompatActivity {


    private static String TAG = MainActivity.class.getSimpleName();
    EditText PID, PNAME, PRICE;

    Button bedit, bdelete;


    // Progress dialogs
    private ProgressDialog pDialog;

        ProgressDialog PD;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);


        PID = findViewById(R.id.edit_id);
        PNAME = findViewById(R.id.edit_name);
        PRICE = findViewById(R.id.edit_price);

        bedit = findViewById(R.id.btn_edit);
        bdelete = findViewById(R.id.btn_delete);

//--------------------receiive data by getExtra-----------------------------------------
        Intent intent = getIntent();

        int pidd = intent.getIntExtra("id", 0);
        String tnamee = intent.getStringExtra("p_name");
        String shortd = intent.getStringExtra("p_price");

        PNAME.setText(tnamee);
        PRICE.setText(shortd);
        PID.setText(String.valueOf(pidd));


//-------------------------------------------------------------
        PD = new ProgressDialog(this);
        PD.setMessage("please wait.....");
        PD.setCancelable(false);








//--------edit------

        bedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Update_data();

                confirmeditdata();
            }
        });

//--------edit------


        //-------delete--------
        bdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //deletedata();

                //DELETEDATA();
                confirmDeleteEmployee();
            }
        });


        //-------delete--------

    } //slb

//------------------------

    public void delete_data() {


//String myurl = "http://192.168.56.1/android/crudmysql/volleycrud/deleteProduct.php?";
      //  String myurl = "http://192.168.56.1/android/crudmysql/volleycrud/deleteProduct.php?";

        String myurl = "http://10.0.2.2/volleycrud/deleteProduct.php?";



        //if everything is fine
        StringRequest StringRequest = new StringRequest(Request.Method.POST, myurl,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                PID.setText("");
                PNAME.setText("");
                PRICE.setText("");
                Toast.makeText(getApplicationContext(), "Data DELETE Successfully", Toast.LENGTH_SHORT).show();


            }

        },new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<>();
                map.put("p_id",PID.getText().toString());
                return map;
            }
        };
        AppSingleton1.getInstance(this).addToRequestQueue(StringRequest,TAG);
    }
       // AppSingleton1.getInstance(this).addToRequestQueue(StringRequest,TAG);
        //OR


//-------------------oterway



    private void confirmDeleteEmployee(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to delete this employee?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        delete_data();
                        startActivity(new Intent(UpdateDelete.this,ViewproductList_2.class));
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



        //---------OHER






//--------------------------------EDIT




    private void showpDialog() {
        if (!pDialog.isShowing()) pDialog.show();
    }

    private void hidepDialog() {
        if (pDialog.isShowing()) pDialog.dismiss();
    }




    //-------------------EDIT---------------------


private void Update_data()
{
    //pd.setMessage("Update Data");
   // pd.setCancelable(false);
    //pd.show();

   // String updateDataURL = "http://192.168.56.1/android/crudmysql/volleycrud/updateProduct.php";

    String updateDataURL = "http://10.0.2.2/volleycrud/updateProduct.php";




    StringRequest updateReq = new StringRequest(Request.Method.POST, updateDataURL,
            new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    PNAME.setText("");
                    PRICE.setText("");
                    Toast.makeText(getApplicationContext(), "Data UPDATE Successfully", Toast.LENGTH_SHORT).show();
                    //pd.cancel();
                    try {
                        JSONObject res = new JSONObject(response);
                        //Toast.makeText(InsertData.this, "pesan : "+   res.getString("message") , Toast.LENGTH_SHORT).show();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    //startActivity( new Intent(InsertData.this,MainActivity.class));
                }
            },
            new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {


                }
            }){
        @Override
        protected Map<String, String> getParams() throws AuthFailureError {
            Map<String,String> map = new HashMap<>();
            map.put("p_id",PID.getText().toString());
            map.put("p_name",PNAME.getText().toString());
            map.put("p_price",PRICE.getText().toString());


            return map;
        }
    };

    AppSingleton1.getInstance(this).addToRequestQueue(updateReq,TAG);
}


    //----------CONFIRM EDIT---------------


    private void confirmeditdata(){
       AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage("Are you sure you want to update this Data?");

        alertDialogBuilder.setPositiveButton("Yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Update_data(); //edit method call
                        startActivity(new Intent(UpdateDelete.this,ViewproductList_2.class));
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


    //------


}//mlb
