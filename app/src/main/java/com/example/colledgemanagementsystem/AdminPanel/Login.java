package com.example.colledgemanagementsystem.AdminPanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.colledgemanagementsystem.Database;
import com.example.colledgemanagementsystem.R;
import com.example.colledgemanagementsystem.RegistrationPage;

public class Login extends AppCompatActivity {
    EditText editTextuser,editTextpassword;
Button login,signUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextuser=findViewById(R.id.editTextusername);
        editTextpassword=findViewById(R.id.editTextpassword);

        login=findViewById(R.id.btnLogin);
        signUp=findViewById(R.id.btnSignup);

        Database db = new Database(getApplicationContext(), "collage", null,1);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String userName = editTextuser.getText().toString();
                String password = editTextpassword.getText().toString();

//                startActivity(new Intent(LoginActivity.this, HomeActivity.class));

                if (userName.length()==0 || password.length()==0){
//                    Toast.makeText(getApplicationContext(),"Please Fill All The Data Field.",Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(),"Please Fill All The Data Field.",Toast.LENGTH_SHORT).show();
                }else {

                    if (db.login(userName,password)==1){
                        Toast.makeText(getApplicationContext(),"Login Success Tex.",Toast.LENGTH_SHORT).show();
                        SharedPreferences preferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("usernamr",userName);
                        editor.apply();
                        startActivity(new Intent(Login.this, AdminHomePage.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"Wrong Password and UserName..",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, RegistrationPage.class));
            }
        });
    }
}