package com.example.weatherapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.commons.lang3.StringUtils;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity{

    ImageButton button;
    Typeface legoFont;
    LinearLayout newsLayout;
    ConstraintLayout layout;
    ImageView currentImg;
    TextView name;
    TextView tempText;
    TextView quote;
    TextView newsText;
    TextView cityText;
    TextView descText;
    EditText editText;
    ListView listView;
    Quotes quotes = new Quotes();
    TextView breakingNews;
    ProgressBar progressBar;
    Boolean theme = true;

    ArrayList<DailyWeather> weatherlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        if(theme){
            setTheme(R.style.MarioTheme);
        }
        else
        {
            setTheme(R.style.AppTheme);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.id_constraint);
        newsLayout = findViewById(R.id.id_newsLayout);
        legoFont = ResourcesCompat.getFont(this, R.font.mario );
        button = findViewById(R.id.request);
        name = findViewById(R.id.textView2);
        quote = findViewById(R.id.id_quote);
        breakingNews = findViewById(R.id.textView3);
        tempText = findViewById(R.id.textView);
        editText = findViewById(R.id.id_editText);
        cityText =  findViewById(R.id.id_cityname);
        listView = findViewById(R.id.weatherDisplay);
        currentImg = findViewById(R.id.id_currentImg);
        descText = findViewById(R.id.id_description);
        newsText = findViewById(R.id.id_news);
        newsText.setSelected(true);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        if(theme){
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
            layout.setBackgroundResource(R.drawable.background);
            editText.setTypeface(legoFont);
            tempText.setTypeface(legoFont);
            cityText.setTypeface(legoFont);
            newsText.setTypeface(legoFont);
            descText.setTypeface(legoFont);
            breakingNews.setTypeface(legoFont);
            quote.setTypeface(legoFont);
            name.setTypeface(legoFont);
        }
        else
        {
            layout.setBackgroundResource(R.drawable.bg_gradient);
        }

        weatherlist = new ArrayList<>();
        final CustomAdapter customAdapter = new CustomAdapter(this, R.layout.adapter_custom, weatherlist);
        listView.setAdapter(customAdapter);

        new newsThread().execute();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = editText.getText().toString();
                new weatherThread().execute(input);
            }
        });
    }

    public class weatherThread extends AsyncTask<String, Integer, List<JSONObject>> {

        @Override
        protected List<JSONObject> doInBackground(String... strings) {
            JSONObject dataForecast = null;
            JSONObject dataWeather = null;
            List<JSONObject> list = new ArrayList<JSONObject>();
            try {
                String cityLink1 =  "https://api.openweathermap.org/data/2.5/forecast?q=";
                String cityLink2 =  "https://api.openweathermap.org/data/2.5/weather?q=";

                String link1 =  "https://api.openweathermap.org/data/2.5/forecast?zip=";
                String link2 =  "https://api.openweathermap.org/data/2.5/weather?zip=";

                String APIKey = ",us&APPID=94dd43ad2e9c4f7021f03f4e2260569e&units=imperial";
                String cityAPIKey = "&APPID=94dd43ad2e9c4f7021f03f4e2260569e&units=imperial";

                URL url = null;
                URL url2 = null;

                if((strings[0].matches("[a-zA-Z ]+")) || (strings[0].contains(" "))){
                    url = new URL(cityLink1 + strings[0] + cityAPIKey);
                    url2 = new URL(cityLink2 + strings[0] + cityAPIKey);
                }
                else{
                    url = new URL(link1 + strings[0] + APIKey);
                    url2 = new URL(link2 + strings[0] + APIKey);
                }

                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                connection.disconnect();

                connection = (HttpURLConnection) url2.openConnection();
                connection.setRequestMethod("GET");

                in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine2;
                StringBuffer content2 = new StringBuffer();
                while ((inputLine2 = in.readLine()) != null) {
                    content2.append(inputLine2);
                }
                in.close();
                connection.disconnect();

                dataForecast = new JSONObject(content.toString());
                dataWeather = new JSONObject(content2.toString());

                list.add(dataForecast);
                list.add(dataWeather);

            } catch (Exception e) {
                e.printStackTrace();
                Log.d("TAG", ":(");
            }
            return list;
        }

        @Override
        protected void onPreExecute() {
            CustomAdapter myAdapter = (CustomAdapter) listView.getAdapter();
            weatherlist.clear();
            myAdapter.notifyDataSetChanged();
            currentImg.setImageResource(0);
            progressBar.setVisibility(View.VISIBLE);
            tempText.setText("");
            descText.setText("");
            cityText.setText("");
        }



        @Override
        protected void onPostExecute(List<JSONObject> listOfObj) {
            try {

                if(!(listOfObj.isEmpty())) {
                    CustomAdapter myAdapter = (CustomAdapter) listView.getAdapter();

                    JSONArray list = (listOfObj.get(0).getJSONArray("list"));
                    JSONObject city = (listOfObj.get(0).getJSONObject("city"));

                    int startIndex = 0;
                    for (int i = 0; i < list.length(); i++) {
                        JSONObject temp = list.getJSONObject(i);
                        String date = temp.getString("dt_txt");
                        if (date.contains("12:")) {
                            startIndex = i;
                            i = list.length() + 1;
                        }
                    }

                    for (int i = 0; i < 5; i++) {
                        JSONObject currentForecast = list.getJSONObject(startIndex);
                        JSONObject currentMain = currentForecast.getJSONObject("main");
                        JSONArray currentWeatherA = currentForecast.getJSONArray("weather");
                        JSONObject currentWeatherB = (JSONObject) currentWeatherA.get(0);

                        weatherlist.add(new DailyWeather(currentMain.get("temp_max").toString(),
                                currentMain.get("temp_min").toString(),
                                currentForecast.get("dt_txt").toString(), currentWeatherB.get("icon").toString()));
                        startIndex += 8;
                    }

                    cityText.setText(city.get("name").toString());
                    myAdapter.notifyDataSetChanged();

                    JSONObject currentWeather = (JSONObject) listOfObj.get(1).get("main");
                    JSONArray weatherArray = listOfObj.get(1).getJSONArray("weather");
                    JSONObject moreWeatherInfo = (JSONObject) weatherArray.get(0);

                    String currentTempString = currentWeather.get("temp").toString();
                    double currentTemp = Double.parseDouble(currentTempString);
                    tempText.setText(Math.round(currentTemp) + "°");
                    descText.setText(StringUtils.capitalize(moreWeatherInfo.get("description").toString()));

                    switch(moreWeatherInfo.get("main").toString()){
                        case("Clouds"):
                            int rand = (int)(Math.random()*2)+1;
                            switch (rand){
                                case(1):
                                    quote.setText(quotes.cloudy);
                                    break;
                                case(2):
                                    quote.setText(quotes.cloudy2);
                                    break;
                            }
                            break;
                        case("Clear"):
                            int rand3 = (int)(Math.random()*2)+1;
                            switch (rand3){
                                case(1):
                                    quote.setText(quotes.clear);
                                    break;
                                case(2):
                                    quote.setText(quotes.clear2);
                                    break;
                            }
                            break;
                        case("Snow"):
                            quote.setText(quotes.snow);
                            break;
                        case("Rain"):
                            int rand2 = (int)(Math.random()*2)+1;
                            switch (rand2){
                                case(1):
                                    quote.setText(quotes.rain);
                                    break;
                                case(2):
                                    quote.setText(quotes.rain2);
                                    break;
                            }
                            break;
                        case("Drizzle"):
                            quote.setText(quotes.drizzle);
                            break;
                        case("Thunderstorm"):
                            quote.setText(quotes.thunderstorm);
                            break;
                    }

                    String iconFirstLink = "https://openweathermap.org/img/wn/";
                    String iconCode = moreWeatherInfo.get("icon").toString();
                    String iconLink = iconFirstLink + iconCode + "@2x.png";

                    Picasso.with(MainActivity.this).load(iconLink).into(currentImg);
                }
                else{
                    cityText.setText("City Not Found");
                    descText.setText(":(");
                    tempText.setText("");
                    quote.setText("");
                    Toast.makeText(MainActivity.this, "Whoops. Something went wrong", Toast.LENGTH_LONG).show();
                }


            } catch (JSONException e) {
                e.printStackTrace();
            }
            progressBar.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0]);
        }
    }

    public class newsThread extends AsyncTask<Void, Void, String>{
        @Override
        protected String doInBackground(Void... voids) {

            String link =  "https://newsapi.org/v2/top-headlines?country=us&apiKey=dbcd03fd30e643138a73d04b39384eaa";
            StringBuffer content = new StringBuffer();
            try {
                URL url = new URL(link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");

                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            try{
                JSONObject jsonObject = new JSONObject(s);
                JSONArray array = jsonObject.getJSONArray("articles");
                String news = "";
                for(int i = 0; i < 5; i++){
                    JSONObject temp = array.getJSONObject(i);
                    String tempTitle = temp.get("title").toString();
                    news += tempTitle + " ";
                }

                newsText.setText(news);
            }
            catch (Exception e){

            }
        }
    }

    public class CustomAdapter extends ArrayAdapter<DailyWeather> {

        List<DailyWeather> list;
        Context context;
        int xmlresource;

        public CustomAdapter(@NonNull Context context, int resource, @NonNull List<DailyWeather> objects) {
            super(context, resource, objects);
            this.context = context;
            xmlresource = resource;
            list = objects;
        }

        @NonNull
        @Override
        public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            View adapterView = layoutInflater.inflate(xmlresource, null);
            TextView dateTxt = adapterView.findViewById(R.id.id_adapter_date);
            TextView lowTxt = adapterView.findViewById(R.id.id_adapter_low);
            TextView highTxt = adapterView.findViewById(R.id.id_adapter_high);
            ImageView icon = adapterView.findViewById(R.id.id_adapter_imageview);

            dateTxt.setText(formatDate(list.get(position).getDate()));
            lowTxt.setText(list.get(position).getLow());
            highTxt.setText(list.get(position).getHigh());
            if(theme) {
                dateTxt.setTypeface(legoFont);
                lowTxt.setTypeface(legoFont);
                highTxt.setTypeface(legoFont);
            }

            String iconFirstLink = "https://openweathermap.org/img/wn/";
            String iconCode = list.get(position).getIcon();
            String iconLink = iconFirstLink + iconCode + "@2x.png";

            Picasso.with(MainActivity.this).load(iconLink).into(icon);

            return adapterView;
        }
    }

    public class DailyWeather{
        String high;
        String low;
        String date;
        String icon;
        String main;

        public DailyWeather(String high, String low, String date, String icon){
            this.high = high;
            this.low = low;
            this.date = date;
            this.icon = icon;
            this.main = main;
        }

        public String getDate(){
            return date;
        }

        public String getLow(){
            return low;
        }

        public String getHigh(){
            return high;
        }

        public String getIcon(){
            return icon;
        }

        public String getMain(){ return main; }

    }
    public String formatDate(String str){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = null;
        try {
            date = simpleDateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String finalDate = date.toString();

        ArrayList<String> list = new ArrayList<>();
        String temp = "";
        for(int i = 0; i < finalDate.length(); i++){
            if(finalDate.charAt(i) != ' '){
                temp += finalDate.charAt(i);
            }
            else{
                list.add(temp);
                temp = "";
            }
        }

        finalDate = list.get(0) + ", " + list.get(1) + " " + list.get(2);
        return finalDate;
    }
}

