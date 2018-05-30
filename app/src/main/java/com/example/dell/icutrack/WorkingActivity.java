package com.example.dell.icutrack;

import android.app.AlertDialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class WorkingActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    String lang="en";
    RadioButton rEnglish,rBangla;
    String changedLang;
   public static   String read,severe,mortality;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        String defaultValue = "en";
        changedLang = sharedPref.getString("language",defaultValue);
        Configuration config = getBaseContext().getResources().getConfiguration();
        Locale locale = new Locale(changedLang);
        Locale.setDefault(locale);
        config.locale = locale;


        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_working);
        read=getString(R.string.tab_readmission);
        severe=getString(R.string.tab_Apache);
        mortality=getString(R.string.tab_SAPS);
      //  recreate();


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ViewPager viewPager=findViewById(R.id.viewpager);
        FragmentAdapter adapter=new FragmentAdapter(getSupportFragmentManager(),this);
        viewPager.setAdapter(adapter);

        TabLayout tabLayout=findViewById(R.id.sliding_tabs);
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_readmission));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_Apache));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tab_SAPS));
       tabLayout.setupWithViewPager(viewPager);





        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);



      /* NavigationView nav=findViewById(R.id.nav_view);
        View hView =  nav.getHeaderView(1);
        TextView nav_user = hView.findViewById(R.id.tViewnav);

        nav_user.setImageResource(R.drawable.common_google_signin_btn_icon_dark_focused);
*/    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.working, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Intent intent =new Intent(getApplicationContext(),SavedData.class);
            startActivity(intent);

        } /*else if (id == R.id.nav_slideshow) {

        }*/ else if (id == R.id.nav_manage) {

            final AlertDialog.Builder mydialogue=new AlertDialog.Builder(this);
            LayoutInflater inflater=this.getLayoutInflater();
            View dialogue=inflater.inflate(R.layout.fragment_settings,null);
            mydialogue.setView(dialogue);
            mydialogue.setTitle("Change Language");
            rEnglish=findViewById(R.id.rEnglish);
            rBangla=findViewById(R.id.rBangla);


            mydialogue.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {


                    SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
                    SharedPreferences.Editor sharededitor=sharedPref.edit();
                    sharededitor.putString("language",lang);
                    sharededitor.commit();

                    Configuration config = getBaseContext().getResources().getConfiguration();
                   Locale locale = new Locale(changedLang);
                    Locale.setDefault(locale);
                    config.locale = locale;
                    getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
                    //finish();

                    recreate();
                   // Toast.makeText(WorkingActivity.this, "Saved", Toast.LENGTH_SHORT).show();

                }
            });
            mydialogue.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });



          mydialogue.create();
          mydialogue.show();


           /* android.support.v4.app.FragmentManager fm=getSupportFragmentManager();
            new Settingspager().show(fm,"SEttings");*/



        } /*else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void onLanguage(View view) {
        {
            boolean checked=((RadioButton)view).isChecked();
            switch(view.getId()){
                case R.id.rBangla:
                    if(checked)
                        lang="bn";

                    break;
                case R.id.rEnglish:
                    if (checked)

                        lang="en";

                    break;

            }

        }
    }
}
