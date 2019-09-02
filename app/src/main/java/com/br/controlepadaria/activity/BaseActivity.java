package com.br.controlepadaria.activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Toast;

import com.br.controlepadaria.R;
import com.br.controlepadaria.utils.NavDrawerUtil;

public class BaseActivity extends AppCompatActivity {

    protected DrawerLayout drawerLayout;

    //Configura a Toolbar
    protected void setUpToolbar()
    {
        Toolbar toolbar =  findViewById(R.id.toolbar);

        if (toolbar != null)
        {
            setSupportActionBar(toolbar);
        }
    }

    // Configura o Nav Drawer
    protected void setupNavDrawer() {
        // Drawer Layout
        final ActionBar actionBar = getSupportActionBar();
        // Ícone do menu do nav drawer
        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_dp);
        actionBar.setDisplayHomeAsUpEnabled(true);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        if (navigationView != null && drawerLayout != null) {
            // Atualiza a imagem e textos do header---Arrumar essa parte aqui
            NavDrawerUtil.setHeaderValues(navigationView, R.id.containerNavDrawerListViewHeader, R.drawable.nav_drawer_header, R.drawable.ic_logo_user, R.string.nav_drawer_username, R.string.nav_drawer_email);
            // Trata o evento de clique no menu.
            navigationView.setNavigationItemSelectedListener(
                    new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(MenuItem menuItem) {
                            // Seleciona a linha
                            menuItem.setChecked(true);
                            // Fecha o menu
                            drawerLayout.closeDrawers();
                            // Trata o evento do menu
                            onNavDrawerItemSelected(menuItem);
                            return true;
                        }
                    });
        }
    }

    // Trata o evento do menu lateral
    private void onNavDrawerItemSelected(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.nav_item_dashboard:

                break;
            case R.id.nav_item_produtos:
                Intent intent = new Intent(this, ProdutosActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_item_lojas:
                intent = new Intent(this, LojasActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_item_pedidos:
                intent = new Intent(this, PedidosActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_item_ped_loja:
                intent = new Intent(this, EmitirPedLojaActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_item_ped_padeiro:
                intent = new Intent(this, EmitirPedPadeiroActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_item_relatorio:
                intent = new Intent(this, RelatoriosActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_item_settings:
                Toast.makeText(this,"Clicou em Configuraçãoes", Toast.LENGTH_LONG).show();
                break;
        }
    }

    //Adicona o fragment no centro da tela
    protected void replaceFragment(Fragment frag)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, frag, "TAG").commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                // Trata o clique no botão que abre o menu.
                if (drawerLayout != null) {
                    openDrawer();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    // Abre o menu lateral
    protected void openDrawer() {
        if (drawerLayout != null) {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    // Fecha o menu lateral
    protected void closeDrawer() {
        if (drawerLayout != null) {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
    }
}
