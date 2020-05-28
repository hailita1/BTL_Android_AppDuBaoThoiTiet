package com.example.test.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.test.R;
import com.example.test.models.City;
import com.example.test.models.Recycle_City;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import javax.xml.transform.Templates;

public class ShopLocal extends RecyclerView.Adapter<ShopLocal.ViewHolder> {
    ArrayList<Recycle_City> local;
    Context context;

    public ShopLocal(ArrayList<Recycle_City> local, Context context) {
        this.local = local;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.local_custom,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Recycle_City local1 = local.get(position);
        /////
        holder.tvCountry.setText(local1.getTvCountry());
        holder.tvCity.setText(local1.getTvCity());
        holder.tvNhietDo.setText(local1.getTvNhietDo());
        holder.tvMinMax.setText(local1.getTvMinMax());
        holder.tvStatus.setText(local1.getTvLineStatus());
        holder.tvDoAm.setText(local1.getTvDoAm());
        holder.tvCloud.setText(local1.getTvMay());
        holder.tvSpeed.setText(local1.getTvGio());
        holder.tvHours.setText(local1.getTvHours());
        holder.tvSunOn.setText(local1.getTvSunOn());
        holder.tvSunOff.setText(local1.getTvSunOff());
        Picasso.get().load("http://openweathermap.org/img/wn/" + local1.getIcon() + ".png").into(holder.imgWeather);
        /////
        holder.imgLocal.setImageResource(R.drawable.marker);
//        holder.imgWeather.setImageResource(R.drawable.sun);
        holder.imgCloud.setImageResource(R.drawable.rainn);
        holder.imgDoAm.setImageResource(R.drawable.cloudddd);
        holder.imgSpeed.setImageResource(R.drawable.snowflake);
        holder.imgSunOn.setImageResource(R.drawable.sunrise);
        holder.imgSunOff.setImageResource(R.drawable.sunset);
    }

    @Override
    public int getItemCount() {
        return local.size();
    }
    public void RemoveItems(){
        notifyItemRangeRemoved(0, getItemCount());

    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCountry, tvCity, tvNhietDo, tvMinMax, tvStatus, tvDoAm, tvCloud, tvSpeed, tvHours, tvSunOn, tvSunOff;
        ImageView imgLocal, imgWeather, imgCloud, imgDoAm, imgSpeed, imgSunOn, imgSunOff;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCountry = itemView.findViewById(R.id.tv_country_local);
            tvCity = itemView.findViewById(R.id.tv_city_local);
            tvNhietDo = itemView.findViewById(R.id.tv_nhietdo_local);
            tvMinMax = itemView.findViewById(R.id.tvMinmax_local);
            tvStatus = itemView.findViewById(R.id.tv_line_status_local);
            tvDoAm = itemView.findViewById(R.id.tv_doam_local);
            tvCloud = itemView.findViewById(R.id.tv_may_local);
            tvSpeed = itemView.findViewById(R.id.tv_gio_local);
            tvHours = itemView.findViewById(R.id.tv_hours_local);
            tvSunOn = itemView.findViewById(R.id.tv_sunrise_local);
            tvSunOff = itemView.findViewById(R.id.tv_sunset_local);
            /////
            imgLocal = itemView.findViewById(R.id.imgLocal);
            imgWeather = itemView.findViewById(R.id.img_weather_local);
            imgCloud = itemView.findViewById(R.id.imgCloud);
            imgDoAm = itemView.findViewById(R.id.imgDoAm);
            imgSpeed = itemView.findViewById(R.id.imgSpeed);
            imgSunOn = itemView.findViewById(R.id.imgSunOn);
            imgSunOff = itemView.findViewById(R.id.imgSunOff);
        }
    }
}
