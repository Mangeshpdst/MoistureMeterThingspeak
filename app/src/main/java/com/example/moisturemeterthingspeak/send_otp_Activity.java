package com.example.moisturemeterthingspeak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

public class send_otp_Activity extends AppCompatActivity {
    FirebaseAuth mAuth;
    CountryCodePicker ccp;
    TextView admin_Login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_send_otp);


        admin_Login = findViewById(R.id.admin_login);
        mAuth = FirebaseAuth.getInstance();

        if (mAuth.getCurrentUser() != null){
            startActivity(new Intent(send_otp_Activity.this, Starting_Activity.class));
            finish();
        }
//
//        final ProgressBar progressBar = findViewById(R.id.progress_bar);
//        progressBar.setVisibility(View.INVISIBLE);
        EditText mobile_no = findViewById(R.id.mobile_no);
        Button get_Otp = findViewById(R.id.get_otp);
        ccp = findViewById(R.id.ccode);
        ccp.registerCarrierNumberEditText(mobile_no);
        //String Number = mobile_no.getText().toString();

        get_Otp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (mobile_no.getText().toString().length() < 10){
                    mobile_no.setError("Please Enter 10 digit Mobile Number");
                    return;
                }
//                progressBar.setVisibility(View.VISIBLE);
                get_Otp.setVisibility(View.INVISIBLE);


                Intent i = new Intent(send_otp_Activity.this, otp_recieve_Activity.class);
                i.putExtra("No", ccp.getFullNumberWithPlus().replace(" ", ""));
                startActivity(i);
                finish();
//                progressBar.setVisibility(View.GONE);
            }
        });

        admin_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(send_otp_Activity.this, Admin_Signup.class));
                finish();
            }
        });
    }
}