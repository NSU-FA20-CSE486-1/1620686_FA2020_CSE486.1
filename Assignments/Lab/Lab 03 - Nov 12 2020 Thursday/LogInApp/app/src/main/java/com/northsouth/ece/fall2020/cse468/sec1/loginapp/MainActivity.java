package com.northsouth.ece.fall2020.cse468.sec1.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText user;
    private EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void launchSecondActivity(View view) {
        user = (EditText)findViewById(R.id.editTextEmailAddress);
        password = (EditText)findViewById(R.id.editTextPassword);
        if(user.getText().toString().equals("Roksana") && password.getText().toString().equals("password")){
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        }
        else{
            Toast toast = Toast.makeText(this, R.string.toast_label_fail, Toast.LENGTH_SHORT);
            toast.show();
        }


    }
}