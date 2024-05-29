package com.example.pdm1;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.cardview.widget.CardView;

import java.util.Date;

public class NovaviagemActivity extends AppCompatActivity {

    private Viagem selectedViagem;
    private Button deleteButton;
    LinearLayout veiculocardLayout, veiculodetalhes;
    EditText titleViagem, qtdPessoasViagem, duracaoViagem;
    EditText kmestimado, mediakmph, customediolitro, totalveiculos, valortotalveiculo;
    EditText tarifacustoporpessoa, tarifaaluguel, valortotaltarifa;
    EditText refeicoescusto, refeicoesqtd, valortotalrefeicoes;
    EditText hospedagemcustonoite, hospedagemnoitesqtd, hospedagemquartosqtd, valortotalhospedagem;
    SwitchCompat adicionarviagemveiculo, adicionarviagemtarifa, adicionarviagemrefeicoes, adicionarviagemhospedagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novaviagem);

        iniciaDadosVeiculo();
        iniciaDadosTarifa();
        iniciaDadosRefeicoes();
        iniciaDadosHospedagem();

        //Atualiza os dados do valor total quando um dos outros valores são modificados
        atualizaDadosModificados();


        initWidget();
        checkForEditViagem();
    }

    private void atualizaDadosModificados() {
        TextWatcher gasolinaTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    float kmestimadoval = Integer.parseInt(kmestimado.getText().toString());
                    float mediakmphval = Integer.parseInt(mediakmph.getText().toString());
                    float customediolitroval = Integer.parseInt(customediolitro.getText().toString());
                    float totalveiculosval = Integer.parseInt(totalveiculos.getText().toString());
                    float valtotal = (kmestimadoval*customediolitroval)/(totalveiculosval*mediakmphval);
                    valortotalveiculo.setText(String.format("%.2f", valtotal));
                } catch (Exception e) {
                    valortotalveiculo.setText("");
                }

            }
        };
        kmestimado.addTextChangedListener(gasolinaTextWatcher);
        mediakmph.addTextChangedListener(gasolinaTextWatcher);
        customediolitro.addTextChangedListener(gasolinaTextWatcher);
        totalveiculos.addTextChangedListener(gasolinaTextWatcher);

        TextWatcher tarifaTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    float custoPorPessoa = Integer.parseInt(tarifacustoporpessoa.getText().toString());
                    float aluguel = Integer.parseInt(tarifaaluguel.getText().toString());
                    int nPessoas = Integer.parseInt(qtdPessoasViagem.getText().toString());
                    float totalTarifa = (custoPorPessoa * nPessoas) + aluguel;
                    valortotaltarifa.setText(String.format("%.2f", totalTarifa));
                } catch (Exception e) {
                    valortotaltarifa.setText("");
                }
            }
        };

        tarifacustoporpessoa.addTextChangedListener(tarifaTextWatcher);
        tarifaaluguel.addTextChangedListener(tarifaTextWatcher);

        TextWatcher refeicoesTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    float custoRefeicao = Integer.parseInt(refeicoescusto.getText().toString());
                    float qtdRefeicoes = Integer.parseInt(refeicoesqtd.getText().toString());
                    int nPessoas = Integer.parseInt(qtdPessoasViagem.getText().toString());
                    int durViagem = Integer.parseInt(duracaoViagem.getText().toString());
                    float totalRefeicoes = custoRefeicao * qtdRefeicoes * nPessoas * durViagem ;
                    valortotalrefeicoes.setText(String.format("%.2f", totalRefeicoes));
                } catch (Exception e) {
                    valortotalrefeicoes.setText("");
                }
            }
        };

        refeicoescusto.addTextChangedListener(refeicoesTextWatcher);
        refeicoesqtd.addTextChangedListener(refeicoesTextWatcher);

        TextWatcher hospedagemTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    float custoNoite = Integer.parseInt(hospedagemcustonoite.getText().toString());
                    float qtdNoites = Integer.parseInt(hospedagemnoitesqtd.getText().toString());
                    float qtdQuartos = Integer.parseInt(hospedagemquartosqtd.getText().toString());
                    float totalHospedagem = custoNoite * qtdNoites * qtdQuartos;
                    valortotalhospedagem.setText(String.format("%.2f", totalHospedagem));
                } catch (Exception e) {
                    valortotalhospedagem.setText("");
                }
            }
        };

        hospedagemcustonoite.addTextChangedListener(hospedagemTextWatcher);
        hospedagemnoitesqtd.addTextChangedListener(hospedagemTextWatcher);
        hospedagemquartosqtd.addTextChangedListener(hospedagemTextWatcher);
    }

    private void iniciaDadosHospedagem() {
        hospedagemcustonoite = findViewById(R.id.hospedagemcustonoite);
        hospedagemnoitesqtd = findViewById(R.id.hospedagemnoitesqtd);
        hospedagemquartosqtd = findViewById(R.id.hospedagemquartosqtd);
        valortotalhospedagem = findViewById(R.id.valortotalhospedagem);
        adicionarviagemhospedagem = findViewById(R.id.adicionarviagemhospedagem);
    }

    private void iniciaDadosRefeicoes() {
        refeicoescusto = findViewById(R.id.refeicoescusto);
        refeicoesqtd = findViewById(R.id.refeicoesqtd);
        valortotalrefeicoes = findViewById(R.id.valortotalrefeicoes);
        adicionarviagemrefeicoes = findViewById(R.id.adicionarviagemrefeicoes);
    }

    private void iniciaDadosTarifa() {
        tarifacustoporpessoa = findViewById(R.id.tarifacustoporpessoa);
        tarifaaluguel = findViewById(R.id.tarifaaluguel);
        valortotaltarifa = findViewById(R.id.valortotaltarifa);
        adicionarviagemtarifa = findViewById(R.id.adicionarviagemtarifa);
    }

    private void iniciaDadosVeiculo() {
        //DADOS VEICULO
        kmestimado = findViewById(R.id.kmestimado);
        mediakmph = findViewById(R.id.mediakmph);
        customediolitro = findViewById(R.id.customediolitro);
        totalveiculos = findViewById(R.id.totalveiculos);
        valortotalveiculo = findViewById(R.id.valortotalveiculo);
        adicionarviagemveiculo = findViewById(R.id.adicionarviagemveiculo);
    }

    private void checkForEditViagem() {
        Intent previousIntent = getIntent();
        int passedViagemID = previousIntent.getIntExtra(Viagem.VIAGEM_EDIT_EXTRA, -1);
        selectedViagem = Viagem.getViagemForId(passedViagemID);

        if(selectedViagem != null){
            titleViagem.setText(selectedViagem.getTitle());
            qtdPessoasViagem.setText(String.valueOf(selectedViagem.getQtdPessoasViagem()));
            duracaoViagem.setText(String.valueOf(selectedViagem.getDuracaoViagem()));
            kmestimado.setText(String.valueOf(selectedViagem.getKmEstimado()));
            mediakmph.setText(String.valueOf(selectedViagem.getMediaKmPh()));
            customediolitro.setText(String.valueOf(selectedViagem.getCustoMedioLitro()));
            totalveiculos.setText(String.valueOf(selectedViagem.getTotalVeiculos()));
            adicionarviagemveiculo.setChecked(selectedViagem.getAdicionarVeiculo());

            tarifacustoporpessoa.setText(String.valueOf(selectedViagem.getCustoEstimadoPessoaTarifa()));
            tarifaaluguel.setText(String.valueOf(selectedViagem.getAluguelVeiculoTarifa()));
            adicionarviagemtarifa.setChecked(selectedViagem.getAdicionarTarifa());

            refeicoescusto.setText(String.valueOf(selectedViagem.getCustoEstimadoRefeicao()));
            refeicoesqtd.setText(String.valueOf(selectedViagem.getRefeicoesPorDia()));
            adicionarviagemrefeicoes.setChecked(selectedViagem.getAdicionarRefeicoes());

            hospedagemcustonoite.setText(String.valueOf(selectedViagem.getCustoMedioNoiteHospedagem()));
            hospedagemnoitesqtd.setText(String.valueOf(selectedViagem.getTotalNoitesHospedagem()));
            hospedagemquartosqtd.setText(String.valueOf(selectedViagem.getTotalQuartosHospedagem()));
            adicionarviagemhospedagem.setChecked(selectedViagem.getAdicionarHospedagem());

        }
        else {
            deleteButton.setVisibility(View.INVISIBLE);
        }

    }

    private void initWidget() {
        titleViagem = findViewById(R.id.titleViagem);
        deleteButton = findViewById(R.id.deleteButton);
        qtdPessoasViagem = findViewById(R.id.qtdPessoasViagem);
        duracaoViagem = findViewById(R.id.duracaoViagem);
    }

    public void expand(View view) {

        CardView cardView = (CardView) view;
        LinearLayout layoutCard = (LinearLayout) cardView.getChildAt(0);
        LinearLayout layoutDetalhes = (LinearLayout) layoutCard.getChildAt(1);

        int v = (layoutDetalhes.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layoutCard, new AutoTransition());
        layoutDetalhes.setVisibility(v);


    }

    public void salvarViagem(View view) {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        String title = String.valueOf(titleViagem.getText());
        int qtdPessoasViagemVal = Integer.parseInt(qtdPessoasViagem.getText().toString());
        int duracaoViagemVal = Integer.parseInt(duracaoViagem.getText().toString());
        int kmEstimadoVal = Integer.parseInt(kmestimado.getText().toString());;
        int mediaKmPhVal = Integer.parseInt(mediakmph.getText().toString());
        int custoMedioLitroVal = Integer.parseInt(customediolitro.getText().toString());
        int totalVeiculosVal = Integer.parseInt(totalveiculos.getText().toString());
        boolean adicionarviagemveiculoVal = adicionarviagemveiculo.isChecked();
        int tarifaCustoPorPessoa = Integer.parseInt(tarifacustoporpessoa.getText().toString());
        int tarifaAluguel = Integer.parseInt(tarifaaluguel.getText().toString());
        boolean adicionarViagemTarifa = adicionarviagemtarifa.isChecked();
        int refeicoesCusto = Integer.parseInt(refeicoescusto.getText().toString());
        int refeicoesQtd = Integer.parseInt(refeicoesqtd.getText().toString());
        boolean adicionarViagemRefeicoes = adicionarviagemrefeicoes.isChecked();
        int hospedagemCustoNoite = Integer.parseInt(hospedagemcustonoite.getText().toString());
        int hospedagemNoitesQtd = Integer.parseInt(hospedagemcustonoite.getText().toString());
        int hospedagemQuartosQtd = Integer.parseInt(hospedagemcustonoite.getText().toString());
        boolean adicionarViagemHospedagem = adicionarviagemhospedagem.isChecked();

        if(selectedViagem == null){
            int id = Viagem.viagemArrayList.size();
            Viagem novaViagem = new Viagem(id,
                    title,
                    qtdPessoasViagemVal,
                    duracaoViagemVal,
                    kmEstimadoVal,
                    mediaKmPhVal,
                    custoMedioLitroVal,
                    totalVeiculosVal,
                    adicionarviagemveiculoVal,
                    tarifaCustoPorPessoa,
                    tarifaAluguel,
                    adicionarViagemTarifa,
                    refeicoesCusto,
                    refeicoesQtd,
                    adicionarViagemRefeicoes,
                    hospedagemCustoNoite,
                    hospedagemNoitesQtd,
                    hospedagemQuartosQtd,
                    adicionarViagemHospedagem);
            Viagem.viagemArrayList.add(novaViagem);
            sqLiteManager.addViagemToDatabase(novaViagem);
        }
        else {
            selectedViagem.setTitle(title);
            selectedViagem.setQtdPessoasViagem(qtdPessoasViagemVal);
            selectedViagem.setDuracaoViagem(duracaoViagemVal);
            selectedViagem.setKmEstimado(kmEstimadoVal);
            selectedViagem.setMediaKmPh(mediaKmPhVal);
            selectedViagem.setCustoMedioLitro(custoMedioLitroVal);
            selectedViagem.setTotalVeiculos(totalVeiculosVal);
            selectedViagem.setAdicionarVeiculo(adicionarviagemveiculoVal);
            selectedViagem.setCustoEstimadoPessoaTarifa(tarifaCustoPorPessoa);
            selectedViagem.setAluguelVeiculoTarifa(tarifaAluguel);
            selectedViagem.setAdicionarTarifa(adicionarViagemTarifa);
            selectedViagem.setCustoEstimadoRefeicao(refeicoesCusto);
            selectedViagem.setRefeicoesPorDia(refeicoesQtd);
            selectedViagem.setAdicionarRefeicoes(adicionarViagemRefeicoes);
            selectedViagem.setCustoMedioNoiteHospedagem(hospedagemCustoNoite);
            selectedViagem.setTotalNoitesHospedagem(hospedagemNoitesQtd);
            selectedViagem.setTotalQuartosHospedagem(hospedagemQuartosQtd);
            selectedViagem.setAdicionarHospedagem(adicionarViagemHospedagem);
            sqLiteManager.updateViagemInDB(selectedViagem);
        }


        finish();
    }

    public void deleteViagem(View view) {
        selectedViagem.setDeleted(new Date());
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        sqLiteManager.updateViagemInDB(selectedViagem);
        finish();
    }
}
