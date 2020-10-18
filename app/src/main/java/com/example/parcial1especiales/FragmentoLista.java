package com.example.parcial1especiales;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoLista#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoLista extends ListFragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private boolean mDualPane;
    private int mCurCheckPosition = 0;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentoLista() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentoLista.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoLista newInstance(String param1, String param2) {
        FragmentoLista fragment = new FragmentoLista();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onActivityCreated(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                Conceptos.CONCEPTS));

        View fgmDescription = getActivity().findViewById(R.id.fgmDescription);

        mDualPane = fgmDescription != null && fgmDescription.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("curChoice", 0);
        }

        if (mDualPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(mCurCheckPosition);
        } else {
            // We also highlight in uni-pane just for fun
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            getListView().setItemChecked(mCurCheckPosition, true);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("curChoice", mCurCheckPosition);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        showDetails(position);
    }

    public void showDetails(int index) {
        mCurCheckPosition = index;

        if (mDualPane) {
            getListView().setItemChecked(index, true);

            FragmentoDescripcion details = (FragmentoDescripcion) getFragmentManager()
                    .findFragmentById(R.id.fgmDescription);
            if (details == null || details.getShownIndex() != index) {
                details = FragmentoDescripcion.newInstance(index);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.fgmDescription, details);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), DescriptionActivity.class);
            intent.putExtra("index", index);

            startActivity(intent);
        }
    }
}