package com.northsouth.ece.fall2020.cse486.sec1.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class AddItems extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.northsouth.ece.fall2020.cse486.sec1.twoactivities.extra.REPLY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_items);
        Intent intent = getIntent();

    }
    public void addItem(View view){
        Button item = (Button)findViewById(view.getId());
        String name = item.getText().toString();
        Intent replyIntent = new Intent();
        replyIntent.putExtra(EXTRA_REPLY, name);
        setResult(RESULT_OK,replyIntent);
        finish();
    }
    }