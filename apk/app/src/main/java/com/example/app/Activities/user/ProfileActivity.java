package com.example.app.Activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.app.Activities.MainActivity;
import com.example.app.R;
import com.example.app.Adapters.ListViewAdapter;
import com.example.app.Activities.restaurant.DisplayRestaurantActivity;
import com.example.app.Activities.restaurant.PostRestaurantActivity;
import com.example.app.Classes.Restaurant;
import com.example.app.Apis.Api;
import com.example.app.Classes.Grade;
import com.example.app.Adapters.GradesListViewAdapter;
import com.example.app.Classes.Session;
import com.example.app.Classes.User;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    Session SESSION = MainActivity.getSESSION();
    User user = SESSION.getUser();
    ArrayList<Restaurant> restaurants;
    //ArrayList<Comment> comments;
    ArrayList<Grade> grades;
    ListViewAdapter listViewAdapter;
    GradesListViewAdapter gradesListViewAdapter;
    LinearLayout Opt = null;
    Integer sw = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        loadNavbar();

        restaurants = new ArrayList<>();
        grades = new ArrayList<>();

        // = new ArrayList<>();
        ImageView profileImage = findViewById(R.id.profileImage);
        TextView profileUsername = findViewById(R.id.profileUsername);
        TextView profileFirstname = findViewById(R.id.profileFirstname);
        TextView profileName = findViewById(R.id.profileName);
        TextView profileEmail = findViewById(R.id.profileEmail);
        TextView profileAge = findViewById(R.id.profileAge);
        Button settingsButton = findViewById(R.id.profileSettingsButton);
        Button commentsButton = findViewById(R.id.profileCommentsButton);
        Button restaurantsButton = findViewById(R.id.profileRestaurantsButton);
        Button postRestaurantButton = findViewById(R.id.postRestaurantButton);

        listViewAdapter = new ListViewAdapter(getApplicationContext(), restaurants);
        gradesListViewAdapter = new GradesListViewAdapter(getApplicationContext(), grades);

        ListView lv_grades = findViewById(R.id.profileCommentListView);
        ListView lv_restaurants = findViewById(R.id.profileRestaurantListView);
        lv_restaurants.setAdapter(listViewAdapter);
        lv_grades.setAdapter(gradesListViewAdapter);
        postRestaurantButton.setVisibility(View.GONE);
        lv_restaurants.setVisibility(View.GONE);
        lv_grades.setVisibility(View.VISIBLE);

        Glide.with(getBaseContext())
                .load("https://www.serieously.com/app/uploads/2021/10/luffy-funny-face.jpg")
                .apply(new RequestOptions().override(profileImage.getWidth(), profileImage.getHeight()))
                .centerCrop()
                .into(profileImage);
        profileUsername.setText(user.getData("username"));
        profileFirstname.setText(user.getData("firstname"));
        profileName.setText(user.getData("name"));
        profileEmail.setText(user.getData("email"));
        profileAge.setText(user.getData("age") + " ans");

        settingsButton.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), ProfileSettingsActivity.class);
            startActivity(intent);
        });

        getUserRestaurantViaApi();
        getUserGradesViaApi();


        restaurantsButton.setOnClickListener(view -> {
            postRestaurantButton.setVisibility(View.VISIBLE);
            lv_restaurants.setVisibility(View.VISIBLE);
            lv_grades.setVisibility(View.GONE);
        });

        commentsButton.setOnClickListener(view -> {
            postRestaurantButton.setVisibility(View.GONE);
            lv_restaurants.setVisibility(View.GONE);
            lv_grades.setVisibility(View.VISIBLE);
        });

        // View Options to modify and delete


        postRestaurantButton.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), PostRestaurantActivity.class);
            startActivity(intent);
        });

        lv_restaurants.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(getBaseContext(), DisplayRestaurantActivity.class);
            intent.putExtra("Restaurant", restaurants.get(i));
            intent.putExtra("edit", "true");
            startActivity(intent);
        });
    }

    protected void getUserRestaurantViaApi() {
        Api.UserApi().getUserRestaurants("Bearer " + SESSION.getToken()).enqueue(new Callback<List<Restaurant>>() {
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

    protected void getUserGradesViaApi() {
        Api.UserApi().getUserGrades("Bearer " + SESSION.getToken()).enqueue(new Callback<List<Grade>>() {
            @Override
            public void onResponse(Call<List<Grade>> call, Response<List<Grade>> response) {
                List<Grade> gradeList = response.body();
                System.out.println(response.body());
                if (gradeList != null) {
                    grades.addAll(gradeList);
                    gradesListViewAdapter.notifyDataSetChanged();
                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Grade>> call, Throwable t) {
                Log.d("MainActivity", t.getMessage());
            }
        });
    }

    protected void deleteGradeViaApi(Grade grade) {
        Api.GradeApi().deleteGrade("Bearer "+SESSION.getToken(), grade.getData("id")).enqueue(new Callback<Grade>() {
            @Override
            public void onResponse(Call<Grade> call, Response<Grade> response) {
                if (response.isSuccessful()) {
                    finish();
                    startActivity(getIntent());
                }
            }

            @Override
            public void onFailure(Call<Grade> call, Throwable t) {

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
}