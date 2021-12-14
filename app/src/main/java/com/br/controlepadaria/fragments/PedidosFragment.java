package com.br.controlepadaria.fragments;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.br.controlepadaria.R;
import com.br.controlepadaria.activity.HistoricoPedidos;
import com.br.controlepadaria.activity.ItensPedidoActivity;
import com.br.controlepadaria.domain.Loja;
import com.br.controlepadaria.domain.Pedido;
import com.orm.SugarRecord;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * A simple {@link BaseFragment} subclass.
 */
public class PedidosFragment extends BaseFragment implements View.OnClickListener {


//    private RecyclerView recyclerView;
//    private List<Pedido> pedidos;
    private List<Loja> lojas;
    private EditText edtDta;
    private Spinner spinnerLoja;
    private Loja lojaSelect;
    private Button btnAddPedido, btnHistorico;

    private final Calendar dataCalendar = Calendar.getInstance();
    private DateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_pedido2, container, false);


       edtDta = view.findViewById(R.id.edtDta);
       edtDta.setText(dataFormat.format(dataCalendar.getTime()));
       edtDta.setOnClickListener(this);

        spinnerLoja = view.findViewById(R.id.lojaspinnerPedido);
        lojas = SugarRecord.listAll(Loja.class);

        ArrayAdapter spinnerAdapterLoja = new ArrayAdapter(getContext(), R.layout.support_simple_spinner_dropdown_item, lojas);
        spinnerLoja.setAdapter(spinnerAdapterLoja);


        spinnerLoja.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                lojaSelect = (Loja) adapterView.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });



        btnAddPedido = view.findViewById(R.id.btnadicionarPedido);
        btnAddPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Pedido pedido = new Pedido(edtDta.getText().toString(), 0.0, lojaSelect);
                pedido.save();


                Toast.makeText(getContext(), "Adicionando: "+ lojaSelect, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getContext(), ItensPedidoActivity.class);
                Bundle params = new Bundle();
                params.putString("idPed", pedido.getId().toString());
                intent.putExtras(params);
                startActivity(intent);

            }
        });

        btnHistorico = view.findViewById(R.id.historicoPedido);
        btnHistorico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), HistoricoPedidos.class);
                startActivity(i);
            }
        });







        return view;
    }




    @Override
    public void onClick(View view) {

//        if(view.getId() == R.id.edtLoja) {
//            lojas = SugarRecord.listAll(Loja.class);
//
//            if (lojas.size() > 0) {
//                selectLojaDialog(lojas);
//            } else {
//                Toast.makeText(getContext(), "Nenhuma loja adicionado! Adicone uma loja", Toast.LENGTH_LONG).show();
//            }
//        }

        if(view.getId() == R.id.edtDta){
            showDatePickerDialog();
        }



    }

    private void showDatePickerDialog() {

        new DatePickerDialog(getContext(), dtPicker, dataCalendar.get(Calendar.YEAR), dataCalendar.get(Calendar.MONTH), dataCalendar.get(Calendar.DAY_OF_MONTH)).show();

    }

    //Listener para dtpicker dialog
    DatePickerDialog.OnDateSetListener dtPicker = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dataCalendar.set(Calendar.YEAR, year);
            dataCalendar.set(Calendar.MONTH, month);
            dataCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            atualizaDataEdtTxt();
        }
    };

    private void atualizaDataEdtTxt() {
        edtDta.setText(dataFormat.format(dataCalendar.getTime()));
    }

//    private void selectLojaDialog(final List<Loja> lojas) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Selecionar Loja");
//        builder.create();
//
//        final String[] items = new String[lojas.size()];
//
//        for(int i=0; i < lojas.size(); i++){
//            items[i] = lojas.get(i).getNome();
//        }
//
//
//        builder.setItems(items, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int which) {
//
//                Toast.makeText(
//                        getActivity(),
//                        "Select:" + items[which],
//                        Toast.LENGTH_SHORT)
//                        .show();
//
//                edtLoja.setText( items[which]);
//            }
//        });
//
//        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                Toast.makeText(getContext(), "Tarefa Cancelada", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        builder.show();
//
//    }
}
