package com.example.pdm1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResumoActivity extends AppCompatActivity {

    private Viagem selectedViagem;
    Button editar;
    TextView resumoDestino, resumoViajantes, resumoDuracao, resumoCustoTotal, resumoCustoPessoa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resumo);

        inicializaDadosResumo();

        editar = findViewById(R.id.editarViagem);

        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editViagemIntent = new Intent(getApplicationContext(), NovaviagemActivity.class);
                editViagemIntent.putExtra(Viagem.VIAGEM_EDIT_EXTRA, selectedViagem.getId());
                startActivity(editViagemIntent);
            }
        });

        atualizaDados();

    }

    private void atualizaDados() {

        Intent previousIntent = getIntent();
        int passedViagemId = previousIntent.getIntExtra(Viagem.VIAGEM_EDIT_EXTRA, -1);
        selectedViagem = Viagem.getViagemForId(passedViagemId);

        if(selectedViagem != null){
            resumoDestino.setText(selectedViagem.getTitle());
            resumoViajantes.setText(String.valueOf(selectedViagem.getQtdPessoasViagem()));
            resumoDuracao.setText(String.valueOf(selectedViagem.getDuracaoViagem()));

            int valorTotal, valorCarro, valorTarifa, valorRefeicoes, valorHospedagem;
            valorCarro = 0;
            valorTarifa = 0;
            valorRefeicoes = 0;
            valorHospedagem = 0;


            if (selectedViagem.getAdicionarVeiculo()){
                valorCarro = (selectedViagem.getKmEstimado()*selectedViagem.getCustoMedioLitro())/(selectedViagem.getTotalVeiculos()*selectedViagem.getMediaKmPh());
            }

            if(selectedViagem.getAdicionarTarifa()){
                valorTarifa = (selectedViagem.getCustoEstimadoPessoaTarifa()*selectedViagem.getQtdPessoasViagem())+selectedViagem.getAluguelVeiculoTarifa();
            }

            if (selectedViagem.getAdicionarRefeicoes()){
                valorRefeicoes = selectedViagem.getCustoEstimadoRefeicao()*selectedViagem.getRefeicoesPorDia()*selectedViagem.getQtdPessoasViagem()*selectedViagem.getDuracaoViagem();
            }
            if(selectedViagem.getAdicionarHospedagem()){
                valorHospedagem = selectedViagem.getCustoMedioNoiteHospedagem()*selectedViagem.getDuracaoViagem()*selectedViagem.getTotalQuartosHospedagem();
            }

            valorTotal = valorCarro + valorTarifa + valorRefeicoes + valorHospedagem;

            resumoCustoTotal.setText(String.valueOf(valorTotal));

            int valorPessoa = valorTotal/selectedViagem.getQtdPessoasViagem();

            resumoCustoPessoa.setText(String.valueOf(valorPessoa));
        }

    }

    private void inicializaDadosResumo() {

        resumoDestino = findViewById(R.id.resumoDestino);
        resumoViajantes = findViewById(R.id.resumoViajantes);
        resumoDuracao = findViewById(R.id.resumoDuracao);
        resumoCustoTotal = findViewById(R.id.resumoCustoTotal);
        resumoCustoPessoa = findViewById(R.id.resumoCustoPessoa);

    }
}
