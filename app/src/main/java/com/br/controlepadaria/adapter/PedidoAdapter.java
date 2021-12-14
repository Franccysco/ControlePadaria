package com.br.controlepadaria.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.br.controlepadaria.R;
import com.br.controlepadaria.domain.Pedido;

import java.util.List;

public class PedidoAdapter extends RecyclerView.Adapter<PedidoAdapter.PedidoViewHolder> {

    private Context context;
    private List<Pedido> pedidos;

    public PedidoAdapter(Context context, List<Pedido> pedidos) {
        this.context = context;
        this.pedidos = pedidos;
    }



    @Override
    public PedidoViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {


        View view = LayoutInflater.from(context).inflate(R.layout.adapter_pedido, viewGroup, false);
        PedidoViewHolder holder = new PedidoViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder( PedidoViewHolder pedidoViewHolder, int position) {
        final Pedido pedido = pedidos.get(position);

        pedidoViewHolder.txtpedido.setText("PEDIDO: #"+pedido.getId());
        pedidoViewHolder.txtData.setText(pedido.getData());
        pedidoViewHolder.txtLojaPedido.setText(pedido.getLoja().getNome());
        pedidoViewHolder.txtValorPedido.setText(String.valueOf(pedido.getValor()));
        pedidoViewHolder.btnVerItens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(context, ItensPedidoActivity.class);
//                Bundle params = new Bundle();
//                params.putString("idPed", pedido.getId().toString());
//                intent.putExtras(params);
//                context.startActivity(intent);


                Toast.makeText(context,"Lista de itens do pedido relacionado", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.pedidos != null ? pedidos.size() : 0;
    }

    public static class PedidoViewHolder extends RecyclerView.ViewHolder {
        private TextView txtpedido, txtData, txtLojaPedido, txtValorPedido;
        private Button btnVerItens;

        public PedidoViewHolder(View itemView) {
            super(itemView);

            txtpedido = itemView.findViewById(R.id.txtPedido);
            txtData = itemView.findViewById(R.id.txtdate);
            txtLojaPedido = itemView.findViewById(R.id.txtNomeLoja);
            txtValorPedido = itemView.findViewById(R.id.txtTotalPedido);
            btnVerItens = itemView.findViewById(R.id.btn_ver_mais);


        }
    }
}
