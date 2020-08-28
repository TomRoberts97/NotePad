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

import java.util.HashSet;

public class NoteEditorActivity extends AppCompatActivity {

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
        WorkSession uobj = getIntent().getParcelableExtra("sessionObject");
        //String text1 = String.valueOf(uobj.getSessionNumber());
        textView1.setText(String.valueOf(uobj.getSessionNumber()));
        textView2.setText(uobj.getCurrentDate());
        editText.setText(uobj.getSessionDescription());



        /*noteId = intent.getIntExtra("noteId", -1);
        if(noteId != -1){
            editText.setText(MainActivity.notes.get(noteId));
        } else {
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size() - 1;
            MainActivity.arrayAdapter.notifyDataSetChanged();
        }*/

       /* editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                MainActivity.notes.set(noteId, String.valueOf(charSequence));
                MainActivity.arrayAdapter.notifyDataSetChanged();

                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notepad", Context.MODE_PRIVATE);
                HashSet<String> set = new HashSet<>(MainActivity.notes);
                sharedPreferences.edit().putStringSet("notes",set).apply();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/
    }
}