package com.example.dalls.pregnancycalander;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MonthsFragment extends Fragment {
    protected TextView textViewMonth1;
    protected TextView textViewMonth2;
    protected TextView textViewMonth3;
    protected TextView textViewMonth4;
    protected TextView textViewMonth5;
    protected TextView textViewMonth6;
    protected TextView textViewMonth7;
    protected TextView textViewMonth8;
    protected TextView textViewMonth9;
    protected TextView textViewMonth10;


    public MonthsFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_months, container, false);
        textViewMonth1=(TextView) view.findViewById(R.id.month1);
        textViewMonth2=(TextView) view.findViewById(R.id.month2);
        textViewMonth3=(TextView) view.findViewById(R.id.month3);
        textViewMonth4=(TextView) view.findViewById(R.id.month4);
        textViewMonth5=(TextView) view.findViewById(R.id.month5);
        textViewMonth6=(TextView) view.findViewById(R.id.month6);
        textViewMonth7=(TextView) view.findViewById(R.id.month7);
        textViewMonth8=(TextView) view.findViewById(R.id.month8);
        textViewMonth9=(TextView) view.findViewById(R.id.month9);
        textViewMonth10=(TextView) view.findViewById(R.id.month10);

        textViewMonth1.setOnClickListener(onClickListener);
        textViewMonth2.setOnClickListener(onClickListener);
        textViewMonth3.setOnClickListener(onClickListener);
        textViewMonth4.setOnClickListener(onClickListener);
        textViewMonth5.setOnClickListener(onClickListener);
        textViewMonth6.setOnClickListener(onClickListener);
        textViewMonth7.setOnClickListener(onClickListener);
        textViewMonth8.setOnClickListener(onClickListener);
        textViewMonth9.setOnClickListener(onClickListener);
        textViewMonth9.setOnClickListener(onClickListener);
        textViewMonth10.setOnClickListener(onClickListener);

        // Inflate the layout for this fragment

        return view;

    }
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // switch(v.getTag().toString()){
            Intent i = new Intent(getActivity(),DetailDescription.class);
            i.putExtra("month",v.getTag().toString());
            //  i.putExtra("month",v.getResources().getResourceName(v.getId()));
            startActivity(i);
        }
    };


}
