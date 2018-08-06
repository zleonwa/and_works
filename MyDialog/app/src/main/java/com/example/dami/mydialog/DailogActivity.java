package com.example.dami.mydialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class DailogActivity extends AppCompatActivity {
    final String[] menu = {"아메","라떼","카푸치노"};
    final boolean[] checked ={false, false, true};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        Button btnShow = (Button) findViewById(R.id.btnShow);
        btnShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(DailogActivity.this);
                dialog.setTitle("제목");
                //dialog.setMessage("내용ㅇ");
                //dialog.setIcon(R.mipmap.ic_launcher_round);

//                dialog.setItems(menu, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int pos) {
//                        Toast.makeText(DailogActivity.this, menu[pos] + "가 선택되었다", Toast.LENGTH_SHORT).show();
//                    }
//                });

                dialog.setSingleChoiceItems(menu, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int pos) {
                        Toast.makeText(DailogActivity.this, menu[pos] + "가 선택되었다", Toast.LENGTH_SHORT).show();
                    }
                });
//
//                dialog.setMultiChoiceItems(menu, checked, new DialogInterface.OnMultiChoiceClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int pos, boolean isChecked) {
//                        Toast.makeText(DailogActivity.this, menu[pos] + "가 선택되었다", Toast.LENGTH_SHORT).show();
//                    }
//                });

                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DailogActivity.this,"확인버튼클릭",Toast.LENGTH_LONG).show();
                    }
                });

                dialog.setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DailogActivity.this,"취소버튼클릭",Toast.LENGTH_LONG).show();
                    }
                });
                dialog.show();
            }
        });
    }
}
