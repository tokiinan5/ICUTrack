package com.example.dell.icutrack;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

import java.util.concurrent.TimeoutException;

public class SignIn extends AppCompatActivity {
    Button mSignin;
    TextView tEmail, tPassword,tNew;
    String  email,password;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
       // int defaultValue = getResources().getInteger(R.integer.saved_high_score_default_key);
        int status = sharedPref.getInt("SIGN_IN_STATUS",100);
        if(status==1)
        {
            Intent intent=new Intent(this,WorkingActivity.class);
            startActivity(intent);
        }
        setContentView(R.layout.activity_sign_in);
        mSignin=findViewById(R.id.bSignin);
        tPassword=findViewById(R.id.epassword);
        tEmail=findViewById(R.id.eUserName);
        auth=FirebaseAuth.getInstance();
        tNew=findViewById(R.id.tNew);
        tNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(SignIn.this,SignUp.class);
                startActivity(intent);
            }
        });



        mSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
         Intent intent=new Intent(getApplicationContext(),WorkingActivity.class);
         startActivity(intent);
                password=tPassword.getText().toString();
                email=tEmail.getText().toString();
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {

                            FirebaseUser user=auth.getCurrentUser();
                            if(user.isEmailVerified()) {
                                Toast.makeText(SignIn.this, R.string.msg_Sign_IN, Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignIn.this, WorkingActivity.class);
                                SharedPreferences sharedPref = getPreferences(MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPref.edit();
                                editor.putInt("SIGN_IN_STATUS",1);
                                editor.commit();
                                startActivity(intent);
                            }
                            else
                            {

                                Toast.makeText(SignIn.this, "Please Go to your email id and verify ", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            try {
                                throw task.getException();
                            } catch (FirebaseAuthEmailException e) {
                                tEmail.setError(getResources().getString(R.string.excp_Mail));
                                tEmail.requestFocus();

                            } catch (FirebaseAuthInvalidCredentialsException e) {
                                tPassword.setError(getResources().getString(R.string.excp_Mail));
                                tPassword.requestFocus();
                            } catch (FirebaseAuthInvalidUserException e) {
                                tEmail.setError(getResources().getString(R.string.excp_Mail));
                                tPassword.setError(getResources().getString(R.string.excp_Password));
                            } catch (FirebaseNetworkException e) {
                                Toast.makeText(SignIn.this, "Check Your Internet Connection", Toast.LENGTH_SHORT).show();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });

               // auth.signInWithCredential(new Cre)

            }
        });
    }
}


