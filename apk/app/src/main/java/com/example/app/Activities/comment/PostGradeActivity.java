package com.example.app.Activities.comment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.app.Activities.MainActivity;
import com.example.app.Activities.restaurant.DisplayRestaurantActivity;
import com.example.app.Apis.Api;
import com.example.app.Classes.Grade;
import com.example.app.R;
import com.example.app.Classes.Restaurant;
import com.example.app.Classes.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostGradeActivity extends AppCompatActivity {

    Session SESSION = MainActivity.getSESSION();
    Button button;
    Restaurant restaurant;
    RatingBar ratingBar;
    EditText et_comment;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_grade);
        restaurant = (Restaurant) getIntent().getSerializableExtra("Restaurant");

        button = findViewById(R.id.postGradeButton);
        et_comment = findViewById(R.id.comment);
        ratingBar = findViewById(R.id.ratingBar);
        title = findViewById(R.id.title);

        title.setText(restaurant.getData("name"));

        button.setOnClickListener(view -> postGrade());
    }

    public void postGrade(){
        String comment = et_comment.getText().toString();
        Float value = ratingBar.getRating();
        Grade grade = new Grade();
        grade.setComment(comment);
        Integer i = Math.round(value);
        grade.setValue(i.toString());
        Api.GradeApi().postGrade("Bearer "+SESSION.getToken(), restaurant.getData("id"), grade).enqueue(new Callback<Grade>() {
            @Override
            public void onResponse(Call<Grade> call, Response<Grade> response) {
                if (response.isSuccessful()) {
                    Intent intent = new Intent(getBaseContext(), DisplayRestaurantActivity.class);
                    intent.putExtra("Restaurant", restaurant);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<Grade> call, Throwable t) {

            }
        });
    }
}
