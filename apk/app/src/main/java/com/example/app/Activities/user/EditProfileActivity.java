package com.example.app.Activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.Activities.MainActivity;
import com.example.app.R;
import com.example.app.Classes.Session;
import com.example.app.Classes.User;
import com.example.app.Apis.Api;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    Session SESSION = MainActivity.getSESSION();
    LinearLayout modifyLinearLayout;
    TextView modifySuccess;
    EditText et_firstname;
    EditText et_name;
    EditText et_email;
    EditText et_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        et_firstname = findViewById(R.id.modifyFirstname);
        et_name = findViewById(R.id.modifyName);
        et_email = findViewById(R.id.modifyEmail);
        et_age = findViewById(R.id.modifyAge);

        modifyLinearLayout = findViewById(R.id.modifyLinearLayout);
        modifySuccess = findViewById(R.id.modifySuccess);

        Button modifyButton = findViewById(R.id.modifyButton);

        modifyButton.setOnClickListener(view -> modifyUser());
    }

    private void modifyUser() {
        refreshErrors();
        User user = new User();
        String firstname = et_firstname.getText().toString();
        String name = et_name.getText().toString();
        String email = et_email.getText().toString();
        String age = et_age.getText().toString();
        user.setData("firstname", firstname);
        user.setData("name", name);
        user.setData("email", email);
        user.setData("age", age);
        modifyUserViaApi(user);
    }

    private void modifyUserViaApi(User user) {
        Api.UserApi().modifyUser("Bearer "+SESSION.getToken(), user).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()) {
                    SESSION.setUser(response.body());
                    showSuccess();
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            returnToProfile();
                        }
                    }, 3000);
                } else {
                    if  (response.errorBody() != null) {
                        Gson gson = new Gson();
                        try {
                            User user = gson.fromJson(response.errorBody().string(), User.class);
                            displayErrors(user);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.d("RegisterActivity", "null");
                    }
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void showSuccess() {
        modifyLinearLayout.setVisibility(View.GONE);
        modifySuccess.setVisibility(View.VISIBLE);
    }

    private void returnToProfile() {
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

    private void refreshErrors() {
        TextView textView;
        textView = findViewById(R.id.modifyFirstnameError);
        textView.setVisibility(View.GONE);
        textView = findViewById(R.id.modifyNameError);
        textView.setVisibility(View.GONE);
        textView = findViewById(R.id.modifyEmailError);
        textView.setVisibility(View.GONE);
        textView = findViewById(R.id.modifyAgeError);
        textView.setVisibility(View.GONE);

    }
    private void displayErrors(User user) {
        if(user.getData("firstname") != null) {
            TextView textView = findViewById(R.id.modifyFirstnameError);
            textView.setText(user.getData("firstname"));
            textView.setVisibility(View.VISIBLE);
        }
        if(user.getData("name") != null) {
            TextView textView = findViewById(R.id.modifyNameError);
            textView.setText(user.getData("name"));
            textView.setVisibility(View.VISIBLE);
        }
        if(user.getData("email") != null) {
            TextView textView = findViewById(R.id.modifyEmailError);
            textView.setText(user.getData("email"));
            textView.setVisibility(View.VISIBLE);
        }
        if(user.getData("age") != null) {
            TextView textView = findViewById(R.id.modifyAgeError);
            textView.setText(user.getData("age"));
            textView.setVisibility(View.VISIBLE);
        }
    }
}