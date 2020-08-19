package com.example.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    static List<WorkSession> workSessionArrayList = new ArrayList<>();
    static ArrayList<String> notes = new ArrayList<>();
    static CustomListAdapter arrayAdapter;
    private ListView listView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.add_note){
            Intent intent = new Intent(getApplicationContext(), NoteEditorActivity.class);

            startActivity(intent);
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.ListView);
        // below is used to have permenent storage of the data
     /*   SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notepad", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>)sharedPreferences.getStringSet("notes", null);

        if(set == null){ // if the set from storage is null(nothing saved) then add example note
            notes.add("Example note");
        } else { // if data is found then fill the notes array with the data
            notes = new ArrayList<>(set);
        }*/

        WorkSession workSession = new WorkSession(1, Calendar.getInstance().getTime(), "Example session!");
        workSessionArrayList.add(workSession);
        workSessionArrayList.add(new WorkSession(2, Calendar.getInstance().getTime(), "Example session 2!"));
        //notes.add("Example note");

        //arrayAdapter = new CustomListAdapter(this, workSessionArrayList);
        //arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
        //listView.setAdapter(arrayAdapter);

        inflateListView(workSessionArrayList);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //String selected = ((TextView) view.findViewById(R.id.ListItem_tfDate)).getText().toString();

                    Toast.makeText(getApplicationContext(), "selected", Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(),NoteEditorActivity.class);
                //intent.putExtra("noteId", i);
                startActivity(intent);
            }
        });

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int itemToDelete = i;

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                workSessionArrayList.remove(itemToDelete);
                                arrayAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.notepad", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet<>(MainActivity.notes);
                                sharedPreferences.edit().putStringSet("notes",set).apply();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });
    }
    private void inflateListView(List<WorkSession> likedQuoteIdList) {
        CustomListAdapter adapter = new CustomListAdapter(this, likedQuoteIdList);
        listView.setAdapter(adapter);

        listView.setDivider(null);
        listView.setDividerHeight(0);
    }


}