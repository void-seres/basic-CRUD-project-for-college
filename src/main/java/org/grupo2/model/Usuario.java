package org.grupo2.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.grupo2.model.enums.Sexo;
import org.grupo2.model.enums.TipoSangue;

@Entity
@Table(name = "usuario")
public class Usuario {
	
	@Id
	private long   CPF;
	private String nome;
	private String email;
	private long   telefone;
	private String senha;
	private String dataDeNascimento;
	private boolean funcionario;
	@Enumerated(EnumType.STRING)
	private Sexo sexo;
	@Enumerated(EnumType.STRING)
	private TipoSangue tipoSanguineo;
	
	public Usuario() {
	}
	public Usuario(long cPF, String nome, String email, long telefone, String senha, String dataDeNascimento,
				   boolean funcionario, Sexo sexo, TipoSangue tipoSanguineo) {
		super();
		CPF = cPF;
		this.nome = nome;
		this.email = email;
		this.telefone = telefone;
		this.senha = senha;
		this.dataDeNascimento = dataDeNascimento;
		this.funcionario = funcionario;
		this.sexo = sexo;
		this.tipoSanguineo = tipoSanguineo;
	}



	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public long getCPF() {
		return CPF;
	}
	public void setCPF(long cPF) {
		CPF = cPF;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getTelefone() {
		return telefone;
	}
	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getDataDeNascimento() {
		return dataDeNascimento;
	}
	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}
	public Boolean getFuncionario() {
		return funcionario;
	}	
	public void setFuncionario(Boolean funcionario) {
		this.funcionario = funcionario;
	}
	public Sexo getSexo() {
		return sexo;
	}
	public void setSexo(Sexo sexo) {
		this.sexo = sexo;
	}
	public TipoSangue getTipoSanguineo() {
		return tipoSanguineo;
	}
	public void setTipoSanguineo(TipoSangue tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}
}
