package com.example.schedule;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.google.firebase.database.DatabaseReference;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
DatabaseReference RootReference;
Button BtnCreate,BtnList;
private Intent In;
private Resources Resourses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BtnCreate = findViewById(R.id.BtnCreate);
        BtnCreate.setOnClickListener(this);
        BtnList = findViewById(R.id.BtnList);
        BtnList.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
    switch (v.getId()){
        case R.id.BtnCreate:
            In = new Intent(MainActivity.this, CreateContact.class);
            startActivity(In);
            break;

        case R.id.BtnList:
            In = new Intent(MainActivity.this, DataRequest.class);
            startActivity(In);
     }
    }
}

