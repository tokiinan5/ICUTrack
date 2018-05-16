package com.example.dell.icutrack;


import android.content.Context;
import android.content.Intent;
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
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.atomic.AtomicReference;


import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.Logistic;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.core.Attribute;
import weka.core.DenseInstance;
import weka.core.Instances;

import static com.example.dell.icutrack.R.layout.abc_action_bar_view_list_nav_layout;
import static com.example.dell.icutrack.R.layout.new_ystem;

/**
 * A simple {@link Fragment} subclass.
 */
public class IRPS extends Fragment {

     private  EditText eAge,eBillirubin,ePlatelate,eLos,ePaO2,eSodium,ePotassium,eCreatinine,eWbc,eHematocrit,eTemp,ePh;
     private  RadioButton rMale,rFemale;
    private double pAge,pBillirubin,pPlatelate,pLos,pPaO2,pSodium,pPotassium,pCreatinine,pWbc,pHematocrit,pTemp,pPh;
    private  String pGender;
    String error="Can not be empty";
    Context context;
    Logistic bayes = null;







    View myView;
    Button bSave,bReset,bCalculate;

   public  IRPS()
   {

   }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

       myView = inflater.inflate(new_ystem,container,false);
        init();


        try {
            bayes= (Logistic) weka.core.SerializationHelper.read(getActivity().getAssets().open("logic.model"));
           //  Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
           // Log.e("Model333333333333333333","Loaded Successfully");
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("MODEL",e.toString());
        }
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
        bCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               neuralModel();
               // Toast.makeText(context, dataa, Toast.LENGTH_SHORT).show();
            }
        });



        return myView;

    }
    private  void decision(){
        Features features=new Features();
        features.setpAge(pAge);
        features.setpBillirubin(pBillirubin);
        features.setpCreatinine(pCreatinine);
        features.setpGender(pGender);
        features.setpHematocrit(pHematocrit);
        features.setpLos(pLos);
        features.setpPaO2(pPaO2);
        features.setpPh(pPh);
        features.setpPlatelate(pPlatelate);
        features.setpPotassium(pPotassium);
        features.setpSodium(pSodium);
        features.setpWbc(pWbc);
        features.setpTemp(pTemp);

    }

   private void  init() {
        bSave=myView.findViewById(R.id.bSave);
        bReset=myView.findViewById(R.id.bReset);
        bCalculate=myView.findViewById(R.id.bCalculate);
        eAge=myView.findViewById(R.id.eAge);
        eBillirubin=myView.findViewById(R.id.eBilirubin);
        eCreatinine=myView.findViewById(R.id.eCreatine);
        eHematocrit=myView.findViewById(R.id.eHematocrit);
        eLos=myView.findViewById(R.id.eLos);
        ePaO2=myView.findViewById(R.id.ePaO2);
        ePlatelate=myView.findViewById(R.id.ePlatelet);
        eSodium=myView.findViewById(R.id.eNa);
        ePotassium=myView.findViewById(R.id.ePotassium);
        eTemp=myView.findViewById(R.id.eTemp);
        ePh=myView.findViewById(R.id.ePH);
        eWbc=myView.findViewById(R.id.eWBC);
        rMale=myView.findViewById(R.id.rMale);
        rFemale=myView.findViewById(R.id.rfeMale);


/*
       try {
           perceptron= (NaiveBayes) weka.core.SerializationHelper.read(getContext().getAssets().open("multi.model"));
           Toast.makeText(getContext(), "Success", Toast.LENGTH_SHORT).show();
       }

       catch (Exception e) {
           Log.e("Error",e.toString());
       }
*/



    }
    private void initvalues()
    {
        if(TextUtils.isEmpty(eAge.getText().toString()))
        {
            eAge.setError(error);  // bangla lagbe
            eAge.requestFocus();
        }

        else
        {
            pAge= Double.parseDouble(String.valueOf(eAge.getText()));
        }

        // for Temp

      try {
          pTemp= Double.parseDouble(eTemp.getText().toString());
      }
      catch (Exception e)
      {
          Log.e("SOMOSSAAAA","BAAAAAAL");
      }





        if(TextUtils.isEmpty(eBillirubin.getText().toString()))
        {
            eBillirubin.setError(error);  // bangla lagbe
            eBillirubin.requestFocus();
        }

        else
        {
            pBillirubin= Double.parseDouble(String.valueOf(eBillirubin.getText()));
        }

        //


        if(TextUtils.isEmpty(ePlatelate.getText().toString()))
        {
            ePlatelate.setError(error);  // bangla lagbe
            ePlatelate.requestFocus();
        }

        else
        {
            pPlatelate= Double.parseDouble(String.valueOf(ePlatelate.getText()));
        }

        if(TextUtils.isEmpty(eLos.getText().toString()))
        {
            eLos.setError(error);  // bangla lagbe
            eLos.requestFocus();
        }

        else
        {
            pLos= Double.parseDouble(String.valueOf(eLos.getText()));
        }
        //


        if(TextUtils.isEmpty(ePaO2.getText().toString()))
        {
            ePaO2.setError(error);  // bangla lagbe
            ePaO2.requestFocus();
        }

        else
        {
            pPaO2= Double.parseDouble(String.valueOf(ePaO2.getText()));
        }

        ///

        if(TextUtils.isEmpty(eSodium.getText().toString()))
        {
            eSodium.setError(error);  // bangla lagbe
            eSodium.requestFocus();
        }

        else
        {
            pSodium= Double.parseDouble(String.valueOf(eSodium.getText()));
        }

        //


        if(TextUtils.isEmpty(ePotassium.getText().toString()))
        {
            ePotassium.setError(error);  // bangla lagbe
            ePotassium.requestFocus();
        }

        else
        {
            pAge= Double.parseDouble(String.valueOf(ePotassium.getText()));
        }
//


        if(TextUtils.isEmpty(eCreatinine.getText().toString()))
        {
            eCreatinine.setError(error);  // bangla lagbe
            eCreatinine.requestFocus();
        }

        else
        {
            pCreatinine= Double.parseDouble(String.valueOf(eCreatinine.getText()));
        }
//

        if(TextUtils.isEmpty(eWbc.getText().toString()))
        {
            eWbc.setError(error);  // bangla lagbe
            eWbc.requestFocus();
        }

        else
        {
            pWbc= Double.parseDouble(String.valueOf(eWbc.getText()));
        }

        //
        if(TextUtils.isEmpty(ePh.getText().toString()))
        {
            ePh.setError(error);  // bangla lagbe
            ePh.requestFocus();
        }

        else
        {
            pPh= Double.parseDouble(String.valueOf(ePh.getText()));
        }



        if(TextUtils.isEmpty(eHematocrit.getText().toString()))
        {
            eHematocrit.setError(error);  // bangla lagbe
            eHematocrit.requestFocus();
        }

        else
        {
            pHematocrit= Double.parseDouble(String.valueOf(eHematocrit.getText()));
        }
//
        if(!(rFemale.isChecked()) || !(rMale.isChecked()) )
        {
            rMale.setError(error);
            rFemale.setError(error);
        }

        else
        {
            if(rFemale.isChecked())
            {
                pGender="F";
            }
            else
                pGender="M";
            Toast.makeText(getContext(), pGender.toString(), Toast.LENGTH_SHORT).show();
        }



        // sAdmission=myView.findViewById(R.id.sAdmmission);



    }

    private void neuralModel()
    {

        initvalues();
        final Attribute attributeBilirubin=new Attribute("BILUBIRIN_NV");
        final  Attribute attributePlatelete=new Attribute("PLATELET_NV");
        final  Attribute attributeGender=new Attribute("G");
        final Attribute attributeLOS=new Attribute("LOS");
        final Attribute attributePaO2=new Attribute("PAO2_NV");
        final Attribute attributePh=new Attribute("PH_NV");
        final Attribute attributeNa=new Attribute("NA_NV");
        final Attribute attributeK=new Attribute("K_NV");
        final Attribute attributeCreatinine=new Attribute("CREATININE_NV");
        final Attribute attributetemp=new Attribute("TMP_NV");
        final Attribute attributeWbc=new Attribute("WHITEBLOODCELL");
        final Attribute attributeHematocrit=new Attribute("HEMAROCRIT_NV");
        final Attribute attributeAge=new Attribute("AGE_NV");
        final ArrayList<String> classes=new ArrayList();
        classes.add("Y");
        classes.add("N");
        ArrayList <Attribute> attributeList=new ArrayList<>();
        Attribute classAtrribute=new Attribute("I",classes);
        attributeList.add(attributeAge);
        attributeList.add(attributeBilirubin);
        attributeList.add(attributeCreatinine);
        attributeList.add(attributeGender);
        attributeList.add(attributeHematocrit);
        attributeList.add(attributeK);
        attributeList.add(attributeLOS);
        attributeList.add(attributeNa);
        attributeList.add(attributePaO2);
        attributeList.add(attributePh);
        attributeList.add(attributePlatelete);
        attributeList.add(attributetemp);
        attributeList.add(attributeWbc);
        attributeList.add(classAtrribute);

        Instances dataUnpredicted = new Instances("TestInstances",
                attributeList, 1);

        dataUnpredicted.setClassIndex(dataUnpredicted.numAttributes()-1);
        DenseInstance instance=new DenseInstance(dataUnpredicted.numAttributes());
        instance.setValue(attributeAge,pAge);
        instance.setValue(attributeBilirubin,pBillirubin);
        instance.setValue(attributeCreatinine,pCreatinine);
      //  instance.setValue(attributeGender,"F");
        instance.setValue(attributeHematocrit,pHematocrit);
        instance.setValue(attributeK,pPotassium);
        instance.setValue(attributeLOS,pLos);
        instance.setValue(attributeNa,pSodium);
        instance.setValue(attributePaO2,pPaO2);
        instance.setValue(attributePh,pPh);
        instance.setValue(attributePlatelete,pPlatelate);
        instance.setValue(attributetemp,pTemp);
        instance.setValue(attributeWbc,pWbc);
     //   Toast.makeText(context, String.valueOf(pTemp), Toast.LENGTH_SHORT).show();

        DenseInstance newInstance = instance;
        // reference to dataset
        String output=null;
        newInstance.setDataset(dataUnpredicted);

        try {
            Double result=bayes.classifyInstance(instance);
            output=classes.get(new Double(result).intValue());
            Log.e("OUTPUT DROM MUL",output.toString());
            Toast.makeText(getContext(), output, Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Log.e("error",e.getMessage());
        }
        //return output;





    }

}
