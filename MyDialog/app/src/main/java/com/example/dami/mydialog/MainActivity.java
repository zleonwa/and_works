package com.example.dami.mydialog;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Dialog dailog = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dailog = new Dialog(MainActivity.this);
        dailog.setTitle("My Dialog");
        dailog.setContentView(R.layout.dialog_layout);

        Button btnShow = (Button)findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dailog.show();
            }
        });

        Button btnCancel = (Button) dailog.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dailog.dismiss();
            }
        });

    }
}
