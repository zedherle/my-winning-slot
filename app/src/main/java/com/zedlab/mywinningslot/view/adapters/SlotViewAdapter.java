package com.zedlab.mywinningslot.view.adapters;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.zedlab.mywinningslot.R;
import com.zedlab.mywinningslot.model.RegionSlot;

import java.util.List;


public class SlotViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Activity context;
    List<RegionSlot> userArrayList;

    public SlotViewAdapter(Activity context, List<RegionSlot> userArrayList) {
        this.context = context;
        this.userArrayList = userArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(context).inflate(R.layout.item, parent, false);
        return new RecyclerViewViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        RegionSlot regionSlot = userArrayList.get(position);
        RecyclerViewViewHolder viewHolder = (RecyclerViewViewHolder) holder;
        viewHolder.txtView_date.setText(regionSlot.getDate());
        viewHolder.txtView_centerName.setText(regionSlot.getCenterName());
        viewHolder.txtView_vaccine.setText(regionSlot.getVaccineName());
        viewHolder.txtView_slots.setText(regionSlot.getSlots());
        viewHolder.textView_age.setText(regionSlot.getAgeGroup());
    }

    @Override
    public int getItemCount() {
        return userArrayList.size();
    }

    class RecyclerViewViewHolder extends RecyclerView.ViewHolder {
        TextView txtView_date;
        TextView txtView_centerName;
        TextView txtView_vaccine;
        TextView txtView_slots;
        TextView textView_age;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);

            txtView_date = itemView.findViewById(R.id.txtView_date);
            txtView_centerName = itemView.findViewById(R.id.txtView_center);
            txtView_vaccine = itemView.findViewById(R.id.txtView_vaccine);
            txtView_slots = itemView.findViewById(R.id.txtView_slots);
            textView_age = itemView.findViewById(R.id.txtView_age);
        }
    }
}
