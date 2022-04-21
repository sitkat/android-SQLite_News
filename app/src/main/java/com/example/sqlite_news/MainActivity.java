package com.example.sqlite_news;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText username, password, repassword;
    Button signup, signin;
    DBHelper DB;
    Spinner spinner;

    String[] data = {"админ", "читатель"};
    String role;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        repassword = (EditText) findViewById(R.id.repassword);
        signup = (Button) findViewById(R.id.btnsignup);
        signin = (Button) findViewById(R.id.btnsignin);
        DB = new DBHelper(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, data);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner = findViewById(R.id.spinner);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Выберить роль");
        spinner.setSelection(1);

        getInfoUsers();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String repass = repassword.getText().toString();
                role = spinner.getSelectedItem().toString();

                if (user.equals("") || pass.equals("") || repass.equals(""))
                    Toast.makeText(MainActivity.this, "Пожалуйста, заполните все поля", Toast.LENGTH_SHORT).show();
                else {
                    if (pass.equals(repass)) {
                        Boolean checkuser = DB.checkusername(user);
                        if (checkuser == false) {
                            Boolean insert = DB.insertData(user, pass, role);
                            if (insert == true) {
                                if (role.equals("админ")) {
                                    Toast.makeText(MainActivity.this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), CreateNewsActivity.class);
                                    startActivity(intent);
                                } else if (role.equals("читатель")) {
                                    Toast.makeText(MainActivity.this, "Успешная регистрация", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getApplicationContext(), ReaderActivity.class);
                                    startActivity(intent);
                                }
                            } else {
                                Toast.makeText(MainActivity.this, "Ошибка!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Такой пользователь уже существует!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Пароли не совпадают", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
    private void getInfoUsers(){
        Cursor res = DB.getData();
        if(res.getCount() == 0){
            Toast.makeText(this,"Нет данных", Toast.LENGTH_SHORT).show();
            return;
        }

        StringBuilder buffer = new StringBuilder();
        while (res.moveToNext()){
            buffer.append("Логин: ").append(res.getString(0)).append("\n");
            buffer.append("Пароль: ").append(res.getString(1)).append("\n");
            buffer.append("Роль: ").append(res.getString(2)).append("\n\n");
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle("Данные пользователей");
        builder.setMessage(buffer.toString());
        builder.show();
    }
}