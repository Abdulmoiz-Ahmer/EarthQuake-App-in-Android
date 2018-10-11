package com.example.aceahmer.quakequake;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ListAdapter extends ArrayAdapter<DataModel> {
    ArrayList<DataModel> list;
    Context context;

    public ListAdapter(@NonNull Context context, ArrayList<DataModel> list) {
        super(context, R.layout.custom_list, list);
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            convertView = layoutInflater.inflate(R.layout.custom_list, parent, false);
        }
        DataModel dataModel = list.get(position);
        TextView mag_txt, time_txt, date_txt, directions_txt, place_txt;
        mag_txt = convertView.findViewById(R.id.magnitude_txt);
        time_txt = convertView.findViewById(R.id.time_txt);
        date_txt = convertView.findViewById(R.id.date_txt);
        directions_txt = convertView.findViewById(R.id.directions_txt);
        place_txt = convertView.findViewById(R.id.place_txt);
        DateTime dateTime = new DateTime();
        StringSeperator stringSeperator=new StringSeperator();
        mag_txt.setText(Double.toString(dataModel.getMagnitude()));
        GradientDrawable gradientDrawable=(GradientDrawable) mag_txt.getBackground();
        gradientDrawable.setColor(colorPicker.setColor(getContext(),dataModel.getMagnitude()));
        date_txt.setText(dateTime.date(dataModel.getMillisecond(), "MMM dd yyyy"));
        time_txt.setText(dateTime.date(dataModel.getMillisecond(), "HH:mm a"));
        String array[] = stringSeperator.seperate(dataModel.getPlace());
        place_txt.setText(array[1]);
        directions_txt.setText(array[0]);
        return convertView;
    }




}
