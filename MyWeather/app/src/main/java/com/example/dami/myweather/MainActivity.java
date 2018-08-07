package com.example.dami.myweather;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.dami.myweather.model.Forecast;
import com.example.dami.myweather.model.WeatherForecast;
import com.example.dami.myweather.service.ImageViewTask;
import com.example.dami.myweather.service.MyBaseAdapter;
import com.example.dami.myweather.service.WeatherApi;

import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public TextView tvView = null;
    public LinearLayout forecast_layout = null;
    public ListView listView = null;
    public Spinner spinner = null;
    public Button btnSearch = null;
    public String local = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("SimpleWeather","MainActivity");

        tvView = (TextView) findViewById(R.id.tvView);
        //forecast_layout = (LinearLayout) findViewById(R.id.forcast_layout);
        listView = (ListView) findViewById(R.id.listView);
        spinner = (Spinner) findViewById(R.id.spinner);
        btnSearch = (Button) findViewById(R.id.btnSearch);

        String[] data = getResources().getStringArray(R.array.city_list);

        ArrayAdapter<String> adpater = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, data);
        spinner.setAdapter(adpater);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // MyWeatherTask myWeatherTask = new MyWeatherTask(this);
                MyWeatherTask myWeatherTask = new MyWeatherTask(getApplicationContext());
                myWeatherTask.execute(spinner.getSelectedItem().toString());
                local = spinner.getSelectedItem().toString();
            }
        });

        // thread
    }

    // 지역에 해당하는 String, 진행사항 따로 필요 없으니까 void, 리턴 값 WeatherForecast
    class MyWeatherTask extends AsyncTask<String, Void, WeatherForecast>{

        ProgressDialog loading = new ProgressDialog(MainActivity.this);

        // 보통 컨텍스트를 받음
        private final Context context;
        public MyWeatherTask(Context context){
            this.context = context;
        }

        @Override
        protected void onPreExecute() {
            loading.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            loading.setMessage("날씨 데이터 불러오는 중");
            super.onPreExecute();
        }

        @Override
        protected WeatherForecast doInBackground(String... locations) {
            // HttpURLConnection
            try{
                return WeatherApi.getWeather(this.context, locations[0]);
            } catch (Exception ex){
                ex.printStackTrace();
            }
            return null;
        }

        // 데이터가 다 모아져야 어댑터 생성할 수 있으니까 스레드가 다 완료되고 나서 어댑터를 생성해야 함...
        @Override
        protected void onPostExecute(WeatherForecast weatherForecast) {
            tvView.setText(local);
            //forecast_layout.set
            MyBaseAdapter adapter = new MyBaseAdapter(MainActivity.this, (ArrayList<Forecast>) weatherForecast.forecastList);
            listView.setAdapter(adapter);

            SystemClock.sleep(5000);
            loading.dismiss();
            super.onPostExecute(weatherForecast);
        }
    }
}







/*    weather.city.name -> seoul
    weather.list[0].dt.txt -> 2018-08-07 03:00:00
    weather.list[0].main.temp -> 32.03
    weather.list[0].weather[0].decription -> light rain
    weather.list[0].weather[0].icon -> 10d
    {
        "list": [
        {
            "dt": 1533610800,
                "main": {
            "temp": 32.03,
                    "temp_min": 27.05,
                    "temp_max": 32.03,
                    "humidity": 87,
        },
            "weather": [
            {
                "main": "Rain",
                    "description": "light rain",
                    "icon": "10d"
            }
	    ],
            "dt_txt": "2018-08-07 03:00:00"
        }, ...........* 40*/
