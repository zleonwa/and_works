package com.example.dami.mylistview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dami.mylistview.adapter.MyBaseAdapter;
import com.example.dami.mylistview.model.Student;

import java.util.ArrayList;

public class ListViewActivity extends AppCompatActivity {
    private ListView listView = null;
    private ArrayList<Student> data = null;
    private MyBaseAdapter adapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        data = new ArrayList<>();
        for(int i=1; i<=100; i++){
            Student student = new Student();
            student.name = "안드로이드";
            student.number = "20180804-" + i;
            student.department = "lecto 16기" + i;

            data.add(student);
        }

        // 어댑터 생성 -> 데이터 설정
        MyBaseAdapter adapter = new MyBaseAdapter(this, data);
        // 리스트뷰에 어댑터 설정
        listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter);
    }

    public void onClick(View v){
        if(v.getId() == R.id.btnAdd){
            EditText edName = (EditText) findViewById(R.id.edName);
            EditText edNum = (EditText) findViewById(R.id.edNumber);
            EditText edDelp = (EditText) findViewById(R.id.edDelp);

            Student std = new Student();
            std.name = edName.getText().toString();
            std.number = edNum.getText().toString();
            std.department = edDelp.getText().toString();
            adapter.add(std);
        } else if(v.getId() == R.id.btnDel){
            EditText edIdx = (EditText) findViewById(R.id.edIdx);
            if(edIdx != null && edIdx.getText() != null && !edIdx.getText().toString().isEmpty()){
                int index = Integer.parseInt(edIdx.getText().toString());
                adapter.remove(index);
            } else{
                Toast.makeText(getApplicationContext(), "삭제할 번호를 입력하세요.", Toast.LENGTH_SHORT).show();
            }
        } else if(v.getId() == R.id.btnClear){
            adapter.clearAll();
        }
    }
}
