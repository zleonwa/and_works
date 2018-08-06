package com.example.dami.mylistview.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dami.mylistview.R;
import com.example.dami.mylistview.model.Student;

import java.util.ArrayList;

public class MyBaseAdapter extends BaseAdapter {
// 데이터와 화면의 위젯하고 연결하는게 어댑터임

    Context context = null;
    ArrayList<Student> data = null;
    // 레이아웃 관리자인
    LayoutInflater layoutInflater = null;

    public MyBaseAdapter(Context context, ArrayList<Student> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }

    // 데이터 몇개?
    @Override
    public int getCount() {
        return data.size();
    }

    // 해당하는 아이템
    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    // 해당하는 아이템의 아이디
    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        TextView tvName = null;
        TextView tvNumber = null;
        TextView tv = null;
    }

    // 백 개의 데이터를 보여주는 애로, 100번 불리는 애임
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        // 1. 리스트의 한 항목에 해당하는 레이아웃 생성
        View itemLayout = view;
        ViewHolder viewHolder = null;

        if(itemLayout == null) { // 뷰 매번 생성 안하고 널 값일 때만 생송
            layoutInflater.inflate(R.layout.activity_list_view, null);

            // findViewById()에서 검색된(생성된) View를 ViewHolder에 저장
            // ViewHolder 저장된 VIew를 참조
            viewHolder = new ViewHolder();

            TextView tvName = (TextView) itemLayout.findViewById(R.id.tvName);
            TextView tvNumber = (TextView) itemLayout.findViewById(R.id.tvNumber);
            TextView tv = (TextView) itemLayout.findViewById(R.id.tv);

            // 2. 이름, 학번, 학과 데이터를 참조해서 레이아웃을 갱신
            viewHolder.tvName.setText(data.get(position).name);
            viewHolder.tvNumber.setText(data.get(position).number);
            viewHolder.tv.setText(data.get(position).department);

            itemLayout.setTag(viewHolder); // 임시 정보 저장 위한 메소드
        } else{
            viewHolder = (ViewHolder) itemLayout.getTag();
        }
        return itemLayout;
    }

    public void add(Student addData){
        data.add(addData);
        notifyDataSetChanged();
    }

    public void remove(int index){
        data.remove(index);
        notifyDataSetChanged();
    }

    public void clearAll(){
        data.clear();
        notifyDataSetChanged();
    }
}
