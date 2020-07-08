package com.example.firebasesavedata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

//DataInsert Firebase Project


   private FirebaseDatabase mDatabase;
    private DatabaseReference mDatabaseReference;
    public EditText name, age, phone,email;
    public Button insert;
   // private DatabaseReference mDatabase;
    User user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText)findViewById(R.id.editTextName);
        age = (EditText)findViewById(R.id.editTextAge);
        phone = (EditText)findViewById(R.id.editTextPhone);
        email = (EditText)findViewById(R.id.editTextEmail);
        insert = (Button)findViewById(R.id.buttonInsert);
        mDatabase = FirebaseDatabase.getInstance();

        mDatabaseReference = mDatabase.getReference("Users");

      //  mDatabase = FirebaseDatabase.getInstance().getReference("Users");
      //  mDatabaseReference = mDatabase.getReference("Users");

        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                addName();

            }
        });

    }

    private void addName(){

        String name1 = name.getText().toString().trim();
        String age1 = age.getText().toString().trim();
        String phone1 = phone.getText().toString().trim();
        String email1 = email.getText().toString().trim();

        if(!TextUtils.isEmpty(name1)&& !TextUtils.isEmpty(age1)&& !TextUtils.isEmpty(phone1)&& !TextUtils.isEmpty(email1)){
            user = new User(name1,age1,phone1,email1);
            String id = mDatabaseReference.push().getKey();
            mDatabaseReference.child(id).setValue(user);
            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Insert data",Toast.LENGTH_SHORT).show();
        }

    }
}