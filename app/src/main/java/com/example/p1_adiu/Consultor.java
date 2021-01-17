package com.example.p1_adiu;

import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class Consultor extends Thread{
    private String JSON_URL;
    private MainActivity main;
    RequestQueue requestQueue;

    public Consultor(MainActivity main, String JSON_URL, RequestQueue requestQueue){
        this.JSON_URL = JSON_URL;
        this.main = main;
        this.requestQueue = requestQueue;
    }

    @Override
    public void run(){
        System.out.println("Objeto consultor");
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        int p1 = Integer.parseInt((response.substring(response.indexOf(":") + 1, response.indexOf("}"))));
                        System.out.println("Datos obtenidos (primera consulta) = " + p1);

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(main.getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                        System.out.println("Error en la consulta");
                    }
                });


        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}
