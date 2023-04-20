package com.sandhya.firebasedatabasesendandreceive;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    EditText edtName,edtMobile,edtEmail,edtAdBranch,edtCollege,edtRollNo,edtAddress;
    Button btnSubmit;
    FloatingActionButton floating;
    ProgressDialog dialog;
    FirebaseAuth auth;

    ArrayList<ContactModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        edtMobile = findViewById(R.id.edtMobile);
        edtEmail = findViewById(R.id.edtEmail);
        edtAdBranch = findViewById(R.id.edtAdBranch);
        edtCollege = findViewById(R.id.edtCollege);
        edtRollNo = findViewById(R.id.edtRollNo);
        edtAddress = findViewById(R.id.edtAddress);
        btnSubmit = findViewById(R.id.btnSubmit);
        floating = findViewById(R.id.floating);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = edtName.getText().toString();
                String mobile = edtMobile.getText().toString();
                String email = edtEmail.getText().toString();
                String branch = edtAdBranch.getText().toString();
                String college = edtCollege.getText().toString();
                String rollNo = edtRollNo.getText().toString();
                String address = edtAddress.getText().toString();

//                dialog = new ProgressDialog(getApplicationContext());
//                dialog.setTitle("Submit");
//                dialog.setMessage("Please Wait...");
//                dialog.setCancelable(true);
//                dialog.show();

                if (name.isEmpty()||mobile.isEmpty()||email.isEmpty()||branch.isEmpty()||college.isEmpty()||rollNo.isEmpty()||address.isEmpty()){
                    Toast.makeText(MainActivity.this, "Fill all Fields...", Toast.LENGTH_SHORT).show();
                }
                models = new ArrayList<>();
                DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Contacts");
                auth = FirebaseAuth.getInstance();
                String id = reference.push().getKey();
                ContactModel model = new ContactModel(name,mobile,email,address,branch,college,rollNo);
                HashMap<String, Object> map = new HashMap<>();
                map.put("name",name);
                map.put("email",email);
                map.put("mobile_no",mobile);
                map.put("college",college);
                map.put("roll_no",rollNo);
                map.put("branch",branch);
                map.put("address",address);
                reference.child(id).push().setValue(model)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
//                            dialog.dismiss();
                                if (task.isSuccessful()){
                                    Toast.makeText(MainActivity.this, "Submited ", Toast.LENGTH_SHORT).show();
//                                    startActivity(new Intent());
                                }else {
                                    Toast.makeText(MainActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


        floating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),ContactActivity.class);
                startActivity(intent);
            }
        });

    }
}