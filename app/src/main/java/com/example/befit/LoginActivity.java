package com.example.befit;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    TextView btn,fp;
    SharedPreferences sp;
    EditText inputEmail,inputPassword;
    Button btnLogin;
    private Button btnGoogle;
    String emailPattern="^(.+)@(.+)$";
    String passwordPattern= "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}";
    ProgressDialog progressDialog;

    FirebaseAuth mAuth;
    FirebaseUser mUser;
    GoogleSignInClient gsc;
    GoogleSignInOptions gso;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn=findViewById(R.id.textViewSignUp);
        fp=findViewById(R.id.forgotPassword);
        inputEmail=findViewById(R.id.forgotEmail);
        inputPassword=findViewById(R.id.inputPassword);
        btnLogin=findViewById(R.id.btnreset);
        btnGoogle=findViewById(R.id.btnGoogle);
        check();
        progressDialog = new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc= GoogleSignIn.getClient(this,gso);

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View view) {
                signIn();
                final ProgressDialog dialog= ProgressDialog.show(LoginActivity.this,"Login", "Please Wait While Login...",true);
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(5000);
                            dialog.dismiss();
                        }
                        catch(InterruptedException ex){
                            ex.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
            }
        });

        fp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,ForgotPasswordActivity.class));
            }
        });


    }

    void signIn(){
        Intent signInIntent=gsc.getSignInIntent();
        someActivityResultLauncher.launch(signInIntent);
    }

    ActivityResultLauncher<Intent> someActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode()== Activity.RESULT_OK){
                        Intent data=result.getData();
                        Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);

                        try {
                            task.getResult(ApiException.class);
                            finish();
                            Intent i=new Intent(LoginActivity.this,HomeActivity.class);
                            startActivity(i);

                        } catch (ApiException e) {
                            Toast.makeText(LoginActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }

                    }
                }
            }
    );


    private void checkCredentials(){
        String email=inputEmail.getText().toString();
        String password=inputPassword.getText().toString();

       if (email.isEmpty() || !email.matches(emailPattern)){
            showError(inputEmail,"Email is not valid");
        }
        else if (password.isEmpty() || !password.matches(passwordPattern)) {
            showError(inputPassword,"Password is not valid");
        }
        else {
           progressDialog.setMessage("Please Wait While Login...");
           progressDialog.setTitle("Login");
           progressDialog.setCanceledOnTouchOutside(false);
           progressDialog.show();

           mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()) {
                       progressDialog.dismiss();
                       sp = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
                       SharedPreferences.Editor editor = sp.edit();
                       editor.putString("status", "true");
                       editor.commit();
                       //Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                       //startActivity(new Intent(getApplicationContext(), Dashboard.class));
                       //finish();
                       Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                       Intent rIntent = new Intent(LoginActivity.this, HomeActivity.class);
                       startActivity(rIntent);
                       finish();
                   }
                   else{
                       progressDialog.dismiss();
                       Toast.makeText(LoginActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                   }

               }
           });
       }
    }

    void check(){
        SharedPreferences sp = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
        if(sp.getString("status","").equals("true")){
            startActivity(new Intent(getApplicationContext(),HomeActivity.class));
        }
    }

    private void showError(EditText input,String s){
        input.setError(s);
        input.requestFocus();
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Exit")
                .setMessage("Are you sure you want to Exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }
}