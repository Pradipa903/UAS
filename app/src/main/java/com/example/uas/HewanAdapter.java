package com.example.uas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HewanAdapter extends RecyclerView.Adapter<HewanAdapter.HewanViewHolder> {
    private Context context;
    private List<Hewan> hewans;

    public HewanAdapter(Context context, List<Hewan> hewans) {
        this.context = context;
        this.hewans = hewans;
    }

    @NonNull
    @Override
    public HewanAdapter.HewanViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.isi_rv, parent, false);
        return new HewanViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HewanAdapter.HewanViewHolder holder, int position) {
        Hewan hewan = hewans.get(position);
        holder.jenis.setText(hewan.getNama());
        holder.kaki.setText("Berkaki" + hewan.getKaki());
    }

    @Override
    public int getItemCount() {
        return hewans.size();
    }

    public static class HewanViewHolder extends RecyclerView.ViewHolder{
        TextView jenis, kaki;

        public HewanViewHolder(@NonNull View itemView) {
            super(itemView);
            jenis = itemView.findViewById(R.id.jenis);
            kaki = itemView.findViewById(R.id.kaki);
        }
    }
}
