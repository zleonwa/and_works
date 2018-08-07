package com.example.dami.myweather.service;

import android.content.Context;
import android.util.Log;

import com.example.dami.myweather.model.WeatherForecast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

// 외부 api 접속 클래스로 스레드 안에서 움직이는 데이터
public class WeatherApi {
    // http://api.openweathermap.org/data/2.5/forecast?apikey=3cc5d95c74a066326579c104c7fe60b2&units=metric&q=Seoul
    public static final String URL_STR = "http://api.openweathermap.org/data/2.5/forecast"
            + "?apikey=3cc5d95c74a066326579c104c7fe60b2"
            + "&units=metric"
            + "&q=";

    // 날씨 정보 얻는 메소드
    public static WeatherForecast getWeather(Context context, String location) throws IOException, JSONException{
        //
        URL url = new URL(URL_STR + (location == null ? "Seoul" : location));
        HttpURLConnection conn = null;
        StringBuilder sb = new StringBuilder();
        JSONObject json = null;

        try {
            // Connection -> create JSON -> return WeatherForecast
            conn = (HttpURLConnection) url.openConnection(); // url을 오픈해서 커넥션 객체 받음
            conn.setConnectTimeout(10000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            int responseCode = conn.getResponseCode(); // 커넥션 객체로부터 응답 코드 받음
            if(responseCode == HttpURLConnection.HTTP_OK) { // 응답 코드가 오케이면
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream())); //
                String line = null;
                while ((line = br.readLine()) != null) { // null이 아닐 때까지
                    sb.append(line+"\n");
                }
                json = new JSONObject(sb.toString());
                WeatherForecast wf = new WeatherForecast(json);
                return wf;
            } else{
                Log.e("WeatherApi error", "URL= " + URL_STR + location);
            }
        } catch(Exception ex){
            Log.e("WeatherApi error", "Error Message = " + ex.getMessage());
        } finally {
            conn.disconnect();
        }
        return null;
    }

}
