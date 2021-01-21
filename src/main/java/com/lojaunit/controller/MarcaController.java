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
import com.lojaunit.model.Marca;
import com.lojaunit.repository.MarcaRepository;

@Controller
@RequestMapping(path="/marca")
public class MarcaController {
	@Autowired
	
	private MarcaRepository marcaRepository;
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/adicionar" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setMarca(@RequestBody Marca marca )
	{
		marcaRepository.save(marca);
	}
	
	
	@PostMapping(path="/adicionar")
	public @ResponseBody String addNewMarca(
			@RequestBody String nome,
			@RequestBody String descricao) {
		
		Marca marca = new Marca();
		marca.setNome(nome);
		marca.setDescricao(descricao);
		marcaRepository.save(marca);
		return "Marca cadastrada: "+marca.getNome();
	}
	
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<Marca> getAllMarca(){
		return marcaRepository.findAll();
	}
	
	@GetMapping(path="/procurar/{id}")
	public @ResponseBody Optional<Marca> getMarcaById(@PathVariable("id")Integer id){
		return marcaRepository.findById(id);
	}
	
	
	@DeleteMapping(path="/excluir/{id}")
	public @ResponseBody String deleteMarcaById(@PathVariable("id")Integer id) {
		if(marcaRepository.existsById(id)) {
			marcaRepository.deleteById(id);
			return "Marca excluída";
		}
		return "Marca não encontrada para deleção, verifique o ID";
	}
	
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/atualizar/{id}" , method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateMarca(@RequestBody Marca marca )
	{
		marcaRepository.save(marca);
	}
	
	
	@PutMapping(path="/atualizar/{id}")
	public @ResponseBody String updateMarcaById(@RequestBody String nome, @RequestBody String descricao,
			@PathVariable("id")Integer id) {
		if(marcaRepository.existsById(id)) {
			Marca marca = new Marca();
			marca.setId(id);
			marca.setNome(nome);
			marca.setDescricao(descricao);
			marcaRepository.save(marca);
			return "Marca atualizada: "+marca.getNome();
		}
		return "Marca não encotrada para atualização, verifique o ID";
	}
}
