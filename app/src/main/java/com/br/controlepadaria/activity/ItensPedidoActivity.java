package com.br.controlepadaria.activity;

import android.os.Bundle;

import com.br.controlepadaria.R;
import com.br.controlepadaria.fragments.ItensPedidoFragment;

public class ItensPedidoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itens_pedido);

        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Titulo
        getSupportActionBar().setTitle("Itens do Pedido");
        if (savedInstanceState == null){
            ItensPedidoFragment frag = new ItensPedidoFragment();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.fragment_itens_pedido, frag).commit();
        }
    }

}
