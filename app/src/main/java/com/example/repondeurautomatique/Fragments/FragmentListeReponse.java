package com.example.repondeurautomatique.Fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.example.repondeurautomatique.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentListeReponse} factory method to
 * create an instance of this fragment.
 */
public class FragmentListeReponse extends Fragment {


    private ArrayAdapter<String> mAdapter;

    private Button addResponseButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View result = inflater.inflate(R.layout.fragment_liste_reponse, container, false);

        addResponseButton = result.findViewById(R.id.addResponseButton);

        ListView listContact = result.findViewById(R.id.listResponse);

        mAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice);

        listContact.setAdapter(mAdapter);

        listContact.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        mAdapter.addAll("ok", "Je vous rappelle plus tard", "Je peux pas j'ai piscine");

        addResponseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Récuper ce que l'utilisateur écrit
                mAdapter.add("new Element");
            }
        });

        return result;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Sauvegarder les données enregistrées par l'utilisateur

    }

}