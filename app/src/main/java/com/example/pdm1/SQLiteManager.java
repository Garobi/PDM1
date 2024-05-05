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
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "Viagem";
    private static final String COUNTER = "Counter";


    private static final String ID_FIELD = "id";
    private static final String TITLE_FIELD = "title";
    private static final String DELETED_FIELD = "deleted";

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
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" TEXT, ")
                .append(TITLE_FIELD)
                .append(" TEXT, ")
                .append(DELETED_FIELD)
                .append(" TEXT)");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder sql;
        sql = new StringBuilder()
                .append("CREATE TABLE ")
                .append(TABLE_NAME)
                .append("(")
                .append(COUNTER)
                .append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD)
                .append(" TEXT, ")
                .append(TITLE_FIELD)
                .append(" TEXT, ")
                .append(DELETED_FIELD)
                .append(" TEXT)");
        db.execSQL(sql.toString());
    }

    public void addViagemToDatabase(Viagem viagem){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, viagem.getId());
        contentValues.put(TITLE_FIELD, viagem.getTitle());
        contentValues.put(DELETED_FIELD, getStringFromDate(viagem.getDeleted()));

        sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
    }

    public void populateViagemListArray(){
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        try (Cursor result = sqLiteDatabase.rawQuery("SELECT * FROM " + TABLE_NAME, null)) {
            if (result.getCount() != 0){
                while (result.moveToNext()){
                    int id = result.getInt(1);
                    String title = result.getString(2);
                    String stringDeleted = result.getString(3);
                    Date deleted = getDateFromString(stringDeleted);
                    Viagem viagem = new Viagem(id, title, deleted);
                    Viagem.viagemArrayList.add(viagem);
                }
            }
        }

    }

    public void updateViagemInDB(Viagem viagem){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID_FIELD, viagem.getId());
        contentValues.put(TITLE_FIELD, viagem.getTitle());
        contentValues.put(DELETED_FIELD, getStringFromDate(viagem.getDeleted()));

        sqLiteDatabase.update(TABLE_NAME, contentValues, ID_FIELD + " =? ", new String[]{String.valueOf(viagem.getId())});
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
