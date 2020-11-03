package com.lojaunit.repository;

import org.springframework.data.repository.CrudRepository;

import com.lojaunit.model.Produto;

public interface ProdutoRepository extends CrudRepository<Produto, Integer> {

}
