package br.ufscar.dc.dsw.domain;

public class Foto {
    private Long idPacote;
    private String url;

    public Foto(Long idPacote, String url) {
        this.setIdPacote(idPacote);
        this.setUrl(url);
    }

    public Foto(String url) {
        super();
        this.setUrl(url);
    }

    public Long getIdPacote() {
        return idPacote;
    }

    public void setIdPacote(Long idPacote) {
        this.idPacote = idPacote;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
