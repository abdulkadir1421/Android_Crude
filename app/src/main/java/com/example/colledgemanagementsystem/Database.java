package com.example.colledgemanagementsystem;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.colledgemanagementsystem.entity.AddFacultyEntity;
import com.example.colledgemanagementsystem.entity.AddStudentEntity;
import com.example.colledgemanagementsystem.entity.SignUp;

import java.util.ArrayList;
import java.util.HashMap;

public class Database extends SQLiteOpenHelper {
    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public Database(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public Database(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams) {
        super(context, name, version, openParams);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query =
                "CREATE TABLE SIGNUP ( ID INTEGER PRIMARY KEY AUTOINCREMENT, USERNAME TEXT, EMAIL TEXT, PASSWORD TEXT, CONFIRMPASSWORD TEXT)";
        String queryAddFaculty =
                "CREATE TABLE FACULTY ( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, ADDRESS TEXT, SALARY INTEGER,  DEP TEXT )";
        String queryAddStudent =
                "CREATE TABLE STUDENT ( ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME TEXT, EMAIL TEXT, ADDRESS TEXT, SALARY INTEGER,  DEP TEXT )";

        db.execSQL(query);
        db.execSQL(queryAddFaculty);
        db.execSQL(queryAddStudent);

    }
    public void addNewUser(String userNAme, String email, String password,String confirm) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("USERNAME", userNAme);
        values.put("EMAIL", email);
        values.put("PASSWORD", password);
        values.put("CONFIRMPASSWORD", password);

        db.insert("SIGNUP", null, values);

        db.close();
    }

// Add New Faculty

    public void addNewFaculty(AddFacultyEntity emp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NAME", emp.getName());
        values.put("ADDRESS", emp.getAddress());
        values.put("SALARY", emp.getSalary());
        values.put("EMAIL", emp.getEmail());
        values.put("DEP", emp.getDep());

        db.insert("FACULTY", null, values);
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAllFaculty() {

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = db.rawQuery("select * from FACULTY ", null);

        ArrayList<HashMap<String, String>> FacultyList = new ArrayList<>(c.getCount());
        HashMap<String, String> faculty;
        if (c.moveToFirst()) {

            do {
                faculty = new HashMap<>();
                faculty.put("ID", c.getString(0));
                faculty.put("NAME", c.getString(1));
                faculty.put("EMAIL", c.getString(2));
                faculty.put("ADDRESS", c.getString(3));
                faculty.put("SALARY", c.getString(4));
                faculty.put("DEP", c.getString(5));

                FacultyList.add(faculty);

            } while (c.moveToNext());

        }
        db.close();
        return FacultyList;
    }

    public boolean updateEmployee(AddFacultyEntity emp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NAME", emp.getName());
        values.put("ADDRESS", emp.getAddress());
        values.put("SALARY", emp.getSalary());
        values.put("EMAIL", emp.getEmail());
        values.put("DEP", emp.getDep());
        int result = db.update("FACULTY", values, "id = ?", new String[]{emp.getId() + ""});
        db.close();

        return result > 0;
    };
    public boolean deleteEmployee(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowCount = db.delete("FACULTY", "id = ?", new String[]{id + ""});
        db.close();
        return rowCount > 0;
    }

    //Add student
    public void addNewStudent(AddStudentEntity emp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NAME", emp.getName());
        values.put("ADDRESS", emp.getAddress());
        values.put("SALARY", emp.getSalary());
        values.put("EMAIL", emp.getEmail());
        values.put("DEP", emp.getDep());

        db.insert("STUDENT", null, values);
        db.close();
    }

    public ArrayList<HashMap<String, String>> getAllStudent() {

        SQLiteDatabase db = this.getWritableDatabase();
        @SuppressLint("Recycle") Cursor c = db.rawQuery("select * from STUDENT ", null);

        ArrayList<HashMap<String, String>> StudentList = new ArrayList<>(c.getCount());
        HashMap<String, String> faculty;
        if (c.moveToFirst()) {

            do {
                faculty = new HashMap<>();
                faculty.put("ID", c.getString(0));
                faculty.put("NAME", c.getString(1));
                faculty.put("EMAIL", c.getString(2));
                faculty.put("ADDRESS", c.getString(3));
                faculty.put("SALARY", c.getString(4));
                faculty.put("DEP", c.getString(5));

                StudentList.add(faculty);

            } while (c.moveToNext());

        }
        db.close();
        return StudentList;
    }

    public boolean updateStudent(AddStudentEntity emp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("NAME", emp.getName());
        values.put("ADDRESS", emp.getAddress());
        values.put("SALARY", emp.getSalary());
        values.put("EMAIL", emp.getEmail());
        values.put("DEP", emp.getDep());
        int result = db.update("STUDENT", values, "id = ?", new String[]{emp.getId() + ""});
        db.close();

        return result > 0;
    };

    public boolean deleteStudent(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowCount = db.delete("STUDENT", "id = ?", new String[]{id + ""});
        db.close();
        return rowCount > 0;
    }

    public int login(String userName, String password) {

        String[] arr = new String[2];
        arr[0] = userName;
        arr[1] = password;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(" SELECT * FROM SIGNUP WHERE USERNAME=? AND PASSWORD=? ", arr);
        if (c.moveToFirst()) {
            return 1;
        }
        return 0;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
