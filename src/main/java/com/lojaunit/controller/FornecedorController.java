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

import com.lojaunit.model.Fornecedor;
import com.lojaunit.repository.FornecedorRepository;

@Controller
@RequestMapping(path="/fornecedor")
public class FornecedorController {
	@Autowired
	
	private FornecedorRepository fornecedorRepository;
	
	@PostMapping(path="/adicionar")
	public @ResponseBody String addNewFornecedor(
			@RequestParam String nome,
			@RequestParam String endereco,
			@RequestParam String telefone,
			@RequestParam String cnpj,
			@RequestParam String email) {
		
		Fornecedor fornecedor = new Fornecedor();
		fornecedor.setNome(nome);
		fornecedor.setEndereco(endereco);
		fornecedor.setTelefone(telefone);
		fornecedor.setCnpj(cnpj);
		fornecedor.setEmail(email);
		fornecedorRepository.save(fornecedor);
		return "Fornecedor adiconado: "+ fornecedor.getNome();
	}
	
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<Fornecedor> getAllFornecedores(){
		return fornecedorRepository.findAll();
	}
	
	@GetMapping(path="/procurar/{id}")
	public @ResponseBody Optional<Fornecedor> getFornecedorById(@PathVariable("id")Integer id){
		return fornecedorRepository.findById(id);
	}

	@DeleteMapping(path="/excluir/{id}")
	public @ResponseBody String deleteFornecedorById(@PathVariable("id")Integer id) {
		if(fornecedorRepository.existsById(id)) {
			fornecedorRepository.deleteById(id);
			return "Fornecedor apagado com sucesso";
		}
		return "Fornecedor não encontrado para deleção, verifique o ID";
	}
	
	@PutMapping(path="/atualizar/{id}")
	public @ResponseBody String updateFornecedorById(
			@RequestParam String nome,
			@RequestParam String endereco,
			@RequestParam String telefone,
			@RequestParam String cnpj,
			@RequestParam String email,
			@PathVariable("id")Integer id) {
		if(fornecedorRepository.existsById(id)) {
			Fornecedor fornecedor = new Fornecedor();
			fornecedor.setId(id);
			fornecedor.setNome(nome);
			fornecedor.setEndereco(endereco);
			fornecedor.setTelefone(telefone);
			fornecedor.setCnpj(cnpj);
			fornecedor.setEmail(email);
			fornecedorRepository.save(fornecedor);
			return "Fornecedor atualizado: "+fornecedor.getNome();
		}
		return "Fornecedor não encontrado para a atualização, verifique o ID";
	}
}
