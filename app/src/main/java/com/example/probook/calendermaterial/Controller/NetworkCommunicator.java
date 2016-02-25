package com.example.probook.calendermaterial.Controller;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.probook.calendermaterial.R;

import org.json.JSONArray;

/**
 * Created by probook on 2/25/2016.
 */
public class NetworkCommunicator {
    private static final String TAG = "NetworkCommunicator";

    private Context context;

    public NetworkCommunicator(Context context) {
        this.context = context;
    }


    public void getDataFromServer(){
        fetchData();
    }
    // Operations:
    // 1. Fetch Data
    // 2. Clear Local db
    // 3. Update local db

    // 1. Fetch Data
    private void fetchData(){
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "http://nodejst-maucalender.rhcloud.com/schedule/all";

        JsonArrayRequest req = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                if (response.length() > 0){
                    // Clear db and update data async
                    Log.d(TAG, "Received JSON Response " + response.toString());
                } else {
                    Log.d(TAG, "JSONArray received is empty");
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    Toast.makeText(context, context.getString(R.string.error_network_timeout), Toast.LENGTH_LONG).show();
                } else if (error instanceof AuthFailureError) {
                    //TODO
                } else if (error instanceof ServerError) {
                    //TODO
                } else if (error instanceof NetworkError) {
                    //TODO
                } else if (error instanceof ParseError) {
                    //TODO
                }
            }
        });

        requestQueue.add(req);
    }

    // 2. Clear Local db
    // 3. Update local db
    private void updateLocaldb(){

    }
}
