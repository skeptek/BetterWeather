package com.skeptek.betterweather;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.skeptek.betterweather.model.Weather;

import org.json.JSONException;

import java.util.ArrayList;


public class MultiCityActivity extends Activity {

    private TextView cityText;
    private TextView condDescr;
    private TextView temp;
    private TextView press;
    private TextView windSpeed;
    private TextView windDeg;

    private TextView hum;
    private ImageView imgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_city);
        String city = "lat=55.5&lon=37.5&cnt=10";
        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});
        /*
        String city = "San Jose,USA";

        Intent intent = getIntent();
        String singleCityText = intent.getStringExtra(MainActivity.EXTRA_SINGLE_CITY);
        if(singleCityText != null) {
            city = singleCityText;
        }

        //String bbox = "12,32,15,37,10";

        cityText = (TextView) findViewById(R.id.cityText);
        condDescr = (TextView) findViewById(R.id.condDescr);
        temp = (TextView) findViewById(R.id.temp);
        hum = (TextView) findViewById(R.id.hum);
        press = (TextView) findViewById(R.id.press);
        windSpeed = (TextView) findViewById(R.id.windSpeed);
        windDeg = (TextView) findViewById(R.id.windDeg);
        imgView = (ImageView) findViewById(R.id.condIcon);

        JSONWeatherTask task = new JSONWeatherTask();
        task.execute(new String[]{city});
        */
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private class JSONWeatherTask extends AsyncTask<String, Void, ArrayList<Weather>> {

        @Override
        protected ArrayList doInBackground(String... params) {
            ArrayList<Weather> weatherArrayList = new ArrayList<Weather>();
            String data = ((new MultiCityWeatherHttpClient().getWeatherData(params[0])));

            try {
                weatherArrayList = JSONMultiCityWeatherParser.getWeather(data);

                // Let's retrieve the icon
                //weather.iconData = ((new WeatherHttpClient()).getImage(weather.currentCondition.getIcon()));

            } catch (JSONException e) {
                e.printStackTrace();
            }
            return weatherArrayList;

        }

        @Override
        protected void onPostExecute(ArrayList<Weather> weatherArrayList) {
            super.onPostExecute(weatherArrayList);
            TextView debugTextView = (TextView) findViewById(R.id.debugText);
            debugTextView.setText(String.format("length:%d", weatherArrayList.size()));
            TextView weatherIdTextView = (TextView) findViewById(R.id.weatherIdValue);
            weatherIdTextView.setText(String.format("first id:%d", weatherArrayList.get(0).currentCondition.getWeatherId()));

            /*
            if (weather.iconData != null && weather.iconData.length > 0) {
                Bitmap img = BitmapFactory.decodeByteArray(weather.iconData, 0, weather.iconData.length);
                imgView.setImageBitmap(img);
            }

            cityText.setText(weather.location.getCity() + "," + weather.location.getCountry());
            condDescr.setText(weather.currentCondition.getCondition() + "(" + weather.currentCondition.getDescr() + ")");
            temp.setText("" + Math.round((weather.temperature.getTemp() - 273.15)) + "�C");
            hum.setText("" + weather.currentCondition.getHumidity() + "%");
            press.setText("" + weather.currentCondition.getPressure() + " hPa");
            windSpeed.setText("" + weather.wind.getSpeed() + " mps");
            windDeg.setText("" + weather.wind.getDeg() + "�");

            */
        }
    }
}
