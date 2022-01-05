package com.example.moisturemeterthingspeak;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<dataModel> dataHolder;
    View back, scan_btn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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

        View view = inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView = view.findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        back = view.findViewById(R.id.back);
        scan_btn = view.findViewById(R.id.scaning_btn);

        dataHolder = new ArrayList<>();

        dataModel obj1 = new dataModel("7", "10:24", "12/12/21", "s.v. road, irla, vile parle west, mumbai 400056, maharashtra, India", "1", "1");
        dataHolder.add(obj1);

        dataModel obj2 = new dataModel("12", "10:25", "12/12/21", "s.v. road, irla, vile parle west, mumbai 400056, maharashtra, India", "1", "2");
        dataHolder.add(obj2);

        dataModel obj3 = new dataModel("5", "10:27", "12/12/21", "s.v. road, irla, vile parle west, mumbai 400056, maharashtra, India", "1", "3");
        dataHolder.add(obj3);

        dataModel obj4 = new dataModel("10", "10:28", "12/12/21", "s.v. road, irla, vile parle west, mumbai 400056, maharashtra, India", "1", "4");
        dataHolder.add(obj4);

        dataModel obj5 = new dataModel("8", "10:30", "12/12/21", "s.v. road, irla, vile parle west, mumbai 400056, maharashtra, India", "1", "5");
        dataHolder.add(obj5);

        recyclerView.setAdapter(new myAdapter(dataHolder));

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),Admin_Signup.class));
            }
        });

        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(),StartingActivity2.class));
            }
        });

        return view;
    }
}