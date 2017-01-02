package com.example.dalls.pregnancycalander;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import static android.content.Context.MODE_PRIVATE;


public class TodayDetail extends Fragment {

    public TodayDetail() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_today_detail, container, false);
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(getString(R.string.PREF_FILE), MODE_PRIVATE);
        String stringDate = sharedPreferences.getString(getString(R.string.DATE_INFO), "");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        Calendar c1 = Calendar.getInstance();
        try {
            c.setTime(sdf.parse(stringDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Date resultdate = new Date(c.getTimeInMillis());
        Date todayDate = new Date(c1.getTimeInMillis());
        List<Date> d = getDaysBetweenDates(resultdate, todayDate);

        return view;
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
