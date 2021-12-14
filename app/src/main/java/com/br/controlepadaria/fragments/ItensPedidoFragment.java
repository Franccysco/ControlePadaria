package com.br.controlepadaria.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.br.controlepadaria.R;
import com.br.controlepadaria.activity.PedidosActivity;
import com.br.controlepadaria.adapter.ItensPedidoAdapter;
import com.br.controlepadaria.domain.ItensPedido;
import com.br.controlepadaria.domain.Pedido;
import com.br.controlepadaria.domain.Produto;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.List;

public class ItensPedidoFragment extends BaseFragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private TextView txtPedido, txtData, txtLoja, txtVal;
    private Button btnFinalizarPed;
    private Pedido pedido;
    private List<Produto> produtos;
    private List<ItensPedido> itensPedidos = new ArrayList<>();
    private ItensPedidoAdapter itensPedidoAdapter;
    private Double valor = 0.0;


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_itens_pedido, container, false);

        Bundle args = getActivity().getIntent().getExtras();
        String id = args.getString("idPed");

        pedido = SugarRecord.findById(Pedido.class, Long.valueOf(id));

        txtPedido = view.findViewById(R.id.txtPedido);

        txtPedido.setText("# "+pedido.getId().toString());

        txtData = view.findViewById(R.id.txtDta);
        txtData.setText(pedido.getData());

        txtLoja = view.findViewById(R.id.txtLoja);
        txtLoja.setText(pedido.getLoja().getNome());

        txtVal = view.findViewById(R.id.txtValTotalprodutos);
        txtVal.setText(pedido.getValor().toString());


        btnFinalizarPed = view.findViewById(R.id.btnFinalizar);
        btnFinalizarPed.setOnClickListener(this);



        //RecyclerView
        recyclerView = view.findViewById(R.id.itens_pedidos_product_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);




        FloatingActionButton fab = view.findViewById(R.id.fbProdutos);
        fab.setOnClickListener(this);



        return view;
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fbProdutos){
            addItensDialog();
        }
        if(view.getId() == R.id.btnFinalizar){

            if (itensPedidos.size()>0) {
                pedido.setValor(valor);
                pedido.save();

                Intent i = new Intent(getContext(), PedidosActivity.class);
                startActivity(i);
            } else{
                Toast.makeText(getContext(), "Nenhum produto adicionado ao pedido! Adicone um produto", Toast.LENGTH_LONG).show();
            }
        }
    }



//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//
//
//        itensPedidos = SugarRecord.find(ItensPedido.class, "pedido = ?", pedido.getId().toString());
//
//
//        if (itensPedidos.size()>0){
//            recyclerView.setVisibility(View.VISIBLE);
//            itensPedidoAdapter = new ItensPedidoAdapter( itensPedidos, getContext());
//            recyclerView.setAdapter(itensPedidoAdapter);
//
//
//        } else {
//            recyclerView.setVisibility(View.GONE);
//            Toast.makeText(getContext(), "Nenhum produto adicionado ao pedido! Adicone um produto", Toast.LENGTH_LONG).show();
//        }
//
//    }


        private void addItensDialog() {

        LayoutInflater inflater = LayoutInflater.from(getContext());
        final View subview =  inflater.inflate(R.layout.add_pedido, null);

        final Spinner spinnerProdut = subview.findViewById(R.id.itemProdutPedido);
        final EditText qtdItemPedido = subview.findViewById(R.id.qtdItemPedido);
        final Button btnAdd = subview.findViewById(R.id.btnadicionarItem);

        produtos = SugarRecord.listAll(Produto.class);

        ArrayAdapter spinnerAdapter = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, produtos);
        spinnerProdut.setAdapter(spinnerAdapter);


        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("Adicionar Itens do Pedido");
        builder.setView(subview);
        builder.create();

        builder.setPositiveButton("Finalizar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {


//                getActivity().finish();
//                startActivity(getActivity().getIntent());

                atualizaValorTotal();


            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "Tarefa Cancelada", Toast.LENGTH_LONG).show();
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!qtdItemPedido.getText().toString().equals("")) {

                    Produto produto = (Produto) ((Spinner) subview.findViewById(R.id.itemProdutPedido)).getSelectedItem();
                    Toast.makeText(getContext(), "Adicionando: " + produto.getNome() + " Qtd: " + qtdItemPedido.getText(), Toast.LENGTH_LONG).show();


                    ItensPedido itensPedidosave = new ItensPedido(Integer.parseInt(qtdItemPedido.getText().toString()), produto.getValor(), pedido, produto);
                    itensPedidosave.save();

                    itensPedidos = SugarRecord.find(ItensPedido.class, "pedido = ?", pedido.getId().toString());
                    itensPedidoAdapter = new ItensPedidoAdapter(itensPedidos, getContext());
                    recyclerView.setAdapter(itensPedidoAdapter);


                    itensPedidoAdapter.notifyDataSetChanged();

                }
                else {
                    Toast.makeText(getContext(), "Quantidade do produto não informada!", Toast.LENGTH_LONG).show();
                }


            }
        });

        builder.show();
    }


    private void atualizaValorTotal(){



        for (int i = 0; i<itensPedidos.size(); i++){

            valor += (itensPedidos.get(i).getQuantidade() * itensPedidos.get(i).getProduto().getValor());

        }


       Log.i("valor", String.valueOf(valor));


        txtVal.setText(String.valueOf(valor));
    }


}
