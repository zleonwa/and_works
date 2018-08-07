package com.example.dami.myweather.service;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dami.myweather.MainActivity;
import com.example.dami.myweather.R;
import com.example.dami.myweather.model.Forecast;
import com.example.dami.myweather.model.WeatherForecast;
import com.example.dami.myweather.util.ConvertDate;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MyBaseAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Forecast> list;
    private LayoutInflater layoutInflater = null;

    public MyBaseAdapter(Context context, ArrayList<Forecast> list){
        this.context = context;
        this.list = list;
        this.layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    // 어댑터 성능 향상 뷰홀더
    class ViewHolder{
        TextView tvDate;
        TextView tvTemp;
        TextView tvDesc;
        ImageView imageView;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // inflate해서 layout.xml 파일 메모리에 올리기
        View row = view;
        ViewHolder viewHolder = null;

        if(row == null) {
            row = View.inflate(context, R.layout.forecast_row, null);
            viewHolder = new ViewHolder();
            // 레이아웃 다른 애에 있어서 find 그냥하면 안돼, row라는 레이아웃 파일에서 찾아야 함.
            viewHolder.tvDate = (TextView) row.findViewById(R.id.tvDate);
            viewHolder.tvTemp = (TextView) row.findViewById(R.id.tvTemp);
            viewHolder.tvDesc = (TextView) row.findViewById(R.id.tvDesc);
            viewHolder.imageView = (ImageView) row.findViewById(R.id.imageView);
            // 태그에 넣어
           row.setTag(viewHolder);

        } else{
            viewHolder = (ViewHolder) row.getTag();
        }
        try {
            viewHolder.tvDate.setText(ConvertDate.TO_GMT(list.get(i).dt));
            viewHolder.tvTemp.setText(list.get(i).main.temp + "\u2103");
            viewHolder.tvDesc.setText(list.get(i).weather.description);
            viewHolder.imageView.setTag(list.get(i).weather.icon);
            //Log.d("SimpleWeather", list.get(i).weather.icon);
            // 이미지 스레드
            // http://openweathermap.org/img/w/02d.png
            ImageViewTask imageTask = new ImageViewTask(context);
            imageTask.execute(viewHolder.imageView);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return row;
    }
}
