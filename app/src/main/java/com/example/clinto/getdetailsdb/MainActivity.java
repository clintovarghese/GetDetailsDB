package com.example.clinto.getdetailsdb;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    TextView t1, t2,t3,t4;
    int id =2;
    public static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        t1 = (TextView) findViewById(R.id.txt_name);
        t2 = (TextView) findViewById(R.id.txt_age);
        t3=(TextView)findViewById(R.id.txt_mobno);
        t4=(TextView)findViewById(R.id.txt_place);

        String url = "http://192.168.1.4/registration/get.php";
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d(TAG, "onResponse: " + response);
                Toast.makeText(MainActivity.this, "Response" + response, Toast.LENGTH_SHORT).show();


                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i=0;i<=jsonArray.length();i++){
                        JSONObject jsonObject= jsonArray.getJSONObject(i);
                        String name=jsonObject.getString("name");
                        String age=jsonObject.getString("age");
                        String mobileno=jsonObject.getString("mobileno");
                        String place=jsonObject.getString("place");
                        t1.setText(name);
                        t2.setText(age);
                        t3.setText(mobileno);
                        t4.setText(place);


                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d(TAG, "onErrorResponse: " + error);
            }
        }) {

            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<String, String>();
                params.put("id", String.valueOf(id));
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        requestQueue.add(stringRequest);
    }
}
