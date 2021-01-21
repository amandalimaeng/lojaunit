package com.lojaunit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.lojaunit.model.Categoria;
import com.lojaunit.model.Cliente;
import com.lojaunit.repository.CategoriaRepository;

@Controller
@RequestMapping(path="/categoria")
public class CategoriaController {
	@Autowired
	
	private CategoriaRepository categoriaRepository;
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/adicionar" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setCategria(@RequestBody Categoria categoria )
	{
		categoriaRepository.save(categoria);
	}
	
	@PostMapping(path="/adicionar")
	public @ResponseBody String addNewCategoria(@RequestBody String nome, @RequestBody Boolean ativo) {
		Categoria categoria = new Categoria();
		categoria.setNome(nome);
		categoria.setAtivo(ativo);
		categoriaRepository.save(categoria);
		return "Nova categoria adicionada: " + categoria.getNome();
	}
	
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<Categoria> getAllCategoria(){
		return categoriaRepository.findAll();
	}
	
	@GetMapping(path="/procurar/{id}")
	public @ResponseBody Optional<Categoria> getCategoriaById(@PathVariable("id")Integer id) {
		return categoriaRepository.findById(id);
	}
	
	
	@DeleteMapping(path="/excluir/{id}")
	public @ResponseBody String deleteCategoriaById(@PathVariable("id")Integer id) {
		if(categoriaRepository.existsById(id)) {
			categoriaRepository.deleteById(id);
			return "Categoria apagada com sucesso";
		}
		return "Categoria não encontrada para deleção, verifique o ID";
	}
	
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/atualizar/{id}" , method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCategria(@RequestBody Categoria categoria )
	{
		categoriaRepository.save(categoria);
	}
	
	
	@PutMapping(path="/atualizar/{id}")
	public @ResponseBody String updateCategoriaById(@RequestBody String nome, @RequestBody Boolean ativo,
			@PathVariable("id")Integer id) {
		if(categoriaRepository.existsById(id)) {
			Categoria categoria = new Categoria();
			categoria.setId(id);
			categoria.setNome(nome);
			categoria.setAtivo(ativo);
			categoriaRepository.save(categoria);
			return "Categoria atualizada: " + categoria.getNome();
		}
		return "Categoria não encontrada para atualização, verifique o ID";
	}
}
