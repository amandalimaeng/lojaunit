package com.lojaunit.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="vendas")
public class Venda {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	private Timestamp datahora;
	@ManyToOne
	@JoinColumn(name="id_cliente")
	private Cliente cliente;
	@ManyToOne
	@JoinColumn(name="id_forma_pagamento")
	private FormaPagamento formaPagamento;
	private Double valorTotal;
	
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
	
	public Double getValorTotal() {
		return valorTotal;
	}
	
	public void setValorTotal(Double valorTotal) {
		this.valorTotal = valorTotal;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public FormaPagamento getFormaPagamento() {
		return formaPagamento;
	}
	
	public void setFormaPagamento(FormaPagamento formaPagamento) {
		this.formaPagamento = formaPagamento;
	}
}

