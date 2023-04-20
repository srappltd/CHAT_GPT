package com.sandhya.firebasedatabasesendandreceive;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

public class ContactActivity extends AppCompatActivity {


    RecyclerView recycler;
    ArrayList<ContactModel> models;
    ContactAdapter contactAdapter;
    DatabaseReference reference;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

//        recycler = findViewById(R.id.recycler);
//        recycler.setLayoutManager(new LinearLayoutManager(this));
//        models = new ArrayList<>();
//        models.add(new ContactModel("abhay","23qr","2345","2345","2345","12345","234567"));
//        models.add(new ContactModel("abhay","23qr","2345","2345","2345","12345","234567"));
//        models.add(new ContactModel("abhay","23qr","2345","2345","2345","12345","234567"));
//        models.add(new ContactModel("abhay","23qr","2345","2345","2345","12345","234567"));
//        models.add(new ContactModel("abhay","23qr","2345","2345","2345","12345","234567"));
//        contactAdapter = new ContactAdapter(this,models);
//        recycler.setAdapter(contactAdapter);

        reference = FirebaseDatabase.getInstance().getReference("Contacts");
        FirebaseAuth auth = FirebaseAuth.getInstance();

        reference.child(Objects.requireNonNull(auth.getUid())).addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                models = new ArrayList<>();
                if (snapshot.exists()) {
                    for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                        ContactModel model = dataSnapshot.getValue(ContactModel.class);
                        models.add(model);
                    }
                    Collections.shuffle(models);
                    contactAdapter = new ContactAdapter(getApplicationContext(), models);
//                    recycler.setHasFixedSize(true);
//                    recycler.setLayoutManager(new LinearLayoutManager(ContactActivity.this));
                    recycler.setAdapter(contactAdapter);
                    contactAdapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Error" + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}