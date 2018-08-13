package com.example.dogu2.usdparser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {

    public TextView guncelKur;
    public Button kurButonu, silici;
    boolean acikmi=true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        guncelKur = findViewById(R.id.degisenKur);
        kurButonu = findViewById(R.id.kurbutonu);
        silici = findViewById(R.id.silici);


        kurButonu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new mytask(MainActivity.this).execute();
            }
        });


        silici.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guncelKur.setText("");
            }
        });

        }


    public class mytask extends AsyncTask<Void, Void, String>{
        private WeakReference<MainActivity> activityReference;

        mytask(MainActivity context){
            activityReference = new WeakReference<>(context);
        }

    @Override
    protected String doInBackground(Void... strings) {
        String usd="";
        String gold="";
        String avro="";
        String result="";
        Document doc;
            try {
                doc = Jsoup.connect("https://kur.doviz.com/serbest-piyasa/amerikan-dolari").get();
                Element dolar = doc.select("span.menu-row2").get(1);
                Element altin = doc.select("span.menu-row2").get(0);
                Element euro = doc.select("span.menu-row2").get(2);

                usd = dolar.text();
                gold = altin.text();
                avro = euro.text();
                result = "USD: " + usd + "\nEURO: " + avro + "\nALTIN: " + gold;
            } catch (IOException e) {
            }
            return result;
        }


    @Override
    protected void onPostExecute(String kur) {
            guncelKur.setText(kur);

    }


    }}







