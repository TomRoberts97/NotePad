package com.example.notepad;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class CustomListAdapter extends ArrayAdapter<WorkSession> {

    public CustomListAdapter(Context context, List<WorkSession> workSessionList) {
        super(context, 0, workSessionList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        WorkSession workSession = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.note_item, parent, false);
        }

        // setting date text view
        TextView textView = convertView.findViewById(R.id.ListItem_tfDate);
        /*Date date = workSession.getCurrentTime();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String strDate = dateFormat.format(date);*/
        textView.setText(workSession.getCurrentDate());
        //setting num text view
        TextView textView2 = convertView.findViewById(R.id.ListItem_tfDescription);
        textView2.setText( workSession.getSessionDescription());

        return convertView;
    }
}
