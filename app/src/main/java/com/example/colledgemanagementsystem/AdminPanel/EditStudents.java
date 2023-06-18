package com.example.colledgemanagementsystem.AdminPanel;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.colledgemanagementsystem.Database;
import com.example.colledgemanagementsystem.R;
import com.example.colledgemanagementsystem.entity.AddFacultyEntity;
import com.example.colledgemanagementsystem.entity.AddStudentEntity;

public class EditStudents extends AppCompatActivity {
    EditText edEmpName, edEmpAddress, edEmpSalary, edEmail, edDate, edId;
    Button btn;
    TextView tv;

    DatePickerDialog datePickerDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_students);

        edId = findViewById(R.id.editTextAppUsernameEDIT);
        edEmpName = findViewById(R.id.editTextEmpNameEDIT);
        edEmpAddress = findViewById(R.id.editTextEmpAddressEDIT);
        edEmpSalary = findViewById(R.id.editTextEmpSalaryEDIT);
//        edDate = findViewById(R.id.dateEDIT);
//        spinnerDepartment = sqLiteCreateFragment.spinnerDepartment;

        btn = findViewById(R.id.buttonCreateEmpEDIT);
        tv = findViewById(R.id.textViewBackEDIT);



        edId.setKeyListener(null);


        Intent intent = getIntent();

        String id = intent.getStringExtra("ID");
        String name = intent.getStringExtra("NAME");
        String salary = intent.getStringExtra("SALARY");
        String address = intent.getStringExtra("ADDRESS");
        edId.setText(id);
        edEmpName.setText(name);
        edEmpAddress.setText(address);
        edEmpSalary.setText(salary);
        Toast.makeText(this, "NAME: "+name+" , ADDRESS: "+ address, Toast.LENGTH_SHORT).show();

        Database db = new Database(getApplicationContext(), "collage", null,1);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Navigation.findNavController(view).navigate(R.id.nav_gallery);

                AddStudentEntity emp = new AddStudentEntity();
                Integer id = Integer.parseInt(edId.getText().toString());
                String name = edEmpName.getText().toString();
                String address = edEmpAddress.getText().toString();
                String salary = edEmpSalary.getText().toString();

                emp.setId(id);
                emp.setName(name);
                emp.setAddress(address);
                emp.setSalary(Integer.valueOf(salary));


                Boolean result = db.updateStudent(emp);
                String message = result ? "Successfully Updated!" : "Failed to Update.";
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();//
                Intent intent = new Intent(getApplicationContext(), ViewStudents.class);
                startActivity(intent);
            }
        });
    }
}