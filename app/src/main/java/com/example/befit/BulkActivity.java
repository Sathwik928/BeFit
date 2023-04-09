package com.example.befit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class BulkActivity extends AppCompatActivity {
    SharedPreferences sp;
    FirebaseAuth mAuth;

    GoogleSignInClient mGoogle ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bulk);

        // 1- ListView Initialization
        ListView listView = findViewById(R.id.leanlistview);

        // 2- Data Source - Arrays [Title, SubTitle, Images]
        String[] mainTitle = {
                "Chest (Monday)",
                "Shoulder (Tuesday)",
                "Lats (Wednesday)",
                "Bicep (Thursday)",
                "Tricep (Friday)",
                "Squats (Saturday)",
                "Rest/Abs (Sunday)",
        };


        int[] imagesArray = {
                R.drawable.bulk,
                R.drawable.shoulder,
                R.drawable.lats,
                R.drawable.lean1,
                R.drawable.tricep,
                R.drawable.squats,
                R.drawable.lean,
        };



        // 3- Adapter - Custom Adapter
        BulkLeanListAdapter adapter = new BulkLeanListAdapter(this, mainTitle, imagesArray);
        listView.setAdapter(adapter);

        Intent bea=new Intent(BulkActivity.this,BulkExercisesActivity.class);


        // 4- Handling Click Events
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bea.putExtra("pos",""+position);
                startActivity(bea);

                /*switch(position) {
                    case 0:
                        bea.putExtra("pos",""+position);
                        startActivity(bea);
                        Toast.makeText(getApplicationContext(), "Clicked First Item", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        bea.putExtra("pos",""+position);
                        startActivity(bea);
                        Toast.makeText(getApplicationContext(), "Clicked Second Item", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        bea.putExtra("pos",""+position);
                        startActivity(bea);
                        Toast.makeText(getApplicationContext(), "Clicked Third Item", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        bea.putExtra("pos",""+position);
                        startActivity(bea);
                        Toast.makeText(getApplicationContext(), "Clicked Fourth Item", Toast.LENGTH_SHORT).show();
                        break;
                    case 4:
                        bea.putExtra("pos",""+position);
                        startActivity(bea);
                        Toast.makeText(getApplicationContext(), "Clicked Fifth Item", Toast.LENGTH_SHORT).show();
                        break;
                    case 5:
                        bea.putExtra("pos",""+position);
                        startActivity(bea);
                        Toast.makeText(getApplicationContext(), "Clicked Sixth Item", Toast.LENGTH_SHORT).show();
                        break;
                    case 6:
                        bea.putExtra("pos",""+position);
                        startActivity(bea);
                        Toast.makeText(getApplicationContext(), "Clicked Seventh Item", Toast.LENGTH_SHORT).show();
                        break;
                }*/
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.signOut:
                SharedPreferences sp = getSharedPreferences("sharedPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("status", "");
                editor.commit();
                mAuth = FirebaseAuth.getInstance();
                mAuth.signOut();
                //mGoogle.signOut();
                GoogleSignInOptions gso = new GoogleSignInOptions.
                        Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                        build();

                GoogleSignInClient googleSignInClient= GoogleSignIn.getClient(this,gso);
                googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            FirebaseAuth.getInstance().signOut(); // very important if you are using firebase.
                            Intent login_intent = new Intent(getApplicationContext(),LoginActivity.class);
                            login_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK); // clear previous task (optional)
                            startActivity(login_intent);
                        }
                    }
                });
                Intent rIntent = new Intent(BulkActivity.this, LoginActivity.class);
                startActivity(rIntent);
                finish();
                Toast.makeText(this, "Signing Out", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}