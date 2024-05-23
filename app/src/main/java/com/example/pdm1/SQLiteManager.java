package com.example.pdm1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager sqLiteManager;


    private static final String DATABASE_NAME = "ViagemDB";
    private static final int DATABASE_VERSION = 10;
    private static final String TABLE_NAME = "Viagem";
    private static final String COUNTER = "Counter";


    private static final String ID_FIELD = "id";
    private static final String TITLE_FIELD = "title";
    private static final String DELETED_FIELD = "deleted";
    private static final String QTD_PESSOAS_FIELD = "qtdPessoasViagem";
    private static final String DURACAO_VIAGEM_FIELD = "duracaoViagem";

    //Veiculo
    private static final String TOTAL_KM_VEICULO_FIELD = "total_km_veiculo";
    private static final String MEDIA_KMPL_VEICULO_FIELD = "media_kmpl_veiculo";
    private static final String CUSTO_MEDIO_VEICULO_FIELD = "custo_medio_veiculo";
    private static final String TOTAL_VEICULOS_FIELD = "total_veiculos";
    private static final String ADICIONAR_VEICULO_FIELD = "adicionar_veiculo";

    //Tarifa
    private static final String CUSTO_ESTIMADO_PESSOA_TARIFA_FIELD = "custo_estimado_pessoa_tarifa";
    private static final String ALUGUEL_VEICULO_TARIFA_FIELD = "aluguel_veiculo_tarifa";
    private static final String ADICIONAR_TARIFA_FIELD = "adicionar_tarifa";

    //Refeicoes
    private static final String CUSTO_ESTIMADO_REFEICAO_FIELD = "custo_estimado_refeicao";
    private static final String REFEICOES_POR_DIA_FIELD = "refeicoes_por_dia";
    private static final String ADICIONAR_REFEICOES_FIELD = "adicionar_refeicoes";

    //Hospedagens
    private static final String CUSTO_MEDIO_NOITE_HOSPEDAGEM_FIELD = "custo_medio_noite_hospedagem";
    private static final String TOTAL_NOITES_HOSPEDAGEM_FIELD = "total_noites_hospedagem";
    private static final String TOTAL_QUARTOS_HOSPEDAGEM_FIELD = "total_quartos_hospedagem";
    private static final String ADICIONAR_HOSPEDAGEM_FIELD = "adicionar_hospedagem";

    private static final DateFormat dateformat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

    public SQLiteManager(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public static SQLiteManager instanceOfDatabase(Context context){
        if(sqLiteManager == null){
            sqLiteManager = new SQLiteManager(context);
        }
        return sqLiteManager;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (" +
                COUNTER + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                ID_FIELD + " TEXT, " +
                TITLE_FIELD + " TEXT, " +
                QTD_PESSOAS_FIELD + " INTEGER, " +
                DURACAO_VIAGEM_FIELD + " INTEGER,  " +
                TOTAL_KM_VEICULO_FIELD + " INTEGER, " +
                MEDIA_KMPL_VEICULO_FIELD + " INTEGER, " +
                CUSTO_MEDIO_VEICULO_FIELD + " INTEGER, " +
                TOTAL_VEICULOS_FIELD + " INTEGER, " +
                ADICIONAR_VEICULO_FIELD + " INTEGER, " +
                CUSTO_ESTIMADO_PESSOA_TARIFA_FIELD + " INTEGER, " +
                ALUGUEL_VEICULO_TARIFA_FIELD + " INTEGER, " +
                ADICIONAR_TARIFA_FIELD + " INTEGER, " +
                CUSTO_ESTIMADO_REFEICAO_FIELD + " INTEGER, " +
                REFEICOES_POR_DIA_FIELD + " INTEGER, " +
                ADICIONAR_REFEICOES_FIELD + " INTEGER, " +
                CUSTO_MEDIO_NOITE_HOSPEDAGEM_FIELD + " INTEGER, " +
                TOTAL_NOITES_HOSPEDAGEM_FIELD + " INTEGER, " +
                TOTAL_QUARTOS_HOSPEDAGEM_FIELD + " INTEGER, " +
                ADICIONAR_HOSPEDAGEM_FIELD + " INTEGER, " +
                DELETED_FIELD + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addViagemToDatabase(Viagem viagem){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, viagem.getId());
        contentValues.put(TITLE_FIELD, viagem.getTitle());
        contentValues.put(QTD_PESSOAS_FIELD, viagem.getQtdPessoasViagem());
        contentValues.put(DURACAO_VIAGEM_FIELD, viagem.getDuracaoViagem());
        contentValues.put(DELETED_FIELD, getStringFromDate(viagem.getDeleted()));
        contentValues.put(TOTAL_KM_VEICULO_FIELD, viagem.getKmEstimado());
        contentValues.put(MEDIA_KMPL_VEICULO_FIELD, viagem.getMediaKmPh());
        contentValues.put(CUSTO_MEDIO_VEICULO_FIELD, viagem.getCustoMedioLitro());
        contentValues.put(TOTAL_VEICULOS_FIELD, viagem.getTotalVeiculos());
        contentValues.put(ADICIONAR_VEICULO_FIELD, viagem.getAdicionarVeiculo() ? 1 : 0);
        contentValues.put(CUSTO_ESTIMADO_PESSOA_TARIFA_FIELD, viagem.getCustoEstimadoPessoaTarifa());
        contentValues.put(ALUGUEL_VEICULO_TARIFA_FIELD, viagem.getAluguelVeiculoTarifa());
        contentValues.put(ADICIONAR_TARIFA_FIELD, viagem.getAdicionarTarifa() ? 1 : 0);
        contentValues.put(CUSTO_ESTIMADO_REFEICAO_FIELD, viagem.getCustoEstimadoRefeicao());
        contentValues.put(REFEICOES_POR_DIA_FIELD, viagem.getRefeicoesPorDia());
        contentValues.put(ADICIONAR_REFEICOES_FIELD, viagem.getAdicionarRefeicoes() ? 1 : 0);
        contentValues.put(CUSTO_MEDIO_NOITE_HOSPEDAGEM_FIELD, viagem.getCustoMedioNoiteHospedagem());
        contentValues.put(TOTAL_NOITES_HOSPEDAGEM_FIELD, viagem.getTotalNoitesHospedagem());
        contentValues.put(TOTAL_QUARTOS_HOSPEDAGEM_FIELD, viagem.getTotalQuartosHospedagem());
        contentValues.put(ADICIONAR_HOSPEDAGEM_FIELD, viagem.getAdicionarHospedagem() ? 1 : 0);

        db.insert(TABLE_NAME, null, contentValues);

        db.insert(TABLE_NAME, null, contentValues);
    }

    public void populateViagemListArray() {
        SQLiteDatabase db = this.getReadableDatabase();
        try (Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            if (result.getCount() != 0) {
                while (result.moveToNext()) {
                    int id = Integer.parseInt(result.getString(result.getColumnIndexOrThrow(ID_FIELD)));
                    String title = result.getString(result.getColumnIndexOrThrow(TITLE_FIELD));
                    int qtdPessoasViagem = result.getInt(result.getColumnIndexOrThrow(QTD_PESSOAS_FIELD));
                    int duracaoViagem = result.getInt(result.getColumnIndexOrThrow(DURACAO_VIAGEM_FIELD));
                    int totalKmVeiculo = result.getInt(result.getColumnIndexOrThrow(TOTAL_KM_VEICULO_FIELD));
                    int mediaKmplVeiculo = result.getInt(result.getColumnIndexOrThrow(MEDIA_KMPL_VEICULO_FIELD));
                    int custoMedioVeiculo = result.getInt(result.getColumnIndexOrThrow(CUSTO_MEDIO_VEICULO_FIELD));
                    int totalVeiculos = result.getInt(result.getColumnIndexOrThrow(TOTAL_VEICULOS_FIELD));
                    int adicionarVeiculo = result.getInt(result.getColumnIndexOrThrow(ADICIONAR_VEICULO_FIELD));
                    int custoEstimadoPessoaTarifa = result.getInt(result.getColumnIndexOrThrow(CUSTO_ESTIMADO_PESSOA_TARIFA_FIELD));
                    int aluguelVeiculoTarifa = result.getInt(result.getColumnIndexOrThrow(ALUGUEL_VEICULO_TARIFA_FIELD));
                    int adicionarTarifa = result.getInt(result.getColumnIndexOrThrow(ADICIONAR_TARIFA_FIELD));
                    int custoEstimadoRefeicao = result.getInt(result.getColumnIndexOrThrow(CUSTO_ESTIMADO_REFEICAO_FIELD));
                    int refeicoesPorDia = result.getInt(result.getColumnIndexOrThrow(REFEICOES_POR_DIA_FIELD));
                    int adicionarRefeicoes = result.getInt(result.getColumnIndexOrThrow(ADICIONAR_REFEICOES_FIELD));
                    int custoMedioNoiteHospedagem = result.getInt(result.getColumnIndexOrThrow(CUSTO_MEDIO_NOITE_HOSPEDAGEM_FIELD));
                    int totalNoitesHospedagem = result.getInt(result.getColumnIndexOrThrow(TOTAL_NOITES_HOSPEDAGEM_FIELD));
                    int totalQuartosHospedagem = result.getInt(result.getColumnIndexOrThrow(TOTAL_QUARTOS_HOSPEDAGEM_FIELD));
                    int adicionarHospedagem = result.getInt(result.getColumnIndexOrThrow(ADICIONAR_HOSPEDAGEM_FIELD));
                    String stringDeleted = result.getString(result.getColumnIndexOrThrow(DELETED_FIELD));

                    Date deleted = getDateFromString(stringDeleted);
                    Viagem viagem = new Viagem(id, title, qtdPessoasViagem, duracaoViagem, totalKmVeiculo, mediaKmplVeiculo, custoMedioVeiculo, totalVeiculos, adicionarVeiculo == 1, custoEstimadoPessoaTarifa, aluguelVeiculoTarifa, adicionarTarifa == 1, custoEstimadoRefeicao, refeicoesPorDia, adicionarRefeicoes == 1, custoMedioNoiteHospedagem, totalNoitesHospedagem, totalQuartosHospedagem, adicionarHospedagem == 1, deleted);
                    Viagem.viagemArrayList.add(viagem);
                }
            }
        }
    }

    public void updateViagemInDB(Viagem viagem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, viagem.getId());
        contentValues.put(TITLE_FIELD, viagem.getTitle());
        contentValues.put(QTD_PESSOAS_FIELD, viagem.getQtdPessoasViagem());
        contentValues.put(DURACAO_VIAGEM_FIELD, viagem.getDuracaoViagem());
        contentValues.put(DELETED_FIELD, getStringFromDate(viagem.getDeleted()));
        contentValues.put(TOTAL_KM_VEICULO_FIELD, viagem.getKmEstimado());
        contentValues.put(MEDIA_KMPL_VEICULO_FIELD, viagem.getMediaKmPh());
        contentValues.put(CUSTO_MEDIO_VEICULO_FIELD, viagem.getCustoMedioLitro());
        contentValues.put(TOTAL_VEICULOS_FIELD, viagem.getTotalVeiculos());
        contentValues.put(ADICIONAR_VEICULO_FIELD, viagem.getAdicionarVeiculo() ? 1 : 0);
        contentValues.put(CUSTO_ESTIMADO_PESSOA_TARIFA_FIELD, viagem.getCustoEstimadoPessoaTarifa());
        contentValues.put(ALUGUEL_VEICULO_TARIFA_FIELD, viagem.getAluguelVeiculoTarifa());
        contentValues.put(ADICIONAR_TARIFA_FIELD, viagem.getAdicionarTarifa() ? 1 : 0);
        contentValues.put(CUSTO_ESTIMADO_REFEICAO_FIELD, viagem.getCustoEstimadoRefeicao());
        contentValues.put(REFEICOES_POR_DIA_FIELD, viagem.getRefeicoesPorDia());
        contentValues.put(ADICIONAR_REFEICOES_FIELD, viagem.getAdicionarRefeicoes() ? 1 : 0);
        contentValues.put(CUSTO_MEDIO_NOITE_HOSPEDAGEM_FIELD, viagem.getCustoMedioNoiteHospedagem());
        contentValues.put(TOTAL_NOITES_HOSPEDAGEM_FIELD, viagem.getTotalNoitesHospedagem());
        contentValues.put(TOTAL_QUARTOS_HOSPEDAGEM_FIELD, viagem.getTotalQuartosHospedagem());
        contentValues.put(ADICIONAR_HOSPEDAGEM_FIELD, viagem.getAdicionarHospedagem() ? 1 : 0);

        db.update(TABLE_NAME, contentValues, ID_FIELD + " = ?", new String[]{String.valueOf(viagem.getId())});
    }

    private String getStringFromDate(Date date) {
        if (date == null)
            return null;
        return dateformat.format(date);
    }
    private Date getDateFromString(String string){
        try {
            return dateformat.parse(string);
        }
        catch (ParseException | NullPointerException e){
            return null;
        }
    }
}
