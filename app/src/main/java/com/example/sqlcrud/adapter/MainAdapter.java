package com.example.sqlcrud.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlcrud.R;
import com.example.sqlcrud.data.MyDbHandler;
import com.example.sqlcrud.model.Users;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    ArrayList<Users> arrayList;
    MyDbHandler myDbHandler;
    public MainAdapter(Context context, ArrayList<Users> arrayList) {
        myDbHandler = new MyDbHandler(context);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_user_recycler, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.username.setText(arrayList.get(position).getName());
        holder.password.setText(arrayList.get(position).getPassword());
        holder.email.setText(arrayList.get(position).getEmail());
        holder.phoneNo.setText(arrayList.get(position).getPhoneNum());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                myDbHandler.deleteUser(arrayList.get(position).getName());
                arrayList.remove(position);
                notifyDataSetChanged();
                Toast.makeText(context, "user Deleted Successfully!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView username, password, email, phoneNo;
        private Button btnDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.tv_name);
            password = itemView.findViewById(R.id.tv_password);
            email = itemView.findViewById(R.id.tv_email);
            phoneNo = itemView.findViewById(R.id.tv_phoneNum);
            btnDelete = itemView.findViewById(R.id.btn_delete);
        }
    }
}
