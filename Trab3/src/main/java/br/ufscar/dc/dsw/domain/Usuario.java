package br.ufscar.dc.dsw.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@SuppressWarnings("serial")
@Entity
@Table(name = "Usuario")
public class Usuario extends AbstractEntity<Long> {
  
	@NotBlank
    @Column(nullable = false, length = 20, unique = true)
    private String username;
    
	@NotBlank
    @Column(nullable = false, length = 64)
    private String password;
       
    @NotBlank
    @Column(nullable = false, length = 60)
    private String name;
    
    @NotBlank
    @Column(nullable = false, length = 18)
    private String CPF;

    @Column(nullable = true, length = 60)
    private String email;

    @Column(nullable = true, length = 20)
    private String telefone;

    @Column(nullable = true, length = 20)
    private String sexo;

    @Column(nullable = true, length = 20)
    private String dataNascimento;
    
    @NotBlank
    @Column(nullable = false, length = 18)
    private String role;
    
    @Column(nullable = false)
    private boolean enabled;
		
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

     public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    
    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
}
