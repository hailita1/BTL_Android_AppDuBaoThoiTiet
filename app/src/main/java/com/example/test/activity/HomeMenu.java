package com.example.test.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.example.test.adapter.DBhelper;
import com.example.test.models.City;
import com.example.test.models.Data;
import com.example.test.R;
import com.example.test.adapter.CustomAdapter2;
import com.example.test.models.GoiY;

import java.util.ArrayList;
import java.util.Random;

public class HomeMenu extends AppCompatActivity {

    ListView listView;
    DBhelper dbh;
    ImageButton imgbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
        listView = (ListView) findViewById(R.id.listView);
        imgbtn = findViewById(R.id.imgbtn);
        dbh = new DBhelper(HomeMenu.this);
        dbh.openDB();
        goiY();
        ArrayList<Data> list1 = new ArrayList<Data>();
        list1.add(new Data(R.drawable.loc2, "Thời tiết hiện tại của vị trí thiết bị"));
        list1.add(new Data(R.drawable.search2, "Xem thời tiết theo địa điểm nhập bất kì"));
        list1.add(new Data(R.drawable.calandar, "Xem thời tiết theo địa điểm nhập bất kì và dự báo các ngày kế tiếp"));
        list1.add(new Data(R.drawable.actions_save, "Xem thời tiết theo địa điểm đã lưu"));
        list1.add(new Data(R.drawable.googlmap, "Xem thời tiết trên Google Map"));
        CustomAdapter2 adapter = new CustomAdapter2(HomeMenu.this, R.layout.activity_custom_l_v, list1);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            Intent intent;

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    intent = new Intent(HomeMenu.this, weather_location.class);
                    startActivity(intent);
                } else if (position == 1) {
                    intent = new Intent(HomeMenu.this, MainActivity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    intent = new Intent(HomeMenu.this, seven_day_forecast.class);
                    startActivity(intent);
                } else if (position == 3) {
                    intent = new Intent(HomeMenu.this, local_form.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(HomeMenu.this, MapsActivity.class);
                    startActivity(intent);
                }
            }
        });
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HomeMenu.this, "Need long click!", Toast.LENGTH_LONG).show();
            }
        });
        registerForContextMenu(imgbtn);

    }

    private void goiY() {
        final ArrayList<GoiY> goiYS = dbh.getAllNotes();
        if (goiYS.size() != 0) {
            return;
        }
        long result;
        result = dbh.InsertGoiY(1, "mây thưa", "Nhắc nhở: Thời tiết ít mây, nắng chiếu vừa ra ngoài nên mang theo mũ và áo chống nắng để bảo vệ cơ thể" +
                "\n -- Trời đẹp dành cho những chuyến picnic và dã ngoại, thích hợp với những buổi tham quan và đi chơi xa.");

        result = dbh.InsertGoiY(2, "mây cụm", "Chú ý: Thời tiết nhiều mây dễ mưa. Nên mang ô hoặc áo mưa khi ra ngoài. " +
                "\n -- Thời tiết đẹp để ra ngoài uống cafe với bạn bè.");

        result = dbh.InsertGoiY(3, "mây rải rác", "Nhắc nhở: Thời tiết ít mây, nắng chiếu vừa ra ngoài nên mang theo mũ và áo chống nắng để bảo vệ cơ thể" +
                "\n -- Trời đẹp dành cho những chuyến picnic và dã ngoại, thích hợp với những buổi tham quan và đi chơi xa.");

        result = dbh.InsertGoiY(4, "bầu trời quang đãng", "Chú ý: THời tiết ít mây đôi lúc không có, nắng chiếu gay gắt. " +
                "\n -- Đề nghị: Hạn chế ra ngoài, nếu ra ngoài cần trang bị đầy đủ(áo, mũ, áo trùm, che mặt...) tránh ánh nắng chiếu trực tiếp." +
                "\n -- Nắng lớn thích hợp với việc ở trong nhà làm việc và giặt giũ đồ dùng.");

        result = dbh.InsertGoiY(5, "mưa vừa", "Chú ý: Thời tiết có mưa, mưa thường xuyên. " +
                "\n -- Ra ngoài nên mang theo áo mưu hoặc ô, ưu tiên di chuyển trên các phương tiện có mái che(xe bus, ô tô, ...) " +
                " \n -- Thời tiết thích hợp với việc ở nhà nấu ăn và thực hiện các sở thích có thể làm trong nhà.");

        result = dbh.InsertGoiY(6, "mưa cường độ nặng", "Chú ý: Mưa to, mưa lớn không thích hợp ra ngoài nên hạn chế ra ngoài tối đa. Nếu cần di chuyển ưu tiên " +
                " các phương tiện có mái che(xe bus, ô tô, ...).");

        result = dbh.InsertGoiY(7, "mưa nhẹ", "Nhắc nhở: Thời tiết có mưa, mưa lâm thâm, ra ngoài nên mang theo ô. " +
                "\n -- Thời tiết thích hợp đi dạo, đi chơi với bạn bè");

        result = dbh.InsertGoiY(8, "mây đen u ám", "Chú ý: Trời râm, rất nhiều mây, có khả năng mưa đột ngột. Đề nghị mang theo ô hoặc áo mưa khi ra ngoài" +
                "\n -- Hạn chế ra đường vì có thể mưa bất chợt từ mưa vừa tới mưa to. " +
                "\n -- Ưu tiên làm các buối party tại nhà.");

        if (result == -1) {
            Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Add Done", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.menu, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.btnChiTiet:
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(HomeMenu.this);
                alertDialog.setTitle("Thông tin sản phẩm.");
                alertDialog.setMessage("Sản phẩm giúp người dùng có thể xem dự báo thời tiết ở mọi lúc mọi nơi khi có internet và đưa ra gợi ý đối với các trường hợp thời tiết khác nhau \nRất mong mọi người sẽ sử dụng và ủng hộ nhóm !!!");
                alertDialog.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog.show();
                break;
            case R.id.btnInformation:
                AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(HomeMenu.this);
                alertDialog1.setTitle("Thông tin nhóm.");
                alertDialog1.setMessage("Member: Vũ Quang Minh \nMember: Trần Thanh Hải \nMember: Đỗ Văn Dương \nLớp Công Nghệ Thông Tin 1 - K58");
                alertDialog1.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alertDialog1.show();
                break;
        }
        return super.onOptionsItemSelected(item);
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