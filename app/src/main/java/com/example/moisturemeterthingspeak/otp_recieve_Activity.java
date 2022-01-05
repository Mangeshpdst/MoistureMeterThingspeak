package com.example.moisturemeterthingspeak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class otp_recieve_Activity extends AppCompatActivity {

    EditText inputCode1, inputCode2, inputCode3, inputCode4, inputCode5, inputCode6;
    FirebaseAuth mAuth;
    String Number, otpId;
    String Otp;
    View back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_otp_recieve);

        final TextView Number_tv = findViewById(R.id.number_tv);
        TextView Resend_tv = findViewById(R.id.resend_tv);

        back = findViewById(R.id.back_v);
        Button Verify_btn = findViewById(R.id.Verify_Btn);

        mAuth = FirebaseAuth.getInstance();

        inputCode1 = findViewById(R.id.inputcode1);
        inputCode2 = findViewById(R.id.inputcode2);
        inputCode3 = findViewById(R.id.inputcode3);
        inputCode4 = findViewById(R.id.inputcode4);
        inputCode5 = findViewById(R.id.inputcode5);
        inputCode6 = findViewById(R.id.inputcode6);

        Number =getIntent().getStringExtra("No");
        Number_tv.setText(Number);

        generateOTP();

        setupOTPiInputs();

        Verify_btn.setOnClickListener(v -> {

            if (inputCode1.getText().toString().isEmpty()
                    ||inputCode2.getText().toString().isEmpty()
                    ||inputCode3.getText().toString().isEmpty()
                    ||inputCode4.getText().toString().isEmpty()
                    ||inputCode5.getText().toString().isEmpty()
                    ||inputCode6.getText().toString().isEmpty()) {

                Toast.makeText(otp_recieve_Activity.this, "Please Enter Valid OTP", Toast.LENGTH_SHORT).show();
            }

             Otp = inputCode1.getText().toString() +
                        inputCode2.getText().toString() +
                        inputCode3.getText().toString() +
                        inputCode4.getText().toString() +
                        inputCode5.getText().toString() +
                        inputCode6.getText().toString();

            startActivity(new Intent(otp_recieve_Activity.this, Starting_Activity.class));
            finish();

            PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(otpId, Otp);
            signInWithPhoneAuthCredential(phoneAuthCredential);
        });

        back.setOnClickListener(v -> {
            startActivity(new Intent(getApplicationContext(),send_otp_Activity.class));
            finish();
        });
        Resend_tv.setOnClickListener(v -> {
            generateOTP();
            Toast.makeText(otp_recieve_Activity.this, "otp will resend", Toast.LENGTH_SHORT).show();
        });
    }

    private void generateOTP() {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(Number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                                otpId=s;
                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull FirebaseException e) {
                                Toast.makeText(otp_recieve_Activity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        })          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }

    private void setupOTPiInputs(){

        inputCode1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputCode2.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputCode2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputCode3.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputCode3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputCode4.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputCode4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputCode5.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        inputCode5.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!s.toString().trim().isEmpty()){
                    inputCode6.requestFocus();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {

                        startActivity(new Intent(otp_recieve_Activity.this, Starting_Activity.class));
                        finish();


                    } else {
                        Toast.makeText(otp_recieve_Activity.this, "Sign-In Error", Toast.LENGTH_SHORT).show();

                    }
                });
    }
}