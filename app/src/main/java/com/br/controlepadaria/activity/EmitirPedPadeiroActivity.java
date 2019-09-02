package com.br.controlepadaria.activity;

import android.os.Bundle;

import com.br.controlepadaria.R;
import com.br.controlepadaria.fragments.EmitirPedPadeiroFragment;

public class EmitirPedPadeiroActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emitir_ped_padeiro);

        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Titulo
        getSupportActionBar().setTitle("Emitir pedidos Padeiro");
        if (savedInstanceState == null){
            EmitirPedPadeiroFragment frag = new EmitirPedPadeiroFragment();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.emitirPadeiroFragment, frag).commit();
        }
    }
}
