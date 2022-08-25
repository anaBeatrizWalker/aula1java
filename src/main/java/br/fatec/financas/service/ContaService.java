package br.fatec.financas.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import br.fatec.financas.model.Conta;

@Service
public class ContaService {
	private static List<Conta> contas = new ArrayList<>();
	
	//Construtor vazio
	public ContaService() {}
	
	//Cria
	public void create(Conta conta) {
		conta.setId(conta.generateId());
		contas.add(conta);
	}
	
	//Lista todas as contas
	public List<Conta> findAll(){
		return contas;
	}
	
	//Lista uma conta
	public Conta find(Conta conta) {
		for(Conta c : contas) {
			if(c.equals(conta)) {
				return c;
			}
		}
		return null;
	}
	//Se não encontrou, cria uma conta
	public Conta find(Long id) {
		return find(new Conta(id));
	}
	
	//Atualiza
	public boolean update(Conta conta) { //boolean pra se existe conta ou não
		Conta _conta = find(conta);
		if(_conta != null) {
			_conta.setAgencia(conta.getAgencia());
			_conta.setNumero(conta.getNumero());
			_conta.setTitular(conta.getTitular());
			_conta.setSaldo(conta.getSaldo());
			return true;
		}
		return false;
	}
	
	//Remove 
	public boolean delete(Long id) { //long é tipo inteiro
		Conta _conta = find(id);
		if(_conta != null) {
			contas.remove(_conta);
			return true;
		}
		return false;
	}
	
	//Depositar
	public Float depositar(Long id, Float valor) throws IllegalArgumentException {
		Conta _conta = find(id);
		if(_conta != null) {
			//atualiza saldo
			_conta.setSaldo(_conta.getSaldo() + valor);
			return _conta.getSaldo();
		}
		throw new IllegalArgumentException("Conta não encontrada.");
		//throw instancia uma exception
	}
	
	//Sacar
	public Float sacar(Long id, Float valor) throws IllegalArgumentException {
		Conta _conta = find(id);
		if(_conta == null) {
			throw new IllegalArgumentException("Conta não encontrada.");
		} else if(_conta.getSaldo() >= valor) {
			_conta.setSaldo(_conta.getSaldo() - valor);
			return _conta.getSaldo();
		}
		throw new IllegalArgumentException("Saldo insuficiente para o saque.");
	}
}
