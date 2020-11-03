package com.lojaunit.controller;

import java.sql.Timestamp;
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
	
	@PostMapping(path="/adicionar")
	public @ResponseBody String addNewFaq(
			@RequestParam Timestamp datahora,
			@RequestParam String texto,
			@RequestParam Integer idProduto
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
	
	@PutMapping(path="/atualizar/{id}")
	public @ResponseBody String updateFaqById(
			@RequestParam Timestamp datahora,
			@RequestParam String texto,
			@RequestParam Integer idProduto,
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
