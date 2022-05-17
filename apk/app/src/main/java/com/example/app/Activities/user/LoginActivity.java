package com.example.app.Activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.util.Log;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.app.Activities.MainActivity;
import com.example.app.R;
import com.example.app.Classes.LoginRequest;
import com.example.app.Classes.LoginResponse;
import com.example.app.Classes.Session;
import com.example.app.Apis.Api;


import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Session SESSION = MainActivity.getSESSION();
    LinearLayout loginLinearLayout;
    TextView loginSuccess;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginLinearLayout= findViewById(R.id.loginLinearLayout);

        loginSuccess = findViewById(R.id.loginSuccess);
        loginSuccess.setVisibility(View.INVISIBLE);

        EditText et_loginLogin = findViewById(R.id.loginLogin);
        EditText et_loginPassword = findViewById(R.id.loginPassword);

        Button loginButton = findViewById(R.id.loginButton);
        Button registerButton = findViewById(R.id.registerButton);

        registerButton.setOnClickListener(view -> startActivity(new Intent(getBaseContext(), RegisterActivity.class)));
        loginButton.setOnClickListener(view -> {
            refreshErrors();
            LoginRequest loginRequest = new LoginRequest();
            loginRequest.setLogin(et_loginLogin.getText().toString());
            loginRequest.setPassword(et_loginPassword.getText().toString());
            authenticateViaApi(loginRequest);
        });
    }

    protected void authenticateViaApi(LoginRequest loginRequest) {
        Api.UserApi().authenticateUser(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {
                    LoginResponse loginResponse = response.body();
                    SESSION.setToken(loginResponse.getToken());
                    SESSION.setUser(loginResponse.getUser());
                    successLogin();
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            returnToCatalog();
                    }
                }, 3000);
                } else {
                    try {
                        assert response.errorBody() != null;
                        displayErrors(response.errorBody().string().replace("\"", ""));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d("LoginActivity", t.getMessage());
            }
        });
    }

    protected void successLogin() {
        loginLinearLayout.setVisibility(View.INVISIBLE);
        loginSuccess.setVisibility(View.VISIBLE);
    }

    protected void refreshErrors() {
        TextView textView = findViewById(R.id.loginError);
        textView.setVisibility(View.GONE);
    }

    protected void displayErrors(String error) {
        TextView textView = findViewById(R.id.loginError);
        textView.setText(error);
        textView.setVisibility(View.VISIBLE);
    }

    private void returnToCatalog() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}