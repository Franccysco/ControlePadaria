package com.br.controlepadaria.activity;

import android.os.Bundle;

import com.br.controlepadaria.R;
import com.br.controlepadaria.fragments.PedidosFragment;

public class PedidosActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedidos);

        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Titulo
        getSupportActionBar().setTitle("Pedidos");
        if (savedInstanceState == null){
            PedidosFragment frag = new PedidosFragment();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.pedidoFragment, frag).commit();
        }
    }
}
