package com.example.uas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private HewanAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
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
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        EditText jenis = view.findViewById(R.id.et_jenis);
        EditText kaki = view.findViewById(R.id.et_kaki);

        Button btn = view.findViewById(R.id.save);
        DbHelper dbHelper = new DbHelper(getContext());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean status = dbHelper.addData(jenis.getText().toString(), kaki.getText().toString());
                if (status) {
                    Toast.makeText(getContext(), "Hewan inserted", Toast.LENGTH_SHORT).show();
                    RVFragment rvFragment = (RVFragment) requireActivity().getSupportFragmentManager().findFragmentByTag("RVFragmentTag");
                    if (rvFragment!= null){
                        rvFragment.loadData();
                    }
                    requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new RVFragment());
                }
                else{
                    Toast.makeText(getContext(), "Fail to insert", Toast.LENGTH_SHORT).show();
                }

            }
        });
        return view;
    }
}