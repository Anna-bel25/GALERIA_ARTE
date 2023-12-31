package com.example.comentarios_valoracion_scarletgg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link GaleriaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class GaleriaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public GaleriaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment GaleriaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static GaleriaFragment newInstance(String param1, String param2) {
        GaleriaFragment fragment = new GaleriaFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_galeria, container, false);
        View view = inflater.inflate(R.layout.fragment_galeria, container, false);

        ImageView campana = view.findViewById(R.id.imagenCamp);

        campana.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirFragment1();
            }
        });

        ImageView eduardo = view.findViewById(R.id.imagenlen);
        eduardo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirFragment2();
            }
        });

        return view;
    }

    private void abrirFragment1() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        CampanasFragment Gonzalo = new CampanasFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout,Gonzalo)
                .addToBackStack(null)
                .commit();
    }

    private void abrirFragment2() {
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
        LenguajeFragment lenguaje = new LenguajeFragment();

        fragmentManager.beginTransaction()
                .replace(R.id.frame_layout,lenguaje)
                .addToBackStack(null)
                .commit();
    }


}