package com.example.repondeurautomatique;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.repondeurautomatique.Fragments.FragmentContact;
import com.example.repondeurautomatique.Fragments.FragmentListeReponse;
import com.example.repondeurautomatique.Fragments.FragmentReponseAutomatique;

public class MyPageAdapter extends FragmentPagerAdapter {
    public MyPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentContact();
            case 1:
                return new FragmentListeReponse();
            case 2:
                return new FragmentReponseAutomatique();
            default:
                return null;
        }
    }

    public CharSequence getPageTitle(int position){
        switch(position) {
            case 0:
                return "Liste des contacts";
            case 1:
                return "Liste des réponses";
            case 2:
                return "Réponses automatiques";
            default:
                return "";
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
