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
import com.lojaunit.model.FormaPagamento;
import com.lojaunit.repository.FormaPagamentoRepository;

@Controller
@RequestMapping(path="/formapagamento")
public class FormaPagamentoController {
	@Autowired
	
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/adicionar" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setFormaPagamento(@RequestBody FormaPagamento formaPagamento )
	{
		formaPagamentoRepository.save(formaPagamento);
	}
	
	
	@PostMapping(path="/adicionar")
	public @ResponseBody String addNewFormaPagamento (
			@RequestBody String forma,
			@RequestBody String descricao,
			@RequestBody Boolean ativo) {
		
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
	
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/atualiza/{id}" , method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateFormaPagamento(@RequestBody FormaPagamento formaPagamento )
	{
		formaPagamentoRepository.save(formaPagamento);
	}
	
	
	
	
	@PutMapping(path="/atualizar/{id}")
	public @ResponseBody String updateFormaPagamentoById(
			@RequestBody String forma,
			@RequestBody String descricao,
			@RequestBody Boolean ativo,
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
