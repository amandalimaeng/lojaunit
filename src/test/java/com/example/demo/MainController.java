package com.example.demo;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lojaunit.model.Cliente;
import com.lojaunit.repository.ClienteRepository;

@Controller
@RequestMapping(path="/demo")
public class MainController {
	@Autowired
	private ClienteRepository clienteRepository;

	@PostMapping(path="/add")
	public @ResponseBody String addNewCliente (@RequestParam String cpf,
			@RequestParam String nome, @RequestParam String email) {
		
		Cliente cli = new Cliente();
		cli.setCpf(cpf);
		cli.setNome(nome);
		cli.setEmail(email);
		clienteRepository.save(cli);
		return "Client Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<Cliente> getAllClientes(){
		return clienteRepository.findAll();
	}
	
	@GetMapping(path="/cliente/{id}")
	public @ResponseBody Optional<Cliente> getClienteById(@PathVariable("id")Integer id){
		return clienteRepository.findById(id);
	}
	
	@DeleteMapping(path="/delete/{id}")
	public @ResponseBody String deleteCliente(@PathVariable("id")Integer id) {
		
		if(clienteRepository.findById(id) != null) {
			clienteRepository.deleteById(id);
			return "Client Deleted";
		}
		
		//Return não funciona - Retorna Status 500
		return "Client Not Found!";
	}
	
	//Falta Fazer
	/*@PutMapping(path="/update/{nome}")
	public @ResponseBody String updateCliente(@RequestParam String cpf,
			@RequestParam String nome, @RequestParam String email) {
		
		Cliente cli = new Cliente();
		cli.setCpf(cpf);
		cli.setNome(nome);
		cli.setEmail(email);
		clienteRepository.save(cli);
		return "Saved";
	}*/
}
