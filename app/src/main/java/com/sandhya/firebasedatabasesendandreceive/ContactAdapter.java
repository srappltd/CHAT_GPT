package com.sandhya.firebasedatabasesendandreceive;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    Context context;
    ArrayList<ContactModel> models;
    DatabaseReference reference;
    ContactAdapter contactAdapter;


    public ContactAdapter(Context context, ArrayList<ContactModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.contact_items,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        ContactModel model = models.get(position);
        if (model!=null) {
            holder.txtName.setText(model.getName());
            holder.txtEmail.setText(model.getEmail());
            holder.txtMobileNo.setText(model.getMobile());
            holder.txtBranch.setText(model.getBranch());
            holder.txtAddress.setText(model.getAddress());
            holder.txtCollege.setText(model.getCollege());
            holder.txtRollNo.setText(model.getRollNo());

          //  getData(holder.txtName, holder.txtEmail, holder.txtRollNo, holder.txtCollege, holder.txtAddress, holder.txtBranch, holder.txtMobileNo);
        }
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName,txtEmail,txtMobileNo,txtRollNo,txtAddress,txtCollege,txtBranch;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            txtMobileNo = itemView.findViewById(R.id.txtMobileNo);
            txtRollNo = itemView.findViewById(R.id.txtRollNo);
            txtAddress = itemView.findViewById(R.id.txtAddress);
            txtCollege = itemView.findViewById(R.id.txtCollege);
            txtBranch = itemView.findViewById(R.id.txtBranch);
        }
    }
}
