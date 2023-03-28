package com.example.repondeurautomatique.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.example.repondeurautomatique.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentContact} factory method to
 * create an instance of this fragment.
 */
public class FragmentContact extends Fragment {

    private ArrayAdapter<String> mAdapter;

    public FragmentContact() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_contact, container, false);

        ListView listContact = result.findViewById(R.id.listContact);

        mAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_multiple_choice);

        listContact.setAdapter(mAdapter);

        listContact.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

        showContacts();

        return result;
    }

    /**
     * Get all phone contacts
     */
    public void getContacts(){
        ContentResolver contentResolver = this.requireActivity().getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,new String[]{ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE, ContactsContract.CommonDataKinds.Phone.NUMBER},null,null,null);
        if(cursor == null && cursor.getCount() <= 0){
            Log.d("Contacts", "error cursor");
        }else{
            while(cursor.moveToNext()){
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME_ALTERNATIVE));
                @SuppressLint("Range") String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                //Add contact in Adapter
                mAdapter.add(name + " : " + number);
            }
            cursor.close();
        }
    }

    /**
     * Check permission
     */
    private final ActivityResultLauncher<String> requestPermissionLauncher =
            registerForActivityResult(new ActivityResultContracts.RequestPermission(), isGranted -> {
                if (isGranted) {
                    // Permission is granted. Continue the action or workflow in your
                    // app.
                    showContacts();
                } else {
                    // Explain to the user that the feature is unavailable because the
                    // feature requires a permission that the user has denied. At the
                    // same time, respect the user's decision. Don't link to system
                    // settings in an effort to convince the user to change their
                    // decision.
                    Toast.makeText(getContext(),"Veuillez accepter l'accès à vos contacts pour utiliser l'application", Toast.LENGTH_LONG).show();
                }
            });

    /**
     *display phone contacts
     */
    public void showContacts(){
        if (ContextCompat.checkSelfPermission(
                requireContext(), Manifest.permission.READ_CONTACTS) ==
                PackageManager.PERMISSION_GRANTED) {
            // You can use the API that requires the permission.

            getContacts();

        }
        else{
            requestPermissionLauncher.launch(Manifest.permission.READ_CONTACTS);
        }
    }
}