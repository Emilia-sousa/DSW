package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@SuppressWarnings("serial")
@Entity
@Table(name = "PacoteTuristico")
public class PacoteTuristico extends AbstractEntity<Long> {

	@NotBlank(message = "{NotBlank.pacote.destino}")
	@Size(max = 60)
	@Column(nullable = false, length = 60)
	private String destino;

    
	@Column(nullable = false, length = 15)
	private String dataPartida;
	
	@NotNull(message = "{NotNull.livro.preco}")
	@Column(nullable = false, columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private BigDecimal preco;

    
    @Size(min = 1, max = 10)
    @Column(nullable = true, length = 10)
    private String fotos;

    @NotNull(message = "{NotNull.pacote.agencia}")
	@ManyToOne
	@JoinColumn(name = "agencia_id")
	private Agencia agencia;

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(String data) {
        this.dataPartida = data;
    }

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public Agencia getAgencia() {
		return agencia;
	}

	public void setAgencia(Agencia agencia) {
		this.agencia = agencia;
	}

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    public String getFotos() {
        return fotos;
    }
}
