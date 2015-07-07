package com.skeptek.betterweather;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends Activity {

    private TextView cityText;
    private TextView condDescr;
    private TextView temp;
    private TextView press;
    private TextView windSpeed;
    private TextView windDeg;

    private TextView hum;
    private ImageView imgView;

    public final static String EXTRA_SINGLE_CITY = "com.skeptek.betterweather.SINGLE_CITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public void startSingleCityActivity(View view) {
        Intent intent = new Intent(this, SingleCityActivity.class);
        EditText editText = (EditText)findViewById(R.id.singleCity);
        String singleCityText = editText.getText().toString();
        intent.putExtra(EXTRA_SINGLE_CITY, singleCityText);
        startActivity(intent);
    }

    public void startMultiCityActivity(View view) {
        Intent intent = new Intent(this, MultiCityActivity.class);
        startActivity(intent);
    }

}