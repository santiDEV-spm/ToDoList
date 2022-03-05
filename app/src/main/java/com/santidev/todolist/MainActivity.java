package com.santidev.todolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Note mTempNote = new Note();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogShowNote dialogShowNote = new DialogShowNote();
                //indico al dialogo la nota que debe mostrar en pantalla
                dialogShowNote.sendNoteSelected(mTempNote);
                dialogShowNote.show(getFragmentManager(), "note_show");
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
        this.mTempNote = newNote;
    }

}