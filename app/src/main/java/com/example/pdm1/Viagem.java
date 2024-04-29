package com.example.pdm1;

import java.util.ArrayList;
import java.util.Date;

public class Viagem {
    public static ArrayList<Viagem> viagemArrayList = new ArrayList<>();
    public  static String VIAGEM_EDIT_EXTRA = "viagemEdit";
    private int id;
    private String title;
    private Date deleted;

    public Viagem(int id, String title, Date deleted) {
        this.id = id;
        this.title = title;
        this.deleted = deleted;
    }

    public Viagem(int id, String title) {
        this.id = id;
        this.title = title;
        this.deleted = null;
    }

    public static Viagem GetViagemForId(int passedViagemID) {
        for (Viagem viagem : viagemArrayList){
            if (viagem.getId() == passedViagemID)
                return viagem;
        }

        return null;
    }
    public static ArrayList<Viagem> nonDeletedViagens(){
        ArrayList<Viagem> nonDeleted = new ArrayList<>();

        for (Viagem viagem : viagemArrayList){
            if (viagem.getDeleted() == null){
                nonDeleted.add(viagem);
            }
        }

        return nonDeleted;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDeleted() {
        return deleted;
    }

    public void setDeleted(Date deleted) {
        this.deleted = deleted;
    }
}
