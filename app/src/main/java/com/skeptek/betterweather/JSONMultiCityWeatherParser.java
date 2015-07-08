package com.skeptek.betterweather;

import com.skeptek.betterweather.model.Location;
import com.skeptek.betterweather.model.Weather;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by tor611 on 6/27/15.
 */
public class JSONMultiCityWeatherParser {
    private static final String TAG = "JSONMultiCityWeatherP";

    public static ArrayList<Weather> getWeather(String data) throws JSONException{
        ArrayList<Weather> weatherArrayList = new ArrayList<Weather>();

        // We create out JSONObject from the data
        JSONObject jObj = new JSONObject(data);
        //Log.d(TAG, String.format("JSON length:%d" + jObj.length()));

        JSONArray cityJsonArray = jObj.optJSONArray("list");
        for(int i=0; i < cityJsonArray.length(); i++) {
            JSONObject jsonObject = cityJsonArray.getJSONObject(i);

            Weather weather = new Weather();
            weather.currentCondition.setWeatherId(jsonObject.optInt("id", 0));
            Location location = new Location();
            location.setCity(jsonObject.optString("name", "City Name Not Found"));
            weather.location = location;
            JSONObject weatherMainJsonObject = jsonObject.getJSONObject("main");
            weather.temperature.setTemp((float)weatherMainJsonObject.optDouble("temp", 12345));

            weatherArrayList.add(weather); //add 1 object for testing

            //int id = Integer.parseInt(jsonObject.optString("id").toString());
            //String name = jsonObject.optString("name").toString();
            //float salary = Float.parseFloat(jsonObject.optString("salary").toString());

            //data += "Node"+i+" : \n id= "+ id +" \n Name= "+ name +" \n Salary= "+ salary +" \n ";
        }

        return weatherArrayList;
    }

    private static JSONObject getObject(String tagName, JSONObject jObj)  throws JSONException {
        JSONObject subObj = jObj.getJSONObject(tagName);
        return subObj;
    }

    private static String getString(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getString(tagName);
    }

    private static float  getFloat(String tagName, JSONObject jObj) throws JSONException {
        return (float) jObj.getDouble(tagName);
    }

    private static int  getInt(String tagName, JSONObject jObj) throws JSONException {
        return jObj.getInt(tagName);
    }

}
