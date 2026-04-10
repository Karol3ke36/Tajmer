package com.example.tajmer;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView textViewCzas;
    int sekundy = 0;
    boolean czyDziala = false;
    private Button start, stop, zapisz, reset;
    private ArrayList<String> arrayListCzasy;
    private ArrayAdapter<String> arrayAdapter;
    private ListView lista;


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
        lista = findViewById(R.id.lista);

        Handler handler = new Handler();
        handler.post(
                new Runnable() {
                    @Override
                    public void run() {
                        if(czyDziala){
                            sekundy++;
                            textViewCzas.setText(wyswietlLadnie());
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
                        textViewCzas.setText(wyswietlLadnie());
                    }
                }
        );
        stop.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        czyDziala = false;
                        textViewCzas.setText(wyswietlLadnie());
                    }
                }
        );
        reset.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sekundy = 0;
                        textViewCzas.setText(wyswietlLadnie());
                    }
                }
        );
        arrayListCzasy = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayListCzasy);
        lista.setAdapter(arrayAdapter);
        zapisz.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        arrayListCzasy.add(wyswietlLadnie());
                        arrayAdapter.notifyDataSetChanged();
                    }
                }
        );
    }

    private String wyswietlLadnie(){
        int sek = sekundy%60;
        int min = (sekundy/60)%60;
        int godz = sekundy/3600;
        return String.format("%02d:%02d:%02d",godz,min,sek);
    }

}