package com.example.befit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.FileObserver;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPasswordActivity extends AppCompatActivity {
    private EditText forEmail;
    private Button reset;
    private String email;
    String emailPattern="^(.+)@(.+)$";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        forEmail=findViewById(R.id.forgotEmail);
        reset=findViewById(R.id.btnreset);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=forEmail.getText().toString();

                if (email.isEmpty() || !email.matches(emailPattern)){
                    Toast.makeText(ForgotPasswordActivity.this, "Please Enter a Valid Email", Toast.LENGTH_SHORT).show();
                }
                else {
                    forgotPassword();
                }
            }
        });
    }

    private void forgotPassword(){
        FirebaseAuth auth=FirebaseAuth.getInstance();
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(ForgotPasswordActivity.this, "Check your Email", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(ForgotPasswordActivity.this,LoginActivity.class));
                            finish();
                        }
                        else{
                            Toast.makeText(ForgotPasswordActivity.this, "Error"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }


}