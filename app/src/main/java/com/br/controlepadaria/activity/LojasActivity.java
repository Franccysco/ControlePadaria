package com.br.controlepadaria.activity;

import android.os.Bundle;

import com.br.controlepadaria.R;
import com.br.controlepadaria.fragments.LojasFragment;

public class LojasActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lojas);

        setUpToolbar();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Titulo
        getSupportActionBar().setTitle("Lojas");
        if (savedInstanceState == null){
            LojasFragment frag = new LojasFragment();
            frag.setArguments(getIntent().getExtras());
            getSupportFragmentManager().beginTransaction().add(R.id.lojaFragment, frag).commit();
        }
    }
}
