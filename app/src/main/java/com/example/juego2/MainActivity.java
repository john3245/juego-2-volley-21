package com.example.juego2;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;


import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected RequestQueue fRequestQueue;
    private VolleyS volley;
    Integer suma = 0;





    String url= "http://ramiro174.com/api/numero";
    String manda= "http://ramiro174.com/api/enviar/numero";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        volley= VolleyS.getInstance(this.getApplicationContext());

        fRequestQueue=volley.getRequestQueue();

    }
    @Override
    public void onClick(View view) {
        obtenerNumero();
    }
    private void obtenerNumero(){
        JsonObjectRequest request =  new JsonObjectRequest(Request.Method.GET, url,null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TextView text = findViewById(R.id.text);
                try{
                    Integer num = response.getInt("numero");
                    suma += num;


                    Toast.makeText(MainActivity.this, suma.toString(), Toast.LENGTH_SHORT).show();


                    if(suma==21)
                    {Toast.makeText(MainActivity.this, "GANASTE!!!!!!!!!!!!!!!", Toast.LENGTH_SHORT).show();
                        text.setText("0");
                        suma=0;
                        return;

                    }
                    if(suma > 21){
                        Toast.makeText(MainActivity.this, "Perdiste!", Toast.LENGTH_SHORT).show();
                        text.setText("0");
                        suma = 0;
                        return;
                    }

                    text.setText(suma.toString());

                }catch (JSONException e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse: ", error.toString());
            }
        });
        fRequestQueue.add(request);

    }

    public void miclick(View view) {

        mandarnumero();






    }

    private void mandarnumero() {

        final JSONObject data = new JSONObject();
        try {
            data.put("nombre", "Jonathan");
            data.put("numero", suma);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, manda, data, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(MainActivity.this, "se envio datos", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("OnErrorResponse1: ", error.toString());
            }
        });
        fRequestQueue.add(jsonObjectRequest);
    }


    }

