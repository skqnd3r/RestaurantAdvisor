package com.example.app.Activities.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.example.app.Activities.MainActivity;
import com.example.app.R;
import com.example.app.Classes.Session;
import com.example.app.Apis.Api;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileSettingsActivity extends AppCompatActivity {
    Session SESSION = MainActivity.getSESSION();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        Button delete = findViewById(R.id.profileSettingsDeleteButton);
        Button disconnect = findViewById(R.id.profileSettingDisconnectButton);
        Button modify = findViewById(R.id.profileModifyButton);

        delete.setOnClickListener(view -> deleteViaApi());
        disconnect.setOnClickListener(view -> disconnectViaApi());
        modify.setOnClickListener(view -> {
            Intent intent = new Intent(getBaseContext(), EditProfileActivity.class);
            startActivity(intent);
        });
    }

    private void disconnectViaApi() {
        Api.UserApi().disconnectUser("Bearer "+SESSION.getToken()).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                SESSION.setToken(null);
                SESSION.setUser(null);
                returnToCatalog();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void deleteViaApi() {
        Api.UserApi().deleteUser("Bearer "+SESSION.getToken()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                SESSION.setToken(null);
                SESSION.setUser(null);
                returnToCatalog();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }

    private void returnToCatalog() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}