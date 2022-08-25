package br.fatec.financas.model;
import java.io.Serializable;

public class Conta implements Serializable {
	private static final long serialVersionUID = 1L;
	private static Long nextId = 1L;
	private Long id;
	private Integer agencia;
	private String numero;
	private String titular;
	private Float saldo;
	
	public Conta() { }
	
	public Conta(Long id) { this.id = id; }
	
	// getters e setters
	public Long generateId() {
		return nextId++;
	}
	
	public static Long getNextId() {
		return nextId;
	}

	public static void setNextId(Long nextId) {
		Conta.nextId = nextId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Float getSaldo() {
		return saldo;
	}

	public void setSaldo(Float saldo) {
		this.saldo = saldo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	// hashCode e equals
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Conta other = (Conta) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}	
}
