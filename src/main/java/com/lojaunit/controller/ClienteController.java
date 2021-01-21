package com.lojaunit.controller;

import java.sql.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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

import com.lojaunit.model.Cliente;
import com.lojaunit.repository.ClienteRepository;

@Controller
@CrossOrigin(origins = "http://localhost:4242")
@RequestMapping(path = "/clientes")
public class ClienteController {
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/adicionar" , method = RequestMethod.POST , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void setCliente(@RequestBody Cliente cliente )
	{
		clienteRepository.save(cliente);
	}
	

	@PostMapping(path = "/adicionar")
	public @ResponseBody String addNewCliente(@RequestBody String nome, @RequestBody String cpf,
			@RequestBody String email, @RequestBody Date dataNascimento, @RequestBody String sexo,
			@RequestBody String nomeSocial, @RequestBody String apelido, @RequestBody String telefone) {

		System.out.println(nome);
		Cliente cliente = new Cliente();
		cliente.setNome(nome);
		cliente.setCpf(cpf);
		cliente.setEmail(email);
		cliente.setDataNascimento(dataNascimento);
		cliente.setSexo(sexo);
		cliente.setNomeSocial(nomeSocial);
		cliente.setApelido(apelido);
		cliente.setTelefone(telefone);
		clienteRepository.save(cliente);
		return "Cliente adicionado: "+cliente.getNome();
	}

	@GetMapping(path = "/listar")
	public @ResponseBody Iterable<Cliente> getAllClientes() {
		return clienteRepository.findAll();
	}

	@GetMapping(path = "/procurar/{id}")
	public @ResponseBody Optional<Cliente> getClienteById(@PathVariable("id") Integer id) {
		return clienteRepository.findById(id);
	}

	@DeleteMapping(path = "/excluir/{id}")
	public @ResponseBody String deleteClienteById(@PathVariable("id") Integer id) {
		if (clienteRepository.existsById(id)) {
			clienteRepository.deleteById(id);
			return "Cliente deletado com sucesso";
		}
		return "Cliente não encontrado para deleção, verifique o ID";
	}

	
	@ResponseStatus(value = HttpStatus.OK)
	@RequestMapping(value = "/adicionar/{id}" , method = RequestMethod.PUT , consumes = MediaType.APPLICATION_JSON_VALUE)
	public void updateCliente(@RequestBody Cliente cliente )
	{
		clienteRepository.save(cliente);
	}
	
	@PutMapping(path = "/atualizar/{id}")
	
	public @ResponseBody String updateClienteById(@RequestBody String nome, @RequestBody String cpf,
			@RequestBody String email, @RequestBody Date dataNascimento, @RequestBody String sexo,
			@RequestBody String nomeSocial, @RequestBody String apelido, @RequestBody String telefone,
			@PathVariable("id") Integer id) {

		if (clienteRepository.existsById(id)) {
			Cliente cliente = new Cliente();
			cliente.setId(id);
			cliente.setNome(nome);
			cliente.setCpf(cpf);
			cliente.setEmail(email);
			cliente.setDataNascimento(dataNascimento);
			cliente.setSexo(sexo);
			cliente.setNomeSocial(nomeSocial);
			cliente.setApelido(apelido);
			cliente.setTelefone(telefone);
			clienteRepository.save(cliente);
			return "Cliente atualizado: "+cliente.getNome();
		}

		return "Cliente não localizado para atualização, verifique o ID";
	}
}
