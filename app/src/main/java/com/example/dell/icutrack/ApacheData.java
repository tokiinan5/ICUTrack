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

public class ApacheData extends AppCompatActivity {
    ListView listView;
    ArrayList<ApacheFeatures> apacheList;
    ArrayList<String> dateList;
    ApacheFeatures features;
    ArrayAdapter arrayAdapter;
    String data;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apache_data);
        apacheList=new ArrayList<>();
        dateList=new ArrayList<>();
        listView=findViewById(R.id.lApache);
        features=new ApacheFeatures();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getInstance().getReference().child("tokiinan5com").child("Apache");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot snapshot :dataSnapshot.getChildren())
                {
                    features= snapshot.getValue(ApacheFeatures.class);
                   // Log.e("TEST",String.valueOf(dataSnapshot.getChildrenCount()));
                    apacheList.add(features);
                    dateList.add(features.getDate().toString());
                  // Log.e("APACHE",String.valueOf(dateList.get(0)));
                    //apacheList.add(String.valueOf(snapshot.getValue()));
                }
                Log.e("APACHEtyruyuyu",String.valueOf(dateList.size()));
                arrayAdapter=new ArrayAdapter(getApplicationContext(),R.layout.listviewstyle,R.id.textContent,dateList);
                listView.setAdapter(arrayAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {


                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        final FirebaseDatabase database = FirebaseDatabase.getInstance();
                        Toast.makeText(ApacheData.this, "ssdsd", Toast.LENGTH_SHORT).show();
                        DatabaseReference ref = database.getInstance().getReference().child("tokiinan5com").
                                child("Apache").child(dateList.get(i).toString());
                        ref.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                               ApacheFeatures my=new ApacheFeatures();
                               my= dataSnapshot.getValue(ApacheFeatures.class);
                               data="Temperature : " + my.getTemperature() + "\n" +
                                 "BloodPressure : " + my.getBloodPressure() + "\n" +
                                "HeartRate : " + my.getHeartRate() + "\n" +
                                "RespiratoryRate : " + my.getRespiratoryRate() + "\n" +
                                 "PaO2 : " + my.getPaO2() + "\n" +
                                 "Ph : " + my.getPh() + "\n" +
                                 "Sodium : " + my.getSodium() + "\n" +
                              " Potassium : " + my.getPotassium() + "\n" +
                                 " Creatinine :" + my.getCreatinine() + "\n" +
                                 " Age : " + my.getAge() + "\n" +
                                 "Hematocrit  : " + my.getHematocrit() + "\n" +
                                 "WBc  : " + my.getWBc() + "\n" +
                                 "Coma  : " + my.getComa() + "\n" +
                                 "CriticalDesease  : " + my.getCriticalDesease() + "\n" + "date : " + my.getDate() + "\n" +
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
