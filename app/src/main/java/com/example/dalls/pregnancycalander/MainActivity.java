package com.example.dalls.pregnancycalander;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   private Button button;
   private String date;
    private String getDate;
    private DatePicker datePicker;

    String url="http://www.justmommies.com/get_pregnancycalendar.php?month=1&day=15&year=2017&submit=Create+my+pregnancy+calendar#month1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences sharedPreferences=MainActivity.this.getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        getDate=sharedPreferences.getString(getString(R.string.DATE_INFO),"");
        //date=getDate;
        button=(Button)findViewById(R.id.buttonUrl);
        datePicker=(DatePicker) findViewById(R.id.datePicker);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               int year=datePicker.getYear();
                int month=datePicker.getMonth();
                int day=datePicker.getDayOfMonth();
                date=(year+"-"+month+"-"+day).toString();
                saveDate(v);
                Intent i = new Intent(getApplicationContext(),TabbedViewHome.class);
                startActivity(i);
            }
        });
        button.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                new getJsonData().execute();
            }
        });
    }

   public class getJsonData extends AsyncTask<Void,Void,Void> {
       protected JSONArray jsonArray = new JSONArray();

       @Override
       protected Void doInBackground(Void... params) {
           try {
               Document document = Jsoup.connect(url).get();

             for(Element div: document.select("div#maincal")){
                 for(Element table: div.select("table")){
                     for(Element tr:table.select("tr:nth-child(3n+1)")) {
                         Elements tds = tr.select("td");
                         for (int i = 0; i < tds.size(); i++) {
                             JSONObject jsonObject=new JSONObject();
                             try {
                                 jsonObject.put("description", tds.get(i).text().toString());
                             } catch (JSONException e) {
                                 e.printStackTrace();
                             }
                            jsonArray.put(jsonObject);
                        }
                    }
                 }
             }
               try{
               String fileName = "sample";
               FileOutputStream fo = openFileOutput(fileName,MODE_PRIVATE);
               fo.write(jsonArray.toString().getBytes());
               fo.close();
           }catch (IOException e){
               e.printStackTrace();
           }
           } catch (IOException e) {
               e.printStackTrace();
           }
           return null;
       }
        protected void onPreExecute(){}
        protected void onPostExecute(Void result){}
    }

    protected void saveDate(View v){
        SharedPreferences sharedPreferences=MainActivity.this.getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(getString(R.string.DATE_INFO),date);
        editor.commit();

    }

    protected void clearDate(View v){
        SharedPreferences sharedPreferences=MainActivity.this.getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
