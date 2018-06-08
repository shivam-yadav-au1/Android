package com.example.shivam.activity;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class PersonFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        /*return super.onCreateView(inflater, container, savedInstanceState);*/
        return inflater.inflate(R.layout.fragment_person_fragmetn_details, container, false);
    }
}
