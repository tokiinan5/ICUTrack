package com.example.dell.icutrack;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import static com.example.dell.icutrack.R.layout.new_ystem;

/**
 * A simple {@link Fragment} subclass.
 */
public class IRPS extends Fragment {

    EditText age;
    View myView;
    Button bSave,bReset,bCalculate;
    Spinner sAdmission;



    public IRPS() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       myView = inflater.inflate(new_ystem,container,false);
        init();
        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar=Calendar.getInstance();
                int sec=calendar.get(Calendar.MINUTE);
                Toast.makeText(getContext(), String.valueOf(sec), Toast.LENGTH_SHORT).show();

            }
        });


        bReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),SavedData.class);
                startActivity(intent);

            }
        });

        return myView;

    }

   private void  init() {
        bSave=myView.findViewById(R.id.bSave);
        bReset=myView.findViewById(R.id.bReset);
        bCalculate=myView.findViewById(R.id.bCalculate);
        sAdmission=myView.findViewById(R.id.sAdmmission);
         ArrayAdapter myAdapter=ArrayAdapter.createFromResource(getActivity(),R.array.Admission_Array,android.R.layout.simple_spinner_item);
        myAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sAdmission.setAdapter(myAdapter);
        sAdmission.setPrompt("Click Here");
        sAdmission.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i>0)
                {
                  String s= sAdmission.getItemAtPosition(i).toString();
                    Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



    }

}
