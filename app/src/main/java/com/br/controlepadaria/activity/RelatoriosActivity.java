package com.br.controlepadaria.activity;

import android.os.Bundle;

import com.br.controlepadaria.R;
import com.br.controlepadaria.fragments.RelatoriosFragment;

public class RelatoriosActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorios);

        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Titulo
        getSupportActionBar().setTitle("Relatórios");
        if (savedInstanceState == null){
            RelatoriosFragment frag = new RelatoriosFragment();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.relatFragment, frag).commit();
        }
    }
}
