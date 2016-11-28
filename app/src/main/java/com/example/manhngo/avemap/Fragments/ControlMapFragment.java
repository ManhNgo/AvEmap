package com.example.manhngo.avemap.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.manhngo.avemap.Fragments.CallBack.FragmentCallbacks;
import com.example.manhngo.avemap.Fragments.CallBack.MainCallbacks;
import com.example.manhngo.avemap.MainActivity;
import com.example.manhngo.avemap.R;

/**
 * Created by Manh Ngo on 11/27/2016.
 */

public class ControlMapFragment extends Fragment implements
        AdapterView.OnItemSelectedListener, FragmentCallbacks{

    private String nameControll = "Controll-Frag";
    private String nameImplement = "Show-Traffic";
    MainActivity main;


    public static ControlMapFragment newInstance(String strArg1) {
        ControlMapFragment fragment = new ControlMapFragment();
        Bundle bundle = new Bundle();
        bundle.putString("arg1", strArg1);
        fragment.setArguments(bundle);
        return fragment;
    }// newInstance

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Activities containing this fragment must implement interface: MainCallbacks
        if (!(getActivity() instanceof MainCallbacks)) {
            throw new IllegalStateException( " Activity must implement MainCallbacks");
        }
        main = (MainActivity) getActivity(); // use this reference to invoke main callbacks
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragmentView = inflater.inflate(R.layout.control_map_layout, container, true);



        Spinner mSpinner = (Spinner) fragmentView.findViewById(R.id.layers_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                getActivity(), R.array.layers_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        mSpinner.setAdapter(adapter);
        mSpinner.setOnItemSelectedListener(this);

        FloatingActionButton fabMyLocation = (FloatingActionButton)
                fragmentView.findViewById(R.id.fab_MyLocation);
        fabMyLocation.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Snackbar.make(view, "My location", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();



            }
        });


        FloatingActionButton fabTraffic = (FloatingActionButton) fragmentView.findViewById(R.id.fab_Traffic);
        fabTraffic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Traffic", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                main.onMsgFromFragToMain(nameControll, nameImplement, "Turn-On");
            }
        });
        return fragmentView;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Toast.makeText(getActivity(), "Spiner", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // send message

    @Override
    public void onMsgFromMainToFragment(String strValue) {

    }

    @Override
    public void onMsgFromMainToFragment_Traffic(boolean showTraffic) {

    }

    @Override
    public void onMsgFromMainToFragmentIntLenght(int lenght) {

    }
}
