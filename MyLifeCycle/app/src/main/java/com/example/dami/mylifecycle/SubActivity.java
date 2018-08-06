package com.example.dami.mylifecycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SubActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub);

        Intent intent = getIntent();
        TextView tv1 = (TextView)findViewById(R.id.tv1);
        TextView tv2 = (TextView)findViewById(R.id.tv2);
        Button btnBack = (Button)findViewById(R.id.btnBack);

        String name = intent.getStringExtra("name");
        final String newName = "Hello "+name;

        tv1.setText(String.format("전달받은 값: %s", name));
        tv2.setText(String.format("변경될 값: %s", newName));

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent newIntent = new Intent();
                newIntent.putExtra("new_name",newName);

                setResult(RESULT_OK, newIntent); // 결과 보내기
                finish(); // 현재 창 닫아 줘야 메인 액티비티가 전면에 노출된다.
            }
        });
    }
}
