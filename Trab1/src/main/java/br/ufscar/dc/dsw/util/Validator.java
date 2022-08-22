package br.ufscar.dc.dsw.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.Part;

import org.apache.commons.io.FilenameUtils;

public class Validator<T> {
    private final String nomeCampo;
    private final T valor;
    private final List<String> erros;

    public Validator(String nomeCampo, T valor) {
        this.nomeCampo = nomeCampo;
        this.valor = valor;
        erros = new ArrayList<String>();
    }

    public Validator<T> required() {
        if (valor == null || valor.toString().isEmpty()) {
            erros.add(nomeCampo + " não informado!");
        }

        return this;
    }

    public Validator<T> requiredFile() {
        Part valorArquivo = (Part) valor;

        if (valorArquivo.getSize() == 0) {
            erros.add(nomeCampo + " não informado!");
        }

        return this;
    }

    public Validator<T> requiredList() {
        List<?> lista = (List<?>) valor;

        if (lista.size() == 0) {
            erros.add(nomeCampo + " não informado!");
        }

        return this;
    }

    public Validator<T> email() {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        Pattern p = java.util.regex.Pattern.compile(ePattern);
        Matcher m = p.matcher(valor.toString());
        
        if (!m.matches()) {
            erros.add("Email inválido!");
        }

        return this;
    }

    public Validator<T> compare(String value) {
        if (!value.equals(valor)) {
            erros.add(nomeCampo + " diferente!");
        }

        return this;
    }

    public Validator<T> pdf() {
        Part valorArquivo = (Part) valor;
        String nomeArquivo = valorArquivo.getSubmittedFileName();
        String extensao = getExtension(nomeArquivo);
        if (!extensao.equals("pdf")) {
            erros.add(nomeCampo + " do " + nomeArquivo + " deve ser um PDF! e nãu um " + extensao);
        }

        return this;
    }

    public Validator<T> image() {
        Part valorArquivo = (Part) valor;
        String nomeArquivo = valorArquivo.getSubmittedFileName();
        String extensao = getExtension(nomeArquivo);
        if (!extensao.equals("jpg") && !extensao.equals("png")) {
            erros.add(nomeCampo + " deve ser um JPG ou PNG! e não um " + extensao);
        }

        return this;
    }

    public Boolean validate() {
        if (!erros.isEmpty()) {
            return false; 
        }

        return true;
    }

    public String getErro() {
        if (!erros.isEmpty()) {
            return erros.get(0); 
        }

        return "";
    }

    public Erro addErro(Erro erro) {
        if (!validate()) {
            erro.add(getErro());
        }

        return erro;
    }

    private String getExtension(String nomeArquivo) {
        String extension = "";

        int i = nomeArquivo.lastIndexOf('.');
        int p = Math.max(nomeArquivo.lastIndexOf('/'), nomeArquivo.lastIndexOf('\\'));

        if (i > p) {
            extension = nomeArquivo.substring(i+1);
        }

        return extension;
    }
}
