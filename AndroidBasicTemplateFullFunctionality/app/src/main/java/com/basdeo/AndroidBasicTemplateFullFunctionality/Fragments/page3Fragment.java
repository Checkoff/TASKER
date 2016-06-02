package com.basdeo.AndroidBasicTemplateFullFunctionality.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basdeo.AndroidBasicTemplateFullFunctionality.R;

public class page3Fragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.page3, container, false);
    }
}
