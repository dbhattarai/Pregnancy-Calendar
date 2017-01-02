package com.example.dalls.pregnancycalander;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

public class DetailDescription extends AppCompatActivity {
    private Button button;
    private Context context;
    private Integer day;
    private Integer week;
    private String stringDate;
    private Date date;
    private String month;
    private CalenderAdapter adapter;
    private ListView listView;
    private List<CalendarData> calendarList = new ArrayList<CalendarData>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_description);
        listView = (ListView) findViewById(R.id.list);
        Intent i = getIntent();
        month = i.getStringExtra("month");
        SharedPreferences sharedPreferences = DetailDescription.this.getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        stringDate = sharedPreferences.getString(getString(R.string.DATE_INFO), "");
    }

    protected void onStart() {
        // TODO Auto-generated method stub
        super.onStart();
        readFromFile();
    }

    public void readFromFile() {
        String inputStr;
        String fileName;

        try {
            AssetManager am = getApplicationContext().getAssets();
            // fileName=Locale.getDefault().getDisplayLanguage();
            //if (Locale.getDefault().getDisplayLanguage() == "Hindi")
                fileName = "calendar-np.txt";
            //else
               // fileName = "calendar-en.txt";
            InputStream fis = am.open(fileName);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader streamReader = new BufferedReader(isr);
            StringBuilder responseStrBuilder = new StringBuilder();

            while ((inputStr = streamReader.readLine()) != null)
                responseStrBuilder.append(inputStr);
            try {
                JSONObject jsonobj = new JSONObject(responseStrBuilder.toString());
                //  JSONArray jsonArray=new JSONArray();
                int weekCount = 1;
                if (month == null) {
                    Toast.makeText(getApplicationContext(), "Please select month", Toast.LENGTH_LONG).show();
                } else {
                    JSONArray jsonArray = jsonobj.getJSONArray(month);
                    getDayAndWeek(month);
                    List<Date> dates = new ArrayList<Date>();
                  //  Date dueDate=getDate(280);
                    dates = getDaysBetweenDates(date, getDate(28));
                    for (int i = 0; i < dates.size(); i++) {
                        dates.get(i);
                    }
                    for (int i = 1; i < jsonArray.length(); i++) {
                        if (weekCount > 6) {
                            week++;
                            weekCount = 0;
                        }
                        CalendarData calendarData = new CalendarData();
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        calendarData.setDay("Day " + day);
                        calendarData.setDescription(jsonObject.getString(jsonObject.keys().next()));
                        calendarData.setWeek(week + "." + weekCount + " weeks");
                        calendarData.setDate_range(dates.get(i-1).toString());

                        calendarList.add(calendarData);
                        day++;
                        weekCount++;
                    }
                }

            } catch (JSONException e) {

                e.printStackTrace();
            }
            streamReader.close();
            isr.close();
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            CalenderAdapter adapter = new CalenderAdapter(getApplicationContext(), R.layout.display, calendarList);
            listView.setAdapter(adapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getDayAndWeek(String month) {
        switch (month) {
            .
            .
        }
        date = getDate(day - 1);
    }

    public Date getDate(int d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(stringDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        c.add(Calendar.DATE, d);
        //sdf = new SimpleDateFormat("MM/dd/yyyy");
        Date resultdate = new Date(c.getTimeInMillis());
        date = resultdate;
        stringDate = sdf.format(resultdate);
        return date;


    }

    public static List<Date> getDaysBetweenDates(Date startdate, Date enddate) {
        List<Date> dates = new ArrayList<Date>();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(startdate);

        while (calendar.getTime().before(enddate)) {
            Date result = calendar.getTime();
            dates.add(result);
            calendar.add(Calendar.DATE, 1);
        }
        return dates;
    }
}
