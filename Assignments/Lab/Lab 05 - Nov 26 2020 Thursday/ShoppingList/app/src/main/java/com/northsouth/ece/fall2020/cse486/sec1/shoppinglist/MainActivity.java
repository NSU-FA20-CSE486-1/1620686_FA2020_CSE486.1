package com.northsouth.ece.fall2020.cse486.sec1.shoppinglist;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST = 1;
    private TextView[] items = new TextView[10];
    private ArrayList<String> list = new ArrayList<String>();
    private EditText mLocationEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items[0] = findViewById(R.id.Textview1);
        items[1] = findViewById(R.id.Textview2);
        items[2] = findViewById(R.id.Textview3);
        items[3] = findViewById(R.id.Textview4);
        items[4] = findViewById(R.id.Textview5);
        items[5] = findViewById(R.id.Textview6);
        items[6] = findViewById(R.id.Textview7);
        items[7] = findViewById(R.id.Textview8);
        items[8] = findViewById(R.id.Textview9);
        items[9] = findViewById(R.id.Textview10);

        mLocationEditText = (EditText)findViewById(R.id.editText_uri);

    }

    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST) {
            if (resultCode == RESULT_OK) {
                String reply = data.getStringExtra(AddItems.EXTRA_REPLY);
                if(list.size()<10){
                    list.add(reply);
                }else
                    Toast.makeText(this,"Cannot add any more items", Toast.LENGTH_SHORT).show();
                for(int i=0; i< list.size();i++){
                    items[i].setVisibility(View.VISIBLE);
                    items[i].setText(list.get(i));
                }
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (list.size() != 0) {
            outState.putStringArrayList("ItemsList", list);
        }
    }
    @Override
    public void onRestoreInstanceState (Bundle mySavedInstance){
        if (mySavedInstance != null) {
            list = mySavedInstance.getStringArrayList("ItemsList");
            int i;
            if (list!= null && list.size() > 0) {
                for (i = 0; i < list.size(); i++) {
                    items[i].setVisibility(View.VISIBLE);
                    items[i].setText(list.get(i));
                }
            }
        }
    }


    public void addItem(View view){
        Intent intent = new Intent(this, AddItems.class);
        startActivityForResult(intent, REQUEST);
    }

    @SuppressLint("QueryPermissionsNeeded")
    public void openLocation(View view) {

        String loc = mLocationEditText.getText().toString();

        Uri addressUri = Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, addressUri);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Log.d("ImplicitIntents", "Can't handle this intent!");
        }
    }

}