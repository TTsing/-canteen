package com.example.a10916.manageractivity;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class inlineFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_inlinefragment,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Button buttoninline = getActivity().findViewById(R.id.inline);
        buttoninline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragment fragment = (fragment) getActivity();
                fragment.replaceFragment(new surelineFrag());
            }
        });
    }
}

