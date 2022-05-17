package com.example.app.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.app.Activities.restaurant.DisplayRestaurantActivity;
import com.example.app.R;
import com.example.app.Adapters.ListViewAdapter;
import com.example.app.Classes.Restaurant;
import com.example.app.Classes.Session;
import com.example.app.Activities.user.LoginActivity;
import com.example.app.Activities.user.ProfileActivity;
import com.example.app.Apis.Api;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static final Session SESSION = new Session();

    private ListView listView;
    private ListViewAdapter listViewAdapter;
    private List<Restaurant> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogue);
        loadNavbar();

        restaurants = new ArrayList<>();

        this.listView = findViewById(R.id.restaurantList);
        this.listViewAdapter = new ListViewAdapter(getApplicationContext(), restaurants);
        this.listView.setAdapter(listViewAdapter);

        this.getRestaurantViaApi();

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Restaurant restaurant = restaurants.get(i);
            Intent restaurantIntent = new Intent(getBaseContext(), DisplayRestaurantActivity.class);
            restaurantIntent.putExtra("Restaurant", restaurant);
            startActivity(restaurantIntent);
        });
    }

    protected void getRestaurantViaApi() {
        Api.RestaurantApi().getRestaurants().enqueue(new Callback<List<Restaurant>>() {
            @Override
            public void onResponse(Call<List<Restaurant>> call, Response<List<Restaurant>> response) {
                List<Restaurant> restaurantList = response.body();
                if (restaurantList != null) {
                    restaurants.addAll(restaurantList);
                    listViewAdapter.notifyDataSetChanged();
                } else {
                    Log.d("MainActivity", "non");
                }
            }

            @Override
            public void onFailure(Call<List<Restaurant>> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }
        });
    }

    private void loadNavbar() {
//        swipeRefreshLayout swipeUp = findViewById(R.id.SwipeLayout);
        Button homeButton = findViewById(R.id.homeButton);
        Button profileButton = findViewById(R.id.profileButton);
        //searchButton = findViewById(R.id.searchButton);

        homeButton.setOnClickListener(view -> goTo(new Intent(getBaseContext(), MainActivity.class)));
        profileButton.setOnClickListener(view -> {
            if (SESSION.getToken() != null) {
                goTo(new Intent(getBaseContext(), ProfileActivity.class));
            } else {
                goTo(new Intent(getBaseContext(), LoginActivity.class));
            }
        });
        //searchButton.setOnClickListener(view -> goTo(new Intent(getBaseContext(), MainActivity.class)));
    }

    private void goTo(Intent intent) {
        startActivity(intent);
    }

    public static Session getSESSION() { return SESSION; }

}