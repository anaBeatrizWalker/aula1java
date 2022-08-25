package br.fatec.financas.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.fatec.financas.model.Conta;
import br.fatec.financas.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController {
	
	@Autowired //quem estancia o atributo é o spring
	private ContaService service;
	
	//Lista todas as contas (get padrão retorna findAll())
	@GetMapping 
	public ResponseEntity<List<Conta>> getAll(){
		return ResponseEntity.ok(service.findAll());
		//resposta: lista de objetos da classe Conta
		//método ok retorna status 200; o parametro devolve a lista em formato json
	}
	
	//Lista uma conta por id
	@GetMapping(value = "/{id}")
	public ResponseEntity<?> get(@PathVariable("id") Long id){
		Conta _conta = service.find(id);
		if(_conta != null) {
			return ResponseEntity.ok(_conta);
			//devolve o objeto conta
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		//devolve not found
		//build constrói a resposta. sempre tem que ter no final
	}
	
	//Cria conta
	@PostMapping
	public ResponseEntity<Conta> post(@RequestBody Conta conta){
		service.create(conta);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(conta.getId()).toUri();
		//cria a uri passando o id como parametro
		return ResponseEntity.created(location).body(conta);
		//retorna no corpo  da requisição o objeto que foi criado (padrão é devolver a url do objeto criado)
	}
	
	//Atualiza
	@PutMapping
	public ResponseEntity<?> put(@RequestBody Conta conta){
		//retorna a conta atualizada
		if(service.update(conta)) { 
			return ResponseEntity.ok(conta);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	//Remove
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id){
		if(service.delete(id)) {
			return ResponseEntity.ok().build();
			//devolve somente um ok se o objeto foi deletado
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
}
