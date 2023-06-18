package com.example.colledgemanagementsystem.AdminPanel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.colledgemanagementsystem.R;

public class AdminHomePage extends AppCompatActivity {
CardView addNotices,addfaculty,addstudent,viewstu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home_page);

        addNotices=findViewById(R.id.addNotice);
        addfaculty=findViewById(R.id.addFaculty);
        addstudent=findViewById(R.id.addStudent);
        viewstu=findViewById(R.id.viewstudent);

        addNotices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomePage.this, ViewFacultyList.class));
            }
        });
        addfaculty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomePage.this, AddFaculty.class));
            }
        });
        addstudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomePage.this, AddStudent.class));
            }
        });
        viewstu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminHomePage.this, ViewStudents.class));
            }
        });
    }
}