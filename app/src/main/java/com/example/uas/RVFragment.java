package com.example.uas;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RVFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RVFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private List<Hewan> hewans = new ArrayList<>();
    private RecyclerView recyclerView;
    private HewanAdapter adapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RVFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RVFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RVFragment newInstance(String param1, String param2) {
        RVFragment fragment = new RVFragment();
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
        View view = inflater.inflate(R.layout.fragment_r_v, container, false);

        recyclerView = view.findViewById(R.id.rv_hewan);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));



        adapter = new HewanAdapter(getContext(), hewans);
        recyclerView.setAdapter(adapter);

        Button btn = view.findViewById(R.id.add);
        btn.setOnClickListener(v->{
            requireActivity().getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new AddFragment()).addToBackStack(null).commit();
        });
        return view;
    }

    public void loadData(){
//        hewans.clear();
        DbHelper dbHelper = new DbHelper(getContext());
        Cursor c = dbHelper.getAllData();
        while(c.moveToNext()){
            Hewan h = new Hewan(c.getString(0), c.getString(1));
            hewans.add(h);
        }
        adapter.notifyDataSetChanged();
    }
}