package com.br.controlepadaria.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.controlepadaria.R;
import com.br.controlepadaria.activity.EmitirPedLojaActivity;
import com.br.controlepadaria.activity.EmitirPedPadeiroActivity;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class RelatoriosFragment extends BaseFragment  implements View.OnClickListener {


    private CardView emtirLojaCard, emitirpadeiroCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_relatorios, container, false);

        emtirLojaCard = view.findViewById(R.id.emtirLojacard);
        emitirpadeiroCard = view.findViewById(R.id.emtirPadeirocardId);

        emitirpadeiroCard.setOnClickListener(this);
        emtirLojaCard.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View view) {
        Intent i;

        if (view.getId() == R.id.emtirLojacard){
            i = new Intent(getContext(), EmitirPedLojaActivity.class);
            startActivity(i);
        }
        if(view.getId() == R.id.emtirPadeirocardId){
            i = new Intent(getContext(), EmitirPedPadeiroActivity.class);
            startActivity(i);
        }


    }
}
