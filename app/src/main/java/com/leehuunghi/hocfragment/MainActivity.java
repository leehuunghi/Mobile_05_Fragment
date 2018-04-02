package com.leehuunghi.hocfragment;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class MainActivity extends Activity implements MainCallbacks{
    FragmentTransaction ft;
    ListFragment listFragment;
    DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);

        if(fragment.getClass() == ListFragment.class)
        {
            listFragment = (ListFragment) fragment;
        }

        if(fragment.getClass() == DetailFragment.class)
        {
            detailFragment = (DetailFragment) fragment;

        }
    }

    @Override
    public void onMsgFromFragToMain(String sender, String strValue) {
        if (sender.equals("LIST-FRAG")){
            detailFragment.onMsgFromMainToFragment(strValue);
        }
        if (sender.equals("DETAIL-FRAG")) {
            listFragment.onMsgFromMainToFragment(strValue);
        }

    }
}
