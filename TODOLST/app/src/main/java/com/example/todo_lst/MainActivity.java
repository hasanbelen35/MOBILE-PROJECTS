package com.example.todo_lst;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   // IMPORTING ITEMS;
    EditText text ;
    ImageButton btn;
    ListView listView;
    ArrayList<String> listItems = new ArrayList<>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    // DEFIENING ALL ITEMS;
        listView = findViewById(R.id.listView);
        btn = findViewById(R.id.imageButton);
        text = findViewById(R.id.editText);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1 , listItems);
        listView.setAdapter(adapter);

        // DELETING ITEMS;
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alertBox = new AlertDialog.Builder(MainActivity.this);
                alertBox.setMessage("Are you sure to delete?");

                alertBox.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        listItems.remove(position);
                        adapter.notifyDataSetChanged();
                    }
                });
                alertBox.setNegativeButton("Cancel", null);
                alertBox.show();
                return true;
            }
        });

        // BTN EVENT;
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String getTextFromInput = text.getText().toString();
                    // INPUT EMPTY QUERY
                      if(getTextFromInput.isEmpty()){
                         Toast.makeText(getApplicationContext(), "Enter value", Toast.LENGTH_SHORT).show();
                            } else{
                                   listItems.add(getTextFromInput);
                      }
                      adapter.notifyDataSetChanged()
                      text.setText("");
            }
        });
    }
}