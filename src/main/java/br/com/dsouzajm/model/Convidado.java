package br.com.dsouzajm.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity(name = "convidado")
public class Convidado {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull(message = "Nome não pode estar vazio")
	@NotBlank(message = "Nome não pode estar vazio")
	private String nome;
	
	@NotNull(message = "Email não pode estar vazio")
	@NotBlank(message = "Email não pode estar vazio")
	private String email;
	
	@NotNull(message = "Telefone deve ter no mínimo 10 dígitos e no máximo 12 dígitos")
	@NotBlank(message = "Telefone não pode estar vazio")
	@Size(min=1, max=32, message="First name must be between 1 and 32 characters")
	private String telefone;

	public Convidado() {	
	}
	
	public Convidado(String nome, String email, String telefone) {
		
		this.nome = nome;		
		this.email = email;
		this.telefone = telefone;
	}	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return 	"Nome: " + this.nome + ", Email: " + this.email + ", Telefone: " + this.telefone;		
	}
	
}
