package com.example.colledgemanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colledgemanagementsystem.AdminPanel.Login;

public class RegistrationPage extends AppCompatActivity {
    EditText edUserName, edPassword, edConfirmPassword, edEmail;
    Button btn;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_page);
        edEmail = findViewById(R.id.editTextRegEmail);
        edUserName = findViewById(R.id.editTextRegUsername);
        edPassword = findViewById(R.id.editTextRegPassword);
        edConfirmPassword = findViewById(R.id.editTextRegConfirmPassword);


        btn = findViewById(R.id.buttonRegistration);
        tv = findViewById(R.id.textViewLoginPage);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String userName = edUserName.getText().toString();
                String email = edEmail.getText().toString();
                String password = edPassword.getText().toString();
                String confirm = edConfirmPassword.getText().toString();


                Database db = new Database(getApplicationContext(), "collage", null,1);
                if (userName.length()==0 || email.length()==0 || password.length()==0 || confirm.length()==0){
                    Toast.makeText(getApplicationContext(),"Please Fill All The Data Field.",Toast.LENGTH_SHORT).show();
                }else {
                    if (password.compareTo(confirm)==0){
                        db.addNewUser( userName, email, password,confirm);
                            Toast.makeText(getApplicationContext(),"Record Inserted..",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegistrationPage.this, Login.class));

//                        if (is_Valid_Password(password)){
//                            db.addNewUser( userName, email, password,confirm);
//                            Toast.makeText(getApplicationContext(),"Record Inserted..",Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(RegistrationPage.this, Login.class));
//                        }else {
//                            Toast.makeText(getApplicationContext(),"Invalid Password.",Toast.LENGTH_SHORT).show();
//                        }

                    }else {
                        Toast.makeText(getApplicationContext(),"Password is not matched..",Toast.LENGTH_SHORT).show();
                    }
                }          ;
            }
        });
    }

//    public static boolean is_Valid_Password(String password) {
//
//        if (password.length() < 8) return false;
//
//        int charCount = 0;
//        int numCount = 0;
//        for (int i = 0; i < password.length(); i++) {
//
//            char ch = password.charAt(i);
//
//            if (is_Numeric(ch)) numCount++;
//            else if (is_Letter(ch)) charCount++;
//            else return false;
//        }
//
//
//        return (charCount >= 2 && numCount >= 2);
//    }
//    public static boolean is_Letter(char ch) {
//        ch = Character.toUpperCase(ch);
//        return (ch >= 'A' && ch <= 'Z');
//    }
//    public static boolean is_Numeric(char ch) {
//
//        return (ch >= '0' && ch <= '9');
//    }
}