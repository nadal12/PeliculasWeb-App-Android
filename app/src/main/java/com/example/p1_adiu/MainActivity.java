package com.example.p1_adiu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static com.android.volley.toolbox.Volley.newRequestQueue;


public class MainActivity extends AppCompatActivity {
    private Context mContext;
    AnyChartView anyChartView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.activity_main);

        pieChart();


    }

    private void pieChart(){
        Pie pie = AnyChart.pie();

        List<DataEntry> data = obtenerDatos1();

        pie.data(data);

        anyChartView = (AnyChartView) findViewById(R.id.meuchart);
        anyChartView.setChart(pie);
    }

    public ArrayList<DataEntry> obtenerDatos1(){

        ArrayList<DataEntry> data = new ArrayList<>();
        data.add(new ValueDataEntry("John", 10000));
        data.add(new ValueDataEntry("Jake", 12000));
        data.add(new ValueDataEntry("Peter", 18000));

        return data;
    }


    /**
     * CODIGO PARA REALIZAR LA CONSULTA
     */

    // link para pruebas: https://mdn.github.io/learning-area/javascript/oojs/json/superheroes.json

    String sURL="https://mdn.github.io/learning-area/javascript/oojs/json/superheroes.json";

    public void consulta(){
        // Nueva petición JSONObject
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, sURL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        procesarRespuesta(response);
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        System.out.println("Error");
                    }
                });

        requestQueue.add(jsonObjectRequest);

    }

    //Método para procesar la respuesta (parsear el JSON)

    protected String procesarRespuesta(JSONObject jsObject) {
        //Procesar el JSON
        System.out.println("JSON: " + jsObject.toString());
        return "";
    }
}
