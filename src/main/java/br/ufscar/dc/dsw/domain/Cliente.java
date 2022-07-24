package br.ufscar.dc.dsw.domain;

import java.sql.Timestamp;

public class Cliente extends Usuario {
    private String cpf;
    private String telefone;
    private String sexo;
    private Timestamp dataNascimento;

    public Cliente(Long id){
        super(id);
    }

    public Cliente(Long id, String nome, String email, String senha, String tipo, String cpf, String telefone, String sexo, Timestamp dataNascimento) {
        super(id, nome, email, senha, tipo);

        this.setCpf(cpf);
        this.setTelefone(telefone);
        this.setSexo(sexo);
        this.setDataNascimento(dataNascimento);
    }

    public Cliente(String nome, String email, String senha, String tipo, String cpf, String telefone, String sexo, Timestamp dataNascimento) {
        super(nome, email, senha, tipo);

        this.setCpf(cpf);
        this.setTelefone(telefone);
        this.setSexo(sexo);
        this.setDataNascimento(dataNascimento);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Timestamp getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Timestamp dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
