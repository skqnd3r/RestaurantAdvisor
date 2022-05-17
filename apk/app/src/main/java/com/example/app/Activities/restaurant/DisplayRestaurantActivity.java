package com.example.app.Activities.restaurant;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.app.Activities.MainActivity;
import com.example.app.R;
import com.example.app.Classes.Restaurant;
import com.example.app.Classes.Grade;
import com.example.app.Apis.Api;
import com.example.app.Activities.comment.PostGradeActivity;
import com.example.app.Classes.Menu;
import com.example.app.Activities.menu.PostMenuActivity;
import com.example.app.Activities.user.LoginActivity;
import com.example.app.Activities.user.ProfileActivity;
import com.example.app.Classes.Session;


import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisplayRestaurantActivity extends AppCompatActivity {

    Session SESSION = MainActivity.getSESSION();
    private List<Menu> menus;
    private List<Grade> grades;

    private LinearLayout menuList;
    private LinearLayout commentList;
    private Restaurant restaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_restaurant);
        loadNavbar();

        restaurant = (Restaurant) getIntent().getSerializableExtra("Restaurant");
        ImageView restaurantImage = findViewById(R.id.restaurantDisplayImage);
        TextView restaurantName = findViewById(R.id.restaurantDisplayName);
        TextView restaurantDescription = findViewById(R.id.restaurantDisplayDescription);
        TextView restaurantGrade = findViewById(R.id.restaurantDisplayGrade);
        TextView restaurantLocalization = findViewById(R.id.restaurantDisplayLocalization);
        TextView restaurantWebsite = findViewById(R.id.restaurantDisplayWebsite);
        TextView restaurantPhoneNumber = findViewById(R.id.restaurantDisplayPhoneNumber);
        TextView restaurantHours = findViewById(R.id.restaurantDisplayHours);


        Glide.with(getBaseContext()).load(restaurant.getData("image")).into(restaurantImage);
        restaurantName.setText(restaurant.getData("name"));
        restaurantDescription.setText(restaurant.getData("description"));
        restaurantGrade.setText(restaurant.getData("grade"));
        restaurantLocalization.setText(restaurant.getData("localization"));
        restaurantWebsite.setText(restaurant.getData("website"));
        restaurantPhoneNumber.setText(restaurant.getData("phone_number"));
        restaurantHours.setText(restaurant.getData("hours"));

        //SetLists
        menuList = findViewById(R.id.menuList);
        commentList = findViewById(R.id.commentList);

        menus = new ArrayList<>();
        this.getMenuViaApi(restaurant);
        grades = new ArrayList<>();
        this.getCommentViaApi(restaurant);

        if (getIntent().getStringExtra("edit") != null) {
            Button addMenuButton = findViewById(R.id.addMenuButton);
            Button deleteRestaurant = findViewById(R.id.deleteRestaurant);
            deleteRestaurant.setVisibility(View.VISIBLE);
            addMenuButton.setVisibility(View.VISIBLE);
            Intent intent = new Intent(getBaseContext(), PostMenuActivity.class);
            intent.putExtra("Restaurant", restaurant);
            addMenuButton.setOnClickListener(view -> startActivity(intent));
            deleteRestaurant.setOnClickListener(view -> deleteRestaurantViaApi());
        }

        menuList.setVisibility(View.VISIBLE);

        Button addGradeButton = findViewById(R.id.addGradeButton);

        //menubutton
        findViewById(R.id.menusButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                commentList.setVisibility(View.GONE);
                menuList.setVisibility(View.VISIBLE);
            }
        });

        //comments button
        if (SESSION.getToken() != null) {
//            if has a comment or not
            int i = 0;
            if (i == 0) {
                addGradeButton.setVisibility(View.VISIBLE);
            } else {
                addGradeButton.setVisibility(View.GONE);
            }
        }

        // Listener for Button addGrade

        findViewById(R.id.commentsButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuList.setVisibility(View.GONE);
                commentList.setVisibility(View.VISIBLE);
            }
        });

        addGradeButton.setOnClickListener(v -> {
            Intent gradeIntent = new Intent(v.getContext(), PostGradeActivity.class);
            gradeIntent.putExtra("Restaurant", restaurant);
            startActivity(gradeIntent);
        });
    }

    protected void getMenuViaApi(Restaurant restaurant) {
        Api.MenuApi().getMenus(restaurant.getData("id")).enqueue(new Callback<List<Menu>>() {

            @Override
            public void onResponse(Call<List<Menu>> call, Response<List<Menu>> response) {
                List<Menu> menuList = response.body();
                if (menuList != null) {
                    // for X in x
                    for (Menu menu : menuList) {
                        menu.setImage(restaurant.getData("image"));
                    }
                    menus.addAll(menuList);
                    populateMenus();
                } else {
                    Log.d("MainActivity", "non");
                }
            }

            @Override
            public void onFailure(Call<List<Menu>> call, Throwable t) {
                Log.d("MainActivity", "Fail");

                Log.d("MainActivity", t.getMessage());
            }
        });
    }

    protected void getCommentViaApi(Restaurant restaurant) {
        Api.GradeApi().getComments(restaurant.getData("id")).enqueue(new Callback<List<Grade>>() {

            @Override
            public void onResponse(Call<List<Grade>> call, Response<List<Grade>> response) {
                List<Grade> commentList = response.body();
                if (commentList != null) {
                    grades.addAll(commentList);
                    populateComments();
                } else {
                    Log.d("MainActivity", "RLISTnon");
                }
            }

            @Override
            public void onFailure(Call<List<Grade>> call, Throwable t) {
                Log.d("MainActivity", "Fail");

                Log.d("MainActivity", t.getMessage());
            }
        });
    }

    private void populateMenus() {
        LinearLayout linearLayout = menuList;
        LayoutInflater inflater = LayoutInflater.from(this);
        for (Menu menu : menus) {
            View view = inflater.inflate(R.layout.menu_card, linearLayout, false);

            TextView menuName = view.findViewById(R.id.menuName);
            menuName.setText(menu.getData("name"));

            TextView menuPrice = view.findViewById(R.id.menuPrice);
            menuPrice.setText(menu.getData("price") + " €");

            TextView menuDescription = view.findViewById(R.id.menuDescription);
            menuDescription.setText(menu.getData("description"));

            ImageView menuImage = view.findViewById(R.id.menuImage);
            Glide.with(view.getContext()).load(menu.getData("image")).into(menuImage);

            linearLayout.addView(view);
        }
    }

    private void populateComments() {
        LinearLayout commentLayout = commentList;
        LayoutInflater inflater = LayoutInflater.from(this);
        for (Grade grade : grades) {
            View view = inflater.inflate(R.layout.grade_card, commentLayout, false);

            TextView login = view.findViewById(R.id.login);
            login.setText(grade.getData("user_id"));

            TextView value = view.findViewById(R.id.value);
            value.setText(grade.getData("value"));

            TextView comment = view.findViewById(R.id.comment);
            if(grade.getData("comment") == null){
                comment.setVisibility(View.GONE);
            } else {
                comment.setText(grade.getData("comment"));
            }


            commentLayout.addView(view);
        }
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

    private void deleteRestaurantViaApi() {
        Api.RestaurantApi().deleteRestaurant("Bearer "+SESSION.getToken(), restaurant.getData("id")).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    goTo(new Intent(getBaseContext(), ProfileActivity.class));
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
}