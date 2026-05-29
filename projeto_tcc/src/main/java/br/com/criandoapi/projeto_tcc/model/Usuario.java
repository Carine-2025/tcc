package br.com.criandoapi.projeto_tcc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id //este campo serve para especificar que Id é a Primary Key.
	@GeneratedValue(strategy = GenerationType.IDENTITY) //indica que o campo Id será auto_incremente. 
	@Column(name = "id") //nome do campo no banco de dados
	private Integer id; 
	
	@Column(name = "nome", length = 200, nullable = true) //lenght é o máximo de caracteres e nullable indica que o campo não pode ser vazio.
	private String nome;
	
	@Column(name = "email", length = 50, nullable = true)
	private String email;
	
	@Column(name = "senha", columnDefinition = "TEXT", nullable = true)
	private String senha;
	
	@Column(name = "telefone", length = 15, nullable = true)
	private String telefone;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
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
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getTelefone() {
		return telefone;
	}
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}
