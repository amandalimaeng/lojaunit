package com.lojaunit.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Faq {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Timestamp datahora;
	private String texto;
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Timestamp getDatahora() {
		return datahora;
	}
	public void setDatahora(Timestamp datahora) {
		this.datahora = datahora;
	}
	public String getTexto() {
		return texto;
	}
	public void setTexto(String texto) {
		this.texto = texto;
	}
	public Produto getProduto() {
		return produto;
	}
	public void setProduto(Produto produto) {
		this.produto = produto;
	}
	
}
