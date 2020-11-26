package com.example.a10916.manageractivity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class usermanager extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_usermanager,container,false);

        Intent intent = getActivity().getIntent();

        String name = intent.getStringExtra("idname");
        String  stunumber = intent.getStringExtra("stunumber");

        TextView textViewstu = view.findViewById(R.id.stunumbertext);
        TextView textViewname = view.findViewById(R.id.nametext);

        textViewname.setText(name);
        textViewstu.setText(stunumber);



        return view;
    }
}

