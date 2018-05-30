package com.example.dell.icutrack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class IRPSData extends AppCompatActivity {
    ListView listView;
    ArrayList<Features> apacheList;
    ArrayList<String> dateList;
    Features features;
    ArrayAdapter arrayAdapter;
    String data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_irpsdata);
        listView=findViewById(R.id.ldata);
        apacheList=new ArrayList<>();
        dateList=new ArrayList<>();
        listView=findViewById(R.id.lApache);
        features=new Features();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getInstance().getReference().child("tokiinan5com").child("Apache");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot snapshot :dataSnapshot.getChildren())
                {
                    features= snapshot.getValue(Features.class);
                    // Log.e("TEST",String.valueOf(dataSnapshot.getChildrenCount()));
                    apacheList.add(features);
                    dateList.add(features.getDate().toString());
                }
                Log.e("APACHEtyruyuyu",String.valueOf(dateList.size()));
                arrayAdapter=new ArrayAdapter(getApplicationContext(),R.layout.listviewstyle,R.id.textContent,dateList);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        final FirebaseDatabase database = FirebaseDatabase.getInstance();
                       // Toast.makeText(ApacheData.this, "ssdsd", Toast.LENGTH_SHORT).show();
                        DatabaseReference ref = database.getInstance().getReference().child("tokiinan5com").
                                child("IRPS").child(dateList.get(i).toString());
                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                               Features my=new Features();
                                my= dataSnapshot.getValue(Features.class);
                                data=   "Temperature : " + my.getpTemp() + "\n" +
                                        "Billirubin : " + my.getpBillirubin() + "\n" +
                                        "Hematocrit : " + my.getpHematocrit() + "\n" +
                                        "Platelate : " + my.getpPlatelate() + "\n" +
                                        "PaO2 : " + my.getpPaO2() + "\n" +
                                        "Ph : " + my.getpPh() + "\n" +
                                        "Sodium : " + my.getpSodium() + "\n" +
                                        " Potassium : " + my.getpPotassium() + "\n" +
                                        " Creatinine :" + my.getpCreatinine() + "\n" +
                                        " Age : " + my.getpAge() + "\n" +
                                        "WBc  : " + my.getpWbc() + "\n" +
                                        "Gender  : " + my.getpGender() + "\n" + "date : " + my.getDate() + "\n" +
                                        " Result : "+ my.getResult();
                                //  Log.d("values",my.getDate().toString() + "\n" + my.getAge()+" my");
                                Intent intent=new Intent(getApplicationContext(),Showdata.class);
                                intent.putExtra("data",data);
                                startActivity(intent);
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });

                    }
                });
                //listView.getOnItemClickListener()
                //
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }



    }

