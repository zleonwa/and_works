package com.example.dami.myidol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView = null;
    private ArrayList<Idol> data = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        data = new ArrayList<Idol>();
        /*for(int i=1; i<=50; i++){
            Idol d = new Idol();
            d.group = "레드벨벳";
            d.iname = "아이린";
            d.img = R.drawable.i1;

            data.add(d);
        }*/


        for(int i=1; i<=10; i++){
            data.add(new Idol("레드벨벳","아이린", R.drawable.i1));
            data.add(new Idol("레드벨벳","예리", R.drawable.i2));
            data.add(new Idol("레드벨벳","조이", R.drawable.i3));
            data.add(new Idol("레드벨벳","슬기", R.drawable.i4));
            data.add(new Idol("레드벨벳","웬디", R.drawable.i5));
        }

        MyBaseAdapter adapter = new MyBaseAdapter(this, data);
        listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);

    }
}
