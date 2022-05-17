package com.example.app.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.app.R;
import com.example.app.Classes.Menu;

import java.util.List;

public class MenuListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Menu> menuList;

    public MenuListViewAdapter(Context context, List<Menu> menuList) {
        this.context = context;
        this.menuList = menuList;
    }

    @Override
    public int getCount() {
        return menuList.size();
    }

    @Override
    public Object getItem(int pos) {
        return menuList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return pos;
    }

    @Override
    public View getView(int pos, View view, ViewGroup parent) {
        Log.d("MainActivity", "oui");
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.menu_card, null);
        }

        Menu menu = menuList.get(pos);

        //reference to layout objects
        TextView tv_menuName = view.findViewById(R.id.menuName);
        TextView tv_menuPrice = view.findViewById(R.id.menuPrice);
        TextView tv_menuDescription = view.findViewById(R.id.menuDescription);
        ImageView iv_menuImage = view.findViewById(R.id.menuImage);

        Glide.with(context).load(menu.getData("image")).into(iv_menuImage);
        //import data
        tv_menuName.setText(menu.getData("name"));
        tv_menuDescription.setText(menu.getData("description"));
        tv_menuPrice.setText(menu.getData("price")+"€");

        return view;
    }
}
