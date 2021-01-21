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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

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
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/adicionar" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setProduto(@RequestBody Produto produto )
	{
		produtoRepository.save(produto);
	}
	
	
	@PostMapping(path="/adicionar")
	public @ResponseBody String addNewProduto(
			@RequestBody String nome,
			@RequestBody String descricao,
			@RequestBody Double precoUnitario,
			@RequestBody String unidade,
			@RequestBody Integer idCategoria,
			@RequestBody Integer idFornecedor,
			@RequestBody Integer idMarca) {
		
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
	
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/atualizar/{id}" , method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateProduto(@RequestBody Produto produto )
	{
		produtoRepository.save(produto);
	}
	
	
	
	@PutMapping(path="/atualizar/{id}")
	public @ResponseBody String updateProdutoById(
			@RequestBody String nome,
			@RequestBody String descricao,
			@RequestBody Double precoUnitario,
			@RequestBody String unidade,
			@RequestBody Integer idCategoria,
			@RequestBody Integer idFornecedor,
			@RequestBody Integer idMarca,
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
