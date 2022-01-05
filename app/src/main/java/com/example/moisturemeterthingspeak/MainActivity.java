package com.example.moisturemeterthingspeak;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String JSON_URL = ""; //"https://api.thingspeak.com/channels/1566079/feeds.json?api_key=PV7KBWTPA5M2QVLT&results=2";
    private TextView Moisture_Value;
    private String moisture;
    private RequestQueue mqueue; //Request Queue object
    private Button Start_Btn, Next_btn, Done_btn;

    public FirebaseDatabase database;
    public DatabaseReference myRef;

    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //This code hides the status bar
        //The code is has to be in onCreate super class and before content view
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("Data");

        //Request permission for Write external storage
//        ActivityCompat.requestPermissions(this, new String[]{
//                Manifest.permission.WRITE_EXTERNAL_STORAGE}, PackageManager.PERMISSION_GRANTED);

        Moisture_Value = (TextView) findViewById(R.id.moisture_value);
        Start_Btn = (Button) findViewById(R.id.start);
        Next_btn = (Button) findViewById(R.id.next);
        Done_btn = (Button) findViewById(R.id.done);

        back = findViewById(R.id.back_main);

        Next_btn.setVisibility(View.INVISIBLE);
        Done_btn.setVisibility(View.INVISIBLE);

        JSON_URL = getIntent().getStringExtra("key"); //get data from scanner activity and assign to JSON url object

        mqueue = Volley.newRequestQueue(this);  //Object of RequestQueue

        Start_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoadData();
            }
        });

        Next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Moisture_Value.setText("00.00");
                LoadData();
            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), UserHomeFragment.class));
            }
        });

        Done_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(MainActivity.this)
                        .setIcon(R.drawable.ic_baseline_warning_24)
                        .setTitle("Confirm")
                        .setMessage("Are you sure to Continue? or Add another Bail?")
                        .setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Moisture_Value.setText("00.00");
                                Start_Btn.setVisibility(View.VISIBLE);
                                Next_btn.setVisibility(View.INVISIBLE);
                                Done_btn.setVisibility(View.INVISIBLE);
                            }
                        }).setNegativeButton("Add another Bail", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     dialog.dismiss();
                    }
                }).show();


            }
        });
    }

    private void LoadData() {

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, JSON_URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray JSONarray = response.getJSONArray("feeds");

                            for (int i = 0; i < JSONarray.length(); i++) {
                                JSONObject obj = JSONarray.getJSONObject(i);
                                moisture = obj.getString("field1");
                                Moisture_Value.setText(moisture);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mqueue.add(request);

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                // This method is called once with the initial value and again
//                // whenever data at this location is updated.
//                String value = dataSnapshot.getValue(String.class);
//                Moisture_Value.setText(value);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//                // Failed to read value
//                Toast.makeText(getApplicationContext(), "Fail to read value", Toast.LENGTH_LONG).show();
//            }
//        });

        Start_Btn.setVisibility(View.INVISIBLE);
        Next_btn.setVisibility(View.VISIBLE);
        Done_btn.setVisibility(View.VISIBLE);
    }
}

