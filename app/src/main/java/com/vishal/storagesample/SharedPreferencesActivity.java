package com.vishal.storagesample;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.HashSet;
import java.util.Set;

public class SharedPreferencesActivity extends AppCompatActivity {

    RadioButton radioButtonMale, radioButtonFemale;
    EditText editTextName, editTextAge, editTextWeight;
    CheckBox checkBoxJava, checkBoxJ2ee, checkBoxAndroid;

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);


        radioButtonMale = (RadioButton) findViewById(R.id.radioButton_male);
        radioButtonFemale = (RadioButton) findViewById(R.id.radioButton_female);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAge = (EditText) findViewById(R.id.editTextAge);
        editTextWeight = (EditText) findViewById(R.id.editTextWeight);

        checkBoxJava = (CheckBox) findViewById(R.id.checkBoxJava);
        checkBoxJ2ee = (CheckBox) findViewById(R.id.checkBoxJ2ee);
        checkBoxAndroid = (CheckBox) findViewById(R.id.checkBoxAndroid);

        sharedPreferences = getSharedPreferences("STORAGE_SAMPLE", Context.MODE_PRIVATE);


        editTextName.setText(sharedPreferences.getString("name", ""));
        if(sharedPreferences.getInt("age",-1) != -1)
            editTextAge.setText(String.valueOf(sharedPreferences.getInt("age",-1)));

        if(sharedPreferences.getFloat("weight", 0.0f) != 0.0f)
            editTextWeight.setText(String.valueOf(sharedPreferences.getFloat("weight", 0.0f)));

        boolean genderMale = sharedPreferences.getBoolean("gender_male", false);

        radioButtonMale.setChecked(genderMale);
        radioButtonFemale.setChecked(!(genderMale));

        Set<String> tech = sharedPreferences.getStringSet("tech", null);

        if(tech != null)
        {
            if (tech.contains("Java Core"))
                checkBoxJava.setChecked(true);
            else
                checkBoxJava.setChecked(false);

            if (tech.contains("J2EE"))
                checkBoxJ2ee.setChecked(true);
            else
                checkBoxJ2ee.setChecked(false);

            if (tech.contains("Android"))
                checkBoxAndroid.setChecked(true);
            else
                checkBoxAndroid.setChecked(false);
        }

        //sharedPreferences.edit().putString().putInt().putBoolean().putFloat().putLong().putStringSet()
    }


    public void saveInfo(View view) {


        SharedPreferences.Editor editor = sharedPreferences.edit();

        Set<String> tech = new HashSet<String>();
        if(checkBoxJava.isChecked())
            tech.add(checkBoxJava.getText().toString());

        if(checkBoxJ2ee.isChecked())
            tech.add(checkBoxJ2ee.getText().toString());

        if(checkBoxAndroid.isChecked())
            tech.add(checkBoxAndroid.getText().toString());

        editor.putStringSet("tech", tech);


        editor.putString("name", editTextName.getText().toString().trim());

        editor.putInt("age", Integer.valueOf(editTextAge.getText().toString().trim()));

        editor.putFloat("weight", Float.valueOf(editTextWeight.getText().toString()));

        if(radioButtonMale.isChecked())
            editor.putBoolean("gender_male", true);
        else
            editor.putBoolean("gender_male", false);

        editor.commit();
    }

    public void exitActivity(View view) {

        finish();
    }
}
