package com.example.losowaniekostek;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends Activity {

    EditText editTextLiczbaKostek;
    GridLayout gridKostki;
    TextView textViewWyniki;
    int[] kostki = new int[0];
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextLiczbaKostek = findViewById(R.id.editTextLiczbaKostek);
        gridKostki = findViewById(R.id.gridKostki);
        textViewWyniki = findViewById(R.id.textViewWyniki);
        Button buttonLosuj = findViewById(R.id.buttonLosuj);
        Button buttonRosnaco = findViewById(R.id.buttonRosnaco);
        Button buttonMalejaco = findViewById(R.id.buttonMalejaco);

        buttonLosuj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                losujKostki();
            }
        });

        buttonRosnaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortujKostki(true);
            }
        });

        buttonMalejaco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortujKostki(false);
            }
        });
    }

    void losujKostki() {
        int liczba = Integer.parseInt(editTextLiczbaKostek.getText().toString());

        kostki = new int[liczba];
        for (int i = 0; i < kostki.length; i++) {
            kostki[i] = random.nextInt(6) + 1;
        }
        wyswietlKostki();
    }

    void wyswietlKostki() {
        gridKostki.removeAllViews();
        String wyniki = "";


        for (int i = 0; i < kostki.length; i++) {
            TextView kostka = new TextView(this);
            kostka.setText(String.valueOf(kostki[i]));
            kostka.setTextSize(24);
            kostka.setTextColor(0xFF49454F);
            kostka.setGravity(Gravity.CENTER);
            kostka.setBackgroundResource(R.drawable.tlo_kostki);

            GridLayout.LayoutParams parametry = new GridLayout.LayoutParams();
            int rozmiar = (int) (48 * getResources().getDisplayMetrics().density);
            parametry.width = rozmiar;
            parametry.height = rozmiar;
            parametry.columnSpec = GridLayout.spec(0, GridLayout.CENTER, 1f);
            kostka.setLayoutParams(parametry);
            gridKostki.addView(kostka);

            if (i > 0) {
                wyniki += ", ";
            }
            wyniki += kostki[i];
        }
        textViewWyniki.setText(wyniki);
    }

    void sortujKostki(boolean rosnaco) {
        for (int i = 0; i < kostki.length - 1; i++) {
            for (int j = 0; j < kostki.length - 1 - i; j++) {
                if ((rosnaco && kostki[j] > kostki[j + 1])
                        || (!rosnaco && kostki[j] < kostki[j + 1])) {
                    int pomoc = kostki[j];
                    kostki[j] = kostki[j + 1];
                    kostki[j + 1] = pomoc;
                }
            }
        }
        wyswietlKostki();
    }
}
