package com.example.p1_adiu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
}