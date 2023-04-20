package com.sandhya.firebasedatabasesendandreceive.data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sandhya.firebasedatabasesendandreceive.R;

import java.util.ArrayList;
import java.util.Collections;

public class DataReceiveActivity extends AppCompatActivity {

    RecyclerView dataRecycler;
    ArrayList<DataModel> models;
    DataAdapter adapter;

    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_receive);

        reference = FirebaseDatabase.getInstance().getReference().child("Data");
        dataRecycler = findViewById(R.id.dataRecycler);
        dataRecycler.setLayoutManager(new LinearLayoutManager(this));
        models = new ArrayList<>();
        reference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    models.clear();
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                        DataModel model = dataSnapshot.getValue(DataModel.class);
                        models.add(model);
                    }
                    Collections.shuffle(models);
                    adapter = new DataAdapter(DataReceiveActivity.this,models);
                    dataRecycler.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}