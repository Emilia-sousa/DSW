package br.ufscar.dc.dsw.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import br.ufscar.dc.dsw.validation.UniqueCNPJ;

@SuppressWarnings("serial")
@Entity
@Table(name = "Agencia")
public class Agencia extends AbstractEntity<Long> {

	@UniqueCNPJ (message = "{Unique.agencia.CNPJ}")
	@NotBlank
	@Size(min = 18, max = 18, message = "{Size.agencia.CNPJ}")
	@Column(nullable = false, unique = true, length = 60)
	private String CNPJ;
	
	@NotBlank
	@Size(min = 3, max = 60)
	@Column(nullable = false, unique = true, length = 60)
	private String nome;

    @Size(min=0, max = 140)
    @Column(nullable = true, unique = false, length = 140)
    private String descricao;

    @NotBlank
    @Size(min = 5, max = 60)
    @Column(nullable = false, unique = false, length = 60)
    private String email;

    @NotBlank
    @Column(nullable = false, length = 64)
    private String password;

	@OneToMany(mappedBy = "agencia")
	private List<PacoteTuristico> pacotes;
	
	public String getCNPJ() {
		return CNPJ;
	}

	public void setCNPJ(String CNPJ) {
		this.CNPJ = CNPJ;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<PacoteTuristico> getPacotes() {
		return pacotes;
	}

	public void setPacotes(List<PacoteTuristico> pacotes) {
		this.pacotes = pacotes;
	}

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
