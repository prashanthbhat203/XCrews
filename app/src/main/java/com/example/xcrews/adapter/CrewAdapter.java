package com.example.xcrews.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.xcrews.databinding.CrewItemsBinding;
import com.example.xcrews.model.Crew;

public class CrewAdapter extends RecyclerView.Adapter<CrewAdapter.CrewViewHolder> {




    @NonNull
    @Override
    public CrewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CrewViewHolder(CrewItemsBinding.inflate(LayoutInflater.from(parent.getContext())
                , parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CrewViewHolder holder, int position) {
        Crew crew = (Crew) asyncListDiffer.getCurrentList().get(position);
        holder.itemsBinding.tvCrewName.setText(String.valueOf(crew.getName()));
        holder.itemsBinding.tvCrewAgency.setText(String.valueOf(crew.getAgency()));
        holder.itemsBinding.tvCrewLink.setText(String.valueOf(crew.getWikipedia()));
        Glide.with(holder.itemView).load(crew.getImage()).into(holder.itemsBinding.civCrewImage);

    }



    @Override
    public int getItemCount() {
        return asyncListDiffer.getCurrentList().size();
    }

    private static final DiffUtil.ItemCallback<Crew> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Crew>() {
                @Override
                public boolean areItemsTheSame(@NonNull Crew oldItem, @NonNull Crew newItem) {
                    return oldItem.getId().equals(newItem.getId());
                }

                @Override
                public boolean areContentsTheSame(@NonNull Crew oldItem, @NonNull Crew newItem) {
                    return oldItem.getId().equals(newItem.getId()) && oldItem.getWikipedia().equals(newItem.getWikipedia());
                }
            };

    public AsyncListDiffer asyncListDiffer = new AsyncListDiffer(this, DIFF_CALLBACK);

    static class CrewViewHolder extends RecyclerView.ViewHolder {
        CrewItemsBinding itemsBinding;

        public CrewViewHolder(CrewItemsBinding itemsBinding) {
            super(itemsBinding.getRoot());
            this.itemsBinding = itemsBinding;
        }
    }
}
