package com.example.lab4.main;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lab4.R;
import com.example.lab4.models.University;

import java.util.List;

public class UniversityAdapter extends RecyclerView.Adapter<UniversityAdapter.UniversityViewHolder> {

    private final List<University> universityList;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(University university);
    }

    public UniversityAdapter(List<University> universityList, OnItemClickListener listener) {
        this.universityList = universityList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public UniversityViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_university, parent, false);
        return new UniversityViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UniversityViewHolder holder, int position) {
        University university = universityList.get(position);
        holder.bind(university, listener);
    }

    @Override
    public int getItemCount() {
        return universityList.size();
    }

    static class UniversityViewHolder extends RecyclerView.ViewHolder {
        private final TextView tvUniversityName;
        private final TextView tvUniversityCountry;

        public UniversityViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUniversityName = itemView.findViewById(R.id.tvUniversityName);
            tvUniversityCountry = itemView.findViewById(R.id.tvUniversityCountry);
        }

        public void bind(final University university, final OnItemClickListener listener) {
            tvUniversityName.setText(university.getName());
            tvUniversityCountry.setText(university.getCountry());
            itemView.setOnClickListener(v -> listener.onItemClick(university));
        }
    }
}
