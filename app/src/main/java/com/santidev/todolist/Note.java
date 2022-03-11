package com.santidev.todolist;

import org.json.JSONException;
import org.json.JSONObject;

public class Note {

    private String mTitle;
    private String mDescription;

    private boolean mIdea;
    private boolean mToDo;
    private boolean mImportant;

    private static  final String JSON_TITLE = "title";
    private static  final String JSON_DESCRIPTION = "description";
    private static  final String JSON_IDEA = "idea";
    private static  final String JSON_TODO = "todo";
    private static  final String JSON_IMPORTANT = "important";

    public Note(){

    }

    public Note(JSONObject jsonObject) throws JSONException {
        mTitle = jsonObject.getString(JSON_TITLE);
        mDescription = jsonObject.getString(JSON_DESCRIPTION);
        mIdea = jsonObject.getBoolean(JSON_IDEA);
        mToDo = jsonObject.getBoolean(JSON_TODO);
        mImportant = jsonObject.getBoolean(JSON_IMPORTANT);
    }

    //toma las variabls de la nota y las serializa a un objeto JSON
    public JSONObject convertNoteToJSON() throws  JSONException{
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(JSON_TITLE, this.mTitle);
        jsonObject.put(JSON_DESCRIPTION, this.mDescription);
        jsonObject.put(JSON_IDEA, this.mIdea);
        jsonObject.put(JSON_TODO, this.mToDo);
        jsonObject.put(JSON_IMPORTANT, this.mImportant);

        return jsonObject;

    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }

    public boolean isIdea() {
        return mIdea;
    }

    public void setIdea(boolean mIdea) {
        this.mIdea = mIdea;
    }

    public boolean isToDo() {
        return mToDo;
    }

    public void setToDo(boolean mToDo) {
        this.mToDo = mToDo;
    }

    public boolean isImportant() {
        return mImportant;
    }

    public void setImportant(boolean mImportant) {
        this.mImportant = mImportant;
    }
}
