package com.example.colledgemanagementsystem.AdminPanel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.colledgemanagementsystem.R;

public class AdminPage extends AppCompatActivity {
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);
        start=findViewById(R.id.btnstart);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPage.this, Login.class));
//                Intent intent =new Intent(MainActivity.this,LoginActivity.class);
//                startActivity(intent);
            }
        });
    }
}