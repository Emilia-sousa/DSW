package br.ufscar.dc.dsw.domain;

import java.sql.Timestamp;
import java.util.List;

public class PacoteTuristico {
    private Long id;
    private Agencia agencia;
    private Destino destino;
    private Timestamp dataPartida;
    private Integer duracaoDias;
    private Float valor;
    private String descricao;
    private Integer qtdFotos;
    private List<Foto> fotos;

    public PacoteTuristico(Long id, Agencia agencia, Destino destino, Timestamp dataPartida, Integer duracaoDias,
            Float valor, String descricao, Integer qtdFotos, List<Foto> fotos) {
        this.setId(id);
        this.setAgencia(agencia);
        this.setDestino(destino);
        this.setDataPartida(dataPartida);
        this.setDuracaoDias(duracaoDias);
        this.setValor(valor);
        this.setDescricao(descricao);
        this.setQtdFotos(qtdFotos);
        this.setFotos(fotos);
    }

    public PacoteTuristico(Agencia agencia, Destino destino, Timestamp dataPartida, Integer duracaoDias,
            Float valor, String descricao, Integer qtdFotos, List<Foto> fotos) {
        super();
        this.setAgencia(agencia);
        this.setDestino(destino);
        this.setDataPartida(dataPartida);
        this.setDuracaoDias(duracaoDias);
        this.setValor(valor);
        this.setDescricao(descricao);
        this.setQtdFotos(qtdFotos);
        this.setFotos(fotos);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agencia getAgencia() {
        return agencia;
    }

    public void setAgencia(Agencia agencia) {
        this.agencia = agencia;
    }

    public Destino getDestino() {
        return destino;
    }

    public void setDestino(Destino destino) {
        this.destino = destino;
    }

    public Timestamp getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(Timestamp dataPartida) {
        this.dataPartida = dataPartida;
    }

    public Integer getDuracaoDias() {
        return duracaoDias;
    }

    public void setDuracaoDias(Integer duracaoDias) {
        this.duracaoDias = duracaoDias;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getQtdFotos() {
        return qtdFotos;
    }

    public void setQtdFotos(Integer qtdFotos) {
        this.qtdFotos = qtdFotos;
    }

    public List<Foto> getFotos() {
        return fotos;
    }

    public void setFotos(List<Foto> fotos) {
        this.fotos = fotos;
    }
}