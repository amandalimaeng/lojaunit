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

import com.lojaunit.model.FormaPagamento;
import com.lojaunit.repository.FormaPagamentoRepository;

@Controller
@RequestMapping(path="/formapagamento")
public class FormaPagamentoController {
	@Autowired
	
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@PostMapping(path="/adicionar")
	public @ResponseBody String addNewFormaPagamento (
			@RequestParam String forma,
			@RequestParam String descricao,
			@RequestParam Boolean ativo) {
		
		FormaPagamento formaPagamento = new FormaPagamento();
		formaPagamento.setForma(forma);
		formaPagamento.setDescricao(descricao);
		formaPagamento.setAtivo(ativo);
		formaPagamentoRepository.save(formaPagamento);
		return "Forma de Pagamento adicionada: "+ formaPagamento.getForma();
	}
	
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<FormaPagamento> getAllFormaPagamentos(){
		return formaPagamentoRepository.findAll();
	}
	
	@GetMapping(path="/procurar/{id}")
	public @ResponseBody Optional<FormaPagamento> getFormaPagamentoById(@PathVariable("id")Integer id){
		return formaPagamentoRepository.findById(id);
	}
	
	
	
	@DeleteMapping(path="/excluir/{id}")
	public @ResponseBody String deleteFormaPagamentoById(@PathVariable("id")Integer id) {
		if(formaPagamentoRepository.existsById(id)) {
			formaPagamentoRepository.deleteById(id);
			return "Forma de Pagamento apagada com sucesso";
		}
		return "Forma de Pagamento não encontrada para deleção, verifique o ID";
	}
	
	@PutMapping(path="/atualizar/{id}")
	public @ResponseBody String updateFormaPagamentoById(
			@RequestParam String forma,
			@RequestParam String descricao,
			@RequestParam Boolean ativo,
			@PathVariable("id")Integer id) {
		if(formaPagamentoRepository.existsById(id)) {
			FormaPagamento formaPagamento = new FormaPagamento();
			formaPagamento.setId(id);
			formaPagamento.setForma(forma);
			formaPagamento.setDescricao(descricao);
			formaPagamento.setAtivo(ativo);
			formaPagamentoRepository.save(formaPagamento);
			return "Forma de Pagamento atualizada: "+formaPagamento.getForma();
		}
		return "Forma de pagamento não encotrada para atualização, verifique o ID";
	}
}
