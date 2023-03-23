package com.example.repondeurautomatique.Fragments;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.repondeurautomatique.R;

import java.util.ArrayList;

import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentContact} factory method to
 * create an instance of this fragment.
 */
public class FragmentContact extends Fragment {

    private TextView listContact;

    //private ArrayAdapter<String> arrayAdapter;

    public FragmentContact() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_contact, container, false);

        listContact = result.findViewById(R.id.listContact);
        getContacts();
        return result;
    }

    /**
     * Recupere les contacts
     */
    public void getContacts(){
        ContentResolver contentResolver = this.getActivity().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE, ContactsContract.CommonDataKinds.Phone.NUMBER},null,null,null);
        if(cursor == null){
            Log.d("Contacts", "error cursor");
        }else{
            while(cursor.moveToNext()){
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE));
                @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //Affichage de la liste de contact
                listContact.setText(listContact.getText().toString() + "\n\r" + name + " : " + number);
            }
            cursor.close();
        }
    }
}