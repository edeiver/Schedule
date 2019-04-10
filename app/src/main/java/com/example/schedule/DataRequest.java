package com.example.schedule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DataRequest extends AppCompatActivity {
    DatabaseReference RootReference;
    private RecyclerView recyclerView2;
    private ContactAdapter contactAdapter2;
    private ArrayList<User> userArrayList = new ArrayList<>();
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_contact);
        RootReference = FirebaseDatabase.getInstance().getReference();
        recyclerView2=(RecyclerView)findViewById(R.id.RecyclerView2);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView2.setLayoutManager(manager);
        recyclerView2.setHasFixedSize(true);
        DataRequestFirebase();
    }

    private void DataRequestFirebase() {
        RootReference.child("User").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    userArrayList.clear();
                    for(final DataSnapshot ds: dataSnapshot.getChildren()){
                        User user= ds.getValue(User.class);
                        String name = user.getName();
                        String lastname = user.getLastName();
                        String cellPhone = user.getCellphone();
                        String phone = user.getPhone();
                        userArrayList.add(user);
                    }
                    contactAdapter2 = new ContactAdapter(userArrayList,R.layout.contact_detail);
                    recyclerView2.setAdapter(contactAdapter2);
                }else{

                    Toast.makeText(getApplicationContext(), R.string.empty, Toast.LENGTH_LONG).show();

                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    }


