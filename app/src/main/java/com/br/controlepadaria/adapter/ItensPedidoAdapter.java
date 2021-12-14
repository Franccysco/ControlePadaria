package com.br.controlepadaria.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.br.controlepadaria.R;
import com.br.controlepadaria.domain.ItensPedido;

import java.util.List;

public class ItensPedidoAdapter  extends RecyclerView.Adapter<ItensPedidoAdapter.ItensPedidoViewHolder> {


    private List<ItensPedido> itensPedidos;
    private Context context;

    public ItensPedidoAdapter(List<ItensPedido> itensPedidos, Context context) {
        this.itensPedidos = itensPedidos;
        this.context = context;
    }

    @NonNull
    @Override
    public ItensPedidoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_itens_pedido, viewGroup, false);
        ItensPedidoViewHolder holder = new ItensPedidoViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ItensPedidoViewHolder itensPedidoViewHolder, int position) {

        final ItensPedido itensPedido = itensPedidos.get(position);

        itensPedidoViewHolder.nome.setText(itensPedido.getProduto().getNome());
        itensPedidoViewHolder.valor.setText(String.valueOf(itensPedido.getQuantidade() * itensPedido.getProduto().getValor()));
        itensPedidoViewHolder.qtd.setText(String.valueOf(itensPedido.getQuantidade()));


        itensPedidoViewHolder.editItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        itensPedidoViewHolder.deleteItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });




    }

    @Override
    public int getItemCount() {
        return this.itensPedidos != null ? itensPedidos.size() : 0;
    }

    public static class ItensPedidoViewHolder extends RecyclerView.ViewHolder {

        public TextView nome, valor, qtd;
        public ImageView editItem;
        public ImageView deleteItem;
        

        public ItensPedidoViewHolder(View itemView) {
            super(itemView);

            nome = itemView.findViewById(R.id.item_product_name);
            valor = itemView.findViewById(R.id.item_product_valor);
            qtd = itemView.findViewById(R.id.item_txtqtd);
            editItem = itemView.findViewById(R.id.edit_item);
            deleteItem = itemView.findViewById(R.id.delete_item);
        }
    }
}
