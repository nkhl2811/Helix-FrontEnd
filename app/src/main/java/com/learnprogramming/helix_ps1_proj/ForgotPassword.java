package com.learnprogramming.helix_ps1_proj;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ForgotPassword extends AppCompatActivity {

    EditText email;
    Button button;
    private String email_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email = findViewById(R.id.editText3);
        button = findViewById(R.id.nextButton);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email_id = email.getText().toString();
                if(isEmailValid(email_id) && doesEmailExist(email_id)) {
                    Toast.makeText(getApplicationContext(), "Success!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getApplicationContext(),"Please enter a valid email address",Toast.LENGTH_LONG).show();
            }
        });

    }
    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
    boolean doesEmailExist(CharSequence email){
        //CHECK DATABASE IF REGISTERED EMAIL IS PRESENT
        return true;
    }
}