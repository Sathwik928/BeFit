package com.example.befit;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BulkLeanListAdapter extends ArrayAdapter<String> {

    private final Activity context;

    private final String[] mainTitle;
    private final int[] imageArray;


    public BulkLeanListAdapter(Activity context, String[] mainTitle, int[] imageArray) {
        super(context, R.layout.bulk_lean_list_item, mainTitle);
        this.context = context;
        this.mainTitle = mainTitle;
        this.imageArray = imageArray;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.bulk_lean_list_item, null, true);

        TextView titleText = rowView.findViewById(R.id.textView1);
        ImageView imageView = rowView.findViewById(R.id.imageview);



        titleText.setText(mainTitle[position]);
        imageView.setImageResource(imageArray[position]);


        return rowView;
    }
}
