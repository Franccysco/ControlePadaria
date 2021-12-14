package com.br.controlepadaria.adapter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.br.controlepadaria.R;
import com.br.controlepadaria.domain.Loja;
import com.orm.SugarRecord;

import java.util.List;

public class LojaAdapter extends RecyclerView.Adapter<LojaAdapter.LojaViewHolder> {


    private final List<Loja> lojas;
    private final Context context;

    public LojaAdapter(List<Loja> lojas, Context context) {
        this.lojas = lojas;
        this.context = context;
    }


    @NonNull
    @Override
    public LojaViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.adapter_loja, viewGroup, false);
        LojaViewHolder holder = new LojaViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LojaViewHolder holder, final int position) {
        final Loja loja = lojas.get(position);

        holder.txtNomeLoja.setText(loja.getNome());
        holder.txtNumero.setText(String.valueOf(loja.getNumero()));
        holder.txtEndereco.setText(loja.getEndereco());
        holder.txtTelefone.setText(loja.gettelefone());

        holder.editLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                editLojaDialog(loja, position);
            }
        });

        holder.deleteLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDelete(loja, position);
            }
        });


    }

    private void confirmDelete(final Loja loja, final int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setIcon(R.drawable.ic_delete);
        builder.setTitle("Excluir loja?");

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                SugarRecord.delete(loja);
                Toast.makeText(context, "Loja excluída", Toast.LENGTH_SHORT).show();



                //refresh the activity page.
                ((Activity) context).finish();
                context.startActivity(((Activity) context).getIntent());
            }
        });

        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(context, "Tarefa Cancelada", Toast.LENGTH_LONG).show();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void editLojaDialog(final Loja loja, final int position) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View subview = inflater.inflate(R.layout.add_loja, null);

        final EditText nomeLojaEdtx = subview.findViewById(R.id.enter_name_loja);
        final EditText enderecoLojaEdtx = subview.findViewById(R.id.enter_endereco);
        final EditText numeroLojaEdtx = subview.findViewById(R.id.enter_numero);
        final EditText telefoneLojaEdtx = subview.findViewById(R.id.enter_telefone);


        if (loja != null) {
            nomeLojaEdtx.setText(loja.getNome());
            enderecoLojaEdtx.setText(loja.getEndereco());
            numeroLojaEdtx.setText(String.valueOf(loja.getNumero()));
            telefoneLojaEdtx.setText(loja.gettelefone());
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Editar loja");
        builder.setView(subview);
        builder.create();

        builder.setPositiveButton("EDITAR LOJA", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int which) {
                final String nomeLoja = nomeLojaEdtx.getText().toString();
                final String endereco = enderecoLojaEdtx.getText().toString();
                final int numero;
                final String telefone = telefoneLojaEdtx.getText().toString();


                if (numeroLojaEdtx.getText().toString().equals("")) {
                    numero = 0;
                } else {
                    numero = Integer.parseInt(numeroLojaEdtx.getText().toString());
                }


                if (TextUtils.isEmpty(nomeLoja) || TextUtils.isEmpty(endereco) || TextUtils.isEmpty(telefone) || numero <= 0) {
                    Toast.makeText(context, "Erro verifique os valores informados", Toast.LENGTH_LONG).show();
                } else {

                    loja.setNome(nomeLoja);
                    loja.setEndereco(endereco);
                    loja.setNumero(numero);
                    loja.settelefone(telefone);
                    loja.save();


                    //Notifica a edição da lista
                    notifyItemChanged(position);

//                    ((Activity) context).finish();
//                    context.startActivity(((Activity) context).getIntent());
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

    @Override
    public int getItemCount() {
        return this.lojas != null ? lojas.size() : 0;
    }

    public static class LojaViewHolder extends RecyclerView.ViewHolder {

        public TextView txtEndereco, txtNumero, txtNomeLoja, txtTelefone;
        public ImageButton editLoja, deleteLoja;


        public LojaViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNomeLoja = itemView.findViewById(R.id.nome_loja);
            txtEndereco = itemView.findViewById(R.id.endereco_loja);
            txtNumero = itemView.findViewById(R.id.endereco_loja_numero);
            txtTelefone = itemView.findViewById(R.id.endereco_loja_telefone);
            editLoja = itemView.findViewById(R.id.main_card_edit);
            deleteLoja = itemView.findViewById(R.id.main_card_delete);
        }
    }
}
