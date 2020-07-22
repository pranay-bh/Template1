package com.solvedunsloved.template1.activities.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.solvedunsloved.template1.R;

public class Signup extends AppCompatActivity {

    private EditText userName;
    private EditText email;
    private EditText password;
    private EditText password2;

    private CardView signUpButton;
    private FirebaseAuth mAuth;
    private ProgressDialog mProgressDialog;
    private DatabaseReference mDatabaseReference;
    private FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        mDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference = mDatabase.getReference().child("MUsers");

        mAuth = FirebaseAuth.getInstance();
        mProgressDialog = new ProgressDialog(this);

        userName = findViewById(R.id.Username);
        email =  findViewById(R.id.Email);
        password =  findViewById(R.id.loginpassword);
        password2 = findViewById(R.id.loginpassword2);
        signUpButton = findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createNewAccount();
            }
        });
    }

    private void createNewAccount() {

        final String uName = userName.getText().toString().trim();
        String em = email.getText().toString().trim();
        String pwd = password.getText().toString().trim();
        String pwd2 = password2.getText().toString().trim();

        if (!TextUtils.isEmpty(uName) && !TextUtils.isEmpty(pwd2)
                && !TextUtils.isEmpty(em) && !TextUtils.isEmpty(pwd)) {
            if(pwd.equals(pwd2)) {

                mProgressDialog.setMessage("Creating Account...");
                mProgressDialog.show();
                mAuth.createUserWithEmailAndPassword(em, pwd)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                if (authResult != null) {
                                    String userid = mAuth.getCurrentUser().getUid();
                                    DatabaseReference currenUserDb = mDatabaseReference.child(userid);
                                    currenUserDb.child("Username").setValue(uName);
                                    mProgressDialog.dismiss();
                                    Intent intent = new Intent(Signup.this, Startscreen.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                    startActivity(intent);
                                    finish();
                                }
                            }
                        });
            }
            else
                Toast.makeText(this, "Password should match confirm password", Toast.LENGTH_SHORT).show();
        }
        else
            Toast.makeText(this, "Enter everything in correct format", Toast.LENGTH_SHORT).show();
    }
}