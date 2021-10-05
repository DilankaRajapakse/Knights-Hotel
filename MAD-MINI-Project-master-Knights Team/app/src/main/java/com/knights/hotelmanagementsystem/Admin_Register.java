package com.knights.hotelmanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Admin_Register extends AppCompatActivity {

    EditText fullName, email, dob, username, password, confPassword, phone;
    Button register;
    DatabaseReference dbRef;
    Admin_model admin_model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin__register);

        register = findViewById(R.id.register);

        fullName = findViewById(R.id.fullName);
        email = findViewById(R.id.email);
        dob = findViewById(R.id.dob);
        username = findViewById(R.id.uname);
        password = findViewById(R.id.pwd);
        confPassword = findViewById(R.id.confPwd);
        phone = findViewById(R.id.phone);

        admin_model = new Admin_model();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance().getReference().child("Admin");
                try{
                    if(TextUtils.isEmpty(fullName.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please enter full name", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(email.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please enter email", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(dob.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please enter DOB", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(username.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please enter user name", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(password.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please enter password", Toast.LENGTH_SHORT).show();
                    }else if(TextUtils.isEmpty(confPassword.getText().toString())){
                        Toast.makeText(getApplicationContext(), "Please enter confirm password", Toast.LENGTH_SHORT).show();
                    }else{
                        if(!password.getText().toString().equals(confPassword.getText().toString())){
                            Toast.makeText(getApplicationContext(), "Re-entered password is not the same.", Toast.LENGTH_LONG).show();
                            password.setText("");
                            confPassword.setText("");
                        }else{
                            admin_model.setFullName(fullName.getText().toString().trim());
                            admin_model.setEmail(email.getText().toString().trim());
                            admin_model.setDOB(dob.getText().toString().trim());
                            admin_model.setUsername(username.getText().toString().trim());
                            admin_model.setPassword(password.getText().toString().trim());
                            admin_model.setPhone(Integer.parseInt(phone.getText().toString().trim()));

                            dbRef.push().setValue(admin_model);

                            Toast.makeText(getApplicationContext(), "Data Saved Successfully", Toast.LENGTH_SHORT).show();
                            clearControls();
                            Intent intent = new Intent(getApplicationContext(), Admin_Login.class);
                            startActivity(intent);

                        }

                    }
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(), "Invalid Contact Number", Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private void clearControls(){
        fullName.setText("");
        email.setText("");
        dob.setText("");
        username.setText("");
        password.setText("");
        confPassword.setText("");
    }
}