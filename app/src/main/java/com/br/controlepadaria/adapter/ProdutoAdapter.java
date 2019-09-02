package com.br.controlepadaria.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.br.controlepadaria.R;
import com.br.controlepadaria.domain.Produto;
import com.orm.SugarRecord;

import java.util.List;

public class ProdutoAdapter  extends RecyclerView.Adapter<ProdutoAdapter.ProdutoViewHolder> {


    private final Context context;
    private final List<Produto> produtos;

    public ProdutoAdapter(Context context, List<Produto> produtos) {
        this.context = context;
        this.produtos = produtos;
    }


    @NonNull
    @Override
    public ProdutoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_produto, viewGroup, false);
        ProdutoViewHolder holder = new ProdutoViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProdutoViewHolder holder, int position) {
        final Produto p = produtos.get(position);

        holder.nome.setText(p.getNome());
        holder.valor.setText(p.getValor().toString());

        holder.editProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editTaskDialog(p);
            }
        });

        holder.deleteProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmDelete(p);


            }
        });
        

    }



    @Override
    public int getItemCount() {
        return this.produtos != null ? produtos.size() : 0;
    }


    //Dialogo editar produto
    private void editTaskDialog(final Produto produto) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View subView = inflater.inflate(R.layout.add_produto, null);

        final EditText nomeProduto = subView.findViewById(R.id.enter_name);
        final EditText valorProduto = subView.findViewById(R.id.enter_valor);

        if (produto != null){
            nomeProduto.setText(produto.getNome());
            valorProduto.setText(String.valueOf(produto.getValor()));
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Editar produto");
        builder.setView(subView);
        builder.create();

        builder.setPositiveButton("EDITAR PRODUTO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int wich) {
                final String name = nomeProduto.getText().toString();
                final Double valor = Double.parseDouble(valorProduto.getText().toString());

                if(TextUtils.isEmpty(name) || valor <= 0){
                    Toast.makeText(context, "Erro verifique os valores informados", Toast.LENGTH_LONG).show();
                }
                else{

                    //Update dos dados do produto
                    produto.setNome(name);
                    produto.setValor(valor);
                    produto.save();

                    //mDatabase.updateProduct(new Product(product.getId(), name, quantity));
                    //refresh the activity
                    ((Activity)context).finish();
                    context.startActivity(((Activity)context).getIntent());
                }
            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(context, "Tarefa Cancelada", Toast.LENGTH_LONG).show();
            }
        });
        builder.show();


    }

    //Confirma exclusão

    private void confirmDelete(final Produto produto){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_delete);
        builder.setTitle("Excluir produto?");

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SugarRecord.delete(produto);
                Toast.makeText(context, "Produto excluído", Toast.LENGTH_SHORT).show();
                //refresh the activity page.
                ((Activity)context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        builder.setNegativeButton("NÂO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Tarefa Cancelada", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }



    public static class ProdutoViewHolder extends RecyclerView.ViewHolder{

        public TextView nome, valor;
        public ImageView editProduct;
        public ImageView deleteProduct;

        public ProdutoViewHolder(@NonNull View itemView) {
            super(itemView);
            nome = itemView.findViewById(R.id.product_name);
            valor = itemView.findViewById(R.id.product_valor);
            editProduct = itemView.findViewById(R.id.edit_product);
            deleteProduct = itemView.findViewById(R.id.delete_product);
        }
    }


}
