package com.example.dell.icutrack;


import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import static com.example.dell.icutrack.R.layout.apache;
import static com.example.dell.icutrack.R.layout.design_bottom_navigation_item;
import static java.security.AccessController.getContext;

/**
 * A simple {@link Fragment} subclass.
 */
public class Apache extends Fragment {
    private Spinner sTemp, sBp, sHeart, sRespiratory, sPaO2, sPh, sSodium, sPotassium, sCreatinine, sAage,
            sHematocrit, sWBc, sComa, sCritical;
    private int mTemp, mBp, mHeart, mRespiratory, mPaO2, mPh, mSodium, mPotassium, mCreatinine, mAge,
            mHematocrit, mWBc, mComa, mCritical, summ;
    private EditText eComa;
    String result,pCritical;
    private Button bSave, bReset, bCalculate;
    TextView tResult;
    private FirebaseAuth auth;
    private  FirebaseUser currentUser;


    View view;

    ArrayAdapter adapterTemp, adapterBp, adapterHeart, adapterRespiratory, adapterPaO2, adapterPh, adapterSodium, adapterPotassium, adapterCreatinine, adapterAge,
            adapterHematocrit, adapterWBc, adapterComa, adapterCritical;


    public Apache() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(apache, container, false);

        init();
        bCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result1();
                tResult.setText(result);

            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // au if()
                dataSave();
/*                auth=FirebaseAuth.getInstance();

                if(auth.getCurrentUser()!=null)
                {
                    Toast.makeText(getActivity(), "Method Has been called", Toast.LENGTH_SHORT).show();
                    dataSave();
                }
                else
                {
                    final AlertDialog.Builder mydialogue=new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater=getActivity().getLayoutInflater();
                    View dialogue=inflater.inflate(R.layout.signinalertdialogue,null);
                    mydialogue.setView(dialogue);
                    mydialogue.setTitle("Change Language");
                    mydialogue.create();
                    mydialogue.show();

                }*/

            }
        });

        return view;
    }

    private void init() {
        sTemp = view.findViewById(R.id.sTemp);
        sBp = view.findViewById(R.id.sBp);
        sHeart = view.findViewById(R.id.sHeart);
        sRespiratory = view.findViewById(R.id.sResp);
        sPaO2 = view.findViewById(R.id.sPaO2);
        sPh = view.findViewById(R.id.sPH);
        sSodium = view.findViewById(R.id.sSodium);
        sPotassium = view.findViewById(R.id.sPotassium);
        sCreatinine = view.findViewById(R.id.sCreatinine);
        sHematocrit = view.findViewById(R.id.sHematocrit);
        sWBc = view.findViewById(R.id.sWBC);
        summ = 0;
        //  sComa=view.findViewById(R.id.sComa);
        sCritical = view.findViewById(R.id.sChronic);
        sAage = view.findViewById(R.id.sAge);
        eComa = view.findViewById(R.id.eComa);
        bCalculate = view.findViewById(R.id.bCalculate);
        bSave = view.findViewById(R.id.bSave);
        bReset = view.findViewById(R.id.bReset);
        tResult=view.findViewById(R.id.tResult);

        //Spinner For Temperature

        List<String> tempList = Arrays.asList(getResources().getStringArray(R.array.Temperature_Array));
        adapterTemp = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, tempList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
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
                // summation=0;


                switch (i) {

                    case 1:
                        mTemp = 4;
                        break;
                    case 2:
                        mTemp = 3;
                        break;
                    case 3:
                        mTemp = 1;
                        break;
                    case 4:
                        mTemp = 0;
                        break;
                    case 5:
                        mTemp = 1;
                        break;
                    case 6:
                        mTemp = 2;
                        break;
                    case 7:
                        mTemp = 3;
                        break;
                    default:
                        mTemp = 0;

                }
                //  Toast.makeText(getContext(), String.valueOf(summation), Toast.LENGTH_SHORT).show();

                summ = summ + mTemp;
                //Toast.makeText(getContext(), String.valueOf(mTemp), Toast.LENGTH_SHORT).show();


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner for BP

        final List<String> bpList = Arrays.asList(getResources().getStringArray(R.array.Bp_Array));
        adapterBp = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, bpList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterBp.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sBp.setAdapter(adapterBp);
        sBp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Log.e("Result111111111111111", String.valueOf(summ));
                // Toast.makeText(getContext(),bpList.get(i).toString(),Toast.LENGTH_SHORT).show();
                switch (i) {
                    case 1:
                        mBp = 4;
                        break;
                    case 2:
                        mBp = 3;
                        break;
                    case 3:
                        mBp = 2;
                        break;
                    case 4:
                        mBp = 0;
                        break;
                    case 5:
                        mBp = 2;
                        break;
                    case 6:
                        mBp = 4;
                        break;
                    default:
                        mBp = 0;

                }
                summ = summ + mBp;
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner For Heart Rate
//Log.e("Result", String.valueOf(summation));
        List<String> heartList = Arrays.asList(getResources().getStringArray(R.array.Heart_Array));
        adapterHeart = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, heartList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
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
                switch (i) {
                    case 1:
                        mHeart = 4;
                        break;
                    case 2:
                        mHeart = 3;
                        break;
                    case 3:
                        mHeart = 2;
                        break;
                    case 4:
                        mHeart = 0;
                        break;
                    case 5:
                        mHeart = 2;
                        break;
                    case 6:
                        mHeart = 3;
                        break;
                    case 7:
                        mHeart = 4;
                        break;
                    default:
                        mHeart = 0;

                }
                summ = summ + mHeart;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        //Spinner for Respiratory


        List<String> respList = Arrays.asList(getResources().getStringArray(R.array.Respiratory_Array));
        adapterRespiratory = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, respList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterRespiratory.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sRespiratory.setAdapter(adapterRespiratory);
        sRespiratory.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        mRespiratory = 4;
                        break;

                    case 2:
                        mRespiratory = 3;
                        break;

                    case 3:
                        mRespiratory = 1;
                        break;

                    case 4:
                        mRespiratory = 0;
                        break;
                    case 5:
                        mRespiratory = 1;
                        break;
                    case 6:
                        mRespiratory = 2;
                        break;
                    case 7:
                        mRespiratory = 4;
                        break;
                    default:
                        mRespiratory = 0;

                }
                summ = summ + mRespiratory;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner PaO2


        List<String> paO2List = Arrays.asList(getResources().getStringArray(R.array.Pa02_Array));
        adapterPaO2 = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, paO2List) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterPaO2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sPaO2.setAdapter(adapterPaO2);
        sPaO2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {

                    case 1:
                        mPaO2 = 4;
                        break;

                    case 2:
                        mPaO2 = 3;
                        break;
                    case 3:
                        mPaO2 = 1;
                        break;
                    case 4:
                        mPaO2 = 0;
                        break;
                    case 5:
                        mPaO2 = 1;
                        break;
                    case 6:
                        mPaO2 = 3;
                        break;
                    default:
                        mPaO2 = 0;

                }
                summ = summ + mPaO2;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Spinner PH


        List<String> pHList = Arrays.asList(getResources().getStringArray(R.array.PH_Array));
        adapterPh = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, pHList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterPh.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sPh.setAdapter(adapterPh);
        sPh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        mPh = 4;
                        break;
                    case 2:
                        mPh = 3;
                        break;
                    case 3:
                        mPh = 1;
                        break;
                    case 4:
                        mPh = 0;
                        break;
                    case 5:
                        mPh = 2;
                        break;
                    case 6:
                        mPh = 4;
                        break;
                    default:
                        mPh = 0;
                }
                summ = summ + mPh;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner for Sodium


        final List<String> sodiumList = Arrays.asList(getResources().getStringArray(R.array.Sodium_Array));
        adapterSodium = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, sodiumList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterSodium.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sSodium.setAdapter(adapterSodium);
        sSodium.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        mSodium = 4;
                        break;
                    case 2:
                        mSodium = 3;
                        break;
                    case 3:
                        mSodium = 2;
                        break;
                    case 4:
                        mSodium = 1;
                        break;
                    case 5:
                        mSodium = 0;
                        break;
                    case 6:
                        mSodium = 2;
                        break;
                    case 7:
                        mSodium = 3;
                        break;
                    case 8:
                        mSodium = 4;
                        break;
                    default:
                        mSodium = 0;
                }
                summ = summ + mSodium;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // spinner Potassium

        List<String> potassiumList = Arrays.asList(getResources().getStringArray(R.array.Potassium_Array));
        adapterPotassium = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, potassiumList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterPotassium.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sPotassium.setAdapter(adapterPotassium);
        sPotassium.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        mPotassium = 4;
                        break;
                    case 2:
                        mPotassium = 3;
                        break;
                    case 3:
                        mPotassium = 1;
                        break;
                    case 4:
                        mPotassium = 0;
                        break;
                    case 5:
                        mPotassium = 1;
                        break;
                    case 6:
                        mPotassium = 2;
                        break;
                    case 7:
                        mPotassium = 4;
                        break;
                    default:
                        mPotassium = 0;

                }
                summ = summ + mPotassium;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Spinner for Creatinine

        List<String> creatinineList = Arrays.asList(getResources().getStringArray(R.array.Creatinine_Array));
        adapterCreatinine = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, creatinineList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterCreatinine.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sCreatinine.setAdapter(adapterCreatinine);
        sCreatinine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        mCreatinine = 4;
                        break;

                    case 2:
                        mCreatinine = 3;
                        break;
                    case 3:
                        mCreatinine = 2;
                        break;
                    case 4:
                        mCreatinine = 0;
                        break;
                    case 5:
                        mCreatinine = 2;
                        break;
                    default:
                        mCreatinine = 0;

                }
                // summation=summation+mCreatinine;

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Spinner Hematocrit

        List<String> hemaList = Arrays.asList(getResources().getStringArray(R.array.Hematocrit_Array));
        adapterHematocrit = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, hemaList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterHematocrit.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sHematocrit.setAdapter(adapterHematocrit);
        sHematocrit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        mHematocrit = 4;
                        break;
                    case 2:
                        mHematocrit = 2;
                        break;
                    case 3:
                        mHematocrit = 1;
                        break;
                    case 4:
                        mHematocrit = 0;
                        break;
                    case 5:
                        mHematocrit = 2;
                        break;
                    case 6:
                        mHematocrit = 4;
                        break;
                    default:
                        mHematocrit = 0;
                }
                summ = summ + mHematocrit;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        // Spinner WBC

        List<String> wbcList = Arrays.asList(getResources().getStringArray(R.array.Wbc_Array));
        adapterWBc = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, wbcList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterWBc.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sWBc.setAdapter(adapterWBc);
        sWBc.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        mWBc = 4;
                        break;
                    case 2:
                        mWBc = 2;
                        break;
                    case 3:
                        mWBc = 1;
                        break;
                    case 4:
                        mWBc = 0;
                        break;
                    case 5:
                        mWBc = 2;
                        break;
                    case 6:
                        mWBc = 4;
                        break;
                    default:
                        mWBc = 0;


                }
                summ = summ + mWBc;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        // COMA


        // Critical


        final List<String> criticalList = Arrays.asList(getResources().getStringArray(R.array.Critical_Array));
        adapterCritical = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, criticalList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterCritical.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sCritical.setAdapter(adapterCritical);
        sCritical.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                pCritical=criticalList.get(i).toString();
                switch (i) {
                    case 1:
                        mCritical = 0;
                        break;
                    case 2:
                        mCritical = 5;
                        break;

                    case 3:
                        mCritical = 5;
                        break;
                    case 4:
                        mCritical = 2;
                        break;
                    default:
                        mCritical = 0;


                }
                summ = summ + mCritical;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        //Age
        List<String> ageList = Arrays.asList(getResources().getStringArray(R.array.Age_Array));
        adapterAge = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, ageList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }

        };
        adapterAge.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sAage.setAdapter(adapterAge);
        sAage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        mAge = 6;
                        break;
                    case 2:
                        mAge = 5;
                        break;
                    case 3:
                        mAge = 3;
                        break;
                    case 4:
                        mAge = 2;
                        break;
                    case 5:
                        mAge = 0;
                        break;
                    default:
                        mAge = 0;

                }
                summ = summ + mAge;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        if (TextUtils.isEmpty(eComa.getText().toString())) {
            mAge = 0;
        } else {
            mAge = 15 - Integer.parseInt(eComa.getText().toString());
        }

        // summation=summation+mAge;

    }

    private  void dataSave(){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        ApacheFeatures features=new ApacheFeatures();
        Calendar calendar=Calendar.getInstance();
        String date=calendar.get(Calendar.HOUR_OF_DAY)+ ":"+calendar.get(Calendar.MINUTE)+":" +calendar.get(Calendar.SECOND);
        features.setAge(mAge);
        features.setBloodPressure(mBp);
        features.setComa(mComa);
        features.setCreatinine(mCreatinine);
        features.setHeartRate(mHeart);
        features.setCriticalDesease(pCritical);
        features.setHematocrit(mHematocrit);
        features.setDate(date);
        features.setPaO2(mPaO2);
        features.setPh(mPh);
        features.setPotassium(mPotassium);
        features.setSodium(mSodium);
        features.setTemperature(mTemp);
        features.setRespiratoryRate(mRespiratory);
        features.setWBc(mWBc);
        features.setResult(result);


      //  features.setAge(45);
        //features.setBloodPressure(1000);
        reference.child("tokiinan5com").child("Apache").child(features.getDate().toString()).setValue(features);
    }


    private void result1() {

        if (summ >= 0 && summ <= 4) {
           // Toast.makeText(getContext(), "Resuklt", Toast.LENGTH_SHORT).show();
            result = getResources().getString(R.string.apache_1);

        }

        if (summ > 4 && summ <= 9) {
            result = getResources().getString(R.string.apache_2);
        }
        if (summ > 9 && summ <= 14) {
            result = getResources().getString(R.string.apache_3);
        }

        if (summ > 14 && summ <= 19) {
            result = getResources().getString(R.string.apache_4);
        }


        if (summ > 19 && summ <= 24) {
            result = getResources().getString(R.string.apache_5);
        }


        if (summ > 24 && summ <= 29) {
            result = getResources().getString(R.string.apache_6);
        }

        if (summ > 29 && summ <= 34) {
            result = getResources().getString(R.string.apache_7);
        }

        if (summ > 35 && summ <= 100) {
            result = getResources().getString(R.string.apache_8);
        }

    }


}









