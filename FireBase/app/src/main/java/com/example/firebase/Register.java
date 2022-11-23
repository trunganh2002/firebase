package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    Button btn_dangki, btnBack;
    EditText name,pass;
    private FirebaseAuth mAuth;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Back();
            }
        });


        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);

        btn_dangki = findViewById(R.id.btn_dangki);
        btn_dangki.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenHome();
            }
        });
    }

    private void Back() {
        startActivity(new Intent(Register.this,Login.class));
    }

    private void OpenHome() {
        String names, passs;
        names = name.getText().toString();
        passs = pass.getText().toString();

        if (TextUtils.isEmpty(names)){
            Toast.makeText(this,"Vui lòng nhập name !",Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(passs)){
            Toast.makeText(this,"Vui lòng nhập pass !",Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(names,passs).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Tạo tài khoản thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Register.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Tạo tài khoản không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}