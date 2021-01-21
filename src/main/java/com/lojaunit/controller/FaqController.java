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
import com.lojaunit.model.Faq;
import com.lojaunit.model.Produto;
import com.lojaunit.repository.FaqRepository;
import com.lojaunit.repository.ProdutoRepository;

@Controller
@RequestMapping(path="/faq")
public class FaqController {
	@Autowired
	private FaqRepository faqRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/adicionar" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setFaq(@RequestBody Faq faq )
	{
		faqRepository.save(faq);
	}
	
	
	@PostMapping(path="/adicionar")
	public @ResponseBody String addNewFaq(
			@RequestBody Timestamp datahora,
			@RequestBody String texto,
			@RequestBody Integer idProduto
			) {
		Faq faq = new Faq();
		faq.setDatahora(datahora);
		faq.setTexto(texto);
		Produto produto = produtoRepository.findById(idProduto).get();
		faq.setProduto(produto);
		faqRepository.save(faq);
		return "Faq Cadastrado.";
	}
	
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<Faq> getAllFaqs(){
		return faqRepository.findAll();
	}
	
	@GetMapping(path="/procurar/{id}")
	public @ResponseBody Optional<Faq> getFaqById(@PathVariable("id")Integer id){
		return faqRepository.findById(id);
	}
	
		
	@DeleteMapping(path="/excluir/{id}")
	public @ResponseBody String deleteById(@PathVariable("id")Integer id) {
		if(faqRepository.existsById(id)) {
			faqRepository.deleteById(id);
			return "Faq deletado com sucesso";
		}
		return "Faq não encontrado para deleção, verifique o ID";
	}
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/adicionar" , method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCategria(@RequestBody Faq faq )
	{
		faqRepository.save(faq);
	}
	
	
	@PutMapping(path="/atualizar/{id}")
	public @ResponseBody String updateFaqById(
			@RequestBody Timestamp datahora,
			@RequestBody String texto,
			@RequestBody Integer idProduto,
			@PathVariable("id")Integer id) {
		if(faqRepository.existsById(id)) {
			Produto produto = produtoRepository.findById(idProduto).get();
			Faq faq = new Faq();
			faq.setId(id);
			faq.setDatahora(datahora);
			faq.setTexto(texto);
			faq.setProduto(produto);
			faqRepository.save(faq);
			return "Faq atualizado.";
		}
		return "Faq não encontrado para atualização, verifique o ID";
	}
}
