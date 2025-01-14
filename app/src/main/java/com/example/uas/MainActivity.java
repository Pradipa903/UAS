package com.example.uas;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Hewan> hewans = new ArrayList<>();
    private RecyclerView recyclerView;
    private HewanAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        recyclerView = findViewById(R.id.rv_hewan);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        DbHelper dbHelper = new DbHelper(this);
        Cursor c = dbHelper.getAllData();
        while(c.moveToNext()){
            Hewan h = new Hewan(c.getString(0), c.getInt(1));
            hewans.add(h);
        }

        adapter = new HewanAdapter(this, hewans);
        recyclerView.setAdapter(adapter);

        Button btn = findViewById(R.id.add);
        btn.setOnClickListener(v->{
            getSupportFragmentManager().beginTransaction().replace(R.id.framelayout, new AddFragment()).commit();
        });

    }
}