package com.sandhya.firebasedatabasesendandreceive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        DatabaseReference reference,userRef;
        FirebaseAuth auth,auths;
        auth = FirebaseAuth.getInstance();
        auths = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        reference.child(auth.getUid()).child("/0").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String email = snapshot.child("email").getValue().toString();
                    String name = snapshot.child("name").getValue().toString();

                    TextView txtName,txtEmail;
                    txtName = findViewById(R.id.txtName);
                    txtEmail = findViewById(R.id.txtEmail);
                    txtName.setText(name);
                    txtEmail.setText(email);
                    getSupportActionBar().setTitle(name);
                    getSupportActionBar().setSubtitle(email);

                    txtEmail.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            startActivity(new Intent(HomeActivity.this,MainActivity.class));
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        userRef = FirebaseDatabase.getInstance().getReference().child("Contacts");
        userRef.child(Objects.requireNonNull(auths.getUid())).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()){
                    String mobile = Objects.requireNonNull(snapshot.child("mobile").getValue()).toString();
                    TextView txtMobile = findViewById(R.id.txtMobiles);
                    txtMobile.setText(mobile);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}