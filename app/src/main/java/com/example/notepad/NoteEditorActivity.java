package com.example.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {
    WorkSession newObj;
    int noteId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText = (EditText)  findViewById(R.id.editText);
        TextView textView1 = (TextView) findViewById(R.id.sessionNumberTextView);
        TextView textView2 = (TextView) findViewById(R.id.sessionDateTextView);

        // code to get object info from main activity
        Intent intent = getIntent();
        //WorkSession workSession = (WorkSession)intent.getSerializableExtra("sampleObject");
        final WorkSession uobj = getIntent().getParcelableExtra("sessionObject");
        //String text1 = String.valueOf(uobj.getSessionNumber());


        if(uobj != null){
            textView1.setText(String.valueOf(uobj.getSessionNumber()));
            textView2.setText(uobj.getCurrentDate());
            editText.setText(uobj.getSessionDescription());
        } else {
            int newId = MainActivity.workSessionArrayList.size() + 1;
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String newDate = dateFormat.format(date);
            newObj = new WorkSession(newId,newDate,"Write description here!");
            MainActivity.workSessionArrayList.add(newObj);
            MainActivity.adapter.notifyDataSetChanged();
            textView1.setText(String.valueOf(newObj.getSessionNumber()));
            textView2.setText(newObj.getCurrentDate());
            editText.setText(newObj.getSessionDescription());
        }


        //noteId = intent.getIntExtra("noteId", -1);
       /* if(noteId != -1){ // if the user has clicked on the list view
            editText.setText(MainActivity.notes.get(noteId));
        } else { // if the user has clicked the add menu
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size() - 1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }
*/
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                // need to work out how to update arrayList with the new description
                // using .set i need the index of the object (could get from sessionID -1 )
                //
                //HOW DO I DETERMINE WHICH OBJECT IS BEING EDITED
                // USE THE uobj for excisting objects but what for newly added objects?

               // MainActivity.notes.set(noteId, String.valueOf(charSequence));
               // MainActivity.arrayAdapter.notifyDataSetChanged();
                //MainActivity.workSessionArrayList.set();
                if (uobj != null){
                    WorkSession updatedObject = new WorkSession(uobj.sessionNumber,uobj.currentDate, String.valueOf(charSequence));
                    MainActivity.workSessionArrayList.set(uobj.getSessionNumber()-1, updatedObject );
                    MainActivity.adapter.notifyDataSetChanged();
                } else {
                    WorkSession updatedObject = new WorkSession(newObj.sessionNumber,newObj.currentDate, String.valueOf(charSequence));
                    MainActivity.workSessionArrayList.set(newObj.getSessionNumber()-1, updatedObject );
                    MainActivity.adapter.notifyDataSetChanged();
                }


                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(MainActivity.workSessionArrayList);
                editor.putString("session list", json);
                editor.apply();

               /* SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notepad", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();*/
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }


}