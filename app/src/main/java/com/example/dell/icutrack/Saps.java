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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Arrays;
import java.util.List;

import static com.example.dell.icutrack.R.layout.apache;
import static com.example.dell.icutrack.R.layout.layout_saps;

/**
 * A simple {@link Fragment} subclass.
 */
public class Saps extends Fragment {
    private Spinner sTemp, sBp, sHeart, sUrine, sPaO2, sBUN, sSodium, sPotassium, sBicarbonate, sAage,
            sBilirubin, sWBc, sComa, sCritical,sAdmissiom;
    private int mTemp, mBp, mHeart, mUrine, mPaO2, mBUN, mSodium, mPotassium, mBicarbonate, mAge,
            mBilirubin, mWBc, mComa, mCritical,mAdmission,summ;
    private EditText eComa;
    String result;
    private Button bSave, bReset, bCalculate;
    TextView tResult;
    private FirebaseAuth auth;
    private FirebaseUser currentUser;
    private  double mortality;


    View view;

    ArrayAdapter adapterTemp, adapterBp, adapterHeart, adapterRespiratory, adapterPaO2, adapterPh, adapterSodium, adapterPotassium, adapterCreatinine, adapterAge,
            adapterHematocrit, adapterWBc, adapterComa, adapterCritical,adapterAdmission;


    public Saps() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(layout_saps, container, false);

        init();
        bCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                result1();
                Log.e("Resulthhfdsjfjd", String.valueOf(result));

                //Toast.makeText(getContext(), result, Toast.LENGTH_SHORT));
                tResult.setText("Outcome:  "+String.valueOf(mortality));


            }
        });

        bSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // au if()
                auth=FirebaseAuth.getInstance();

                if(auth.getCurrentUser()!=null)
                {
                    Toast.makeText(getActivity(), "Method Has been called", Toast.LENGTH_SHORT).show();
                    // dataSave();
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

                }

            }
        });

        return view;
    }

    private void init() {
        sTemp = view.findViewById(R.id.sTemp);
        sBp = view.findViewById(R.id.sBp);
        sHeart = view.findViewById(R.id.sHeart);
        sUrine = view.findViewById(R.id.sUrine);
        sPaO2 = view.findViewById(R.id.sPaO2);
        sBUN = view.findViewById(R.id.sBUN);
        sSodium = view.findViewById(R.id.sSodium);
        sPotassium = view.findViewById(R.id.sPotassium);
        sBicarbonate = view.findViewById(R.id.sBicarbonate);
        sBilirubin = view.findViewById(R.id.sBilirubin);
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
        sAdmissiom=view.findViewById(R.id.sAdmission);

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
                        mTemp = 0;
                        break;
                    case 2:
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
                        mBp = 13;
                        break;
                    case 2:
                        mBp = 5;
                        break;
                    case 3:
                        mBp = 0;
                        break;
                    case 4:
                        mBp = 2;
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
                        mHeart = 11;
                        break;
                    case 2:
                        mHeart = 2;
                        break;
                    case 3:
                        mHeart = 0;
                        break;
                    case 4:
                        mHeart = 4;
                        break;
                    case 5:
                        mHeart = 7;

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
        sUrine.setAdapter(adapterRespiratory);
        sUrine.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        mUrine = 11;
                        break;

                    case 2:
                        mUrine = 4;
                        break;

                    case 3:
                        mUrine = 0;
                        break;


                    default:
                        mUrine = 0;

                }
                summ = summ + mUrine;

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
                        mPaO2 = 11;
                        break;

                    case 2:
                        mPaO2 = 9;
                        break;
                    case 3:
                        mPaO2 = 6;
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
        sBUN.setAdapter(adapterPh);
        sBUN.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        mBUN = 0;
                        break;
                    case 2:
                        mBUN = 6;
                        break;

                    default:
                        mBUN = 0;
                }
                summ = summ + mBUN;

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
                        mSodium = 5;
                        break;
                    case 3:
                        mSodium = 0;
                        break;
                    case 4:
                        mSodium = 1;
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
                        mPotassium = 3;
                        break;
                    case 2:
                        mPotassium = 0;
                        break;
                    case 3:
                        mPotassium = 3;
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
        sBicarbonate.setAdapter(adapterCreatinine);
        sBicarbonate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        mBicarbonate = 6;
                        break;

                    case 2:
                        mBicarbonate = 3;
                        break;
                    case 3:
                        mBicarbonate = 0;
                        break;

                    default:
                        mBicarbonate = 0;

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
        sBilirubin.setAdapter(adapterHematocrit);
        sBilirubin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        mBilirubin = 0;
                        break;
                    case 2:
                        mBilirubin = 4;
                        break;
                    case 3:
                        mBilirubin = 9;
                        break;

                    default:
                        mBilirubin = 0;
                }
                summ = summ + mBilirubin;
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
                        mWBc = 12;
                        break;
                    case 2:
                        mWBc = 0;
                        break;
                    case 3:
                        mWBc = 3;
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


        List<String> criticalList = Arrays.asList(getResources().getStringArray(R.array.Critical_Array));
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
                switch (i) {
                    case 1:
                        mCritical = 0;
                        break;
                    case 2:
                        mCritical = 9;
                        break;

                    case 3:
                        mCritical = 10;
                        break;
                    case 4:
                        mCritical = 17;
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
                        mAge = 0;
                        break;
                    case 2:
                        mAge = 12;
                        break;
                    case 3:
                        mAge = 15;
                        break;
                    case 4:
                        mAge = 16;
                        break;
                    case 5:
                        mAge = 18;
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

        // Admission



        List<String> admissionList = Arrays.asList(getResources().getStringArray(R.array.Saps_Admissions));
        adapterAdmission = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, admissionList) {
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
        adapterAdmission.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sAdmissiom.setAdapter(adapterAdmission);
        sAdmissiom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 1:
                        mAdmission = 0;
                        break;
                    case 2:
                        mAdmission = 6;
                        break;
                    case 3:
                        mAdmission = 8;
                        break;

                    default:
                        mAdmission = 0;

                }
                summ = summ + mAdmission;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        if (TextUtils.isEmpty(eComa.getText().toString())) {
            mComa = 0;
        } else {
            mComa = 15 - Integer.parseInt(eComa.getText().toString());
        }
        summ=summ+mComa;

        // summation=summation+mAge;

    }

    private  void dataSave(){
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference();
        ApacheFeatures features=new ApacheFeatures();
        features.setAge(45);
        features.setBloodPressure(1000);
        reference.child("IPRS").child("Val").setValue(features);
    }


    private void result1() {
        double logit=-7.7631 + 0.0737*summ +  0.9971* (Math.log(summ+1))/(Math.log(2));
        mortality=Math.exp(logit)/(1+Math.exp(logit));



    }


}
