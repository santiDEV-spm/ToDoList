package com.santidev.todolist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends BaseAdapter {

    private List<Note> notes = new ArrayList<>();
    private JSONSerializer mSerializer;
    private Context context;

    public NoteAdapter(Context context) {
        this.notes = notes;
        this.context = context;
        mSerializer = new JSONSerializer("ToDoList.json", context);

        try {
            notes = mSerializer.load();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void saveNotes(){
        try{
            mSerializer.save(notes);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int itemPosition) {
        return notes.get(itemPosition);
    }

    @Override
    public long getItemId(int itemPosition) {
        return itemPosition;
    }

    @Override
    public View getView(int itemPosition, View view, ViewGroup viewGroup) {
        //Aqui programaremos la logica de las celdas de la lista
        if (view == null){
            //la vista no ha sido accedida anteriormente
            //asi que lo primero que hay q hacer es inflarla
            //apartir del layout list_item.xml
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item, viewGroup, false);
        }
        //cuando estamos aqui tenemos la vista bien definida
        // y podemos proceder a ocultar las imagenes que sobren del layout...
        // y rellenar titulo y descripcion de la tarea.

        TextView textViewTitle = (TextView) view.findViewById(R.id.text_view_title);
        TextView textViewDescription = (TextView) view.findViewById(R.id.text_view_description);

        ImageView imageViewImportant = (ImageView) view.findViewById(R.id.image_view_important);
        ImageView imageViewToDo = (ImageView) view.findViewById(R.id.image_view_todo);
        ImageView imageViewIdea = (ImageView) view.findViewById(R.id.image_view_idea);

        Note currentNote = notes.get(itemPosition);

        if (currentNote.isImportant() && MainActivity.mAnimOptions != SettingsActivity.NONE){
            view.setAnimation(MainActivity.animFlash);
        }else {
            view.setAnimation(MainActivity.animFadeIn);
        }

        if(!currentNote.isImportant()){
            imageViewImportant.setVisibility(View.GONE);
        }
        if(!currentNote.isToDo()){
            imageViewToDo.setVisibility(View.GONE);
        }
        if(!currentNote.isIdea()){
            imageViewIdea.setVisibility(View.GONE);
        }

        textViewTitle.setText(currentNote.getTitle());
        textViewDescription.setText(currentNote.getDescription());

        return view;
    }

    public void addNote(Note note){
        this.notes.add(note);
        notifyDataSetChanged();
    }
}
