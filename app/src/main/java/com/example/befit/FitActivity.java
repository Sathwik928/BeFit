package com.example.befit;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class FitActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk);

        // 1- ListView Initialization
        ListView listView = findViewById(R.id.leanlistview);

        // 2- Data Source - Arrays [Title, SubTitle, Images]
        String[] mainTitle = {
                "Chest, Lats \n(Monday)",
                "Shoulder, Legs \n(Tuesday)",
                "Bicep, Tricep \n(Wednesday)",
                "Chest, Lats \n(Thursday)",
                "Shoulder, Legs \n(Friday)",
                "Bicep, Tricep \n(Saturday)",
                "Abs (Sunday)",
        };


        int[] imagesArray = {
                R.drawable.bulk,
                R.drawable.shoulder,
                R.drawable.lean1,
                R.drawable.lats,
                R.drawable.squats,
                R.drawable.tricep,
                R.drawable.lean,
        };



        // 3- Adapter - Custom Adapter
        BulkLeanListAdapter adapter = new BulkLeanListAdapter(this, mainTitle, imagesArray);
        listView.setAdapter(adapter);
        Intent fea=new Intent(FitActivity.this,BulkExercisesActivity.class);


        // 4- Handling Click Events
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                fea.putExtra("pos",""+position);
                startActivity(fea);
                /*switch(position) {
                    case 0:
                        fea.putExtra("pos",""+position);
                        startActivity(fea);
                        Toast.makeText(getApplicationContext(), "Clicked First Item", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        fea.putExtra("pos",""+position);
                        startActivity(fea);
                        //Toast.makeText(getApplicationContext(), "Clicked Second Item", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        fea.putExtra("pos",""+position);
                        startActivity(fea);
                        //Toast.makeText(getApplicationContext(), "Clicked Third Item", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        fea.putExtra("pos",""+position);
                        startActivity(fea);
                        Toast.makeText(getApplicationContext(), "Clicked Fourth Item", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        fea.putExtra("pos",""+position);
                        startActivity(fea);
                        Toast.makeText(getApplicationContext(), "Clicked Fifth Item", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        fea.putExtra("pos",""+position);
                        startActivity(fea);
                        Toast.makeText(getApplicationContext(), "Clicked Sixth Item", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        fea.putExtra("pos",""+position);
                        startActivity(fea);
                        Toast.makeText(getApplicationContext(), "Clicked Seventh Item", Toast.LENGTH_SHORT).show();
                        break;
                }*/
            }
        });

    }
}