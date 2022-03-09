package com.santidev.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteAdapter mNoteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNoteAdapter = new NoteAdapter(this);
        ListView listNotes = (ListView) findViewById(R.id.listView);
        listNotes.setAdapter(mNoteAdapter);

        listNotes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int itemPosition, long id) {
                //recuperamos la nota de la posicion pulsada por el usuario
                Note temNote = mNoteAdapter.getItem(itemPosition);
                //creamos una instancia de show note
                DialogShowNote dialogShowNote = new DialogShowNote();
                dialogShowNote.sendNoteSelected(temNote);
                dialogShowNote.show(getFragmentManager(),"");
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_add){
            DialogNewNote dialogNewNote = new DialogNewNote();
            dialogNewNote.show(getFragmentManager(), "note_create");
        }
        return false;
    }

    public void createNewNote(Note newNote){
        //Recibira una nueva nota creada por el dialogo DialogNewNote
        this.mNoteAdapter.addNote(newNote);
    }

}