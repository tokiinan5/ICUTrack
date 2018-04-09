package com.example.dell.icutrack;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.example.dell.icutrack.R.layout.apache;
import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Apache extends Fragment {
    Spinner sTemp,sBp,sHeart,sRespiratory,sPaO2,sPh,sSodium,sPotassium,sCreatinine,sAage,
    sHematocrit,sWBc,sComa,sCritical;
    int mTemp,mBp,mHeart,mRespiratory,mPaO2,mPh,mSodium,mPotassium,mCreatinine,mAge,
            mHematocrit,mWBc,mComa,mCritical,summation;


    View view;

    ArrayAdapter adapterTemp,adapterBp,adapterHeart,adapterRespiratory,adapterPaO2,adapterPh,adapterSodium,adapterPotassium
            ,adapterCreatinine,adapterAge,
            adapterHematocrit,adapterWBc,adapterComa,adapterCritical;


    public Apache() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(apache,container,false);
        init();
        summation=0;
        return view;
    }

    private void init() {
        sTemp=view.findViewById(R.id.sTemp);
        sBp=view.findViewById(R.id.sBp);
        sHeart=view.findViewById(R.id.sHeart);
        sRespiratory=view.findViewById(R.id.sResp);
        sPaO2=view.findViewById(R.id.sPaO2);
        sPh=view.findViewById(R.id.sPH);
        sSodium=view.findViewById(R.id.sSodium);
        sPotassium=view.findViewById(R.id.sPotassium);
        sCreatinine=view.findViewById(R.id.sCreatinine);
        sHematocrit=view.findViewById(R.id.sHematocrit);
        sWBc=view.findViewById(R.id.sWBC);
        sComa=view.findViewById(R.id.sComa);
        sCritical=view.findViewById(R.id.sChronic);
        //Spinner For Temperature


        List<String> tempList = Arrays.asList(getResources().getStringArray(R.array.Temperature_Array));
     adapterTemp=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,tempList){
         @Override
         public boolean isEnabled(int position){
             if(position == 0)
             {
                 // Disable the first item from Spinner
                 // First item will be use for hint
                 return false;
             }
             else
             {
                 return true;
             }
         }
         @Override
         public View getDropDownView(int position, View convertView,
                                     ViewGroup parent) {
             View view = super.getDropDownView(position, convertView, parent);
             TextView tv = (TextView) view;
             if(position == 0){
                 // Set the hint text color gray
                 tv.setTextColor(Color.GRAY);
             }
             else {
                 tv.setTextColor(Color.BLACK);
             }
             return view;
         }

     };
     adapterTemp.setDropDownViewResource(android.R.layout.simple_spinner_item);
     sTemp.setAdapter(adapterTemp);
     sTemp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             switch (i){
                 case 0: mTemp=4;
                      break;
                 case  1: mTemp=3;
                 break;
                 case  2: mTemp=1;
                 break;
                 case 3: mTemp=0;
                 break;
                 case 4: mTemp=1;
                 break;
                 case 5: mTemp=2;
                 break;
                 case 6: mTemp=3;
                 break;
                 default:mTemp=0;
             }
             summation=summation+mTemp;

         }

         @Override
         public void onNothingSelected(AdapterView<?> adapterView) {

         }
     });

     //Spinner for BP

        final List<String> bpList = Arrays.asList(getResources().getStringArray(R.array.Bp_Array));
        adapterBp=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,bpList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterBp.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sBp.setAdapter(adapterTemp);
        sBp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
               // Toast.makeText(getContext(),bpList.get(i).toString(),Toast.LENGTH_SHORT).show();
                switch (i){
                    case 0:mBp=4;
                    break;
                    case 1:mBp=3;
                    break;
                    case 2:mBp=2;
                    break;
                    case 3: mBp=0;
                    break;
                    case 4: mBp=2;
                    break;
                    case 5: mBp=4;
                    break;
                    default:mBp=0;

                }
                summation=summation+mBp;
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner For Heart Rate
//Log.e("Result", String.valueOf(summation));
        List<String> heartList = Arrays.asList(getResources().getStringArray(R.array.Heart_Array));
        adapterHeart=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,heartList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterHeart.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sHeart.setAdapter(adapterHeart);
        sHeart.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0: mHeart=4;
                    break;
                    case 1: mHeart=3;
                    break;






                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        //Spinner for Respiratory


        List<String> respList = Arrays.asList(getResources().getStringArray(R.array.Respiratory_Array));
        adapterRespiratory=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,respList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterRespiratory.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sRespiratory.setAdapter(adapterRespiratory);

        //Spinner PaO2


        List<String> paO2List = Arrays.asList(getResources().getStringArray(R.array.Pa02_Array));
        adapterPaO2=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,paO2List){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterPaO2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sPaO2.setAdapter(adapterPaO2);

        // Spinner PH


        List<String> pHList = Arrays.asList(getResources().getStringArray(R.array.PH_Array));
        adapterPh=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,pHList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterPh.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sPh.setAdapter(adapterPh);

        //Spinner for Sodium


        List<String> sodiumList = Arrays.asList(getResources().getStringArray(R.array.Sodium_Array));
        adapterSodium=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,sodiumList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterSodium.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sSodium.setAdapter(adapterSodium);

        // spinner Potassium

        List<String> potassiumList = Arrays.asList(getResources().getStringArray(R.array.Potassium_Array));
        adapterPotassium=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,potassiumList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterPotassium.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sPotassium.setAdapter(adapterPotassium);

        //Spinner for Creatinine

        List<String> creatinineList = Arrays.asList(getResources().getStringArray(R.array.Creatinine_Array));
        adapterCreatinine=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,creatinineList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterCreatinine.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sCreatinine.setAdapter(adapterCreatinine);

        // Spinner Hematocrit

        List<String> hemaList = Arrays.asList(getResources().getStringArray(R.array.Hematocrit_Array));
        adapterHematocrit=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,hemaList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterHematocrit.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sHematocrit.setAdapter(adapterHematocrit);
        // Spinner WBC

        List<String> wbcList = Arrays.asList(getResources().getStringArray(R.array.Wbc_Array));
        adapterWBc=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,wbcList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterWBc.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sWBc.setAdapter(adapterWBc);


        // COMA
/*

        List<String> ageList = Arrays.asList(getResources().getStringArray(R.array.Age_Array));
        adapterAge=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,ageList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sAage.setAdapter(adapterAge);*/

        // Critical


        List<String> criticalList = Arrays.asList(getResources().getStringArray(R.array.Critical_Array));
        adapterCritical=new ArrayAdapter(getActivity(),android.R.layout.simple_spinner_item,criticalList){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterCritical.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sCritical.setAdapter(adapterCritical);













    }



}
