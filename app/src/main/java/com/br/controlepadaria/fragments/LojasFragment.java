package com.br.controlepadaria.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.br.controlepadaria.R;
import com.br.controlepadaria.adapter.LojaAdapter;
import com.br.controlepadaria.domain.Loja;
import com.orm.SugarRecord;

import java.util.List;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class LojasFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<Loja> lojas;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_lojas, container, false);


        //RecyclerView
        recyclerView = view.findViewById(R.id.loja_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);



        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addLojaDialog();
            }
        });



        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.lojas = SugarRecord.listAll(Loja.class);

        if (lojas.size()>0){
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new LojaAdapter(lojas, getContext()));
        } else {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Nenhuma loja adicionado! Adicone uma loja", Toast.LENGTH_LONG).show();
        }
        
        
        
    }

    private void addLojaDialog() {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View subview =  inflater.inflate(R.layout.add_loja, null);

        final EditText nomeLojaEdtx = subview.findViewById(R.id.enter_name_loja);
        final EditText enderecoLojaEdtx = subview.findViewById(R.id.enter_endereco);
        final EditText numeroLojaEdtx = subview.findViewById(R.id.enter_numero);
        final EditText telefoneLojaEdtx  = subview.findViewById(R.id.enter_telefone);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Adicionar loja");
        builder.setView(subview);
        builder.create();

        builder.setPositiveButton("ADICIONAR LOJA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                final String nomeLoja = nomeLojaEdtx.getText().toString();
                final String endereco = enderecoLojaEdtx.getText().toString();
                final int numero;
                final String telefone = telefoneLojaEdtx.getText().toString();


                if (numeroLojaEdtx.getText().toString().equals("")){
                    numero = 0;
                } else{
                    numero = Integer.parseInt(numeroLojaEdtx.getText().toString());
                }


                if (TextUtils.isEmpty(nomeLoja) || TextUtils.isEmpty(endereco) || TextUtils.isEmpty(telefone) || numero <=0){
                    Toast.makeText(getContext(), "Erro verifique os valores informados", Toast.LENGTH_LONG).show();
                } else {
                    Loja loja = new Loja(nomeLoja, endereco, numero,telefone);
                    loja.save();

                    getActivity().finish();
                    startActivity(getActivity().getIntent());
                }



            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Tarefa Cancelada", Toast.LENGTH_LONG).show();
            }
        });

        builder.show();


    }

}
