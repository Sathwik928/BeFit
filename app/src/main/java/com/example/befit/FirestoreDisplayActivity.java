package com.example.befit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class FirestoreDisplayActivity extends AppCompatActivity {

    private FirebaseFirestore db= FirebaseFirestore.getInstance();
    public static final String KEY_NAME = "url";

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firestore_display);
        imageView=findViewById(R.id.firestoreImage);
        Intent dda=getIntent();
        String docId=dda.getStringExtra("docId");
        String colId=dda.getStringExtra("colId");
        db.collection(colId).document(docId).addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                if(error!=null){
                    Toast.makeText(FirestoreDisplayActivity.this, "Error!!", Toast.LENGTH_SHORT).show();
                }
                else if (value!=null && value.exists()) {
                    Glide.with(getApplicationContext()).load(value.get("url")).into(imageView);
                }
            }
        });

    }
}