package com.example.tajmer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    TextView textViewCzas;
    int sekundy = 0;
    boolean czyDziala = false;
    private Button start, stop, zapisz, reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        textViewCzas = findViewById(R.id.TextViewCzas);
        start = findViewById(R.id.Start);
        stop = findViewById(R.id.Stop);
        zapisz = findViewById(R.id.Zapisz);
        reset = findViewById(R.id.Reset);

        Handler handler = new Handler();
        handler.post(
                new Runnable() {
                    @Override
                    public void run() {
                        if(czyDziala){
                            sekundy++;
                            textViewCzas.setText(sekundy+"");
                        }
                        handler.postDelayed(this, 1000);
                    }
                }
        );
        start.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        czyDziala = true;
                        textViewCzas.setText(sekundy+"");
                    }
                }
        );
        stop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        czyDziala = false;
                        textViewCzas.setText(sekundy+"");
                    }
                }
        );
        reset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sekundy = 0;
                        textViewCzas.setText(sekundy+"");
                    }
                }
        );
    }
}