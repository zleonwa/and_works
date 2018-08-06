package com.example.dami.myidol;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyBaseAdapter extends BaseAdapter {

    Context context = null;
    ArrayList<Idol> data = null;
    LayoutInflater layoutInflater = null;

    public MyBaseAdapter(Context context, ArrayList<Idol> data){
        this.context = context;
        this.data = data;
        this.layoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // view 는 변경된 값 사용하기 위한 애
        View itemLayout = layoutInflater.inflate(R.layout.listview_item, null);

        // findViewById()에서 검색된(생성된) View를 ViewHolder에 저장
        TextView tvGroup = (TextView) itemLayout.findViewById(R.id.tvGroup);
        TextView tvName = (TextView) itemLayout.findViewById(R.id.tvName);
        ImageView img = (ImageView) itemLayout.findViewById(R.id.img);

        tvGroup.setText(data.get(i).group);
        tvName.setText(data.get(i).iname);
        img.setImageResource(data.get(i).img);
        return itemLayout;
    }
}
