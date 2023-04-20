package com.sandhya.firebasedatabasesendandreceive;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SecondActivity extends AppCompatActivity {

    EditText edtName, edtEmail, edtPassword;
    Button btnSignUp;
    TextView txtLogin;
    ImageView imgPhoto;
    FirebaseAuth auth;
    StorageReference storageReference;
    DatabaseReference reference;
    Uri uri;
    Bitmap bitmap;

    String downloadUrl = "";

    ArrayList<UsersModel> models;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        getSupportActionBar().hide();


        auth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference().child("Users");
        storageReference = FirebaseStorage.getInstance().getReference().child("Users");
        initSignUp();
        initNewAccount();
        initImagePick();
    }

    private void initImagePick() {
        imgPhoto = findViewById(R.id.imgPhoto);
        imgPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,100);
            }
        });

    }

    private void uploadImage() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
      //  bitmap.compress(Bitmap.CompressFormat.JPEG,50,baos);
        byte[] finalimg = baos.toByteArray();
        final StorageReference filePath;
        filePath = storageReference.child(finalimg+".jpg");
        final UploadTask uploadTask =filePath.putBytes(finalimg);
        uploadTask.addOnCompleteListener(SecondActivity.this, new OnCompleteListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                if (task.isSuccessful()){
                    uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            filePath.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    downloadUrl = String.valueOf(uri);
                                }
                            });
                        }
                    });
                }else {
//                    pd.dismiss();
                    //Toast.makeText(UploadImage.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode==RESULT_OK){
            if (requestCode==100){
                uri = data.getData();
                imgPhoto.setImageURI(uri);
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void initSignUp() {
        edtEmail = findViewById(R.id.edtEmail);
        edtName = findViewById(R.id.edtName);
        edtPassword = findViewById(R.id.edtPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtName.getText().toString();
                String email = edtEmail.getText().toString();
                String password = edtPassword.getText().toString();
                if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SecondActivity.this, "Please enter login email and password", Toast.LENGTH_SHORT).show();
                } else {
                    uploadImage();

                    auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                String url = String.valueOf(uri);
                                models =  new ArrayList<>();
                                String date = DateFormat.getDateInstance().format(new Date());
                                models.add(new UsersModel(email,name,password,date,downloadUrl));
                                reference.child(auth.getUid()).setValue(models);
                                Toast.makeText(SecondActivity.this, "successfully", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(SecondActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
//
                }
            }
        });

    }

    private void initNewAccount() {
        txtLogin = findViewById(R.id.txtLogin);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SecondActivity.this, LoginActivity.class));
            }
        });
    }
}