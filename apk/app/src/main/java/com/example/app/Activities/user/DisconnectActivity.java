package com.example.app.Activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.app.Activities.MainActivity;
import com.example.app.Apis.Api;
import com.example.app.Classes.Session;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DisconnectActivity extends AppCompatActivity {
    Session SESSION = MainActivity.getSESSION();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disconnectViaApi();
    }

    private void disconnectViaApi() {
        Api.UserApi().disconnectUser(SESSION.getToken()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                SESSION.setToken(null);
                returnToCatalog();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }
    private void returnToCatalog() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}