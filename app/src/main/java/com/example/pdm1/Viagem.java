package com.example.pdm1;

import java.util.ArrayList;
import java.util.Date;

public class Viagem {
    public static ArrayList<Viagem> viagemArrayList = new ArrayList<>();
    public static String VIAGEM_EDIT_EXTRA = "viagemEdit";
    private int id;
    private String title;
    private Date deleted;
    private int qtdPessoasViagem;
    private int duracaoViagem;

    // Veiculo
    private int kmEstimado;
    private int mediaKmPh;
    private int custoMedioLitro;
    private int totalVeiculos;
    private boolean adicionarVeiculo;

    // Tarifa
    private int custoEstimadoPessoaTarifa;
    private int aluguelVeiculoTarifa;
    private boolean adicionarTarifa;

    // Refeicoes
    private int custoEstimadoRefeicao;
    private int refeicoesPorDia;
    private boolean adicionarRefeicoes;

    // Hospedagens
    private int custoMedioNoiteHospedagem;
    private int totalNoitesHospedagem;
    private int totalQuartosHospedagem;
    private boolean adicionarHospedagem;

    public Viagem(int id, String title, int qtdPessoasViagem, int duracaoViagem, int kmEstimado, int mediaKmPh, int custoMedioLitro, int totalVeiculos,
                  boolean adicionarVeiculo, int custoEstimadoPessoaTarifa, int aluguelVeiculoTarifa,
                  boolean adicionarTarifa, int custoEstimadoRefeicao, int refeicoesPorDia,
                  boolean adicionarRefeicoes, int custoMedioNoiteHospedagem, int totalNoitesHospedagem,
                  int totalQuartosHospedagem, boolean adicionarHospedagem, Date deleted) {
        this.id = id;
        this.title = title;
        this.qtdPessoasViagem = qtdPessoasViagem;
        this.duracaoViagem = duracaoViagem;
        this.kmEstimado = kmEstimado;
        this.mediaKmPh = mediaKmPh;
        this.custoMedioLitro = custoMedioLitro;
        this.totalVeiculos = totalVeiculos;
        this.adicionarVeiculo = adicionarVeiculo;
        this.custoEstimadoPessoaTarifa = custoEstimadoPessoaTarifa;
        this.aluguelVeiculoTarifa = aluguelVeiculoTarifa;
        this.adicionarTarifa = adicionarTarifa;
        this.custoEstimadoRefeicao = custoEstimadoRefeicao;
        this.refeicoesPorDia = refeicoesPorDia;
        this.adicionarRefeicoes = adicionarRefeicoes;
        this.custoMedioNoiteHospedagem = custoMedioNoiteHospedagem;
        this.totalNoitesHospedagem = totalNoitesHospedagem;
        this.totalQuartosHospedagem = totalQuartosHospedagem;
        this.adicionarHospedagem = adicionarHospedagem;
        this.deleted = deleted;
    }

    public Viagem(int id, String title, int qtdPessoasViagem, int duracaoViagem, int kmEstimado, int mediaKmPh, int custoMedioLitro, int totalVeiculos,
                  boolean adicionarVeiculo, int custoEstimadoPessoaTarifa, int aluguelVeiculoTarifa,
                  boolean adicionarTarifa, int custoEstimadoRefeicao, int refeicoesPorDia,
                  boolean adicionarRefeicoes, int custoMedioNoiteHospedagem, int totalNoitesHospedagem,
                  int totalQuartosHospedagem, boolean adicionarHospedagem) {
        this(id, title, qtdPessoasViagem, duracaoViagem, kmEstimado, mediaKmPh, custoMedioLitro, totalVeiculos, adicionarVeiculo,
                custoEstimadoPessoaTarifa, aluguelVeiculoTarifa, adicionarTarifa, custoEstimadoRefeicao,
                refeicoesPorDia, adicionarRefeicoes, custoMedioNoiteHospedagem, totalNoitesHospedagem,
                totalQuartosHospedagem, adicionarHospedagem, null);
    }

    public int getQtdPessoasViagem() {
        return qtdPessoasViagem;
    }

    public void setQtdPessoasViagem(int qtdPessoasViagem) {
        this.qtdPessoasViagem = qtdPessoasViagem;
    }
// Getter and Setter methods


    public int getDuracaoViagem() {
        return duracaoViagem;
    }

    public void setDuracaoViagem(int duracaoViagem) {
        this.duracaoViagem = duracaoViagem;
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

    public int getKmEstimado() {
        return kmEstimado;
    }

    public void setKmEstimado(int kmEstimado) {
        this.kmEstimado = kmEstimado;
    }

    public int getMediaKmPh() {
        return mediaKmPh;
    }

    public void setMediaKmPh(int mediaKmPh) {
        this.mediaKmPh = mediaKmPh;
    }

    public int getCustoMedioLitro() {
        return custoMedioLitro;
    }

    public void setCustoMedioLitro(int custoMedioLitro) {
        this.custoMedioLitro = custoMedioLitro;
    }

    public int getTotalVeiculos() {
        return totalVeiculos;
    }

    public void setTotalVeiculos(int totalVeiculos) {
        this.totalVeiculos = totalVeiculos;
    }

    public boolean isAdicionarVeiculo() {
        return adicionarVeiculo;
    }

    public void setAdicionarVeiculo(boolean adicionarVeiculo) {
        this.adicionarVeiculo = adicionarVeiculo;
    }

    public int getCustoEstimadoPessoaTarifa() {
        return custoEstimadoPessoaTarifa;
    }

    public void setCustoEstimadoPessoaTarifa(int custoEstimadoPessoaTarifa) {
        this.custoEstimadoPessoaTarifa = custoEstimadoPessoaTarifa;
    }

    public int getAluguelVeiculoTarifa() {
        return aluguelVeiculoTarifa;
    }

    public void setAluguelVeiculoTarifa(int aluguelVeiculoTarifa) {
        this.aluguelVeiculoTarifa = aluguelVeiculoTarifa;
    }

    public boolean isAdicionarTarifa() {
        return adicionarTarifa;
    }

    public void setAdicionarTarifa(boolean adicionarTarifa) {
        this.adicionarTarifa = adicionarTarifa;
    }

    public int getCustoEstimadoRefeicao() {
        return custoEstimadoRefeicao;
    }

    public void setCustoEstimadoRefeicao(int custoEstimadoRefeicao) {
        this.custoEstimadoRefeicao = custoEstimadoRefeicao;
    }

    public int getRefeicoesPorDia() {
        return refeicoesPorDia;
    }

    public void setRefeicoesPorDia(int refeicoesPorDia) {
        this.refeicoesPorDia = refeicoesPorDia;
    }

    public boolean isAdicionarRefeicoes() {
        return adicionarRefeicoes;
    }

    public void setAdicionarRefeicoes(boolean adicionarRefeicoes) {
        this.adicionarRefeicoes = adicionarRefeicoes;
    }

    public int getCustoMedioNoiteHospedagem() {
        return custoMedioNoiteHospedagem;
    }

    public void setCustoMedioNoiteHospedagem(int custoMedioNoiteHospedagem) {
        this.custoMedioNoiteHospedagem = custoMedioNoiteHospedagem;
    }

    public int getTotalNoitesHospedagem() {
        return totalNoitesHospedagem;
    }

    public void setTotalNoitesHospedagem(int totalNoitesHospedagem) {
        this.totalNoitesHospedagem = totalNoitesHospedagem;
    }

    public int getTotalQuartosHospedagem() {
        return totalQuartosHospedagem;
    }

    public void setTotalQuartosHospedagem(int totalQuartosHospedagem) {
        this.totalQuartosHospedagem = totalQuartosHospedagem;
    }

    public boolean isAdicionarHospedagem() {
        return adicionarHospedagem;
    }

    public void setAdicionarHospedagem(boolean adicionarHospedagem) {
        this.adicionarHospedagem = adicionarHospedagem;
    }

    public static Viagem getViagemForId(int passedViagemID) {
        for (Viagem viagem : viagemArrayList) {
            if (viagem.getId() == passedViagemID) {
                return viagem;
            }
        }
        return null;
    }

    public static ArrayList<Viagem> nonDeletedViagens() {
        ArrayList<Viagem> nonDeleted = new ArrayList<>();
        for (Viagem viagem : viagemArrayList) {
            if (viagem.getDeleted() == null) {
                nonDeleted.add(viagem);
            }
        }
        return nonDeleted;
    }

    public boolean getAdicionarVeiculo() {
        return adicionarVeiculo;
    }

    public boolean getAdicionarTarifa() {
        return adicionarTarifa;
    }

    public boolean getAdicionarRefeicoes() {
        return adicionarRefeicoes;
    }

    public boolean getAdicionarHospedagem() {
        return adicionarHospedagem;
    }
}
