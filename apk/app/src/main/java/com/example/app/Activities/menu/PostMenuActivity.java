package com.example.app.Activities.menu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.app.Activities.MainActivity;
import com.example.app.R;
import com.example.app.Classes.Restaurant;
import com.example.app.Activities.restaurant.DisplayRestaurantActivity;
import com.example.app.Classes.Menu;
import com.example.app.Classes.Session;
import com.google.gson.Gson;
import com.example.app.Apis.Api;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostMenuActivity extends AppCompatActivity {

    Session SESSION = MainActivity.getSESSION();
    EditText et_name;
    EditText et_description;
    EditText et_price;
    Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_menu);

        restaurant = (Restaurant) getIntent().getSerializableExtra("Restaurant");
        System.out.println(restaurant.getData("id"));
        et_name = findViewById(R.id.et_menuName);
        et_description = findViewById(R.id.et_menuDescription);
        et_price = findViewById(R.id.et_menuPrice);
        Button addMenu = findViewById(R.id.postMenuButton);

        addMenu.setOnClickListener(view -> addMenu());
    }

    private void addMenu() {
        refreshErrors();
        String name = et_name.getText().toString();
        String description = et_description.getText().toString();
        String price = et_price.getText().toString();
        Menu menu = new Menu();
        menu.setName(name);
        menu.setDescription(description);
        menu.setPrice(price);
        postMenuViaApi(menu);
    }

    private void postMenuViaApi(Menu menu) {
        Api.MenuApi().postMenu("Bearer "+SESSION.getToken(), restaurant.getData("id"), menu).enqueue(new Callback<Menu>() {
            @Override
            public void onResponse(Call<Menu> call, Response<Menu> response) {

                if (response.isSuccessful()) {
                    returnToRestaurantDisplay();
                } else {
                    if (response.errorBody() != null) {
                        Gson gson = new Gson();
                        try {
                            Menu menu = gson.fromJson(response.errorBody().string(), Menu.class);
                            displayErrors(menu);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<Menu> call, Throwable t) {

            }
        });
    }

    private void refreshErrors() {
        TextView textView;
        textView = findViewById(R.id.postMenuNameError);
        textView.setVisibility(View.GONE);
        textView = findViewById(R.id.postMenuDescriptionError);
        textView.setVisibility(View.GONE);
        textView = findViewById(R.id.postMenuPriceError);
        textView.setVisibility(View.GONE);
    }
    private void displayErrors(Menu menu) {
        if(menu.getData("name") != null) {
            TextView textView = findViewById(R.id.postMenuNameError);
            textView.setText(menu.getData("data"));
            textView.setVisibility(View.VISIBLE);
        }
        if(menu.getData("description") != null) {
            TextView textView = findViewById(R.id.postMenuDescriptionError);
            textView.setText(menu.getData("description"));
            textView.setVisibility(View.VISIBLE);
        }
        if(menu.getData("price") != null) {
            TextView textView = findViewById(R.id.postMenuPriceError);
            textView.setText(menu.getData("price"));
            textView.setVisibility(View.VISIBLE);
        }
    }

    private void returnToRestaurantDisplay() {
        Intent intent = new Intent(getBaseContext(), DisplayRestaurantActivity.class);
        intent.putExtra("Restaurant", restaurant);
        startActivity(intent);
    }
}
