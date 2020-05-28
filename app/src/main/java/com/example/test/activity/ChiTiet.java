package com.example.test.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.test.R;
import com.example.test.adapter.DBhelper;
import com.example.test.models.City;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ChiTiet extends AppCompatActivity {
    private static final String EXTRA_DATA = "EXTRA_DATA";
    ImageButton btnBack;
    ListView lvChiTiet;
    ArrayList<String> adapter;
    FloatingActionButton fabAdd;
    DBhelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet);
        /////
        btnBack = (ImageButton) findViewById(R.id.btnBack);
        lvChiTiet = (ListView) findViewById(R.id.lvChiTiet);
        fabAdd = (FloatingActionButton) findViewById(R.id.fabAdd);
        dbh = new DBhelper(ChiTiet.this);
        /////
        allView();
        fabAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ChiTiet.this, MapsActivity.class);
                startActivity(intent);
            }
        });
        lvChiTiet.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final Intent data = new Intent();
                data.putExtra("name", ""+position);
                setResult(Activity.RESULT_OK, data);
                finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                onBackPressed();
            }
        });
        lvChiTiet.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(ChiTiet.this);
                alertDialog.setTitle("Delete this local!");
                alertDialog.setMessage("Are you sure you want to delete this local?");
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ArrayList<City> delete = dbh.getAllWords();
//                        Toast.makeText(ChiTiet.this, delete.get(delete.size()-1).getID()+"a", Toast.LENGTH_SHORT).show();
                        long result = dbh.Delete(delete.get(position).getID());
                        if (result==1){
                            Toast.makeText(ChiTiet.this, "Delete success!", Toast.LENGTH_SHORT).show();
                            allView();
                        }else{
                            Toast.makeText(ChiTiet.this, "Delete false!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                alertDialog.setNegativeButton("No", null);
                alertDialog.show();
                return true;
            }
        });
    }

    private void allView() {
        final ArrayList<City> cities = dbh.getAllWords();
        adapter = new ArrayList<>();
        /////
        for (int i=0;i<cities.size();i++){
            String s = "";
            int x = adapter.size();
            s+="Thành Phố : "+cities.get(i).getCity_Name()+ "----- Quốc Gia: "+cities.get(i).getCountry();
            adapter.add(s);
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(ChiTiet.this, android.R.layout.simple_list_item_1, adapter);
        lvChiTiet.setAdapter(arrayAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        allView();
    }

    protected void onStart() {
        super.onStart();
        dbh.openDB();
    }

    protected void onStop() {
        super.onStop();
        dbh.closeDB();
    }
}
