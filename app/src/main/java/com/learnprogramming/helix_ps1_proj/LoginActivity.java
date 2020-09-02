package com.learnprogramming.helix_ps1_proj;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";

    private boolean forgotPassword;
    private TextView tvForgotPass;
    private final String OUT_STATE_STRING = "out_state";
    private static String AUTH_TOKEN = "";
    private static SharedPreferences saveAuthToken;
    private static String USER_TYPE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        saveAuthToken = getSharedPreferences("saveAuthToken", MODE_PRIVATE);

        AUTH_TOKEN = getAuthToken();

        USER_TYPE = getUserType();
        if(!AUTH_TOKEN.equals("default")) {
            if(USER_TYPE.equals("U")) {
                Intent ceIntent = new Intent(LoginActivity.this, HomePage.class);
                startActivity(ceIntent);
                LoginActivity.this.finish();
            } else if(USER_TYPE.equals("L")) {
                Intent ceIntent = new Intent(LoginActivity.this, HomePageArtist.class);
                startActivity(ceIntent);
                LoginActivity.this.finish();
            }
        }

//        edit text for email
        final EditText etEmail = (EditText) findViewById(R.id.et_login_email);
//        edit text for password
        final EditText etPass = (EditText) findViewById(R.id.et_login_pass);
//        go to sign up page link
        TextView tvSignUp = (TextView) findViewById(R.id.tv_link_signup);
//        forgot password/incorrect credentials text view
        tvForgotPass = (TextView) findViewById(R.id.tv_forgot_pass);
        tvForgotPass.setText("");
        forgotPassword = false;

//        Login button
        Button btnLogin = (Button) findViewById(R.id.btn_login);

        btnLogin.setOnClickListener(v -> {

            String email = etEmail.getText().toString();
            String password = etPass.getText().toString();

            validateCredentials(email, password);
        });

        tvSignUp.setOnClickListener(v -> {
            Intent regIntent = new Intent(LoginActivity.this, Registration.class);
            startActivity(regIntent);
            LoginActivity.this.finish();
        });
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putBoolean(OUT_STATE_STRING, forgotPassword);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        forgotPassword = savedInstanceState.getBoolean(OUT_STATE_STRING);
        if(forgotPassword) activateForgotPasswordMode();
    }

    private void validateCredentials(String email, String password) {
//        CHECK DATABASE

        Retrofit loginBuilder = new Retrofit.Builder()
                .baseUrl("https://helix-ps-backend.herokuapp.com/auth/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginAPI loginAPI = loginBuilder.create(LoginAPI.class);

        Call<LoginResponse> call = loginAPI.userLogin(email, password);

        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse;

                boolean isValid = false;

                switch (response.code()) {
                    case 200:
                        loginResponse = response.body();
                        assert loginResponse != null;
                        AUTH_TOKEN = loginResponse.getToken();
                        USER_TYPE = loginResponse.getUser().getUsertype();
                        isValid = true;
                        break;
                    case 400:
                        Toast.makeText(LoginActivity.this, "Please enter a valid email address", Toast.LENGTH_SHORT).show();
                        break;
                    case 401:
                        Toast.makeText(LoginActivity.this, "Incorrect email or password", Toast.LENGTH_SHORT).show();
                        break;
                    case 409:
                        Toast.makeText(LoginActivity.this, "User does not exist", Toast.LENGTH_SHORT).show();
                        break;
                }


//                dismissLoadingDialog();

                if(isValid) {
                    storeAuthToken(AUTH_TOKEN);
                    storeUserType(USER_TYPE);

                    Toast.makeText(LoginActivity.this, "logged in ", Toast.LENGTH_SHORT).show();
                    if(USER_TYPE.equals("U")) {
                        Intent ceIntent = new Intent(LoginActivity.this, HomePage.class);
                        startActivity(ceIntent);
                        LoginActivity.this.finish();
                    } else if(USER_TYPE.equals("L")) {

                        Intent ceIntent = new Intent(LoginActivity.this, HomePageArtist.class);
                        startActivity(ceIntent);
                        LoginActivity.this.finish();
                    }
                } else {
                    activateForgotPasswordMode();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: retrofit failure");
            }
        });
    }

    private void activateForgotPasswordMode()
    {
        forgotPassword = true;
        tvForgotPass.setText("Forgot Password?");
        tvForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent fpIntent = new Intent(LoginActivity.this, ForgotPassword.class);
                startActivity(fpIntent);
                LoginActivity.this.finish();
            }
        });
    }

    private void storeAuthToken(String token) {
        saveAuthToken.edit().putString("authToken", token).commit();
    }

    private void storeUserType(String userType) {
        saveAuthToken.edit().putString("userType", userType).commit();
    }

    public static String getUserType() {
        return saveAuthToken.getString("userType", "default");
    }

    public static String getAuthToken() {
        return saveAuthToken.getString("authToken", "default");
    }
}