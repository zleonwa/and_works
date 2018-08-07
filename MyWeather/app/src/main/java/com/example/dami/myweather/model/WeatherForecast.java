package com.example.dami.myweather.model;

import com.example.dami.myweather.util.ConvertDate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherForecast {
    public final List<Forecast> forecastList = new ArrayList<>();

    public WeatherForecast(JSONObject json) throws JSONException, ParseException{
        JSONArray array = json.getJSONArray("list"); //  복수형 데이터 값 가져올 때 사용
        int length = array.length();
        // weather -> current, tomorrow, tomorrow after
        for(int i=0; i<length; i++){
            JSONObject j = array.getJSONObject(i);

            if(i > 0){
                if(forecastList.size() == 3){
                    break;
                } else{
                    if(!j.getString("dt_txt").endsWith("03:00:00")){
                        continue;
                    }
                }
            }
            Forecast forecast = new Forecast(j);
            forecastList.add(forecast);
        }
    }
}
