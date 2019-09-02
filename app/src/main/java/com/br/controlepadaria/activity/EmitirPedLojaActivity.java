package com.br.controlepadaria.activity;

import android.os.Bundle;

import com.br.controlepadaria.R;
import com.br.controlepadaria.fragments.EmitirPedLojaFragment;

public class EmitirPedLojaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emitir_ped_loja);
        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Titulo
        getSupportActionBar().setTitle("Emitir pedidos Loja");
        if (savedInstanceState == null){
            EmitirPedLojaFragment frag = new EmitirPedLojaFragment();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.emitirLojaFragment, frag).commit();
        }
    }
}
