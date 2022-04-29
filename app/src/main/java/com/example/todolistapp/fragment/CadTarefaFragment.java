package com.example.todolistapp.fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.todolistapp.R;
import com.example.todolistapp.database.AppDatabase;
import com.example.todolistapp.databinding.FragmentCadTarefaBinding;
import com.example.todolistapp.databinding.FragmentDetalheTarefaBinding;
import com.example.todolistapp.databinding.FragmentPrincipalBinding;
import com.example.todolistapp.model.Tarefa;

import java.util.Calendar;


public class CadTarefaFragment extends Fragment {

    private FragmentCadTarefaBinding binding;

    private DatePickerDialog datePicker;

    int year, month, day;
    //variavel para obter a data atual
    Calendar dataAtual;
    //variavel para data formatada
    String dataFormatada = ("");
    //variavel para a database
    AppDatabase database;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //instancia a a database
        database = AppDatabase.getDatabase(getContext());
        //instanciar o binding
        binding = FragmentCadTarefaBinding.inflate(getLayoutInflater(),container, false);

        //instanciar a data atual
        dataAtual = Calendar.getInstance();
        //obter ano mes e dia
        year= dataAtual.get(Calendar.YEAR);
        month= dataAtual.get(Calendar.MONTH);
        day= dataAtual.get(Calendar.DAY_OF_MONTH);
        //instanciondo o datePicker
        datePicker = new DatePickerDialog(getContext(), (datePicker, ano, mes, dia)-> {
            //no escolher uma data no dataPicker, cai aqui
            //passar para as variaveis globais
            year = ano;
            month = mes;
            day = dia;
            //formata a data

            dataFormatada = String.format("%02d/%02d/%04d", day, month + 1, year);
            //aplica data formatada no botão
            binding.dataTarefa.setText(dataFormatada);
        },year,month,day);


        //acao do clik do botao de seleção
        binding.dataTarefa.setOnClickListener(v->{
            datePicker.show();

        });

        //listener do botão salvar
        binding.salvarTarefa.setOnClickListener(v ->{
            if( binding.editTitulo.getText().toString().isEmpty()){
                binding.editTitulo.setError(getString(R.string.alerta_titulo));
            }else if(dataFormatada.isEmpty()){
                Toast.makeText(getContext(), R.string.alerta_data, Toast.LENGTH_LONG).show();
            }else{
                //cria uma tarefa
                Tarefa tarefa= new Tarefa();
                //popular um objeto
                tarefa.setTitulo(binding.editTitulo.getText().toString());
                tarefa.setDescricao(binding.editDescricao.getText().toString());
                tarefa.setDataCriacao((dataAtual.getTimeInMillis()));

                //criar um Calendar
                Calendar dataPrevista = Calendar.getInstance();
                //muda a data para data escolhida no dateapicker
                dataPrevista.set(year, month, day);
                //passa o milissigundo da data para a data prevista
                tarefa.setDataPrevista(dataPrevista.getTimeInMillis());
                //salvar a tarefa
                new InsertTarefa().execute(tarefa);
            }

        });
        // retorna a view raiz do binding
        return binding.getRoot();
    }
    //asyncTask para inserir tarefa
    private class InsertTarefa extends AsyncTask<Tarefa, Void, String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(Tarefa... tarefas) {
            //pegar a tarefa a partir do vetor
            Tarefa t = tarefas[0];
            try {
                //chamar o metodo para salvar a tarefa
                database.getTarefaDao().insert(t);
                //retornar
                return "ok";
            }catch (Exception erro){
                erro.printStackTrace();
                //retorna a menssagem de erro
                return erro.getMessage();
            }
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result) {
            if(result.equals("ok")){
                Log.w("RESULT","IUPIIIIIIIIITAREFA INSERIDA COM SUCESSO" );
                Toast.makeText(getContext(), "Tarefa inserida com sucesso ", Toast.LENGTH_SHORT).show();
                //voltar ao fragment anterior
                getActivity().onBackPressed();
            }else{
                Log.w("RESULT",result);
                Toast.makeText(getContext(), result, Toast.LENGTH_SHORT).show();
            }
        }
    }
}