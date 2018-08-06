package com.example.dami.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.lang.reflect.Array;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String[] items = {"Basic-C","Basic-C++","Basic-C#","Advance-JAVA","Advance-Android", "Advance-Python", "Expert-JavaScript", "Expert-Ruby", "Expert-JQuery","Basic-MFC","Expert-Angular","Basic-React","Expert-Visual Basic"};
        ListView listView = (ListView) findViewById(R.id.listView1);


        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,items);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice,items);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), items[i], Toast.LENGTH_SHORT).show();
            }
        });
    }
}
