package com.santidev.todolist;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.zip.Inflater;

public class DialogShowNote extends DialogFragment {

    private Note mNote;

    public void sendNoteSelected(Note noteSelected){
        this.mNote = noteSelected;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_show_note, null);
        TextView txtTitle = (TextView) dialogView.findViewById(R.id.txtTitle);
        TextView txtDescription = (TextView) dialogView.findViewById(R.id.txtDescription);

        txtTitle.setText(this.mNote.getTitle());
        txtDescription.setText(this.mNote.getDescription());

        ImageView ivImportant = (ImageView) dialogView.findViewById(R.id.imageViewImportant);
        ImageView ivToDo = (ImageView) dialogView.findViewById(R.id.imageViewToDo);
        ImageView ivIdea = (ImageView) dialogView.findViewById(R.id.imageViewIdea);

        if(!this.mNote.isImportant()){
            ivImportant.setVisibility(View.GONE);
        }

        if(!this.mNote.isToDo()){
            ivToDo.setVisibility(View.GONE);
        }

        if(!this.mNote.isIdea()){
            ivIdea.setVisibility(View.GONE);
        }

        Button btnOk = (Button) dialogView.findViewById(R.id.btnOk);

        builder.setView(dialogView)
                .setMessage("Your note: ");

        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return builder.create();
    }
}
