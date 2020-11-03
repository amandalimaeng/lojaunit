package com.lojaunit.model;

import java.io.Serializable;

public class IdItemVenda implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Venda venda;
	private Produto produto;

	public IdItemVenda() {
		
	}
	@Override
	public boolean equals(Object obj) {
		
		return super.equals(obj);
	}
	@Override
	public int hashCode() {
		
		return super.hashCode();
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
