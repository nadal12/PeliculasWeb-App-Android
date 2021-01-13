package com.example.p1_adiu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View mainButton = (Button) findViewById(R.id.button);
        mainButton.setOnClickListener(v -> {
            getData();
        });
    }

    private void getData() {
        Toast.makeText(MainActivity.this, "Botón pulsado", Toast.LENGTH_LONG).show();

    }
    /**

    private static final String TAG = "MiActivity";

//...
    JsonObjectRequest jsObjectRequest;

    String sURL="http://www.example.com";
    Queue requestQueue= Volley.newRequestQueue(this);

// Nueva petición JSONObject
    jsObjectRequest =
            new JsonObjectRequest(
            DownloadManager.Request.Method.GET, sURL,"",
            new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response) {
            procesarRespuesta(response);
        }
    },
            new Response.ErrorListener() {
        @Override
        public void onErrorResponse(VolleyError error) {
            VolleyErrorHelper errorVolley = new VolleyErrorHelper();
            String sError = VolleyErrorHelper.getMessage(error, getApplicationContext());
            Log.d(TAG, "Error: " + sError);
        }
    }
);

// Añadir petición a la cola
requestQueue.add(jsObjectRequest);

//...

//Método para procesar la respuesta (parsear el JSON)

    protected String procesarRespuesta(JSONObject jsObject) {
        //Procesar el JSON

    }
     **/
}
