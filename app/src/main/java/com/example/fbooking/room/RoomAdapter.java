package com.example.fbooking.room;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.fbooking.R;

import java.util.List;

public class RoomAdapter extends RecyclerView.Adapter<RoomViewHolder>{
    private View view;

    Context context;
    private OnRoomClickListener onRoomClickListener;

    public RoomAdapter(Context context) {
        this.context = context;
    }

    public void setData() {

        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RoomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.room_row, parent, false);
        return new RoomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
