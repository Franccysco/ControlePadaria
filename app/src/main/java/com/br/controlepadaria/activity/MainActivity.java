package com.br.controlepadaria.activity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.br.controlepadaria.R;
import com.br.controlepadaria.fragments.DashBoardFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Ativar Toolbar
        setUpToolbar();
        //Ativa o menu lateral
        setupNavDrawer();

        //Inicializa layout principal com o fragment da dashboard
        replaceFragment(new DashBoardFragment());



//        Produto produto = new Produto("Pão", 1.0);
//        produto.save();
//
//        Loja loja = new Loja("Padaria Plahano", "Rua Maria do Rosário", 1432, "3421-0033");
//        loja.save();
//
//        loja = Loja.findById(Loja.class,1);
//
//        long newTime = System.currentTimeMillis();
//
//        Pedido p = new Pedido(newTime, 50.00, loja);
//        p.save();
//
//        ItensPedido itensPedido = new ItensPedido(10, 20.00, p, produto);
//        itensPedido.save();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about){
//            AboutDialog.showAbout(getSupportFragmentManager());
            Toast.makeText(this,"Clicou em sobre", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
