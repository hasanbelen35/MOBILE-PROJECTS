package com.example.local;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.Button;  // Button sınıfı import edildi

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashSet;

public class MainActivity extends AppCompatActivity {
    EditText name;
    EditText surname;
    Button btn;
    RadioGroup radioGroup;
    RadioButton radioButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

      // DEFINING ITEMS BY ID'S
        name = findViewById(R.id.editText1);
        surname = findViewById(R.id.editText2);
        btn = findViewById(R.id.button);
        radioGroup = findViewById(R.id.radiogrupu);


        // BTN EVENT
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              saveDatas();
            }
        });
    }

// SAVE METHOD
    private void saveDatas() {
        // PLAIN BOXES
        String nameGetValue = name.getText().toString();
        String surnameGetValue = surname.getText().toString();
        SharedPreferences sharedPreferences = getSharedPreferences("SAVE" , Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString("name" , nameGetValue);
        editor.putString("surname" , surnameGetValue);
        editor.commit();
        Toast.makeText(getApplicationContext(), "SAVING IS SUCCESFULLY", Toast.LENGTH_SHORT).show();

        // RADIO BUTTON GENDER
        int selectedRadioButton = radioGroup.getCheckedRadioButtonId();
        radioButton = findViewById(selectedRadioButton);

        String gender = radioButton.getText().toString();
        editor.putString("gender" , gender);
        editor.commit();
        Toast.makeText(getApplicationContext(), "SAVING IS SUCCESFULLY", Toast.LENGTH_SHORT).show();

    }
    @Override

    protected void onStart() {
        super.onStart();
        callDatasAfterReflesh();
    }



    public void callDatasAfterReflesh(){
        // PLAIN BOXES
        SharedPreferences sharedPreferences = getSharedPreferences("SAVE" , Context.MODE_PRIVATE);
    String nameGet = sharedPreferences.getString("name", "");

    String surnameGet = sharedPreferences.getString("surname", "");
    String  GenderGet = sharedPreferences.getString("gender" ,"");

    name.setText(nameGet);
    surname.setText(surnameGet);
// RADIO BUTTONS
    RadioButton radioButtonWoman = findViewById(R.id.radioButton);
    RadioButton radioButtonMan = findViewById(R.id.radioButton2);

    if(GenderGet.equalsIgnoreCase("man")){
        radioButtonMan.setChecked(true);

    } else if (GenderGet.equalsIgnoreCase("woman")) {
            radioButtonWoman.setChecked(true);
    }
    Toast.makeText(getApplicationContext(), "WRITING IS SUCCESFULLY", Toast.LENGTH_SHORT).show();

    }





}
