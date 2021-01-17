package com.example.p1_adiu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.toolbox.StringRequest;
import com.anychart.anychart.AnyChart;
import com.anychart.anychart.AnyChartView;
import com.anychart.anychart.DataEntry;
import com.anychart.anychart.LegendLayout;
import com.anychart.anychart.Pie;
import com.anychart.anychart.ValueDataEntry;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

import static com.android.volley.toolbox.Volley.newRequestQueue;


public class MainActivity extends AppCompatActivity {
    private Context mContext;
    AnyChartView anyChartView;

    ArrayList<DataEntry> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getApplicationContext();
        setContentView(R.layout.activity_main);

        anyChartView = (AnyChartView) findViewById(R.id.meuchart);
        anyChartView.setVisibility(View.GONE);

        consulta();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        //anyChartView.setVisibility(View.VISIBLE);

    }

    public void pie(ValueDataEntry dato){
        Pie pieChart = AnyChart.pie();
        pieChart.data(data);
        pieChart.setTitle("Cantidad de actores por edad");
        //pie.getLabels().setPosition("outside");
        pieChart.getLegend().getTitle().setEnabled(true);
        pieChart.getLegend().getTitle()
                .setText("Rango de edades")
                .setPadding(0d, 0d, 10d, 0d);

        data.add(dato);

        pieChart.data(data);
        System.out.println("Data size: " + data.size());

        anyChartView.setChart(pieChart);
        anyChartView.setVisibility(View.VISIBLE);

    }

    public void pintarGrafica(int dato, String rango){
        switch (rango){
            case "0-40":
                pie(new ValueDataEntry("Menores de 40 años", dato));
                break;
            case "40-200":
                pie(new ValueDataEntry("Mayores de 40 años", dato));
                break;
        }
    }

    private void consulta(){
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String RANGO1 = "0-40";
        String RANGO2 = "40-200";

        Consultor con1 = new Consultor(this, RANGO1, requestQueue);
        Consultor con2 = new Consultor(this, RANGO2, requestQueue);
        con1.start();
        con2.start();
    }


    private void consultaVieja() {
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        String JSON_URL1 = "http://adiu.ddns.net/PeliculasWeb20/bdpeliculas?op=cantidadporfranja&par=0-29";
        String JSON_URL2 = "http://adiu.ddns.net/PeliculasWeb20/bdpeliculas?op=cantidadporfranja&par=30-60";

        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL1,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        int p1 = Integer.parseInt((response.substring(response.indexOf(":") + 1, response.indexOf("}"))));
                        System.out.println("Datos obtenidos (primera consulta) = " + p1);
                        //dato1 = p1;
                        //pie();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //adding the string request to request queue
        requestQueue.add(stringRequest);

        /**

        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, JSON_URL2,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        int p2 = Integer.parseInt((response.substring(response.indexOf(":") + 1, response.indexOf("}"))));
                        System.out.println("Datos obtenidos (segunda consulta) = " + p2);
                        dato2 = p2;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        requestQueue.add(stringRequest2);

        **/
    }
}
