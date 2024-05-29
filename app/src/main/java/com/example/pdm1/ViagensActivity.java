package com.example.pdm1;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

public class ViagensActivity extends AppCompatActivity {

    private ListView viagemListView;

    @Override
    protected void onResume() {
        super.onResume();
        setViagemAdapter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viagens);

        initWidgets();
        loadFromDBToMemory();
        setViagemAdapter();
        setOnClickListner();

        Button novaviagem = findViewById(R.id.novaviagem);
        novaviagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViagensActivity.this, NovaviagemActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initWidgets() {
        viagemListView = findViewById(R.id.listaViagem);
    }

    private void setOnClickListner() {
        viagemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Viagem selectedViagem = (Viagem) viagemListView.getItemAtPosition(position);
//                Intent editViagemIntent = new Intent(getApplicationContext(), NovaviagemActivity.class);
//                editViagemIntent.putExtra(Viagem.VIAGEM_EDIT_EXTRA, selectedViagem.getId());
//                startActivity(editViagemIntent);

                Intent mostraViagemIntent = new Intent(getApplicationContext(), ResumoActivity.class);
                mostraViagemIntent.putExtra(Viagem.VIAGEM_EDIT_EXTRA, selectedViagem.getId());
                startActivity(mostraViagemIntent);
            }
        });
    }

    private void loadFromDBToMemory() {
        SQLiteManager sqLiteManager = SQLiteManager.instanceOfDatabase(this);
        try {
            sqLiteManager.populateViagemListArray();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void setViagemAdapter() {
        ViagemAdapter viagemAdapter = new ViagemAdapter(getApplicationContext(), Viagem.nonDeletedViagens());
        viagemListView.setAdapter(viagemAdapter);
    }

}
