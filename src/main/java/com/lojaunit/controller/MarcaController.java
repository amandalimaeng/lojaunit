package com.lojaunit.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lojaunit.model.Marca;
import com.lojaunit.repository.MarcaRepository;

@Controller
@RequestMapping(path="/marca")
public class MarcaController {
	@Autowired
	
	private MarcaRepository marcaRepository;
	
	@PostMapping(path="/adicionar")
	public @ResponseBody String addNewMarca(
			@RequestParam String nome,
			@RequestParam String descricao) {
		
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
	
	@PutMapping(path="/atualizar/{id}")
	public @ResponseBody String updateMarcaById(@RequestParam String nome, @RequestParam String descricao,
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
