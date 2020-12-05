package com.jatin.volleyjsonreq;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView txtData;
    private Button btnGet;
    private RequestQueue requestQueue;
    private final String URL="https://jsonplaceholder.typicode.com/todos/3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();


        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData();
            }
        });

    }

    private void showData() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                StringBuilder sb = new StringBuilder();
                try {
                    sb.append("userId: "+response.getString("userId"));
                    sb.append("\nid: "+response.getString("id"));
                    sb.append("\ntitle: "+response.getString("title"));
                    sb.append("\ncompleted: "+response.getString("completed"));
                    sb.append("\n------------------\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                txtData.setText(sb);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Error code
                        txtData.setText("Unable to get: "+error.getMessage());
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    private void initViews() {

        txtData = findViewById(R.id.txtData);
        btnGet = findViewById(R.id.btnGet);
        requestQueue = Volley.newRequestQueue(this);


    }
}