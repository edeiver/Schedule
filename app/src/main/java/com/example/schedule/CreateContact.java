package com.example.schedule;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CreateContact extends AppCompatActivity implements View.OnClickListener {
    private DatabaseReference RootReference, myData;
    private FirebaseDatabase firebaseDatabase;
    private Button BtnFirebase;
    private EditText Name,LastName,Phone, Cellphone;
    private RecyclerView recyclerView;
    private ContactAdapter contactAdapter;
    private ArrayList<User> userArrayList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_user);
        BtnFirebase = findViewById(R.id.BtnSave);
        RootReference = FirebaseDatabase.getInstance().getReference();
        Name=findViewById(R.id.TxtName);
        LastName=findViewById(R.id.TxtLastName);
        Cellphone=findViewById(R.id.TxtCellphone);
        Phone=findViewById(R.id.TxtPhone);
        BtnFirebase.setOnClickListener(this);
        recyclerView=(RecyclerView)findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       // getData();
    }
    private void LoadFirebaseData(String nameOn, String lastNameOn, String phoneOn, String cellphoneOn) {
        Map<String, Object> UserData = new HashMap<>();
        UserData.put("Name", nameOn);
        UserData.put("LastName", lastNameOn);
        UserData.put("Phone", phoneOn);
        UserData.put("Cellphone", cellphoneOn);
        RootReference.child("User").push().setValue(UserData);
    }
    @Override
    public void onClick(View v) {
        if (Name.getText().length() == 0 || LastName.getText().length() == 0 || Phone.getText().length() == 0 || Cellphone.getText().length() == 0) {
            Toast.makeText(this, R.string.error, Toast.LENGTH_LONG).show();
        } else {
            switch (v.getId()) {
                case R.id.BtnSave:
                    String NameOn, LastNameOn, PhoneOn, CellphoneOn;
                    NameOn = Name.getText().toString();
                    LastNameOn = LastName.getText().toString();
                    PhoneOn = Phone.getText().toString();
                    CellphoneOn = Cellphone.getText().toString();
                    LoadFirebaseData(NameOn, LastNameOn, PhoneOn, CellphoneOn);
                    Toast.makeText(this, R.string.done, Toast.LENGTH_LONG).show();
                    break;
            }
        }
    }

    private void getData(){
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
                    contactAdapter = new ContactAdapter(userArrayList,R.layout.contact_detail);
                    recyclerView.setAdapter(contactAdapter);
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


