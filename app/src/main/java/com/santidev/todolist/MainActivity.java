package com.santidev.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NoteAdapter mNoteAdapter;
    private boolean mSound;
    public static int mAnimOptions;
    private SharedPreferences mPrefs;

    public static Animation animFlash, animFadeIn;

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
    protected void onResume() {
        super.onResume();
        mPrefs = getSharedPreferences("ToDoList", MODE_PRIVATE);
        mSound = mPrefs.getBoolean("sound", true);
        mAnimOptions = mPrefs.getInt("anim option", SettingsActivity.FAST);

        animFlash = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.flash);
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);

        if(mAnimOptions == SettingsActivity.FAST){
            animFlash.setDuration(100);
        }else if(mAnimOptions == SettingsActivity.SLOW){
            animFlash.setDuration(1000);
        }
        mNoteAdapter.notifyDataSetChanged();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mNoteAdapter.saveNotes();
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

        if(item.getItemId() == R.id.action_settings){
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
        }
        return false;
    }

    public void createNewNote(Note newNote){
        //Recibira una nueva nota creada por el dialogo DialogNewNote
        this.mNoteAdapter.addNote(newNote);
    }

    public void someException() throws IOException{


    }

    public void aMethodCallingTheException(){
        try {
            someException();
        }catch (Exception e){
            e.printStackTrace();//tomar accion en consecuencia despues de que haya ocurido el error.
        } finally {
            //ejecutar codigo tanto si el try falla o no
        }
    }


}