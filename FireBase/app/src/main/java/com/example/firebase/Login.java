package com.example.firebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button btn_login;
    EditText name,pass;
    TextView textDangKi;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textDangKi = findViewById(R.id.textDangKi);
        textDangKi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenDangKi();
            }
        });

        mAuth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        pass = findViewById(R.id.pass);

        btn_login = findViewById(R.id.btn_dangnhap);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OpenHome();
            }
        });
    }

    private void OpenDangKi() {
        startActivity(new Intent(Login.this,Register.class));
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

        mAuth.signInWithEmailAndPassword(names,passs).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Đăng nhập không thành công", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}