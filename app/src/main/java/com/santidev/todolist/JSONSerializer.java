package com.santidev.todolist;
import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class JSONSerializer {

    private String mFileName; //nombre del json que guardara la clase
    private Context mContext; //contexto donde se va a guardar el json

    public JSONSerializer(String filename, Context context){
        this.mFileName = filename;
        this.mContext = context;
    }

    public void save(List<Note> notes) throws IOException, JSONException {
        //Array de objetos JSON
        JSONArray jsonArray = new JSONArray();

        //Convertit cada Note en objetos en JSONObject y guardarlos
        for(Note note: notes){
            jsonArray.put(note.convertNoteToJSON());
        }

        //para guardar el fichero de objetos JSON hay que usar el Writer
        Writer writer = null;
        try {
            //abre el fichero donde guardaremos el JSON
            OutputStream out = mContext.openFileOutput(mFileName, mContext.MODE_PRIVATE);
            //El escritor ya sabe donde escribir el contenido en fichero JSON
            writer = new OutputStreamWriter(out);
            //El escritor escribe en el disco el array pasado a formato string
            writer.write(jsonArray.toString());
        }finally {
            //Si elwriter habia podido abrir el fichero, es importante que lo cierre para evitar que se corrompa
            if (writer != null) {
                writer.close();
            }
        }
    }

    public ArrayList<Note> load() throws  IOException, JSONException{
        ArrayList<Note> notes = new ArrayList<>();
        //Buffered reader para leer el fichero de JSON
        BufferedReader reader = null;
        try {
            //INPUT STREAM abre el fichero json que vamos a leer y procesar
            InputStream in = mContext.openFileInput(mFileName);
            //El lector ya sabe de donde leer los datos, de que fichero JSON
            reader = new BufferedReader(new InputStreamReader(in));
            //leemos los string del fichero JSON con un String Builder
            StringBuilder jsonString = new StringBuilder();
            // variable para leer la linea actual...
            String currentLine = null;

            //leer el fichero JSOn entero , hasta acabarlo y pasarlo todo a string
            //Mientras la linea actual no sea nula...
            while ((currentLine = reader.readLine()) != null){
                jsonString.append(currentLine);
            }
            //hemos pasado de un fichero JSON - String largo largo, con todos los objetos Note

            //pasamos de un array entero de String a un array de objetos JSON
            JSONArray jsonArray = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();
            for (int i = 0; i < jsonArray.length(); i++){
                notes.add(new Note(jsonArray.getJSONObject(i)));
            }
            //Ya tenemos el array de notes con todos los objetos de la clase Note..

        }catch (FileNotFoundException e){
            //la primera vez nos va a tronar por que no hay fichero de notas
            //en este caso , nos vasta ignorar la excepcion ya que es normal
        }finally {
            //si el reader abrio el fichero , es hora de cerrarlo
            if(reader != null){
                reader.close();
            }
        }

        return notes;
    }

}
