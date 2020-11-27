package com.example.courseworkproject;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TrainRecyclerViewAdapter
        extends RecyclerView.Adapter<TrainRecyclerViewAdapter.TrainViewHolder> {

    private Context conext;

    private List<TrainDisplay> trains;

    public TrainRecyclerViewAdapter(Context context, List<TrainDisplay> trains){
        super();
        this.conext = context;
        this.trains = trains;



    }

    @NonNull
    @Override
    public TrainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TrainViewHolder holder, int position) {

    }

    @Override
    public int getItemCount(){
        return this.trains.size();
    }
    class TrainViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private View trainItemView;
        private TrainRecyclerViewAdapter adapter;

        public TrainViewHolder(View trainItemView, TrainRecyclerViewAdapter adapter){
            super(trainItemView);
            this.trainItemView = trainItemView;
            this.adapter = adapter;

            trainItemView.findViewById(R.id.recyclerView);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();

            TrainDisplay train = trains.get(position);

            Log.d("Train RECYCLER", "user clicked on Train" + train.getDestination());
        }
    }

}