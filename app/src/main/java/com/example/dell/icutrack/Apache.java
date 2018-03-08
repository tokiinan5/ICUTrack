package com.example.dell.icutrack;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import static com.example.dell.icutrack.R.layout.apache;

/**
 * A simple {@link Fragment} subclass.
 */
public class Apache extends Fragment {


    public Apache() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(apache,container,false);
        return view;
    }

}
