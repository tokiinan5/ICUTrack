package com.example.dell.icutrack;

import android.content.Intent;
import android.nfc.Tag;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUp extends AppCompatActivity {
    RadioButton rDoctor,rPatient;
    EditText pMail,pPassword,pConpassword;
    //Button  bSignup;
    Button bSignup;
    TextView tNew;
    FirebaseAuth auth;
    String email,password,confirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        rDoctor=findViewById(R.id.rDoctor);
        rPatient=findViewById(R.id.rPatient);
        pMail=findViewById(R.id.eEmail);
        pPassword=findViewById(R.id.epassword);
        pConpassword=findViewById(R.id.eCpassword);
        bSignup=findViewById(R.id.bSign);
        tNew=findViewById(R.id.tNew);
        auth=FirebaseAuth.getInstance();
    /*    Intent intent=new Intent(SignUp.this,WorkingActivity.class);
        startActivity(intent);*/
        bSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email=pMail.getText().toString().trim();
                password=pPassword.getText().toString().trim();
                confirmPassword=pConpassword.getText().toString().trim();
                if(!(password.contentEquals(confirmPassword)))
                {
                    pConpassword.setError("Password must be same");
                    pConpassword.requestFocus();
                }
                else
                {
                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {

                                Toast.makeText(SignUp.this, "New User Created", Toast.LENGTH_SHORT).show();
                                final FirebaseUser user = auth.getCurrentUser();
                                user.sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener() {
                                            @Override
                                            public void onComplete(@NonNull Task task) {
                                                // Re-enable button
                                                // findViewById(R.id.verify_email_button).setEnabled(true);

                                                if (task.isSuccessful()) {
                                                    Toast.makeText(getApplicationContext(),
                                                            "Verification email sent to " + user.getEmail(),
                                                            Toast.LENGTH_SHORT).show();
                                                } else {
                                                    Log.e("FD", "sendEmailVerification", task.getException());
                                                    Toast.makeText(getApplicationContext(),
                                                            "Failed to send verification email.",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }

                        }
                    });
                }



               /* Intent intent=new Intent(SignUp.this,WorkingActivity.class);
                startActivity(intent);*/
            }
        });


    }







    public void onRadiochecked(View view) {
        boolean checked=((RadioButton)view).isChecked();
        switch(view.getId()){
            case R.id.rDoctor:
                if(checked)
                    Toast.makeText(this, "Doctor", Toast.LENGTH_SHORT).show();

                    break;
            case R.id.rPatient:
                if (checked)

                    Toast.makeText(this, "patient", Toast.LENGTH_SHORT).show();

                    break;

        }

    }
}
