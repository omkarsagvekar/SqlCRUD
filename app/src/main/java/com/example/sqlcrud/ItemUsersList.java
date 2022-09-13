package com.example.sqlcrud;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sqlcrud.adapter.MainAdapter;
import com.example.sqlcrud.data.MyDbHandler;
import com.example.sqlcrud.model.Users;
import com.example.sqlcrud.params.Params;

import java.util.ArrayList;

public class ItemUsersList extends AppCompatActivity {
    ArrayList<Users> arrayList;
    MainAdapter mainAdapter;
    Button btnDelete;
    MyDbHandler myDbHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_users_list);
        initObj();
//        allListeners();
    }

//    private void allListeners() {
//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                myDbHandler.deleteUser();
//            }
//        });
//    }

    private void initObj() {
        arrayList = new ArrayList<>();
        mainAdapter = new MainAdapter(ItemUsersList.this, arrayList);
        RecyclerView recyclerView = findViewById(R.id.user_recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(ItemUsersList.this));
        recyclerView.setAdapter(mainAdapter);
        myDbHandler = new MyDbHandler(ItemUsersList.this);
        arrayList.addAll(myDbHandler.getAllUsers());

        btnDelete = findViewById(R.id.btn_delete);

    }
}
