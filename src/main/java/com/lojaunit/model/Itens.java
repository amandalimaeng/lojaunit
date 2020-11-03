package com.lojaunit.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(IdItemVenda.class)
public class Itens  {
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_venda")
	private Venda venda;
	@Id
	@ManyToOne
	@JoinColumn(name="id_produto")
	private Produto produto;
	private Integer quantidade;
	@Column(name="valor_unitario")
	private Double valorUnitario;
	
	public Integer getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}
	
	public Double getValorUnitario() {
		return valorUnitario;
	}
	
	public void setValorUnitario(Double valorUnitario) {
		this.valorUnitario = valorUnitario;
	}
	
	public Venda getVenda() {
		return venda;
	}
	
	public void setVenda(Venda venda) {
		this.venda = venda;
	}
	
	public Produto getProduto() {
		return produto;
	}
	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}

}
