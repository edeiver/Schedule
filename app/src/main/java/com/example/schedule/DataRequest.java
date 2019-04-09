package com.example.schedule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DataRequest extends AppCompatActivity {
    DatabaseReference RootReference;
    Button BtnFirebase;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnFirebase = findViewById(R.id.BtnSave);
        RootReference = FirebaseDatabase.getInstance().getReference();
        DataRequestFirebase();


    }

    private void DataRequestFirebase() {
        RootReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(final DataSnapshot snapshot : dataSnapshot.getChildren()){
                    RootReference.child("User").child(snapshot.getKey()).addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            User user= snapshot.getValue(User.class);
                            String name = user.getName();
                            String lastname = user.getLastName();
                            String cellPhone = user.getCellphone();
                            String phone = user.getPhone();
                            Log.e("Name ",""+name);
                            Log.e("LastName ",""+lastname);
                            Log.e("Cellphone:",""+cellPhone);
                            Log.e("Phone ",""+phone);
                            Log.e("Data",""+snapshot.getValue());
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
