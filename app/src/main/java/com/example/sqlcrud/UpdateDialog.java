package com.example.sqlcrud;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sqlcrud.adapter.MainAdapter;
import com.example.sqlcrud.data.MyDbHandler;
import com.example.sqlcrud.model.Users;

public class UpdateDialog extends DialogFragment {
    private MyDbHandler myDbHandler;
    private String name, password, email, phoneNum;
    private EditText etPassword, etEmail, etPhoneNum;
    private TextView tvName;
    private ImageButton btnClose;
    private Button btnUpdate;
    private int position;

    public UpdateDialog() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_update_dialog, container, false);
        getDialog().setCanceledOnTouchOutside(false);
        myDbHandler = new MyDbHandler(getContext());
        assert getArguments() != null;
        name = getArguments().getString("name");
        password = getArguments().getString("password");
        email = getArguments().getString("email");
        phoneNum = getArguments().getString("phoneNum");
        position = Integer.parseInt(getArguments().getString("position"));

        tvName = view.findViewById(R.id.tv_username);
        etPassword = view.findViewById(R.id.et_password);
        etEmail = view.findViewById(R.id.et_email);
        etPhoneNum = view.findViewById(R.id.et_phoneNum);
        btnClose = view.findViewById(R.id.btn_close);
        btnUpdate = view.findViewById(R.id.btn_changeUpdate);

        tvName.setText(name);
        etPassword.setText(password);
        etEmail.setText(email);
        etPhoneNum.setText(phoneNum);

        allListeners();
        return view;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void allListeners() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View view) {
                String password = etPassword.getText().toString();
                String email = etEmail.getText().toString();
                String phoneNum = etPhoneNum.getText().toString();
                Users users = new Users();
                if (password.isEmpty() || email.isEmpty() || phoneNum.isEmpty()){
                    Toast.makeText(getContext(), "Unable to update", Toast.LENGTH_SHORT).show();
                }else{
                    myDbHandler.updateUser(name, password, email, phoneNum);
                    Toast.makeText(getContext(), "update record successfully", Toast.LENGTH_SHORT).show();
                    dismiss();
                    MainAdapter.arrayList.get(position).setPassword(password);
                    MainAdapter.arrayList.get(position).setEmail(email);
                    MainAdapter.arrayList.get(position).setPhoneNum(phoneNum);
//                    MainAdapter.notifyDataSetChanged();
                    ItemUsersList.mainAdapter.notifyDataSetChanged();
                }
            }
        });

    }
}