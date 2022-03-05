package com.santidev.todolist;

public class Note {

    private String mTitle;
    private String mDescription;

    private boolean mIdea;
    private boolean mToDo;
    private boolean mImportant;

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
