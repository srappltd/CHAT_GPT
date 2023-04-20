package com.sandhya.firebasedatabasesendandreceive.data;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sandhya.firebasedatabasesendandreceive.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import de.hdodenhof.circleimageview.CircleImageView;

public class DataSendActivity extends AppCompatActivity {

    EditText edtName,edtAge,edtMobile;
    Button btnSend,btnAdd;
    CircleImageView uploadImage;
    static final int REQ_CODE = 100;

    DatabaseReference dataRef;
    StorageReference storageReference;
    Bitmap bitmap;
    Uri filepath;
    String downloadUrl = "";

    ArrayList<DataModel> models;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_send);

        edtAge = findViewById(R.id.edtAge);
        edtName = findViewById(R.id.edtName);
        edtMobile = findViewById(R.id.edtMobile);
        btnSend = findViewById(R.id.btnSend);
        uploadImage = findViewById(R.id.imgUploadImage);

        dataRef = FirebaseDatabase.getInstance().getReference().child("Data");
        storageReference = FirebaseStorage.getInstance().getReference().child("Data");

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               if (filepath!=null){
                   StorageReference stReference = storageReference.child(System.currentTimeMillis()+"."+MimeTypeMap.getSingleton().getExtensionFromMimeType(getContentResolver().getType(filepath)));
                   stReference.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                       @Override
                       public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                           stReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                               @Override
                               public void onSuccess(Uri uri) {
                                   String name = edtName.getText().toString();
                                   String age = edtAge.getText().toString();
                                   String mobile = edtMobile.getText().toString();
                                   if (name.isEmpty()||age.isEmpty()||mobile.isEmpty()){
                                       Toast.makeText(DataSendActivity.this, "fill all detail", Toast.LENGTH_SHORT).show();
                                   }else {
                                       HashMap<String, Object> map = new HashMap<>();
                                       map.put("name",name);
                                       map.put("age",age);
                                       map.put("mobile",mobile);
                                       map.put("image",uri.toString());
                                       dataRef.child(name).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                           @Override
                                           public void onComplete(@NonNull Task<Void> task) {
                                               if (task.isSuccessful()){
                                                   Toast.makeText(DataSendActivity.this, "send", Toast.LENGTH_SHORT).show();
                                                   startActivity(new Intent(getApplicationContext(),DataReceiveActivity.class));
                                               }else {
                                                   Toast.makeText(DataSendActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                               }
                                           }
                                       });
                                   }
                               }
                           });
                       }
                   });
               }else {
                   Toast.makeText(DataSendActivity.this, "Error", Toast.LENGTH_SHORT).show();
               }
            }
        });

        uploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,REQ_CODE);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==REQ_CODE){
                filepath = data.getData();
                uploadImage.setImageURI(filepath);
            }
        }
    }
}