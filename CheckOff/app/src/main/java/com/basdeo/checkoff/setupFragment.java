package com.basdeo.checkoff;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.basdeo.providercorelib.Globals.ProviderGlobals;

/**
 * Created by Eugene on 3/27/14.
 */
public class setupFragment extends Fragment {
    private static final String ARG_SECTION_NUMBER = "section_number";

    public static setupFragment newInstance(int sectionNumber) {
        setupFragment fragment = new setupFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.dashboard, container, false);

        super.onCreate(savedInstanceState);
        ProviderGlobals g = ProviderGlobals.getInstance();
        setHasOptionsMenu(true);
        return rootView;
    }

}
