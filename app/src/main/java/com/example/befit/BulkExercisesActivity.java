package com.example.befit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BulkExercisesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk_exercises);

        ListView listView=findViewById(R.id.bulkExerciselistview);

        String[] chest={
                "Push Ups",
                "Flat Bench Press",
                "Incline Dumbbell Press",
                "Decline Press",
                "Incline Dumbbell Fly",
                "Dumbbell Bent Arm Pullover"
        };
        String[] shoulder={
                "Military Press",
                "Dumbbell Press",
                "Dumbbell Lateral Raise",
                "Cable Front Raise",
                "Up Right Rows"
        };
        String[] lats={
                "Lat Pull Down's",
                "Seated Cable Row",
                "Bent Over Barbell Row",
                "T Bar Row",
                "Dumbbell Rows"
        };
        String[] bicep={
                "Bicep Curls",
                "Dumbbell Curls",
                "Preacher Curls",
                "Cable Curls",
                "Concentration Curls",
        };
        String[] tricep={
                "Dumbbell Extension",
                "Incline Tricep Extension",
                "Cable Tricep Extension",
                "Close Grip Bench Press",
                "Narrow Grip Push Ups"
        };

        String[] squats={
                "Barbell Squats",
                "Calves Leg Press",
                "Sumo Squat",
                "Calf Machine",
                "Squats"
        };

        String[] abs={
                "Cross Body Crunches",
                "Jackknives",
                "Leg Raises",
                "Reverse Crunches",
                "Plank Exercise"
        };
        Intent bea=getIntent();
        int pos=Integer.parseInt(bea.getStringExtra("pos"));
        String colId = null;
        switch(pos) {
            case 0:
                colId="Chest Exercises";
                ArrayAdapter chestAdapter=new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_selectable_list_item,
                        chest
                );
                listView.setAdapter(chestAdapter);
                break;
            case 1:
                colId="Shoulder Exercises";
                ArrayAdapter shoulderAdapter=new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_selectable_list_item,
                        shoulder
                );
                listView.setAdapter(shoulderAdapter);
                break;
            case 2:
                colId="Lats Exercises";
                ArrayAdapter latsAdapter=new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_selectable_list_item,
                        lats
                );
                listView.setAdapter(latsAdapter);
                break;
            case 3:
                colId="Bicep Exercises";
                ArrayAdapter bicepAdapter=new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_selectable_list_item,
                        bicep
                );
                listView.setAdapter(bicepAdapter);
                break;
            case 4:
                colId="Tricep Exercises";
                ArrayAdapter tricepAdapter=new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_selectable_list_item,
                        tricep
                );
                listView.setAdapter(tricepAdapter);
                break;
            case 5:
                colId="Squats Exercises";
                ArrayAdapter squatsAdapter=new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_selectable_list_item,
                        squats
                );
                listView.setAdapter(squatsAdapter);
                break;
            case 6:
                colId="Abs Exercises";
                ArrayAdapter absAdapter=new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_selectable_list_item,
                        abs
                );
                listView.setAdapter(absAdapter);
                break;
        }



        String finalColId = colId;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                //textView.setText("The best football player is : " + selectedItem);
                //Toast.makeText(LeanDietActivity.this, "Diet is "+selectedItem, Toast.LENGTH_SHORT).show();
                Intent ld = new Intent(BulkExercisesActivity.this, FirestoreDisplayActivity.class);
                ld.putExtra("docId", selectedItem);
                ld.putExtra("colId", finalColId);
                startActivity(ld);
            }
        });
    }
}