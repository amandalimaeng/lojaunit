package com.lojaunit.controller;

import java.sql.Timestamp;
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
import com.lojaunit.model.FormaPagamento;
import com.lojaunit.model.Venda;
import com.lojaunit.repository.ClienteRepository;
import com.lojaunit.repository.FormaPagamentoRepository;
import com.lojaunit.repository.VendaRepository;

@Controller
@RequestMapping(path="/venda")
public class VendaController {
	@Autowired
	
	private VendaRepository vendaRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/adicionar" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setVenda(@RequestBody Venda venda )
	{
		vendaRepository.save(venda);
	}
	
	
	
	@PostMapping(path="/adicionar")
	public @ResponseBody String addNewVenda(
			@RequestBody Timestamp datahora,
			@RequestBody Double valorTotal,
			@RequestBody Integer idCliente,
			@RequestBody Integer idFormaPagamento) {
		
		Venda venda = new Venda();
		venda.setDatahora(datahora);
		venda.setValorTotal(valorTotal);
		Cliente cliente = clienteRepository.findById(idCliente).get();
		venda.setCliente(cliente);
		FormaPagamento formaPagamento = formaPagamentoRepository.findById(idFormaPagamento).get();
		venda.setFormaPagamento(formaPagamento);
		vendaRepository.save(venda);
		return "Venda salva: "+venda.getId();
	}
	
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<Venda> getAllVendas(){
		return vendaRepository.findAll();
	}
	
	@GetMapping(path="/procurar/{id}")
	public @ResponseBody Optional<Venda> getVendaById(@PathVariable("id")Integer id){
		return vendaRepository.findById(id);
	}
	

	
	@DeleteMapping(path="/excluir/{id}")
	public @ResponseBody String deleteVendaById(@PathVariable("id")Integer id) {
		if(vendaRepository.existsById(id)) {
			vendaRepository.deleteById(id);
			return "Venda excluída";
		}
		return "Venda não encontrada para exclusão, verifique o ID";
	}
	
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/atualizar/{id}" , method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateVenda(@RequestBody Venda venda )
	{
		vendaRepository.save(venda);
	}
	
	
	@PutMapping(path="/atualizar/{id}")
	public @ResponseBody String updateVendaById(
			@RequestParam Timestamp datahora,
			@RequestParam Double valorTotal,
			@RequestParam Integer idCliente,
			@RequestParam Integer idFormaPagamento,
			@PathVariable("id")Integer id) {
		if(vendaRepository.existsById(id)) {
			Venda venda = new Venda();
			venda.setId(id);
			venda.setDatahora(datahora);
			venda.setValorTotal(valorTotal);
			Cliente cliente = clienteRepository.findById(idCliente).get();
			venda.setCliente(cliente);
			FormaPagamento formaPagamento = formaPagamentoRepository.findById(idFormaPagamento).get();
			venda.setFormaPagamento(formaPagamento);
			vendaRepository.save(venda);
			return "Venda atualizada: "+venda.getId();
		}
		return "Venda não encontrada para atualização, verifique o ID";
	}
}
