package com.santidev.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingsActivity extends AppCompatActivity {

    private SharedPreferences mPrefs; //leer datos guradados en disco
    private SharedPreferences.Editor mEditor; //para escribir datos en las shared pref

    private boolean mSound; // activar o desactivar el sonido

    public static final  int FAST = 0; //animaciones rapidas
    public static final int SLOW = 1; //animciones lentas
    public static final int NONE = 2; //sin animaciones

    private int mAnimationOption;// para cambiar el tipo de animacion en la app

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        mPrefs = getSharedPreferences("ToDoList", MODE_PRIVATE);
        mEditor = mPrefs.edit();

        //logica de activary desactivar sonido
        mSound = mPrefs.getBoolean("sound", true);
        CheckBox checkBoxSound = (CheckBox) findViewById(R.id.sound_checkbox);
        if (mSound){
            checkBoxSound.setChecked(true);
        }else {
            checkBoxSound.setChecked(false);
        }
        checkBoxSound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //Si el sonido estaba en marcha, lo apagamos
                //Si el sonido estaba apagado, lo ponemos en marcha
                mSound = !mSound;
                mEditor.putBoolean("sound", mSound);
            }
        });

        //Logica de cambiar de animacion
        mAnimationOption = mPrefs.getInt("anim option", FAST);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.radio_group_animation);
        radioGroup.clearCheck();

        switch (mAnimationOption){
            case FAST:
                radioGroup.check(R.id.rb_fast);
                break;
            case SLOW:
                radioGroup.check(R.id.rb_slow);
                break;
            case NONE:
                radioGroup.check(R.id.rb_none);
                break;
        }

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                //recuperamos el radio button que ha sido seleccionado a traves del checkedId
                RadioButton rb = (RadioButton)  radioGroup.findViewById(checkedId);

                if(null != rb){
                    switch (rb.getId()){
                        case R.id.rb_fast:
                            mAnimationOption = FAST;
                            break;
                        case R.id.rb_slow:
                            mAnimationOption = SLOW;
                            break;
                        case R.id.rb_none:
                            mAnimationOption = NONE;
                            break;
                    }
                }
                mEditor.putInt("anim option", mAnimationOption);
            }

        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        mEditor.commit();
    }
}