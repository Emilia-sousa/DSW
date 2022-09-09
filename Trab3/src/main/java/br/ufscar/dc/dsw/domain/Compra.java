package br.ufscar.dc.dsw.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@SuppressWarnings("serial")
@Entity
@Table(name = "Compra")
public class Compra extends AbstractEntity<Long> {

	@NotNull
	@Column(nullable = false, length = 19)
	private String data;
    
	@NotNull
	@Column(columnDefinition = "DECIMAL(8,2) DEFAULT 0.0")
	private BigDecimal valor;
    
	@NotNull(message = "{NotNull.compra.pacote}")
	@ManyToOne
	@JoinColumn(name = "pacote_id")
	private PacoteTuristico pacote;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public PacoteTuristico getPacote() {
		return pacote;
	}

	public void setPacote(PacoteTuristico pacote) {
		this.pacote = pacote;
		setValor(pacote.getPreco());
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
