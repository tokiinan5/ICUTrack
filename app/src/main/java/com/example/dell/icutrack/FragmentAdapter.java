package com.example.dell.icutrack;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 2/18/2018.
 */

public class FragmentAdapter extends FragmentPagerAdapter {

Context context;

ArrayList<String>list=new ArrayList<>();
    public FragmentAdapter(FragmentManager fm,Context context) {
        super(fm);
        this.context=context;

        list.add( WorkingActivity.read);
        list.add( WorkingActivity.severe);
        list.add( WorkingActivity.mortality);
        Toast.makeText(context, String.valueOf( WorkingActivity.read), Toast.LENGTH_SHORT).show();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                Apache apache=new Apache();
                return apache;
            case 0:
                IRPS irps=new IRPS();
                return irps;
            case  2:
                Saps saps=new Saps();
                return saps;
                default:
                    return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
    @Override
    public  String getPageTitle(int position){
        return list.get(position);
    }

}
