package br.ufscar.dc.dsw.domain;

public class Compra {
    private Long id;
    private Cliente cliente;
    private PacoteTuristico pacoteTuristico;

    public Compra(Long id, Cliente cliente, PacoteTuristico pacoteTuristico) {
        this.setId(id);
        this.setCliente(cliente);
        this.setPacoteTuristico(pacoteTuristico);
    }

    public Compra(Cliente cliente, PacoteTuristico pacoteTuristico) {
        this.setCliente(cliente);
        this.setPacoteTuristico(pacoteTuristico);
    }
    
    public PacoteTuristico getPacoteTuristico() {
        return pacoteTuristico;
    }

    public void setPacoteTuristico(PacoteTuristico pacoteTuristico) {
        this.pacoteTuristico = pacoteTuristico;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
