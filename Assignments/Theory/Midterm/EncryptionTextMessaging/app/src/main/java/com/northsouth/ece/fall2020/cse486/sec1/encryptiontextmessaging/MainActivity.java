package com.northsouth.ece.fall2020.cse486.sec1.encryptiontextmessaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE =
            "com.northsouth.ece.fall2020.cse486.sec1.encryptiontextmessaging.extra.MESSAGE";
    public static final String EXTRA_PHONE =
            "com.northsouth.ece.fall2020.cse486.sec1.encryptiontextmessaging.extra.PHONE";
    public static final String EXTRA_KEY =
            "com.northsouth.ece.fall2020.cse486.sec1.encryptiontextmessaging.extra.KEY";
    private EditText editTextPhoneNumber;
    private EditText editTextKey;
    private EditText editTextMessage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextPhoneNumber = (EditText)findViewById(R.id.editTextPhone);
        editTextKey = (EditText)findViewById(R.id.editTextKey);
        editTextMessage = (EditText)findViewById(R.id.editTextMessage);
    }

    public void encrypt(View view){
        int phoneNumber = Integer.parseInt(editTextPhoneNumber.getText().toString());
        String key = editTextKey.getText().toString();
        String message = editTextMessage.getText().toString();
        String encryptedMessage = encrypt(message,key);
        Intent intent = new Intent(this, Encrypted.class);
        intent.putExtra(EXTRA_MESSAGE, encryptedMessage);
        intent.putExtra(EXTRA_PHONE, phoneNumber);
        intent.putExtra(EXTRA_KEY, key);
        startActivity(intent);
    }
    public String encrypt(String text, final String key)
    {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            char c2 = key.charAt(j);
            if(c2 >= '0' && c2 <= '9'){
                if (c < 'A' || c > 'Z')
                    res+=c;
                else{
                    int offset = Integer.parseInt(String.valueOf(c2));
                    offset = offset % 26 + 26;
                    res += (char) ('A' + (c - 'A' + offset) % 26);
                    j = ++j % key.length();
                }
            }else if(c2 >= 'A' && c2 <= 'Z'){
                if (c < 'A' || c > 'Z')
                    res+=c;
                else{
                    res += (char) ((c + key.charAt(j) - 2 * 'A') % 26 + 'A');
                    j = ++j % key.length();
                }
            }


        }
        return res;
    }

    public String decrypt(String text, final String key)
    {
        String res = "";
        text = text.toUpperCase();
        for (int i = 0, j = 0; i < text.length(); i++)
        {
            char c = text.charAt(i);
            char c2 = key.charAt(j);
            if(c2 >= '0' && c2 <= '9'){
                if (c < 'A' || c > 'Z')
                    res+=c;
                else{
                    int offset = Integer.parseInt(String.valueOf(c2));
                    offset = 26-(offset % 26 + 26);
                    res += (char) ('A' + (c - 'A' + offset) % 26);
                    j = ++j % key.length();
                }
            }else if(c2 >= 'A' && c2 <= 'Z'){
                if (c < 'A' || c > 'Z')
                    res+=c;
                else{
                    res += (char) ((c - key.charAt(j) + 26) % 26 + 'A');
                    j = ++j % key.length();
                }
            }
        }
        return res;
    }
}