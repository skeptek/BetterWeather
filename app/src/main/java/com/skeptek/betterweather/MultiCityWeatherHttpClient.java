package com.skeptek.betterweather;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by tor611 on 6/27/15.
 */
public class MultiCityWeatherHttpClient {
    private static String BASE_URL = "http://api.openweathermap.org/data/2.5/find?";
    private static String IMG_URL = "http://openweathermap.org/img/w/";

        public String getWeatherData(String location) {
            HttpURLConnection con = null ;
            InputStream is = null;

            try {
                con = (HttpURLConnection) ( new URL(BASE_URL + location)).openConnection();
                con.setRequestMethod("GET");
                con.setDoInput(true);
                con.setDoOutput(true);
                con.connect();

                // Let's read the response
                StringBuffer buffer = new StringBuffer();
                is = con.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String line = null;
                while (  (line = br.readLine()) != null )
                    buffer.append(line + "\r\n");

                is.close();
                con.disconnect();
                return buffer.toString();
            }
            catch(Throwable t) {
                t.printStackTrace();
            }
            finally {
                try { is.close(); } catch(Throwable t) {}
                try { con.disconnect(); } catch(Throwable t) {}
            }

            return null;

        }

}
