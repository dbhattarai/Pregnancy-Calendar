package com.example.dalls.pregnancycalander;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dalls on 7/26/2016.
 */
public class CalenderAdapter extends ArrayAdapter<CalendarData> {

    private List<CalendarData> calenderlist=new ArrayList<>();
    protected LayoutInflater vi;
    private int Resource;
    protected ViewHolder holder;
    protected Context context;

    public CalenderAdapter(Context context, int resource, List<CalendarData> objects) {
        super(context, resource, objects);
        Resource = resource;
        calenderlist = objects;
        this.context=context;
        vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
       // return super.getView(position,convertView,parent);
        View v=convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = vi.inflate(Resource, null);

           // holder.tvMonth = (TextView) v.findViewById(R.id.tvMonth);
            holder.tvDescription = (TextView) v.findViewById(R.id.tvDescriptionn);
           holder.tvDateRange = (TextView) v.findViewById(R.id.tvDateRange);
            holder.tvWeek = (TextView) v.findViewById(R.id.tvWeek);
            holder.tvDay = (TextView) v.findViewById(R.id.tvDay);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        //holder.tvMonth.setText(calenderlist.get(position).getMonth());
        holder.tvDescription.setText(calenderlist.get(position).getDescription());
        holder.tvDateRange.setText(calenderlist.get(position).getDate_range());
        holder.tvWeek.setText(calenderlist.get(position).getWeek());
        holder.tvDay.setText(calenderlist.get(position).getDay());
        return v;
    }
    static class ViewHolder{
        public TextView tvMonth;
        public TextView tvDateRange;
        public  TextView tvWeek;
        public TextView tvDay;
        public TextView tvDescription;
    }

}
