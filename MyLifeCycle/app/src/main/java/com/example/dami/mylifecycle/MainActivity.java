package com.example.dami.mylifecycle;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final String TAG = "[Life cycle method]";
    static final int REQUEST_CODE = 100;
    EditText etName;
    EditText etName2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toast.makeText(getApplicationContext(), "onCreate() 호출됨", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onCreate() 호출됨");

        Button btnChange = (Button) findViewById(R.id.btnChange);
        etName = (EditText) findViewById(R.id.etName);
        etName2 = (EditText) findViewById(R.id.etName2);

        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(getApplicationContext(),SubActivity.class);
                intent.putExtra("name", etName.getText().toString());
                startActivityForResult(intent,REQUEST_CODE);

            }
        });

        Button prefDel = (Button) findViewById(R.id.prefDel);
        prefDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref = getSharedPreferences("mypref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                if(pref != null && pref.contains("name")) {
                    editor.putString("name", "");
                    editor.clear();
                    editor.commit();
                }
            }
        });
    }

    // setResult할 때, 호출됨 // 100이란 숫자로 subActivity를 알 수 있다.
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == REQUEST_CODE){
            switch (resultCode){
                case RESULT_OK:
                    String newName = data.getStringExtra("new_name");
                    if(newName!=null&& !newName.isEmpty()){
                        etName2.setText(newName);
                    }
                    break;
                case RESULT_CANCELED:
                    break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Toast.makeText(getApplicationContext(), "onStart() 호출됨", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStart() 호출됨");
    }

    @Override
    protected void onStop() {
        super.onStop();
        //Toast.makeText(getApplicationContext(), "onStop() 호출됨", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onStop() 호출됨");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(getApplicationContext(), "onDestroy() 호출됨", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onDestroy() 호출됨");
    }

    @Override
    protected void onPause() {
        super.onPause();
        //Toast.makeText(getApplicationContext(), "onPause() 호출됨", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onPause() 호출됨");

        saveState();

    }

    @Override
    protected void onResume() {
        super.onResume();
        //Toast.makeText(getApplicationContext(), "onResume() 호출됨", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onResume() 호출됨");
        restoreState();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Toast.makeText(getApplicationContext(), "onRestart() 호출됨", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onRestart() 호출됨");
    }

    private void saveState(){
        SharedPreferences pref = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        if(pref != null && pref.contains("name")) {
            editor.putString("name", etName.getText().toString());
            editor.commit();
        }
    }
    private void restoreState(){
        // Bundle
        SharedPreferences pref = getSharedPreferences("mypref", Context.MODE_PRIVATE);
        if(pref != null && pref.contains("name")){ // 저장된 값이 있다면 가져와서 보여주기
            etName.setText(pref.getString("name",""));
        }
    }
}
