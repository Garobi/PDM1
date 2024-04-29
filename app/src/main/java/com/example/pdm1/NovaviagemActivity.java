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
    EditText titleViagem;
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
        atualizaDadosModificadosVeiculos();


        initWidget();
        checkForEditViagem();
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

    private void atualizaDadosModificadosVeiculos() {
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
        selectedViagem = Viagem.GetViagemForId(passedViagemID);

        if(selectedViagem != null){
            titleViagem.setText(selectedViagem.getTitle());
        }
        else {
            deleteButton.setVisibility(View.INVISIBLE);
        }

    }

    private void initWidget() {
        titleViagem = findViewById(R.id.titleViagem);
        deleteButton = findViewById(R.id.deleteButton);
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

        if(selectedViagem == null){
            int id = Viagem.viagemArrayList.size();
            Viagem novaViagem = new Viagem(id, title);
            Viagem.viagemArrayList.add(novaViagem);
            sqLiteManager.addViagemToDatabase(novaViagem);
        }
        else {
            selectedViagem.setTitle(title);
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
