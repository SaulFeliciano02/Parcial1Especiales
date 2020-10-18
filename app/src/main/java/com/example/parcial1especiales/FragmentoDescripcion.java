package com.example.parcial1especiales;

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoDescripcion#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoDescripcion extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    //private String mParam2;

    public FragmentoDescripcion() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //* @param param2 Parameter 2.
     * @param param1 Parameter 1.
     * @return A new instance of fragment FragmentoDescripcion.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoDescripcion newInstance(int param1) {
        FragmentoDescripcion fragment = new FragmentoDescripcion();
        Bundle args = new Bundle();
        args.putInt("index", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ScrollView scroller = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());
        int padding = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, 4, getActivity()
                        .getResources().getDisplayMetrics());
        text.setPadding(padding, padding, padding, padding);
        scroller.addView(text);
        text.setText(Conceptos.DEFINITIONS[getShownIndex()]);
        return scroller;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }
}