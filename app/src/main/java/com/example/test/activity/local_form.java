package com.example.test.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.test.R;
import com.example.test.adapter.DBhelper;
import com.example.test.adapter.ShopLocal;
import com.example.test.models.City;
import com.example.test.models.Recycle_City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class local_form extends AppCompatActivity {
    private static final String EXTRA_DATA = "EXTRA_DATA";
    private static final int REQUEST_CODE_EXAMPLE = 99;
    RecyclerView recyclerView;
    ImageButton btnBack;
    Button btnItem;
    DBhelper dbh;
    String tvCountry, tvCity, tvNhietDo, tvMinMax, tvLineStatus, tvDoAm, tvMay, tvGio, tvHours, tvSunOn, tvSunOff, icon, x, y;
    int i = 0;
    ArrayList<Recycle_City> recycle_cities;
    SnapHelper snapHelper = new LinearSnapHelper();
    ArrayList<City> check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_local_form);
        /////
        btnBack = (ImageButton) findViewById(R.id.btn_local_back);
        dbh = new DBhelper(local_form.this);
        recyclerView = (RecyclerView) findViewById(R.id.recycle_view);
        recyclerView.hasFixedSize();
        /////
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager
                (local_form.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        btnItem = (Button) findViewById(R.id.btnItem);
        /////
        Anhxa();
        check = dbh.getAllWords();
        ShowLocal(0, 0);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        snapHelper.attachToRecyclerView(recyclerView);
        /////
        btnItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(local_form.this, ChiTiet.class);
                startActivityForResult(intent, REQUEST_CODE_EXAMPLE);
            }
        });
    }

    private void Anhxa() {
        recycle_cities = new ArrayList<Recycle_City>();
        ShopLocal shopLocal = new ShopLocal(recycle_cities, local_form.this);
        recyclerView.setAdapter(shopLocal);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_EXAMPLE) {
            if (resultCode == Activity.RESULT_OK) {

                final String result = data.getStringExtra("name");
                int items = Integer.parseInt(result);
                recyclerView.scrollToPosition(items);
            } else {
                // DetailActivity không thành công, không có data trả về.
            }
        }
    }

    private void ShowLocal(int st, int end) {
        final ArrayList<City> cities = dbh.getAllWords();
        if (cities.size() == 0) {
            Toast.makeText(this, "Chưa có địa chỉ nào!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (end == 0) {
            end = cities.size();
        }
        for (int j = st; j < end; j++) {
            RequestQueue requestQueue_weather = Volley.newRequestQueue(local_form.this);
            String url_weather = "https://api.openweathermap.org/data/2.5/weather?lat=" + cities.get(j).getLat() + "&lon=" + cities.get(j).getLng() + "&units=metric&appid=92c6161e0d9ddd64a865f69b71a89c31&lang=vi";
            final StringRequest stringRequest_weather = new StringRequest(Request.Method.GET, url_weather, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    try {
                        final ArrayList<City> cityTry = dbh.getAllWords();
                        tvCity = cityTry.get(recycle_cities.size()).getCity_Name();
                        tvCountry = cityTry.get(recycle_cities.size()).getCountry();
                        //nhận dữ liệu trả về từ api
                        JSONObject jsonObject = new JSONObject(response);
//                        tv_country.setText(formatted_address);
                        String day = jsonObject.getString("dt");
                        long l = Long.valueOf(day);
                        Date date = new Date(l * 1000L);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd-MM HH:mm");
                        String Day = simpleDateFormat.format(date);
                        tvHours = Day;
                        JSONArray jsonArray = jsonObject.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        //get status
                        tvLineStatus = jsonObjectWeather.getString("description");
                        //get image
                        icon = jsonObjectWeather.getString("icon");
//                        Picasso.get().load("http://openweathermap.org/img/wn/" + icon + ".png").into();
                        JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                        String nhietdo = jsonObjectMain.getString("temp");
                        //get temp max, temp min
                        String temp_max = jsonObjectMain.getString("temp_max");
                        String temp_min = jsonObjectMain.getString("temp_min");
                        //Lam tron
                        Double a = Double.valueOf(temp_max);
                        Double b = Double.valueOf(temp_min);
                        String Temp_max = String.valueOf(a.intValue());
                        String Temp_min = String.valueOf(b.intValue());
                        tvMinMax = Temp_min + "°/" + Temp_max + "°";
                        tvDoAm = "Độ ẩm: " + jsonObjectMain.getString("humidity") + "%";
                        Double x = Double.valueOf(nhietdo);
                        String Nhietdo = String.valueOf(x.intValue());
                        tvNhietDo = Nhietdo + "°";
                        JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                        tvGio = "Gió: " + jsonObjectWind.getString("speed") + "m/s";
                        JSONObject jsonObjectCloud = jsonObject.getJSONObject("clouds");
                        tvMay = "Mây: " + jsonObjectCloud.getString("all") + "%";
                        JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                        tvSunOn = jsonObjectSys.getString("sunrise");
                        tvSunOff = jsonObjectSys.getString("sunset");
                        l = Long.valueOf(tvSunOn);
                        date = new Date(l * 1000L);
                        simpleDateFormat = new SimpleDateFormat("HH:mm");
                        tvSunOn = "Mặt trời mọc: " + simpleDateFormat.format(date);
                        l = Long.valueOf(tvSunOff);
                        date = new Date(l * 1000L);
                        simpleDateFormat = new SimpleDateFormat("HH:mm");
                        tvSunOff = "Mặt trời lặn: " + simpleDateFormat.format(date);
                        recycle_cities.add(new Recycle_City(tvCountry, tvCity, tvNhietDo, tvMinMax, tvLineStatus
                                , tvDoAm, tvMay, tvGio, tvHours, tvSunOn, tvSunOff, icon));

                        ShopLocal shopLocal = new ShopLocal(recycle_cities, local_form.this);
                        recyclerView.setAdapter(shopLocal);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(local_form.this, "Không có dữ liệu", Toast.LENGTH_SHORT).show();
                }
            });
            requestQueue_weather.add(stringRequest_weather);

        }
    }

    protected void onStart() {
        super.onStart();
        dbh.openDB();
    }

    @Override
    protected void onResume() {
        super.onResume();
        final ArrayList<City> Rf = dbh.getAllWords();
        if (Rf.size() > check.size()) {
            ShowLocal(check.size(), Rf.size());
        } else if (Rf.size() < check.size()) {
            ShopLocal shopLocal = new ShopLocal(recycle_cities, local_form.this);
            shopLocal.RemoveItems();
            recyclerView.setAdapter(null);
            recycle_cities.clear();
            ShowLocal(0, 0);
        }
        check = dbh.getAllWords();
    }

    protected void onStop() {
        super.onStop();
        dbh.closeDB();
    }
}
