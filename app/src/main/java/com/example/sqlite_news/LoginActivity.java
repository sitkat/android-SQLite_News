package com.example.sqlite_news;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        DB = new DBHelper(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkusernamepass = DB.checkusernamepassword(user, pass);
                    if(checkusernamepass==true){
                        String role = DB.getUserRole(user, pass);
                        Toast.makeText(LoginActivity.this, "Успешная авторизацтя", Toast.LENGTH_SHORT).show();
                        if (role.equals("админ")){
                            Intent intent  = new Intent(getApplicationContext(), CreateNewsActivity.class);
                            startActivity(intent);
                        }
                        else if (role.equals("читатель")){
                            Intent intent  = new Intent(getApplicationContext(), ReaderActivity.class);
                            startActivity(intent);
                        }
                    }else{
                        Toast.makeText(LoginActivity.this, "Аккаунт с такими данными не существует!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}