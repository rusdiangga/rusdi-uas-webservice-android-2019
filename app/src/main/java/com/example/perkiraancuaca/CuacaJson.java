package com.example.perkiraancuaca;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CuacaJson extends AppCompatActivity {
    private TextView textHasilJSON;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json);

        mQueue = Volley.newRequestQueue(this);
        textHasilJSON = findViewById(R.id.textJSON);
        Button tombolJson = findViewById(R.id.btnJSON);

        tombolJson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uraiJSON();
            }
        });
    }
    private void uraiJSON(){
        String url = "https://api.myjson.com/bins/1anvos";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("cuaca");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject cuaca = jsonArray.getJSONObject(i);

                                String namaKota = cuaca.getString("kota");
                                String keadaanSiang = cuaca.getString("siang");
                                String keadaanMalam = cuaca.getString("malam");
                                String keadaanSekarang = cuaca.getString("dini_hari");
                                String suhuKota = cuaca.getString("suhu");
                                String kelembapanKota = cuaca.getString("kelembapan");


                                textHasilJSON.append(namaKota + ", " + keadaanSiang + ", "
                                        + keadaanMalam + ", " + keadaanSekarang + ", " + suhuKota + ", " +
                                        kelembapanKota + "\n\n");

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(getApplicationContext(), "Error1", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(getApplicationContext(), "Error2", Toast.LENGTH_SHORT).show();
            }
        });

        mQueue.add(request);
    }
}
