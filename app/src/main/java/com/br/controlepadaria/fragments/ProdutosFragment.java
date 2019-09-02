package com.br.controlepadaria.fragments;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.br.controlepadaria.R;
import com.br.controlepadaria.adapter.ProdutoAdapter;
import com.br.controlepadaria.domain.Produto;
import com.orm.SugarRecord;

import java.util.List;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class ProdutosFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<Produto> produtos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_produtos, container, false);
        
        recyclerView = view.findViewById(R.id.product_list);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);


        FloatingActionButton fab = view.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addProdutoDialog();
            }
        });

        
        return view;
    }

    private void addProdutoDialog() {

        LayoutInflater inflater = LayoutInflater.from(getContext());

        View subView = inflater.inflate(R.layout.add_produto, null);

        final EditText nomeProduto = subView.findViewById(R.id.enter_name);
        final EditText valorProduto = subView.findViewById(R.id.enter_valor);

        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Adicionar produto");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("ADICIONAR PRODUTO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                final String name = nomeProduto.getText().toString();
                final Double valor;

                if (valorProduto.getText().toString().equals("")){
                    valor = 0.0;
                } else {
                     valor = Double.parseDouble(String.valueOf(valorProduto.getText()));
                }

                if(TextUtils.isEmpty(name) || valor <= 0.0){
                    Toast.makeText(getContext(), "Erro verifique os valores informados", Toast.LENGTH_LONG).show();
                }
                else{


                    Produto p = new Produto(name, valor);
                    p.save();

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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        this.produtos = SugarRecord.listAll(Produto.class);

        if (produtos.size()>0){
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new ProdutoAdapter(getContext(), produtos));
        } else {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(getContext(), "Nenhum produto adicionado! Adicone um produto", Toast.LENGTH_LONG).show();
        }


    }
}
