package com.example.befit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class LeanDietActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lean_diet);

        ListView listView=findViewById(R.id.leanlistview);

        String[] leanDiet={
                "6 am Warm Water",
                "9 am Eggs",
                "9 am Milk",
                "9 am Oats",
                "11 am Apple, Orange",
                "1 pm Pulka",
                "4 pm BlackEyedPeas",
                "4 pm Groundnut",
                "5 pm Green Tea",
                "6 pm Milk",
                "6 pm Eggs",
                "9 pm Coconut Water",
                "9 pm Pulka"

        };

        ArrayAdapter leanadapter=new ArrayAdapter<String>(
                this,
                android.R.layout.simple_selectable_list_item,
                leanDiet
        );

        listView.setAdapter(leanadapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                //textView.setText("The best football player is : " + selectedItem);
                //Toast.makeText(LeanDietActivity.this, "Diet is "+selectedItem, Toast.LENGTH_SHORT).show();
                Intent ld=new Intent(LeanDietActivity.this, FirestoreDisplayActivity.class);
                ld.putExtra("docId",selectedItem);
                ld.putExtra("colId","LeanDiet");
                startActivity(ld);





               /* private DocumentReference freindRef = db.collection("Users").
                        document("Friends");*/
                /*db.collection("LeanDiet")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (QueryDocumentSnapshot document : task.getResult()) {
                                        Log.d(TAG, document.getId() + " => " + document.getData());
                                        Toast.makeText(LeanDietActivity.this, "Clicked On "+document.getId()+"=>"+document.getString(KEY_NAME), Toast.LENGTH_LONG).show();
                                        String imageUrl=document.getString(KEY_NAME);
                                        Intent ld=new Intent(LeanDietActivity.this,DietDisplayActivity.class);
                                        ld.putExtra("image",imageUrl);
                                        startActivity(ld);
                                        break;
                                    }
                                } else {
                                    Log.w(TAG, "Error getting documents.", task.getException());
                                }
                            }
                        });*/

            }
        });
    }
}