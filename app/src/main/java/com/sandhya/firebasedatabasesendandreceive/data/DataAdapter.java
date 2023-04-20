package com.sandhya.firebasedatabasesendandreceive.data;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sandhya.firebasedatabasesendandreceive.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder> {
    Context context;
    ArrayList<DataModel> models;

    public DataAdapter(Context context, ArrayList<DataModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.data_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DataAdapter.ViewHolder holder, int position) {

        holder.txtName.setText(models.get(position).getName());
        holder.txtAge.setText(models.get(position).getAge());
        holder.txtMobile.setText(models.get(position).getMobile());

        // image fetch
        Glide.with(context).load(models.get(position).getImage()).into(holder.dataImage);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtName,txtAge,txtMobile;
        CircleImageView dataImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtAge = itemView.findViewById(R.id.txtAge);
            txtMobile = itemView.findViewById(R.id.txtMobile);
            dataImage = itemView.findViewById(R.id.dataImage);
        }
    }
}
