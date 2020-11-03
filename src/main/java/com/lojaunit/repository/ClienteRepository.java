package com.lojaunit.repository;

import org.springframework.data.repository.CrudRepository;

import com.lojaunit.model.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

}
