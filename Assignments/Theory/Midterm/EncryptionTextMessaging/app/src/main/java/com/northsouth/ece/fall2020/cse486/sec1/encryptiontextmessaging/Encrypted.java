package com.northsouth.ece.fall2020.cse486.sec1.encryptiontextmessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Encrypted extends AppCompatActivity {
    private TextView TextEncrypted;
    private int phoneNumber;
    private String message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encrypted);
        Intent intent = getIntent();
        message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        phoneNumber = intent.getIntExtra(MainActivity.EXTRA_PHONE, 0);
        String key = intent.getStringExtra(MainActivity.EXTRA_KEY);
        TextEncrypted = (TextView) findViewById(R.id.textViewEncryptedMssg);
        TextEncrypted.setText(message);
    }

    public void sendText(View view){
        Uri uri = Uri.parse("sms:+880"+phoneNumber);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", message);
        startActivity(it);
    }
}