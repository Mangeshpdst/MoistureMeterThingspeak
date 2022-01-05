package com.example.moisturemeterthingspeak;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class StartingActivity2 extends AppCompatActivity {

    View l1, c1;
    TextView txt, t1, back_btn;

    Button scan_Bnt;
    EditText station, area, lot_no, bail;

    ImageView back;

    String[] county_name = {"Select Country", "India", "America", "England"};
    String[] state_name = {"Select State", "Maharashtra", "Delhi", "Punjab"};
    String[] city_name = {"Select City", "Mumbai", "Nashik", "Pune"};
    String[] bargain_values = {"", "1", "2", "3", "4", "5"};

    String mCountry, mState, mCity, mStation, mArea, tlotno, tbail;
    int mLotno, mBail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_starting2);

        back = findViewById(R.id.back_wc2);
        back.setVisibility(View.INVISIBLE);
        back_btn = findViewById(R.id.back_btn);
        back_btn.setVisibility(View.VISIBLE);


        Spinner country = findViewById(R.id.country2);
        Spinner state = findViewById(R.id.state2);
        Spinner city = findViewById(R.id.city2);

        Spinner Bargain_User = findViewById(R.id.bargain_spinner_user2);

        station = findViewById(R.id.station2);
        area = findViewById(R.id.area2);
        lot_no = findViewById(R.id.lotno2);
        bail = findViewById(R.id.bail2);

        l1 = findViewById(R.id.l12); //linear layout
        c1 = findViewById(R.id.c12);  //card view
        txt = findViewById(R.id.txt2);  //linear text
        t1 = findViewById(R.id.t12);  //or


        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.VISIBLE);
                c1.setVisibility(View.GONE);
                txt.setVisibility(View.GONE);
                t1.setVisibility(View.GONE);
                back.setVisibility(View.VISIBLE);
                back_btn.setVisibility(View.INVISIBLE);
            }
        });

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, county_name);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        //Setting the ArrayAdapter data on the Spinner
        country.setAdapter(aa);

        country.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                                              @Override
                                              public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                                                  mCountry = parent.getItemAtPosition(position).toString();
                                                  if (mCountry == "Select Country") {
                                                      mCountry = null;
                                                  }

                                              }

                                              @Override
                                              public void onNothingSelected(AdapterView<?> parent) {
                                                  mCountry = null;
                                              }
                                          }
        );


        ArrayAdapter aa2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, state_name);
        aa2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(aa2);
        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mState = parent.getItemAtPosition(position).toString();
                if (mState == "Select State") {
                    mState = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mState = null;
            }
        });

        ArrayAdapter aa3 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, city_name);
        aa3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(aa3);
        city.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mCity = parent.getItemAtPosition(position).toString();
                if (mCity == "Select City") {
                    mCity = null;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mCity = null;
            }
        });

        ArrayAdapter bargain_Adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, bargain_values);
        bargain_Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Bargain_User.setAdapter(bargain_Adapter);
        Bargain_User.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //mState = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mState = null;
            }
        });

        mStation = station.getText().toString();
        mArea = area.getText().toString();
        tlotno = lot_no.getText().toString();
        tbail = bail.getText().toString();

        scan_Bnt = findViewById(R.id.scan_btn2);
        scan_Bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (tpincode.isEmpty() | tlotno.isEmpty() | tbail.isEmpty()) {
//                    startActivity(new Intent(getContext(), Scanner.class));
//                } else {

//                    mLotno = Integer.parseInt(tlotno);
//                    mBail = Integer.parseInt(tbail);
                startActivity(new Intent(getApplicationContext(), Scanner.class));

            }
//            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txt.setVisibility(View.VISIBLE);
                c1.setVisibility(View.VISIBLE);
                t1.setVisibility(View.VISIBLE);

                l1.setVisibility(View.GONE);
                back.setVisibility(View.INVISIBLE);
                back_btn.setVisibility(View.VISIBLE);

            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Admin_MainActivity.class));
                finish();
            }
        });

    }
}