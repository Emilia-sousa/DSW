package br.ufscar.dc.dsw.domain;

import java.util.Date; 

public class Cliente {

    private Long id;
    private String cpf;
    private String telefone;
    private char sexo;
    private Date dataNascimento;

    public Cliente(Long id) {
        this.id = id;
    }

    public Cliente(Long id, String cpf, String telefone, char sexo, Date dataNascimento) {
        this.id = id;
        this.cpf = cpf;
        this.telefone = telefone;
        this.sexo = sexo;
        this.dataNascimento = dataNascimento;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCpf() {
        return this.cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
    	return this.telefone;
    }
    
    public void setTelefone(String telefone) {
    	this.telefone = telefone;
    }
    
    public char getSexo() {
    	return this.sexo;
    }
    
    public void setSexo(char sexo) {
    	this.sexo = sexo;
    }
    
    public Date getDataNasc() {
    	return this.dataNascimento;
    }
    
    public void setDataNasc(Date dataNasc) {
    	this.dataNascimento = dataNasc;
    }
}
