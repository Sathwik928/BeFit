package com.example.befit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BulkDietActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_diet);

        ListView listView=findViewById(R.id.bulklistview);

        String[] bulkDiet={
                "7 am Banana",
                "8 am Eggs",
                "9 am Breakfast",
                "11 am Fruits",
                "1 pm Lunch",
                "3 pm Fruits",
                "4 pm Green Tea",
                "5 pm Banana",
                "9 pm Eggs",
                "10 pm Pulka"

        };

        ArrayAdapter bulkadapter=new ArrayAdapter<String>(
                this,
                android.R.layout.simple_selectable_list_item,
                bulkDiet
        );

        listView.setAdapter(bulkadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                //textView.setText(" : " + selectedItem);
                //Toast.makeText(LeanDietActivity.this, "Diet is "+selectedItem, Toast.LENGTH_SHORT).show();
                Intent bd=new Intent(BulkDietActivity.this, FirestoreDisplayActivity.class);
                bd.putExtra("docId",selectedItem);
                bd.putExtra("colId","BulkDiet");

                startActivity(bd);



            }
        });
    }
}