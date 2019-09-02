package com.br.controlepadaria.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.controlepadaria.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EmitirPedPadeiroFragment extends BaseFragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_emitir_ped_padeiro, container, false);
        return view;
    }

}
