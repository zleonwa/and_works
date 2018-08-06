package com.example.dami.mydialog;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

       // Button btnJoin = (Button)findViewById(R.id.btnJoin);
    }

    public void onClick(View view){
        AlertDialog.Builder dialog = new AlertDialog.Builder(JoinActivity.this);
        dialog.setTitle("회원가입");
        dialog.setIcon(R.drawable.join);

        //다른 layout 인플레이트함
        final View dialogLayout = View.inflate(JoinActivity.this, R.layout.dialog, null);
        dialog.setView(dialogLayout);

        dialog.setPositiveButton("가입", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                // [이메일, 이름]으로 가입되었습니다.
                EditText tdEmail = (EditText) dialogLayout.findViewById(R.id.edMail);
                EditText tdName = (EditText) dialogLayout.findViewById(R.id.edName);

                Toast.makeText(JoinActivity.this, "["+ tdEmail.getText().toString()
                        + ", "+ tdName.getText().toString() + "]회원 가입이 완료되었다.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
