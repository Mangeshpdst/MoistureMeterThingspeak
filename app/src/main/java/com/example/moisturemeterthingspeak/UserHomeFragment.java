package com.example.moisturemeterthingspeak;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserHomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserHomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    View l1, c1;
    TextView txt, t1;

    Button scan_Bnt;
    EditText station, area, lot_no, bail;

    View back;

    String[] county_name = {"Select Country", "India", "America", "England"};
    String[] state_name = {"Select State", "Maharashtra", "Delhi", "Punjab"};
    String[] city_name = {"Select City", "Mumbai", "Nashik", "Pune"};
    String[] bargain_values = {"", "1", "2", "3", "4", "5"};

    String mCountry, mState, mCity, mStation, mArea, tpincode, tlotno, tbail;
    int mLotno, mBail;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public UserHomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserHomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserHomeFragment newInstance(String param1, String param2) {
        UserHomeFragment fragment = new UserHomeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user_home, container, false);

        back = view.findViewById(R.id.back_wc);

        Spinner country = view.findViewById(R.id.country);
        Spinner state = view.findViewById(R.id.state);
        Spinner city = view.findViewById(R.id.city);

        Spinner Bargain_User = view.findViewById(R.id.bargain_spinner_user);

        station = view.findViewById(R.id.station);
        area = view.findViewById(R.id.area);
        lot_no = view.findViewById(R.id.lotno);
        bail = view.findViewById(R.id.bail);

        l1 = view.findViewById(R.id.l1); //linear layout
        c1 = view.findViewById(R.id.c1);  //card view
        txt = view.findViewById(R.id.txt);  //linear text
        t1 = view.findViewById(R.id.t1);  //or

        back.setVisibility(View.INVISIBLE);

        txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.VISIBLE);
                c1.setVisibility(View.GONE);
                txt.setVisibility(View.GONE);
                t1.setVisibility(View.GONE);
                back.setVisibility(View.VISIBLE);
            }
        });

        ArrayAdapter aa = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, county_name);
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


        ArrayAdapter aa2 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, state_name);
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

        ArrayAdapter aa3 = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, city_name);
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

        ArrayAdapter bargain_Adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, bargain_values);
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

        scan_Bnt = view.findViewById(R.id.scan_btn);
        scan_Bnt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                if (tpincode.isEmpty() | tlotno.isEmpty() | tbail.isEmpty()) {
//                    startActivity(new Intent(getContext(), Scanner.class));
//                } else {

//                    mLotno = Integer.parseInt(tlotno);
//                    mBail = Integer.parseInt(tbail);
                    startActivity(new Intent(getContext(), Scanner.class));

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
            }
        });

        return view;
    }
}


