package com.example.colledgemanagementsystem.AdminPanel;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.colledgemanagementsystem.Database;
import com.example.colledgemanagementsystem.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ViewFacultyList extends AppCompatActivity {
    ArrayList facultyList;

    Button createBtn;

    SimpleAdapter sa;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_faculty_list);

        Database db = new Database(getApplicationContext(), "collage", null,1);
        facultyList = new ArrayList<>();
        facultyList = db.getAllFaculty();


        System.out.println(facultyList);
        System.out.println("-------------------------");

        createBtn = findViewById(R.id.createButton);
        createBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), AddFaculty.class));
            }
        });

        sa = new SimpleAdapter(this,
                facultyList,
                R.layout.viewfacultylayout,
                new String[]{"ID", "NAME", "ADDRESS", "SALARY"},
                new int[]{R.id.line_id1, R.id.line_c1, R.id.line_d1, R.id.line_e1}
        ) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View v = super.getView(position, convertView, parent);

                ImageView editBtn = v.findViewById(R.id.emp_edit_btn1);
                ImageView delBtn = v.findViewById(R.id.emp_del_btn1);


                editBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println(position);
                        HashMap<String, String> user = new HashMap<>();

                        try {
//                            System.out.println(v.findViewById(R.id.line_c).toString());

                            user = (HashMap<String, String>) facultyList.get(position);


                            System.out.println(facultyList.get(position));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

//                        Toast.makeText(EmpListActivity.this, "Edit button clicked!! + " + position + user, Toast.LENGTH_SHORT).show();
//                        System.out.println("EDIT----");
                        Intent intent = new Intent(getApplicationContext(), EditActivity.class);
                        intent.putExtra("ID", user.get("ID"));
                        intent.putExtra("NAME", user.get("NAME"));
                        intent.putExtra("ADDRESS", user.get("ADDRESS"));
                        intent.putExtra("SALARY", user.get("SALARY"));

                        startActivity(intent);
                    }
                });

                delBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HashMap<String, String> user = new HashMap<>();

                        user = (HashMap<String, String>) facultyList.get(position);

                        boolean deleted = db.deleteEmployee(Integer.parseInt(Objects.requireNonNull(user.get("ID"))));
                        if (deleted) {
                            facultyList.remove(position);
                            notifyDataSetChanged();
                        }
                        String message = deleted ? "Successfully deleted" : "Failed to delete";
                        Toast.makeText(ViewFacultyList.this, message, Toast.LENGTH_SHORT).show();
                    }
                });


                return v;
            }


        };

        ListView lv = findViewById(R.id.listViewUD);
        lv.setAdapter(sa);
//        ImageButton editBtn = lv.findViewById(R.id.emp_edit_btn);
//        ImageButton delBtn = lv.findViewById(R.id.emp_del_btn);
//
//        editBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                System.out.println("EDITTTTTTTTTTTTTTTTTTTTTTTTTT");
////                Intent intent = new Intent(context, AddEmployeeActivity.class);
////                intent.putExtra("empId", employee.getId());
////                intent.putExtra("empName", employee.getName());
////                intent.putExtra("empEmail", employee.getEmail());
////                intent.putExtra("empPrice", employee.getPrice());
////                intent.putExtra("empQuantity", employee.getQuantity());
////                context.startActivity(intent);
//            }
//        });
//
//        delBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                System.out.println("Viewwwwwwwwwwwwwwwwwwwwwwwwwww");
//
////                boolean deleted = EmployeeDao.deleteEmployee(employee.getId());
////                if (deleted) {
////                    employees.remove(i);
////                    notifyDataSetChanged();
////                }
////                String message = deleted ? "Successfully deleted" : "Failed to delete";
////                Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
//            }
//        });

    }
}