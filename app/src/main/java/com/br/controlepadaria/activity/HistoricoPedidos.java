package com.br.controlepadaria.activity;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.br.controlepadaria.R;
import com.br.controlepadaria.adapter.PedidoAdapter;
import com.br.controlepadaria.domain.Pedido;
import com.orm.SugarRecord;

import java.util.List;

public class HistoricoPedidos extends BaseActivity {


    private List<Pedido> pedidos;
    private RecyclerView recyclerView;
    private Button btnVerPedido;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico_pedidos);


        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //Titulo
        getSupportActionBar().setTitle("Histórico Pedidos");

        recyclerView = findViewById(R.id.lista_pedidos);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);



        this.pedidos = SugarRecord.listAll(Pedido.class);

        if (pedidos.size()>0){
            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setAdapter(new PedidoAdapter(this, pedidos));
        } else {
            recyclerView.setVisibility(View.GONE);
            Toast.makeText(this, "Nenhum pedido adicionado! Adicone um pedido", Toast.LENGTH_LONG).show();
        }



    }
}
