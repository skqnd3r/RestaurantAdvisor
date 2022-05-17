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
import com.example.app.Classes.Session;
import com.example.app.R;
import com.example.app.Classes.RegisterRequest;
import com.example.app.Classes.RegisterResponse;
import com.example.app.Apis.Api;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    public Session SESSION = MainActivity.getSESSION();
    LinearLayout registerLinearLayout;
    TextView registerSuccess;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerButton = findViewById(R.id.registerButton);
        EditText et_registerLogin = findViewById(R.id.registerLogin);
        EditText et_registerFirstname = findViewById(R.id.registerFirstname);
        EditText et_registerName = findViewById(R.id.registerName);
        EditText et_registerEmail = findViewById(R.id.registerEmail);
        EditText et_registerPassword = findViewById(R.id.registerPassword);
        EditText et_registerPasswordConfirmation = findViewById(R.id.registerPasswordConfirmation);
        EditText et_registerAge = findViewById(R.id.registerAge);

        registerLinearLayout = findViewById(R.id.registerLinearLayout);

        registerSuccess = findViewById(R.id.registerSuccess);
        registerSuccess.setVisibility(View.INVISIBLE);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                refreshErrors();
                RegisterRequest registerRequest = new RegisterRequest();
                registerRequest.setLogin(et_registerLogin.getText().toString());
                registerRequest.setFirstname(et_registerFirstname.getText().toString());
                registerRequest.setName(et_registerName.getText().toString());
                registerRequest.setEmail(et_registerEmail.getText().toString());
                registerRequest.setPassword(et_registerPassword.getText().toString());
                registerRequest.setPasswordConfirmation(et_registerPasswordConfirmation.getText().toString());
                registerRequest.setAge(et_registerAge.getText().toString());
                registerViaApi(registerRequest);
            }
        });

    }

    private void registerViaApi(RegisterRequest registerRequest) {
        Api.UserApi().registerUser(registerRequest).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful()) {
                    showSuccessRegister();
                    new Timer().schedule(new TimerTask() {

                        @Override
                        public void run() {
                            returnToCatalog();
                        }
                    }, 3000);
                } else {
                    if  (response.errorBody() != null) {
                        Gson gson = new Gson();
                        try {
                            RegisterResponse registerResponse = gson.fromJson(response.errorBody().string(), RegisterResponse.class);
                            displayErrors(registerResponse);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Log.d("RegisterActivity", "null");
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {
            }
        });
    }

    private void showSuccessRegister() {
        registerLinearLayout.setVisibility(View.INVISIBLE);
        registerSuccess.setVisibility(View.VISIBLE);
    }

    private void returnToCatalog() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void refreshErrors() {
        TextView textView;
        textView = findViewById(R.id.registerLoginError);
        textView.setVisibility(View.GONE);
        textView = findViewById(R.id.registerFirstnameError);
        textView.setVisibility(View.GONE);
        textView = findViewById(R.id.registerNameError);
        textView.setVisibility(View.GONE);
        textView = findViewById(R.id.registerEmailError);
        textView.setVisibility(View.GONE);
        textView = findViewById(R.id.registerPasswordError);
        textView.setVisibility(View.GONE);
        textView = findViewById(R.id.registerAgeError);
        textView.setVisibility(View.GONE);

    }
    private void displayErrors(RegisterResponse registerResponse) {
        if(registerResponse.getLogin() != null) {
            TextView textView = findViewById(R.id.registerLoginError);
            textView.setText(registerResponse.getLogin());
            textView.setVisibility(View.VISIBLE);
        }
        if(registerResponse.getFirstname() != null) {
            TextView textView = findViewById(R.id.registerFirstnameError);
            textView.setText(registerResponse.getFirstname());
            textView.setVisibility(View.VISIBLE);
        }
        if(registerResponse.getName() != null) {
            TextView textView = findViewById(R.id.registerNameError);
            textView.setText(registerResponse.getName());
            textView.setVisibility(View.VISIBLE);
        }
        if(registerResponse.getEmail() != null) {
            TextView textView = findViewById(R.id.registerEmailError);
            textView.setText(registerResponse.getEmail());
            textView.setVisibility(View.VISIBLE);
        }
        if(registerResponse.getPassword() != null) {
            TextView textView = findViewById(R.id.registerPasswordError);
            textView.setText(registerResponse.getPassword());
            textView.setVisibility(View.VISIBLE);
        }
        if(registerResponse.getAge() != null) {
            TextView textView = findViewById(R.id.registerAgeError);
            textView.setText(registerResponse.getAge());
            textView.setVisibility(View.VISIBLE);
        }
    }
}