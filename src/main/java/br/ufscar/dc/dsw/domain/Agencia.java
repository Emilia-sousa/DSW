package br.ufscar.dc.dsw.domain;

public class Agencia extends Usuario {
    private String cnpj;
    private String descricao;

    public Agencia(Long id){
        super(id);
    }

    public Agencia(Long id, String nome, String email, String senha, String tipo, String cnpj, String descricao) {
        super(id, nome, email, senha, tipo);

        this.setCnpj(cnpj);
        this.setDescricao(descricao);
    }

    public Agencia(String nome, String email, String senha, String tipo, String cnpj, String descricao) {
        super(nome, email, senha, tipo);

        this.setCnpj(cnpj);
        this.setDescricao(descricao);
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
