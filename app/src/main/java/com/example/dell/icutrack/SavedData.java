package com.example.dell.icutrack;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SavedData extends AppCompatActivity {
   // ArrayList myList;
    ListView listView;
    ArrayList<IRPSdataFormat> myList;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_data);
        listView=findViewById(R.id.lSaved);
        FirebaseAuth auth;
      //  DatabaseReference mDatabase;
        auth=FirebaseAuth.getInstance();
        final FirebaseUser user=auth.getCurrentUser();
        final DatabaseReference mReference=FirebaseDatabase.getInstance().getReference();
      //  Toast.makeText(this, user.getUid().toString(), Toast.LENGTH_SHORT).show();
        mReference.getRoot().child("IRPS").child(String.valueOf(user.getUid())).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                IRPSdataFormat format=dataSnapshot.getValue(IRPSdataFormat.class);
               // String data=format.getAge();
                //Toast.makeText(getApplicationContext(),data,Toast.LENGTH_LONG).show();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

                //

            }
        });
     //   Toast.makeText(this,String .valueOf(myList.size()), Toast.LENGTH_SHORT).show();
        ArrayAdapter myAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,myList);
//        listView.setAdapter(myAdapter);


    }
}
