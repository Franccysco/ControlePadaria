package com.br.controlepadaria.fragments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.br.controlepadaria.R;
import com.br.controlepadaria.activity.LojasActivity;
import com.br.controlepadaria.activity.PedidosActivity;
import com.br.controlepadaria.activity.ProdutosActivity;
import com.br.controlepadaria.activity.RelatoriosActivity;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class DashBoardFragment extends BaseFragment implements View.OnClickListener {


    private CardView produtosCard, lojasCard, pedidosCard, relatoriosCard;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.dashboard2, container, false);

        produtosCard = view.findViewById(R.id.produtocardId);
        lojasCard = view.findViewById(R.id.lojacardId);
        pedidosCard = view.findViewById(R.id.pedidocarId);
        relatoriosCard = view.findViewById(R.id.relatoriocardId);


        produtosCard.setOnClickListener(this);
        lojasCard.setOnClickListener(this);
        pedidosCard.setOnClickListener(this);
        relatoriosCard.setOnClickListener(this);




        return view;
    }

    @Override
    public void onClick(View view) {

        Intent intent;

        switch (view.getId()){
            case R.id.produtocardId:
                intent = new Intent(getContext(), ProdutosActivity.class);
                startActivity(intent);
                break;
            case R.id.lojacardId:
                intent = new Intent(getContext(), LojasActivity.class);
                startActivity(intent);
                break;
            case R.id.pedidocarId:
                intent = new Intent(getContext(), PedidosActivity.class);
                startActivity(intent);
                break;

            case R.id.relatoriocardId:
                intent = new Intent(getContext(), RelatoriosActivity.class);
                startActivity(intent);
                break;

                default:break;
        }
    }
}
