package com.santidev.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class DialogNewNote extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_new_note, null);

        final EditText editTitle = (EditText) dialogView.findViewById(R.id.editTitle);
        final EditText editDescription = (EditText) dialogView.findViewById(R.id.editDescription);

        final CheckBox checkBoxIdea = (CheckBox) dialogView.findViewById(R.id.checkboxIdea);
        final CheckBox checkBoxToDo = (CheckBox) dialogView.findViewById(R.id.checkboxToDo);
        final CheckBox checkBoxImportant = (CheckBox) dialogView.findViewById(R.id.checkboxImportant);

        Button btnCancel = (Button) dialogView.findViewById(R.id.btnCancel);
        Button btnOk = (Button) dialogView.findViewById(R.id.btnOk);

        builder.setView(dialogView)
                .setMessage("Add new note");

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Creamos una nota vacia
                Note newNote = new Note();

                //configuramos la variables de la nota creada
                newNote.setTitle(editTitle.getText().toString());
                newNote.setDescription(editDescription.getText().toString());
                newNote.setIdea(checkBoxIdea.isChecked());
                newNote.setToDo(checkBoxToDo.isChecked());
                newNote.setImportant(checkBoxImportant.isChecked());

                //hago un cating a MainActivity que es quien ha llamado al dilogo
                MainActivity callingActivity = (MainActivity) getActivity();
                //notificatemos al MainActivity que hemos reado una nueva nota
                callingActivity.createNewNote(newNote);
                //cierra el dialogo
                dismiss();
            }
        });

        //una vez configurada la alerta, le indicamos al builder crearla
        return builder.create();
    }
}
