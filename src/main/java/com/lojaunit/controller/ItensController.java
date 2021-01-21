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
import com.lojaunit.model.Itens;
import com.lojaunit.model.Produto;
import com.lojaunit.model.Venda;
import com.lojaunit.repository.ItensRepository;
import com.lojaunit.repository.ProdutoRepository;
import com.lojaunit.repository.VendaRepository;

@Controller
@RequestMapping(path="/itens")
public class ItensController {
	@Autowired
	
	private ItensRepository itensRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private VendaRepository vendaRepository;
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/adicionar" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setItens(@RequestBody Itens itens )
	{
		itensRepository.save(itens);
	}
	
	
	@PostMapping(path="/adicionar")
	public @ResponseBody String addNewItens(
			@RequestBody Integer quantidade,
			@RequestBody Double valorUnitario,
			@RequestBody Integer idVenda,
			@RequestBody Integer idProduto) {
		
		Itens itens = new Itens();
		itens.setQuantidade(quantidade);
		itens.setValorUnitario(valorUnitario);
		Venda venda = vendaRepository.findById(idVenda).get();
		itens.setVenda(venda);
		Produto produto = produtoRepository.findById(idProduto).get();
		itens.setProduto(produto);
		itensRepository.save(itens);
		return "Item cadastrado: "+ itens.getProduto();
	}
		
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<Itens> getAllItens(){
		return itensRepository.findAll();
	}
	
	@GetMapping(path="/procurar/{id}")
	public @ResponseBody Optional<Itens> getItensById(@PathVariable("id")Integer id){
		return itensRepository.findById(id);
	}
	
		
	@DeleteMapping(path="/excluir/{id}")
	public @ResponseBody String deleteItensById(@PathVariable("id")Integer id) {
		if(itensRepository.existsById(id)) {
			itensRepository.deleteById(id);
			return "Item apagado";
		}
		return "Item não encontrado para deleção, verifique o ID";
	}
	
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/atualizar/{id}" , method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateItens(@RequestBody Itens itens )
	{
		itensRepository.save(itens);
	}
	
	
	@PutMapping(path="/atualizar/{id}")
	public @ResponseBody String updateItensById(
			@RequestBody Integer quantidade,
			@RequestBody Double valorUnitario,
			@RequestBody Integer idVenda,
			@RequestBody Integer idProduto,
			@PathVariable("id")Integer id) {
		if(itensRepository.existsById(id)) {
			Venda venda = vendaRepository.findById(idVenda).get();
			Itens itens = new Itens();
			Produto produto = produtoRepository.findById(idProduto).get();
			itens.setQuantidade(quantidade);
			itens.setValorUnitario(valorUnitario);
			itens.setVenda(venda);
			itens.setProduto(produto);
			itensRepository.save(itens);
			return "Item atualizado";
		}
		return "item não encontrado para atualização, verifique o ID";
	}
}
