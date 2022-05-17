package com.example.app.Activities.restaurant;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.Activities.MainActivity;
import com.example.app.R;
import com.example.app.Apis.Api;
import com.example.app.Classes.Restaurant;
import com.example.app.Classes.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostRestaurantActivity extends AppCompatActivity {
    Session SESSION = MainActivity.getSESSION();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_restaurant);

        EditText et_restaurantName = findViewById(R.id.et_restaurantName);
        EditText et_restaurantLocalization = findViewById(R.id.et_restaurantLocalization);
        EditText et_restaurantDescription = findViewById(R.id.et_restaurantDescription);
        EditText et_restaurantWebsite = findViewById(R.id.et_restaurantWebsite);
        EditText et_restaurantPhoneNumber = findViewById(R.id.et_restaurantPhoneNumber);
        EditText et_restaurantOpenHours = findViewById(R.id.et_restaurantOpenHours);
        EditText et_restaurantCloseHours = findViewById(R.id.et_restaurantCloseHours);

        Button postButton = findViewById(R.id.postButton);

        postButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hours = et_restaurantOpenHours.getText().toString().trim() + " - " + et_restaurantCloseHours.getText().toString().trim();
                Restaurant restaurant = new Restaurant();
                restaurant.updateData("name", et_restaurantName.getText().toString().trim());
                restaurant.updateData("localization", et_restaurantLocalization.getText().toString().trim());
                restaurant.updateData("description", et_restaurantDescription.getText().toString().trim());
                restaurant.updateData("website", et_restaurantWebsite.getText().toString().trim());
                restaurant.updateData("phone_number", et_restaurantPhoneNumber.getText().toString().trim());
                restaurant.updateData("hours", hours);
                postRestaurantViaApi(restaurant);
            }
        });
    }

    protected void postRestaurantViaApi(Restaurant restaurant) {
        Api.RestaurantApi().postRestaurant("Bearer " + SESSION.getToken(), restaurant).enqueue(new Callback<Restaurant>() {
            @Override
            public void onResponse(Call<Restaurant> call, Response<Restaurant> response) {
                returnToCatalog();
            }

            @Override
            public void onFailure(Call<Restaurant> call, Throwable t) {

            }
        });
    }

    private void returnToCatalog() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}

