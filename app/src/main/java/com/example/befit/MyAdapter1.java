package com.example.befit;


import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter1 extends RecyclerView.Adapter<MyAdapter1.ViewHolder> {

    //1-Data Source
    private final List<DataModel1> listData;

    public MyAdapter1(List<DataModel1> listData) {
        this.listData = listData;
    }



    //2-View Holder Class
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ImageView imageView;
        private final TextView textView;


        public ViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewRecycler);
            textView = itemView.findViewById(R.id.textViewRecycler);
        }
    }

    //3-Implementing the Methods
    @NonNull
    @Override
    public MyAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View listItem=inflater.inflate(R.layout.item_recycler1,parent,false);
        ViewHolder viewHolder=new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        int pos=position;
        holder.textView.setText(listData.get(position).getTitle());
        holder.imageView.setImageResource(listData.get(position).getImage());


        //Handling Click Events
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v("key",listData.get(pos).toString());
                //Toast.makeText(view.getContext(), "Clicked"+pos, Toast.LENGTH_SHORT).show();
                switch(pos) {
                    case 0:
                        Intent bulkIntent=new Intent(view.getContext(),BulkActivity.class);
                        view.getContext().startActivity(bulkIntent);
                        break;
                    case 1:
                        Intent leanIntent=new Intent(view.getContext(),LeanActivity.class);
                        view.getContext().startActivity(leanIntent);
                        break;
                    case 2:
                        Intent fitIntent=new Intent(view.getContext(),FitActivity.class);
                        view.getContext().startActivity(fitIntent);
                        break;
                    case 3:
                        Intent dietIntent=new Intent(view.getContext(),DietActivity.class);
                        view.getContext().startActivity(dietIntent);
                        break;
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return 4;
    }

}
