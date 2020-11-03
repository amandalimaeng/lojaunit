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

import com.lojaunit.model.Categoria;
import com.lojaunit.model.Fornecedor;
import com.lojaunit.model.Marca;
import com.lojaunit.model.Produto;
import com.lojaunit.repository.CategoriaRepository;
import com.lojaunit.repository.FornecedorRepository;
import com.lojaunit.repository.MarcaRepository;
import com.lojaunit.repository.ProdutoRepository;

@Controller
@RequestMapping(path="/produto")
public class ProdutoController {
	@Autowired
	
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private FornecedorRepository fornecedorRepository;
	@Autowired
	private MarcaRepository marcaRepository;
	
	@PostMapping(path="/adicionar")
	public @ResponseBody String addNewProduto(
			@RequestParam String nome,
			@RequestParam String descricao,
			@RequestParam Double precoUnitario,
			@RequestParam String unidade,
			@RequestParam Integer idCategoria,
			@RequestParam Integer idFornecedor,
			@RequestParam Integer idMarca) {
		
		Produto produto = new Produto();
		produto.setNome(nome);
		produto.setDescricao(descricao);
		produto.setPrecoUnitario(precoUnitario);
		produto.setUnidade(unidade);
		Categoria categoria = categoriaRepository.findById(idCategoria).get();
		produto.setCategoria(categoria);
		Fornecedor fornecedor = fornecedorRepository.findById(idFornecedor).get();
		produto.setFornecedor(fornecedor);
		Marca marca = marcaRepository.findById(idMarca).get();
		produto.setMarca(marca);
		produtoRepository.save(produto);
		return "Produto cadastrado: "+ produto.getNome();
	}
	
	@GetMapping(path="/listar")
	public @ResponseBody Iterable<Produto> getAllProdutos(){
		return produtoRepository.findAll();
	}
	
	@GetMapping(path="/procurar/{id}")
	public @ResponseBody Optional<Produto> getProdutoById(@PathVariable("id")Integer id){
		return produtoRepository.findById(id);
	}
	
	@DeleteMapping(path="/excluir/{id}")
	public @ResponseBody String deleteBydId(@PathVariable("id")Integer id) {
		if(produtoRepository.existsById(id)) {
			produtoRepository.deleteById(id);
			return "Produto excluído";
		}
		return "Produto não encontrado para exclusão, verifique o ID";
	}
	
	
	@PutMapping(path="/atualizar/{id}")
	public @ResponseBody String updateProdutoById(
			@RequestParam String nome,
			@RequestParam String descricao,
			@RequestParam Double precoUnitario,
			@RequestParam String unidade,
			@RequestParam Integer idCategoria,
			@RequestParam Integer idFornecedor,
			@RequestParam Integer idMarca,
			@PathVariable("id")Integer id) {
		if(produtoRepository.existsById(id)) {
			Produto produto = new Produto();
			produto.setId(id);
			produto.setNome(nome);
			produto.setDescricao(descricao);
			produto.setPrecoUnitario(precoUnitario);
			produto.setUnidade(unidade);
			Categoria categoria = categoriaRepository.findById(idCategoria).get();
			produto.setCategoria(categoria);
			Fornecedor fornecedor = fornecedorRepository.findById(idFornecedor).get();
			produto.setFornecedor(fornecedor);
			Marca marca = marcaRepository.findById(idMarca).get();
			produto.setMarca(marca);
			produtoRepository.save(produto);
			return "Produto atualizado: "+produto.getNome();
		}
		return "Produto não encontrado para atualização, verifique o ID ";
	}
}
