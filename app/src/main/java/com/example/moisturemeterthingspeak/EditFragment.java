package com.example.moisturemeterthingspeak;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    LinearLayout l1, l2;
    Button Add, Delete, add_bargain_btn;
    View back_btn, bargain_l;

    public EditFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditFragment newInstance(String param1, String param2) {
        EditFragment fragment = new EditFragment();
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
        View view = inflater.inflate(R.layout.fragment_edit, container, false);

        l1 = view.findViewById(R.id.addlayout);
        l2 = view.findViewById(R.id.deletelayout);

        Add = view.findViewById(R.id.add_btn);
        Delete = view.findViewById(R.id.delete_btn);
        add_bargain_btn = view.findViewById(R.id.add_bargain_btn);
        bargain_l = view.findViewById(R.id.bargain_l);

        back_btn = view.findViewById(R.id.edit_back);
        back_btn.setVisibility(View.INVISIBLE);

        l1.setVisibility(View.INVISIBLE);
        l2.setVisibility(View.INVISIBLE);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l1.setVisibility(View.VISIBLE);
                l2.setVisibility(View.GONE);
                Delete.setVisibility(View.GONE);
                back_btn.setVisibility(View.VISIBLE);
                add_bargain_btn.setVisibility(View.INVISIBLE);
            }
        });

        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                l2.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
                Add.setVisibility(View.GONE);
                back_btn.setVisibility(View.VISIBLE);
                add_bargain_btn.setVisibility(View.INVISIBLE);
            }
        });

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    l1.setVisibility(View.INVISIBLE);
                    l2.setVisibility(View.INVISIBLE);
                    bargain_l.setVisibility(View.GONE);
                    Add.setVisibility(View.VISIBLE);
                    Delete.setVisibility(View.VISIBLE);
                    back_btn.setVisibility(View.INVISIBLE);
                    add_bargain_btn.setVisibility(View.VISIBLE);
            }
        });

        add_bargain_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bargain_l.setVisibility(View.VISIBLE);
                l1.setVisibility(View.GONE);
                l2.setVisibility(View.GONE);
                Add.setVisibility(View.GONE);
                Delete.setVisibility(View.GONE);
                back_btn.setVisibility(View.VISIBLE);


            }
        });

       return view;
    }
}