package com.example.app.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.app.R;
import com.example.app.Classes.Restaurant;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Restaurant> restaurantList;
    private int imgnum = 0;

    public ListViewAdapter(Context context, List<Restaurant> restaurantList) {
        this.context = context;
        this.restaurantList = restaurantList;
    }

    @Override
    public int getCount() {
        return restaurantList.size();
    }

    @Override
    public Object getItem(int pos) {
        return restaurantList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {

        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.restaurant_card, null);
        }

        Restaurant restaurant = restaurantList.get(pos);
        ImageView imageView = view.findViewById(R.id.restaurantImage);

        imgnum++;
        if(imgnum >2) {
            imgnum = 0;
        }

        String url = "";
        switch (imgnum) {
            case 0:
                url = "http://10.0.2.2:8000/api/restaurant/image/resto_tacos.jpg";
                restaurant.updateData("image", url);
                break;
            case 1:
                url = "http://10.0.2.2:8000/api/restaurant/image/resto_sushis.jpg";
                restaurant.updateData("image", url);
                break;
            case 2:
                url = "http://10.0.2.2:8000/api/restaurant/image/resto_burger.jpg";
                restaurant.updateData("image", url);
                break;
        }
        Glide.with(context).load(url).into(imageView);

        TextView tv_restaurantName = view.findViewById(R.id.restaurantName);
        TextView tv_restaurantLocalization = view.findViewById(R.id.restaurantLocalization);
        TextView tv_restaurantGrade = view.findViewById(R.id.restaurantGrade);
        TextView tv_restaurantDescription = view.findViewById(R.id.restaurantDescription);

        tv_restaurantName.setText(restaurant.getData("name"));
        tv_restaurantLocalization.setText(restaurant.getData("localization"));
        tv_restaurantGrade.setText(restaurant.getData("grade"));
        tv_restaurantDescription.setText(restaurant.getData("description"));

        return view;
    }
}
